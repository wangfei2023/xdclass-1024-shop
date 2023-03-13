package net.xdclass.aop;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect//标识该类为切片类
public class CutUtil {
 
    //第一个星号表示方法任意返回类型
    //com.project.service.. 表示匹配指定包及子包中的类
    //*.* 匹配任何类的任何方法
    //（..） 匹配方法任何参数
    //表示com.project.service 包中所有类，在执行所有方法时，都会调用该方法
//    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
//    @Pointcut(value = "execution(public * net.xdclass.service.AddressService.detail())")
    private void logAdvicePointcut(){
    }
    @Before("logAdvicePointcut()")
    public void logAdvice(){
        // 这里只是一个示例，你可以写任何处理逻辑
        System.out.println("get请求的advice触发了");
    }
}


