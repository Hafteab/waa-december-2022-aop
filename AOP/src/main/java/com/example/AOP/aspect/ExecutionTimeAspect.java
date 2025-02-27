package com.example.AOP.aspect;


import com.example.AOP.Entity.ActivityLog;
import com.example.AOP.Service.ActivityLogService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
@RequiredArgsConstructor
public class ExecutionTimeAspect {

    private final ActivityLogService activityLogService;

    @Pointcut("@annotation(com.example.AOP.annotation.ExecutionTime)")
    public void executionTimeAnnotation() {
    }

    @Around("executionTimeAnnotation()")
    public Object calculateAndLogExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        var result = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        long duration = end - begin;
        LocalDateTime timeStamp = LocalDateTime.now();
        String fullPath = proceedingJoinPoint.getSignature().getDeclaringTypeName() + "." + proceedingJoinPoint.getSignature().getName() + "()";

        System.out.println(timeStamp + ": [DEBUG] " + fullPath + " >> Time taken: " + duration + "ms");

        ActivityLog activityLog = new ActivityLog();
        activityLog.setDateTime(timeStamp);
        activityLog.setOperation(fullPath);
        activityLog.setDuration(duration);

        activityLogService.save(activityLog);

        return result;
    }

}