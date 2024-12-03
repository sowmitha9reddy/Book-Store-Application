package com.bookstore.Order.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAdvice {

    Logger log = LoggerFactory.getLogger(LoggingAdvice.class);

    //package,any class,any method with no.of arguments
    @Pointcut(value = "execution(* com.bookstore.Order.*.*.*(..) )")
    public void myPointCut() {

    }

    @Around("myPointCut()")
    public Object applicationLogger(ProceedingJoinPoint pjp) throws Throwable {
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());
        String methodName = pjp.getSignature().getName();
        String className = pjp.getTarget().getClass().toString();
        Object array = pjp.getArgs();


        //Before advice
        log.info("method invoked" + className + ": " + methodName + "()" + "arguments : " + om.writeValueAsString(array));

        //after advice
        Object object = pjp.proceed(); // we will get the response
        log.info(className + " : " + methodName + "()" + "Response : " + om.writeValueAsString(object)); // for json formatting
        return object;
    }

}
