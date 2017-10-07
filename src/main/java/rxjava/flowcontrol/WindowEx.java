package rxjava.flowcontrol;

import java.util.concurrent.TimeUnit;

import common.CommonUtils;
import common.Log;
import io.reactivex.Observable;

public class WindowEx {
	
	/**
	 * window : 처리할 수 있는 일부의 값들을 받아들여 별도의 Observable로 분리
	 */
	public void test() {
		String[] data = {"1", "2", "3", "4", "5", "6"};
		CommonUtils.exampleStart();
		
		Observable<String> earlySource = Observable.fromArray(data)
				.take(3)
				.zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), (a, b) -> a);
		
		Observable<String> middleSource = Observable.just(data[3])
				.zipWith(Observable.timer(300L, TimeUnit.MILLISECONDS), (a, b) -> a);
		
		Observable<String> lateSource = Observable.just( data[4], data[5])
				.zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), (a, b) -> a);
		
		Observable<Observable<String>> source = Observable.concat(earlySource, middleSource, lateSource)
				.window(3); //Observable을 생성하여 3개의 데이터를 담고, 다시 Observable 생성
		
		source.subscribe(observable -> {
			Log.dt("New Observable Started!!");
			observable.subscribe(Log::it);
		});
		CommonUtils.sleep(1000);
		CommonUtils.exampleComplete();
	}

	public static void main(String[] args) {
		WindowEx ex = new WindowEx();
		ex.test();
	}

}
