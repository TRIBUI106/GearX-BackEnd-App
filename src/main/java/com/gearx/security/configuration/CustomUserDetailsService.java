package com.gearx.security.configuration;

import java.util.List;

import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import com.gearx.common.exception.AppException;
import com.gearx.common.exception.ErrorCode;
import com.gearx.security.entity.Role;
import com.gearx.security.entity.User;
import com.gearx.security.mapper.RoleMapper;
import com.gearx.security.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findByUsername(username);
        if (user == null || (user.getIsDeleted() != null && user.getIsDeleted() == 1)) {
            throw new AppException(ErrorCode.NOT_FOUND);
        }

        List<Role> roles = roleMapper.findByUserId(user.getUserId());
        user.setRoles(roles);

        return new CustomUserDetails(user);
    }
}
