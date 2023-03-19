package net.xdclass.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.xdclass.enums.BizCodeEnum;
/*
todo:接口统一协议 JsonData工具类开发
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonData {
    private Integer code;
    private Object data;
    private String message;
    /**
     * 成功，传入数据 * @return
     */
    public static JsonData buildSuccess() {
        return new JsonData(0, null, null);
    }

/**
 * 失败，传入描述信息 * @param msg
 * @return
 */
    public static JsonData buildError(String msg) {
    return new JsonData(-1, null, msg);
  }
    /**
     * 自定义状态码和错误信息 * @param code
     * @param msg
     * @return
     */
    public static JsonData buildCodeAndMsg(int code, String msg) {
        return new JsonData(code, null, msg);
    }
    /**
     * 传入枚举，返回信息 * @param codeEnum * @return
     */
    public static JsonData buildResult(BizCodeEnum codeEnum){
        return JsonData.buildCodeAndMsg(codeEnum.getCode(),codeEnum.getMessage());
    }
}
