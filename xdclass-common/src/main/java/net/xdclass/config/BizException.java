package net.xdclass.config;

public class BizException extends RuntimeException {
    private Integer code;
    private String msg;

    public BizException(Integer code, String message) {
        super(message);
        this.code = code;
        this.msg = message;
   }
    public BizException(BizCodeEnum bizCodeEnum) {
   }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}