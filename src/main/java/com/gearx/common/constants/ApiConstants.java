package com.gearx.common.constants;

public class ApiConstants {
    private ApiConstants() {}

    public static final String BASE_API = "/api";
    public static final String VERSION_V1 = BASE_API + "/v1";

    public static final class Auth {
        public static final String BASE = VERSION_V1 + "/auth";
        public static final String LOGIN = "/login";
        public static final String LOGOUT = "/logout";
        public static final String INTROSPECT = "/introspect";
        public static final String FORGOT_REQUEST = "/forgot/request";
        public static final String FORGOT_RESET = "/forgot/reset";

        private Auth() {}
    }

    public static final class User {
        public static final String FETCH = "/fetch";
        public static final String REGISTER = "/register";
        public static final String BASE = VERSION_V1 + "/user";
        public static final String DELETE = "/delete";

        private User() {}
    }
}
