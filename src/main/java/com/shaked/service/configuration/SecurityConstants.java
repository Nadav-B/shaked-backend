package com.shaked.service.configuration;

public class SecurityConstants {

    public static final String SIGN_UP_URL = "/users/record";
    public static final String KEY = "eyJhbGciOiJIUzUxMiJ9.eyJSb2xlIjoiQWRtaW4iLCJJc3N1ZXIiOiJJc3N1ZXIiLCJVc2VybmFtZSI6IkphdmFJblVzZSIsImV4cCI6MTYxMDUzOTQ1OCwiaWF0IjoxNjEwNTM5NDU4fQ.Y8MadTEH3G300wpHSUT-3_UFxo8kLfcxFuIZ1UPwBMSx-foN6OkkNxBmJBXg48hkOWdFlG2jp3rQcLGbs-2WAg";
    public static final String HEADER_NAME = "Authorization";
    public static final Long EXPIRATION_TIME = 1000L*60*30;
    public static final String TOKEN_PREFIX = "Bearer ";

}