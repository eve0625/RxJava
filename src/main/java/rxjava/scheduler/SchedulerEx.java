package rxjava.scheduler;

import common.CommonUtils;
import common.Log;
import common.Shape;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class SchedulerEx {
	
	/**
	 * scheduler : 스레드를 지정하여 비동기로 동작할 수 있도록 함
	 * 경쟁조건이나 synchronized 키워드 등의 코드 없이, 간결한 코드로 구현 가능
	 */

	public void test() {
		String[] objs = {"1-S", "2-T", "3-P"};
		Observable<String> source = Observable.fromArray(objs)
				//onNext 이벤트가 발생하면 실행되는 코
				.doOnNext(data -> Log.v("Original data = " + data))
				//Observable에서 subscribe 함수가 호출되어 구독할 때 실행되는 스레드 지정 (최초의 데이터 흐름이 발생하는 스레드)
				.subscribeOn(Schedulers.newThread())
				//Observable에서 생성한 데이터 흐름이 처리될때 실행되는 스레드 지정 (지정된 함수를 거쳐 구독자에게 전달되는 스레드)
				.observeOn(Schedulers.newThread())
				.map(Shape::flip);
		source.subscribe(Log::i);
		//** ==> 데이터 흐름이 발생하는 스레드와 처리된 결과를 구독자에게 전달하는 스레드를 분리할 수 있다!!
		
		CommonUtils.sleep(500);
	}
	
	public static void main(String[] args) {
		SchedulerEx ex = new SchedulerEx();
		ex.test();
	}

}
