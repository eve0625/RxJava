package rxjava.operators.transform;

import common.Log;
import io.reactivex.Observable;

public class ScanEx {

	/**
	 * scan : reduce 함수와 비슷하나, 모든 데이터가 입력된 후 그것을 종합하여 마지막 1개의 데이터만을 구독자에게 발행하는 reduce와 달리
	 * scan은 실행할때마다 입력값에 맞는 중간 결과 및 최종 결과를 구독자에게 발행
	 */
	public void test() {
		String[] balls = {"1", "3", "5"};
		Observable<String> source = Observable.fromArray(balls)
				.scan((ball1, ball2) -> ball2 + "(" + ball1 + ")");
		source.subscribe(Log::i);
	}
	
	public static void main(String[] args) {
		ScanEx ex = new ScanEx();
		ex.test();
	}

}
