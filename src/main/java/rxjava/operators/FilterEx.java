package rxjava.operators;

import io.reactivex.Observable;

public class FilterEx {
	
	/**
	 * filter : Observable에서 원하는 데이터만 걸러냄
	 * filter에는 boolean값을 리턴하는 함수형 인터페이스인 Predicate를 인자로 넣는다
	 * 결과값이 true인 경우의 데이터만 발행
	 */
	public void test() {
		
		String[] objs = {"1 CIRCLE", "2 DIAMOND", "3 TRIANGLE", "4 DIAMOND", "5 CIRCLE", "6 HEXAGON"};
		Observable<String> source = Observable.fromArray(objs).filter(obj -> obj.endsWith("CIRCLE"));
		source.subscribe(System.out::println);
		
		Integer[] numbers = {100, 34, 27, 99, 50};
		Observable<Integer> source2 = Observable.fromArray(numbers).filter(num -> num % 2 == 0);
		source2.subscribe(System.out::println);
	}

	public static void main(String[] args) {
		FilterEx ex = new FilterEx();
		ex.test();
	}
}
