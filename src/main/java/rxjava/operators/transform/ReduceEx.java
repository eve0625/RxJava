package rxjava.operators.transform;

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
	
		/**
		 * Maybe 클래스는 Single 클래스와 마찬가지로 최대 데이터 하나를 가질 수 있지만
		 * 데이터 발행없이 바로 데이터 발생을 완료할 수 있음 (0 혹은 1개 완료)
		 * 즉 Single 클래스에 onComplete 이벤트가 추가된 형태임
		 * 
		 * Maybe 객체를 생헝할 수 있는 연산자 : elementAt(), firstElement(), flatMapMaybe(), lastElement(), reduce(), singleElement()
		 */
	
	}
	
	public static void main(String[] args) {
		ReduceEx ex = new ReduceEx();
		ex.test();
	}

}
