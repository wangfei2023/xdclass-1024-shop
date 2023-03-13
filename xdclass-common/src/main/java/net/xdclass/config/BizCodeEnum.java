package net.xdclass.config;

public enum BizCodeEnum {

    /**
     * 通用操作码 */
    OPS_REPEAT(110001,"􏰁复操作"),
    /** *验证码 */
    CODE_TO_ERROR(240001,"接收号码不合规"),

    CODE_LIMITED(240002,"验证码发送过快"),

    CODE_ERROR(240003,"验证码错误"),

    CODE_CAPTCHA(240101,"图形验证码错误"),
    /**
     * 账号 */
    ACCOUNT_REPEAT(250001,"账号已经存在"),

    ACCOUNT_UNREGISTER(250002,"账号不存在"),

    ACCOUNT_PWD_ERROR(250003,"账号或者密码错误");
    private int code;
    private String operatorCode;

    BizCodeEnum(int code, String operatorCode) {
        this.code = code;
        this.operatorCode = operatorCode;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getOperatorCode() {
        return operatorCode;
    }

    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode;
    }
}
