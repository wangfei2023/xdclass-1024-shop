package net.xdclass.exception;
/*
todo:自定义全局异常+处理器开发
 */
import lombok.Data;
import net.xdclass.enums.BizCodeEnum;
import org.apache.ibatis.annotations.Param;

/**
 *
 *
 * @Description
 * @Author
 * @Remark
 * @Version 1.0
 **/

@Data
public class BizException extends RuntimeException {

    private int code;
    private String msg;

    public BizException(int code, String msg){
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public BizException(BizCodeEnum bizCodeEnum){
        super(bizCodeEnum.getMessage());
        this.code = bizCodeEnum.getCode();
        this.msg = bizCodeEnum.getMessage();
    }


}
