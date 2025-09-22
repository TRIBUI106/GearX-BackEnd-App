package com.gearx.security.controller;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gearx.common.constants.ApiConstants;
import com.gearx.common.response.ApiResponse;
import com.gearx.common.response.ResponseHandler;
import com.gearx.security.configuration.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(ApiConstants.User.BASE)
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {

    private final UserService userService;

    @DeleteMapping(ApiConstants.User.DELETE)
    public ResponseEntity<ApiResponse<Object>> deleteUser(@Valid @RequestParam int id) {
        userService.deleteUserById(id);
        return ResponseHandler.success("Đã xoá tài khoản với id " + id + " thành công !", id);
    }
}
