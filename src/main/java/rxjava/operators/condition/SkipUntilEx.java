package rxjava.operators.condition;

import java.util.concurrent.TimeUnit;

import common.CommonUtils;
import common.Log;
import io.reactivex.Observable;

public class SkipUntilEx {
	
	/**
	 * skipUntil : other Observable이 값을 발행할 때까지 값을 건너뛰다가,
	 * 값이 발행되면 그 순간부터 원래 Observable의 값을 발행하기 시작함 (takeUntil과 반대)
	 */
	public void test() {
		String[] data = {"1", "2", "3", "4", "5", "6"};
		
		Observable<String> source = Observable.fromArray(data)
				.zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), (val, notUsed) -> val)
				.skipUntil(Observable.timer(500L, TimeUnit.MILLISECONDS));
		source.subscribe(Log::i);
		CommonUtils.sleep(1000);
	}

	public static void main(String[] args) {
		SkipUntilEx ex = new SkipUntilEx();
		ex.test();
	}

}
