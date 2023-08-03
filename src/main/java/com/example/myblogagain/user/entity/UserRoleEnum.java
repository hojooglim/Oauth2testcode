package com.example.myblogagain.user.entity;

public enum UserRoleEnum {
    GUEST(Authority.GUEST), // 차단할 사람
    USER(Authority.USER),  // 사용자 권한
    ADMIN(Authority.ADMIN);  // 관리자 권한

    private final String authority;

    UserRoleEnum(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return this.authority;
    }

    public static class Authority {

        public static final String USER = "ROLE_USER";
        public static final String ADMIN = "ROLE_ADMIN";
        public static final String GUEST = "ROLE_GUEST";
    }
}
