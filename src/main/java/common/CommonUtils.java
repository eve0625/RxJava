package common;

import java.util.Random;

public class CommonUtils {

	//실행 시간을 표기하기 위한 정적 변수
	public static long startTime;
	
	public static void exampleStart() {
		startTime = System.currentTimeMillis();
	}
	
	public static void exampleComplete() { 
		System.out.println("-----------------------");
	}
	
	public static void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void doSomething() { 
		try { 
			Thread.sleep(new Random().nextInt(100));
		} catch (InterruptedException e) { 
			e.printStackTrace();
		}		
	}
}
