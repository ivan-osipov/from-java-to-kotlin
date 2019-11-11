package fromjavatokotlin.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.Arrays;
import java.util.stream.Collectors;

@Configuration
@EnableAspectJAutoProxy
@Aspect
@Slf4j
public class AspectConfig {

  @Before("execution(* @org.springframework.stereotype.Service fromjavatokotlin..*.*(..))")
  public void log(JoinPoint joinPoint) {
    log.debug(
        "{}#{} is called {}",
        joinPoint.getTarget().getClass(),
        joinPoint.getSignature().getName(),
        joinParams(joinPoint.getArgs())
    );
  }

  private String joinParams(Object[] args) {
    if(args.length == 0) {
      return "";
    }
    return Arrays.stream(args).map(String::valueOf).collect(Collectors.joining(",", "with params [", "]"));
  }
}
