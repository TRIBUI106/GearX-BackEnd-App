package com.gearx.security.configuration;


import com.gearx.common.exception.AppException;
import com.gearx.common.exception.ErrorCode;
import com.gearx.security.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    public final UserMapper userMapper;

    public void deleteUserById(int userId) {

        if ( userMapper.existsById(userId) ) {
            if ( userMapper.deleteUserById(userId) < 1 ) {
                throw new AppException(ErrorCode.NOT_FOUND);
            }
        } else {
            throw new AppException(ErrorCode.NOT_FOUND);
        }

    }

}
