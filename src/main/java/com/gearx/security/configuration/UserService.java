package com.gearx.security.configuration;

import org.springframework.stereotype.Service;

import com.gearx.common.exception.AppException;
import com.gearx.common.exception.ErrorCode;
import com.gearx.security.dto.request.RegisterRequest;
import com.gearx.security.entity.User;
import com.gearx.security.mapper.RoleMapper;
import com.gearx.security.mapper.UserMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder encoder;


    public int register(RegisterRequest req) {

        if (userMapper.existsByUsername(req.getUsername()) > 0) {
            throw new AppException(ErrorCode.USERNAME_ALREADY_EXIST);
        }
        if (userMapper.existsByEmail(req.getEmail()) > 0) {
            throw new AppException(ErrorCode.EMAIL_ALREADY_EXIST);
        }

        User user =
                User.builder()
                        .username(req.getUsername())
                        .email(req.getEmail())
                        .password(encoder.encode(req.getPassword()))
                        .fullName(req.getFullName())
                        .phone(req.getPhone())
                        .address(req.getAddress())
                        .createdBy(req.getCreatedBy() != null ? req.getCreatedBy() : "system")
                        .isActive(req.getIsActive() != null ? req.getIsActive() : 1)
                        .isDeleted(req.getIsDeleted() != null ? req.getIsDeleted() : 0)
                        .build();

        int rows = userMapper.insert(user);

        if (user.getUserId() == null) {
            var created = userMapper.findByUsername(user.getUsername());
            if (created != null) user.setUserId(created.getUserId());
        }

        if (user.getUserId() == null) {
            throw new AppException(ErrorCode.INTERNAL_ERROR);
        }

//          Thay đổi, dùng trigger trong sql
//        var role = roleMapper.findByCode("customer");
//        roleMapper.insertUserRole(user.getUserId(), role.getRoleId());

        return rows;
    }

    public void deleteUserById(int userId) {

        if (userMapper.existsById(userId)) {
            if (userMapper.deleteUserById(userId) < 1) {
                throw new AppException(ErrorCode.NOT_FOUND);
            }
        } else {
            throw new AppException(ErrorCode.NOT_FOUND);
        }
    }
}
