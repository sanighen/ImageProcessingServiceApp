package aspcets;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class PerformanceBenchmarkAspect {

	@Around("execution( * processors.ImageProcessor.transform(..) )")
	public Object measureProcessingPerformance(ProceedingJoinPoint point) throws Throwable {

		long startTime = System.currentTimeMillis();
		Object object = point.proceed();
		long endTime = System.currentTimeMillis();

		System.out.println("Time taken for Execution is : " + (endTime - startTime) + " ms");

		return object;
	}

}
