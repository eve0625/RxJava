package rxjava.operators;

import java.util.concurrent.TimeUnit;

import common.CommonUtils;
import common.Log;
import io.reactivex.Observable;

public class DelayEx {

	/**
	 * delay : 유틸리티 연산자로서, Observable의 데이터 발행을 지연시켜 주는 역할 (계산 스케쥴러에서 실행)
	 */
	public void test() {
		CommonUtils.exampleStart();
		
		String[] data = {"1", "7", "2", "3", "4"};
		Observable<String> source = Observable.fromArray(data).delay(100L, TimeUnit.MILLISECONDS);
		source.subscribe(Log::it);
		
		CommonUtils.sleep(1000);
	}
	
	public static void main(String[] args) {
		DelayEx ex = new DelayEx();
		ex.test();
	}

}
