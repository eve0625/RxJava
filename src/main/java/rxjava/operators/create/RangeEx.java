package rxjava.operators.create;

import io.reactivex.Observable;

public class RangeEx {
	
	/**
	 * range(n, m) : 주어진 값 n부터 m개의 Integer 객체를 발행
	 * 스케쥴러에서 실행되지 않으며, for, while문을 대체할 수 있음
	 */
	public void test() {
		//1부터 10까지 정수중에 짝수만 걸러내기
		Observable<Integer> source = Observable.range(1, 10)
				.filter(num -> num % 2 == 0);
		source.subscribe(System.out::println);
	}

	public static void main(String[] args) {
		RangeEx ex = new RangeEx();
		ex.test();
	}

}
