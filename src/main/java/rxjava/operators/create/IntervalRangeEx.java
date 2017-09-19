package rxjava.operators.create;

import java.util.concurrent.TimeUnit;

import common.CommonUtils;
import common.Log;
import io.reactivex.Observable;

public class IntervalRangeEx {

	/**
	 * intervalRange : interval처럼 일정한 시간 간격으로 값을 출력하지만, range처럼 n부터 m개 만큼의 값만 생성하고 onComplete 이벤트 발생
	 */
	public void test() {
		Observable<Long> source = Observable.intervalRange(1, 5, 100L, 100L, TimeUnit.MILLISECONDS);
		source.subscribe(Log::i);
		CommonUtils.sleep(1000); //별도의 스레드에서 실행되므로 sleep이 필요
		
		//interval로 intervalRange 만들어보기
		Observable<Long> source2 = Observable.interval(100L, 100L, TimeUnit.MILLISECONDS)
				.map(val -> val + 1)
				.take(5);
		source2.subscribe(Log::i);
		CommonUtils.sleep(1000); //별도의 스레드에서 실행되므로 sleep이 필요
	}
	
	public static void main(String[] args) {
		IntervalRangeEx ex = new IntervalRangeEx();
		ex.test();
	}

}
