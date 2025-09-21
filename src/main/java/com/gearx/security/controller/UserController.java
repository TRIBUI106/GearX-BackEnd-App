package com.gearx.security.controller;

import com.gearx.common.constants.ApiConstants;
import com.gearx.common.response.ApiResponse;
import com.gearx.common.response.ResponseHandler;
import com.gearx.security.configuration.UserService;
import com.gearx.security.dto.request.RegisterRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiConstants.User.BASE)
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {

    private final UserService userService;

    // Register
    @PostMapping(ApiConstants.Auth.REGISTER)
    public ResponseEntity<ApiResponse<Object>> register(@Valid @RequestBody RegisterRequest req) {
        int rows = userService.register(req);
        if (rows > 0) {
            return ResponseHandler.success("Tạo user thành công", null);
        }
        return ResponseHandler.error(
                "Tạo user thất bại",
                com.gearx.common.exception.ErrorCode.INTERNAL_ERROR,
                org.springframework.http.HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(ApiConstants.User.DELETE)
    public ResponseEntity<ApiResponse<Object>> deleteUser(
            @Valid @RequestParam int id) {
        userService.deleteUserById(id);
        return ResponseHandler.success("Đã xoá tài khoản với id " + id + " thành công !", id);
    }

}
