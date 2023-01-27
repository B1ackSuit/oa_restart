package cn.ean.oaemp.aspect;

import cn.ean.oplog.Log;
import cn.ean.oplog.aspect.LogAspect;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @FileName OaEmpLogAspect
 * @Author ean
 * @Date 2023/1/9 11:36
 **/
@Aspect
@Component
public class OaEmpLogAspect {



    protected void handleLog(JoinPoint joinPoint, Log controllerLog, Exception e, Object jsonResult) {

    }
}
