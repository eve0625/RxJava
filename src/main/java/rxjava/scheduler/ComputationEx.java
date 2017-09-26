package rxjava.scheduler;

import java.util.concurrent.TimeUnit;

import common.CommonUtils;
import common.Log;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class ComputationEx {

	/**
	 * computation : CPU에 대응하는 계산용 스케쥴러. 대기시간 없이 빠르게 결과를 도출해야 할 때 사용
	 * 아래 예제를 실행하다보면,, 동일한 스레드에서 동작하기도 함
	 */
	public void test() {
		String[] orgs = {"1", "3", "5"};
		Observable<String> source = Observable.fromArray(orgs)
				.zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), (a, b) ->  a);
				
		source.map(item -> "<<" + item + ">>")
			.subscribeOn(Schedulers.computation())
			.subscribe(Log::i);
		
		source.map(item -> "##" + item + "##")
			.subscribeOn(Schedulers.computation())
			.subscribe(Log::i);
		
		CommonUtils.sleep(1000);
	}
	
	public static void main(String[] args) {
		ComputationEx ex = new ComputationEx();
		ex.test();
	}

}
