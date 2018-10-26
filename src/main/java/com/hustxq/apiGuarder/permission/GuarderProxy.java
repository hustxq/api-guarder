package com.hustxq.apiGuarder.permission;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author: qxiong
 * @date: 2018/10/24
 * @description:
 */
@Component
@Aspect
@Order()
public class GuarderProxy {
    private Logger logger = LoggerFactory.getLogger(GuarderProxy.class);

    @Pointcut("execution(public * com.hustxq.apiGuarder.controller..*.*(..)) "
            + "&& @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void check() {
    }

    @Around("check()")
    public Object doBefore(ProceedingJoinPoint point) throws Throwable {
        Class target = point.getTarget().getClass();
        Guarder clsGuarder = (Guarder) target.getAnnotation(Guarder.class);
        Set<Permission> set = new HashSet<>();
        if (null != clsGuarder) {
            Permission[] ps = clsGuarder.name();
            for (Permission p : ps) {
                set.add(p);
            }
        }
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
//        get annotation params
        Guarder guarder = method.getAnnotation(Guarder.class);
        if (null != guarder) {
            Permission[] ps = guarder.name();
            for (Permission p : ps) {
                set.add(p);
            }
        }

        logger.info(set.toString());

        for (Object arg : point.getArgs()) {
            if (!(arg instanceof HttpServletRequest)) {
                continue;
            }
            HttpServletRequest request = (HttpServletRequest) arg;
            String role = request.getHeader("role");
            if (null == role) {
                break;
            }
            boolean flag = false;
            for (Permission p : set) {
                if (p.getV().equalsIgnoreCase(role)) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                return point.proceed();
            } else {
                throw new PermissionException("Forbidden");
            }
        }
        throw new PermissionException("\'@Guarder\' must have Parameter \'HttpServletRequest\' and so on.");
    }
}