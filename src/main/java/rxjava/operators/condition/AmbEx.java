package rxjava.operators.condition;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import common.CommonUtils;
import common.Log;
import io.reactivex.Observable;

public class AmbEx {

	/**
	 * amb : 여러개의 Observable 중에서 가장 먼저 데이터를 발행하는 Observable을 선택하여 계속 값을 발행하도록 해주고,
	 * 나머지 Observable은 모두 무시한다.
	 */
	public void test() {
		String[] data1 = {"1", "3", "5"};
		String[] data2 = {"2-R", "4-R"};
		
		List<Observable<String>> sources = Arrays.asList(
				Observable.fromArray(data1)
				.doOnComplete(() -> Log.d("Observable #1 : onComplete")),
				Observable.fromArray(data2)
				.delay(100L, TimeUnit.MILLISECONDS)
				.doOnComplete(() -> Log.d("Observable #2 : onComplete"))
			);
		Observable.amb(sources)
			.doOnComplete(() -> Log.d("Result : onComplete"))
			.subscribe(Log::i);
		
		CommonUtils.sleep(1000);
	}
	
	public static void main(String[] args) {
		AmbEx ex = new AmbEx();
		ex.test();
	}

}
