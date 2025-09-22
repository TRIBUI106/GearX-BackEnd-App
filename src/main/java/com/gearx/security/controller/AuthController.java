package com.gearx.security.controller;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gearx.common.constants.ApiConstants;
import com.gearx.common.response.ApiResponse;
import com.gearx.common.response.ResponseHandler;
import com.gearx.security.configuration.AuthService;
import com.gearx.security.configuration.PasswordResetService;
import com.gearx.security.dto.request.*;
import com.gearx.security.dto.response.TokenResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(ApiConstants.Auth.BASE)
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {

    private final AuthService authService;
    private final PasswordResetService passwordResetService;

    // Register
    @PostMapping(ApiConstants.Auth.REGISTER)
    public ResponseEntity<ApiResponse<Object>> register(@Valid @RequestBody RegisterRequest req) {
        int rows = authService.register(req);
        if (rows > 0) {
            return ResponseHandler.success("Tạo user thành công", null);
        }
        return ResponseHandler.error(
                "Tạo user thất bại",
                com.gearx.common.exception.ErrorCode.INTERNAL_ERROR,
                org.springframework.http.HttpStatus.BAD_REQUEST);
    }

    // Login
    @PostMapping(ApiConstants.Auth.LOGIN)
    public ResponseEntity<ApiResponse<TokenResponse>> login(@Valid @RequestBody LoginRequest req) {
        TokenResponse tr = authService.login(req);
        return ResponseHandler.success(tr);
    }

    // Logout (Token có thể lấy từ Authorization hoặc body)
    @PostMapping(ApiConstants.Auth.LOGOUT)
    public ResponseEntity<ApiResponse<Object>> logout(
            @RequestHeader(value = "Authorization", required = false) String authorization,
            @RequestBody(required = false) IntrospectRequest body) {
        String token = extractToken(authorization, body);
        authService.logout(token);
        return ResponseHandler.success("Logout thành công", null);
    }

    // Introspect
    @PostMapping(ApiConstants.Auth.INTROSPECT)
    public ResponseEntity<ApiResponse<TokenResponse>> introspect(
            @RequestHeader(value = "Authorization", required = false) String authorization,
            @RequestBody(required = false) IntrospectRequest req) {
        String token = extractToken(authorization, req);
        TokenResponse tr = authService.introspect(token);
        return ResponseHandler.success("Token hợp lệ", tr);
    }

    // Gửi OTP
    @PostMapping(ApiConstants.Auth.FORGOT_REQUEST)
    public ResponseEntity<ApiResponse<Object>> forgotRequest(
            @Valid @RequestBody ForgotPasswordRequest req) {
        passwordResetService.sendOtp(req.getEmail());
        return ResponseHandler.success("Nếu email tồn tại, OTP đã được gửi", null);
    }

    // Reset password
    @PostMapping(ApiConstants.Auth.FORGOT_RESET)
    public ResponseEntity<ApiResponse<Object>> forgotReset(
            @Valid @RequestBody ResetPasswordRequest req) {
        passwordResetService.resetPassword(req.getEmail(), req.getOtp(), req.getNewPassword());
        return ResponseHandler.success("Đổi mật khẩu thành công", null);
    }

    private String extractToken(String authorization, IntrospectRequest req) {
        if (authorization != null && authorization.startsWith("Bearer ")) {
            return authorization.substring(7);
        }
        if (req != null && req.getToken() != null && !req.getToken().isBlank()) {
            return req.getToken();
        }
        return null;
    }
}
