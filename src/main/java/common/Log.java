package common;

public class Log {

	public static void it(Object obj) {
		long time = System.currentTimeMillis() - CommonUtils.startTime;
		System.out.println(time + " | value = " + obj);
	}
	
	public static void i(Object obj) {
		System.out.println("value = " + obj);
	}
}
