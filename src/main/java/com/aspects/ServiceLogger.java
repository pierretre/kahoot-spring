package com.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springdoc.webmvc.api.MultipleOpenApiWebMvcResource;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

@Aspect
@Component
public class ServiceLogger {

    private final String apiPointCut = "execution(* com.web.*Controller.*(..))";

    @Pointcut(apiPointCut)
    public void logController() {
    }

    @Before("logController()")
    public void logBeforeMethod(JoinPoint joinPoint) {
        StringBuilder log = new StringBuilder();
        log.append('[')
                .append(new Date())
                .append("] : ").append(joinPoint.getSignature().getName())
                .append('(')
                .append(Arrays.stream(joinPoint.getArgs()).map(Object::toString).collect(Collectors.joining(", ")))
                .append(')');

        System.out.println(log);
    }
}
