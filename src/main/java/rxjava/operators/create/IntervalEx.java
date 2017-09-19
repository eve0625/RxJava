package rxjava.operators.create;

import java.util.concurrent.TimeUnit;

import common.CommonUtils;
import common.Log;
import io.reactivex.Observable;

public class IntervalEx {

	/**
	 * Interval() : 주어진 시간 간격으로 0부터 1씩 증가하는 Long 객체를 발행
	 * 계산 스케쥴러에서 함수의 동작이 실행되어 별도이 스레드에서 동작함
	 * 기본적으로 영원히 지속 실행되므로 폴링 용도로 많이 사용
	 */
	public void test() {
		
		CommonUtils.exampleStart();
		Observable<Long> source = Observable.interval(100L, TimeUnit.MILLISECONDS)
				.map(data -> (data + 1) * 100)
				.take(5); //첫번째 데이터부터 5개 까지만 발행
		source.subscribe(Log::it);
		
		Observable<Long> source2 = Observable.interval(1000L, 100L, TimeUnit.MILLISECONDS) //초기 지연값 설정
				.map(val -> val + 100)
				.take(5);
		source2.subscribe(Log::it);
		
		//Thread.sleep을 호출하지 않으면 메인 스레드가 종료되면서 프로그램이 바로 종료됨
		CommonUtils.sleep(3000);

	}
	
	public static void main(String[] args) {
		IntervalEx ex = new IntervalEx();
		ex.test();

	}

}
