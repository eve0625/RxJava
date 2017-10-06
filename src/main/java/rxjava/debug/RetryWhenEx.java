package rxjava.debug;

import java.util.concurrent.TimeUnit;

import common.Log;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;

public class RetryWhenEx {
	
	public void test() {
		Observable.create((ObservableEmitter<String> emitter) -> {
			emitter.onError(new RuntimeException("always fails"));
		}).retryWhen(attempts -> {
			//3번씩 재시도
			return attempts.zipWith(Observable.range(1, 3), (n, i) -> i)
					.flatMap(i -> {
						Log.d("delay retry by " + i + " seconds");
						//재시도시 대기시간 설정
						return Observable.timer(i, TimeUnit.SECONDS);
					});
		}).blockingForEach(Log::d);
	}

	public static void main(String[] args) {
		RetryWhenEx ex = new RetryWhenEx();
		ex.test();
	}

}
