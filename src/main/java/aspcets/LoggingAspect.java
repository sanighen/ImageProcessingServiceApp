package aspcets;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class LoggingAspect {

	@Before("execution( * processors.ImageProcessor.transform(..) )")
	public void getArgs(JoinPoint jp) {
		Object[] args = jp.getArgs();

		for (Object fileName : args) {
			System.out.println("Found file: " + fileName);
		}

	}

	@AfterReturning("execution( * processors.ImageProcessor.transform(..) )")
	public void logAfterProcessing(JoinPoint jp) {
		Object[] args = jp.getArgs();

		for (Object fileName : args) {
			System.out.println("Processing of the " + fileName + " image completed");
		}
	}

}
