package rxjava.operators.condition;

import common.Log;
import common.Shape;
import io.reactivex.Observable;
import io.reactivex.Single;

public class AllEx {

	/**
	 * all : 주어진 조건에 모두 맞을때만 true를 발행하고, 조건에 맞지 않으면 false값 발
	 */
	public void test() {
		String[] data = {"1", "2", "3", "4"};
		
		Single<Boolean> source = Observable.fromArray(data)
				.map(Shape::getShape)
				.all(Shape.BALL::equals);
		source.subscribe(Log::i);
	}
	
	public static void main(String[] args) {
		AllEx ex = new AllEx();
		ex.test();
	}

}
