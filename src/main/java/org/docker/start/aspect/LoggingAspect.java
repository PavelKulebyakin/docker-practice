package org.docker.start.aspect;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Log4j2
public class LoggingAspect {

    @Before("execution(* org.docker.start.service.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        log.info("Logging before method: {}", joinPoint.getSignature().getName());
    }
}
