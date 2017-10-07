package rxjava.flowcontrol;

import java.util.concurrent.TimeUnit;

import common.CommonUtils;
import common.Log;
import io.reactivex.Observable;

public class SampleEx {
	
	/**
	 * sample : 특정한 시간 간격을 기준으로 가장 최근에 발행된 데이터만 넘겨줌
	 * 해당 시간에는 아무리 많은 데이터가 들어와도 해당 구간의 마지막 데이터만 발행하고 나머지는 무시
	 */
	
	public void test() {
		String[] data = {"1", "7", "2", "3", "6"};
		
		CommonUtils.exampleStart();
	
		Observable<String> earlySource = Observable.fromArray(data)
				.take(4)
				.zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), (a, b) -> a);
		
		Observable<String> lateSource = Observable.just(data[4])
				.zipWith(Observable.timer(300L, TimeUnit.MILLISECONDS), (a, b) -> a);
		
		Observable<String> source = Observable.concat(earlySource, lateSource)
				.sample(300L, TimeUnit.MILLISECONDS, true); //emitLast : Observable 종료시 데이터가 남아있는 경우 발행할 것인지 여부
		
		source.subscribe(Log::it);
		
		CommonUtils.sleep(1000);
	}

	public static void main(String[] args) {
		SampleEx ex = new SampleEx();
		ex.test();
	}


}
