package rxjava.operators.transform;

import java.util.concurrent.TimeUnit;

import common.CommonUtils;
import common.Log;
import io.reactivex.Observable;

public class switchMapEx {

	/**
	 * switchMap : 인터리빙이 발생할 수 있는 상황에서, 순서를 보장하기 위해 기존에 진행중인던 작업을 바로 중단함.
	 * 중간에 끊기더라도 마지막 데이터의 처리는 보장함
	 * 센서 등의 값을 얻어와서 동적으로 처리하는 경우에 매우 유용 (최종값이 중요한 경우!)
	 */
	public void test() {
		CommonUtils.exampleStart();
		
		String[] balls = {"1", "3", "5"};
		Observable<String> source = Observable.interval(100L, TimeUnit.MILLISECONDS)
				.map(Long::intValue)
				.map(idx -> balls[idx])
				.take(balls.length)
				.doOnNext(Log::dt)
				.switchMap(ball -> Observable.interval(200L, TimeUnit.MILLISECONDS)
						.map(notUsed -> ball + "♢")
						.take(2)
						);
		source.subscribe(Log::it);
		//1, 3의 데이터 발행은 취소되고 5만 처리됨
		
		CommonUtils.sleep(2000);
	}
	
	public static void main(String[] args) {
		switchMapEx ex = new switchMapEx();
		ex.test();
	}

}
