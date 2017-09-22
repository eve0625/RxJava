package rxjava.operators.combine;

import common.Log;
import io.reactivex.Observable;

public class ZipWithEx {

	public void test() {
		Observable<Integer> source = Observable.zip(
				Observable.just(100, 200, 300),
				Observable.just(10, 20, 30),
				(a, b) -> a + b)
				.zipWith(Observable.just(1, 2, 3), (ab, c) -> ab + c);
		source.subscribe(Log::i);
	}
	
	public static void main(String[] args) {
		ZipWithEx ex = new ZipWithEx();
		ex.test();
	}

}
