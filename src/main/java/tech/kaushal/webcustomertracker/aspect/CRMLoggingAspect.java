package tech.kaushal.webcustomertracker.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class CRMLoggingAspect {

    // setup logger
    private Logger logger = Logger.getLogger(getClass().getName());

    // setup point cut declarations
    @Pointcut("execution(* tech.kaushal.webcustomertracker.controller.*.*(..))")
    private void forControllerPackage(){};

    @Pointcut("execution(* tech.kaushal.webcustomertracker.service.*.*(..))")
    private void forServicePackage(){};

    @Pointcut("execution(* tech.kaushal.webcustomertracker.repository.*.*(..))")
    private void forRepositoryPackage(){};

    @Pointcut("forControllerPackage() || forServicePackage() || forRepositoryPackage()")
    private void forAppFlow(){};

    // add @Before advice
    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint) {
        // display the method we are calling
        String theMethod = joinPoint.getSignature().toShortString();
        logger.info("====> in @Before: calling method: " + theMethod);

        // display the argument to the method

        // get the arguments
        Object[] args = joinPoint.getArgs();

        // loop through and display args
        for(Object arg: args){
            logger.info("====> argument: " + arg);
        }
    }

    //add @AfterReturning advice
    @AfterReturning(pointcut = "forAppFlow()", returning = "object")
    public void afterReturning(JoinPoint joinPoint, Object object) {
        // display the method we are returning
        String theMethod = joinPoint.getSignature().toShortString();
        logger.info("====> in @AfterReturning: calling method: " + theMethod);

        // display data returned
        logger.info("====> result: " + object);

    }

}
