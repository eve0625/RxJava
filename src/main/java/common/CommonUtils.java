package common;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Random;

public class CommonUtils {

	//실행 시간을 표기하기 위한 정적 변수
	public static long startTime;
	
	public static final String ERROR_CODE = "-500";
	
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
	
	public static boolean isNetworkAvailable() { 
		try {
			return InetAddress.getByName("www.google.com").isReachable(1000);
		} catch (IOException e) {
			Log.v("Network is not available");	
		}
		return false;
	}
}
