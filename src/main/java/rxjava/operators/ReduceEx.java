package rxjava.operators;

import io.reactivex.Maybe;
import io.reactivex.Observable;

public class ReduceEx {

	/**
	 * Reduce : 발행한 데이터를 모두 사용하여 결과 데이터를 합성할때 활용
	 */
	public void test() {
		String[] balls = {"1", "3", "5"};
		Maybe<String> source = Observable.fromArray(balls).reduce((ball1, ball2) -> ball2 + "(" + ball1 + ")");
		source.subscribe(System.out::println);
		
	}
	
	public static void main(String[] args) {
		ReduceEx ex = new ReduceEx();
		ex.test();
	}

}
