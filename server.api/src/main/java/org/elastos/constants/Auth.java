package org.elastos.constants;

public class Auth {
    /**
     * 存放Authorization的header字段
     */
    public static final String AUTHORIZATION = "authorization";

    /**
     * 存储当前登录用户id的字段名
     */
    public static final String USER_ID = "uid";

    /**
     * token有效期（小时）
     */
    public static final int TOKEN_EXPIRES_HOUR = 72;

    /**
     * 验证码有效期（分钟）
     */
    public static final int VERIFICATION_EXPIRES_MINUTES = 30;

    /**
     * 验证码位数
     */
    public static final int VERIFICATION_CODE_LENTH = 4;

    /**
     * 存储当前预登录户验证码的字段名
     */
    public static final String VERIFICATION_CODE = "code";
}
