package rxjava.flowcontrol;

import java.util.concurrent.TimeUnit;

import common.CommonUtils;
import common.Log;
import io.reactivex.Observable;

public class ThrottleFirstEx {
	
	/**
	 * throttleFirst : 지정된 시간동안 데이터가 발행되지 않도록 막다가, 주어진 조건에서 가장 먼저 입력된 값을 발행
	 * 계산 스케쥴러에서 실행되는 비동기 함수
	 * throttleLast : 지정된 시간동안 입력된 값 중 마지막 값을 발행함 (sample과 동일)
	 */
	public void test() {
		String[] data = {"1", "2", "3", "4", "5", "6"};
		CommonUtils.exampleStart();
		
		Observable<String> earlySource = Observable.just(data[0])
				.zipWith(Observable.timer(100L, TimeUnit.MILLISECONDS), (a, b) -> a);
		
		Observable<String> middleSource = Observable.just(data[1])
				.zipWith(Observable.timer(300L, TimeUnit.MILLISECONDS), (a, b) -> a);
		
		Observable<String> lateSource = Observable.just(data[2], data[3], data[4], data[5])
				.zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), (a, b) -> a)
				.doOnNext(Log::dt);
		
		Observable<String> source = Observable.concat(earlySource, middleSource, lateSource)
				.throttleFirst(200L, TimeUnit.MILLISECONDS);
		
		source.subscribe(Log::it);
		CommonUtils.sleep(1000);
	}

	public static void main(String[] args) {
		ThrottleFirstEx ex = new ThrottleFirstEx();
		ex.test();
	}

}
