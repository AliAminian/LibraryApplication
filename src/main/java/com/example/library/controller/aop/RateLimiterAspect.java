package com.example.library.controller.aop;

import com.google.common.util.concurrent.RateLimiter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RateLimiterAspect {

    private final RateLimiter rateLimiter = RateLimiter.create(5.0); // Adjust the rate as needed (e.g., 5 requests per second)

    @Around("@annotation(rateLimited)")
    public Object limitRequestRate(ProceedingJoinPoint joinPoint, RateLimited rateLimited) throws Throwable {
        if (rateLimiter.tryAcquire()) {
            return joinPoint.proceed();
        } else {
            throw new RuntimeException("Rate limit exceeded");
        }
    }
}