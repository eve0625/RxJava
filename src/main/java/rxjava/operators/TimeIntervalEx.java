package rxjava.operators;

import common.CommonUtils;
import common.Log;
import io.reactivex.Observable;
import io.reactivex.schedulers.Timed;

public class TimeIntervalEx {
	
	/**
	 * timeInterval : 어떤 값을 발행했을때 이전값을 발행한 이후 얼마나 시간이 흘렀는지를 알려줌
	 */

	public void test() {
		String[] data = {"1", "3", "7"};
		
		CommonUtils.exampleStart();
		
		Observable<Timed<String>> source = Observable.fromArray(data)
				.delay(item -> {
					CommonUtils.doSomething();
					return Observable.just(item);
				}).timeInterval();
		source.subscribe(Log::it);
		
		CommonUtils.exampleComplete();
		
	}
	
	public static void main(String[] args) {
		TimeIntervalEx ex = new TimeIntervalEx();
		ex.test();
	}

}
