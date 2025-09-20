package com.gearx.security.mapper;

import java.time.LocalDateTime;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RevokedTokenMapper {

    int isRevoked(@Param("token") String token);

    int deleteExpired();

    int insert(@Param("token") String token, @Param("expiresAt") LocalDateTime expiresAt);
}
