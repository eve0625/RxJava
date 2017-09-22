package rxjava.operators.combine;

import java.util.concurrent.TimeUnit;

import common.CommonUtils;
import common.Log;
import common.Shape;
import io.reactivex.Observable;

public class CombineLatestEx {
	
	/**
	 * combineLatest : 2개 이상의 Observable을 기반으로, 각각의 값이 변경되었을때 갱신
	 * Observable 각각의 데이터 개수가 달라도 정상적으로 완료됨
	 * 각각의 Observable이 모두 값을 발행하여야 최신 데이터를 기반으로 결과값이 발행됨
	 */
	public void test() {
		String[] data1 = {"6", "7", "4", "2"};
		String[] data2 = {"DIAMOND", "STAR", "PENTAGON"};
		
		Observable<String> source = Observable.combineLatest(
				Observable.fromArray(data1).zipWith(Observable.interval(100L,  TimeUnit.MILLISECONDS),
						(shape, notUserd) -> Shape.getColor(shape)),
				Observable.fromArray(data2).zipWith(Observable.interval(150L, 100L,  TimeUnit.MILLISECONDS),
						(shape, notUserd) -> Shape.getSuffix(shape)),
				(v1, v2) -> v1 + v2);
		
		source.subscribe(Log::i);
		CommonUtils.sleep(1000);
	}

	public static void main(String[] args) {
		CombineLatestEx ex = new CombineLatestEx();
		ex.test();
	}

}
