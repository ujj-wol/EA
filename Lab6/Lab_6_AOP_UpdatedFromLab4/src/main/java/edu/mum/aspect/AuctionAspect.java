package edu.mum.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import edu.mum.domain.User;
//import org.aspectj.lang.annotation.Aspect;

@Aspect
@Component
public class AuctionAspect {
//	@Pointcut("execution(public void edu.mum.service.impl..*(..))") //public is optional as it works only for public
//	OR
//	@Pointcut("execution(* edu.mum.service.impl..*(..))") //first * for return type, 2nd * for method names
//	@Pointcut("execution(* edu.mum.service.impl..*(Long))")	//Qn.3
//	@Pointcut("execution(* edu.mum.service.impl..*())")	//Qn.4
	@Pointcut("within(edu.mum.service.impl..*) && args()") //Qn.6
	public void applicationMethod() {}
	
//	@Before("execution(* edu.mum.service..*(..))") //Qn.1
	@Before("applicationMethod()") //Qn.2
	public void printMethodName(JoinPoint joinPoint) {
	    
	    System.out.println();
			    System.out.println( "   **********     METHOD NAME : " + 
    			joinPoint.getSignature().getName() + "    **********");
	}
	
	//Qn.7 add another Advice method
	@Before("within(edu.mum.service..*) && args(user)")
	public void logResourceName(JoinPoint joinPoint, User user) {
		System.out.println();
		System.out.println("USER NAME: " + user.getFirstName() + " " + user.getLastName() 
		+ "   #######     TARGET CLASS & METHOD NAME: " + 
    			joinPoint.getSignature().getDeclaringTypeName() + "." +
    			joinPoint.getSignature().getName() + "    #######");
	}
	
}
