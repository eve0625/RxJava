package rxjava.scheduler;

import common.CommonUtils;
import common.Log;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class SingleEx {
	
	/**
	 * single : RxJava 내부에서 단일 스레드를 별도로 생성하여 구독작업을 처리.
	 * 생성된 스레드는 여러번 구독요청이 와도 공통으로 사용
	 */
	public void test() {
		Observable<Integer> numbers = Observable.range(100, 5);
		Observable<String> chars = Observable.range(0, 5)
				.map(SingleEx::numberToAlphabet);
		
		numbers.subscribeOn(Schedulers.single())
			.subscribe(Log::i);
		chars.subscribeOn(Schedulers.single())
			.subscribe(Log::i);
		
		CommonUtils.sleep(1000);
	}

	public static void main(String[] args) {
		SingleEx ex = new SingleEx();
		ex.test();
	}
	
	private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	public static String numberToAlphabet(long x) { 
		return Character.toString(ALPHABET.charAt((int) x % ALPHABET.length()));
	}

}
