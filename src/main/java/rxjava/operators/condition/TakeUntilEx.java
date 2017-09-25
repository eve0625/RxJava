package rxjava.operators.condition;

import java.util.concurrent.TimeUnit;

import common.CommonUtils;
import common.Log;
import io.reactivex.Observable;

public class TakeUntilEx {
	
	/**
	 * takeUntil : Observable에서 어떤 값을 발행하면, 현재 Observable 의 데이터 발행을 중단하고 즉시 완료함
	 * 비슷한 함수로는 skipWhile이 있음
	 */
	public void test() {
		String[] data = {"1", "2", "3", "4", "5", "6"};
		
		Observable<String> source = Observable.fromArray(data)
				.zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), (val, notUsed) -> val) //100ms당 값을 하나씩 발행
				.takeUntil(Observable.timer(500L, TimeUnit.MILLISECONDS));
		source.subscribe(Log::i);
		CommonUtils.sleep(1000);
	}

	public static void main(String[] args) {
		TakeUntilEx ex = new TakeUntilEx();
		ex.test();
	}

}
