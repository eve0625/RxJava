package rxjava.operators;

import io.reactivex.Observable;
import io.reactivex.Single;

public class OtherFiltersEx {

	/**
	 * filter 이외의 함수
	 * first(default) : Observable의 첫번째 값. 없는 경우 default가 반환됨
	 * last(default) : Observable의 마지막 값. 없는 경우 default가 반환됨
	 * take(n) : 최초 n개의 값만 가져옴
	 * takeLast(n) : 마지막 n개의 값만 가져옴
	 * skip(n) : 최초 n개의 값을 건너 뜀
	 * skipLast(n) : 마지막 n개의 값을 건너 뜀
	 */
	public void test() {
		Integer[] numbers = {100, 200, 300, 400, 500};
		Single<Integer> single;
		Observable<Integer> source;
		
		//first
		single = Observable.fromArray(numbers).first(-1);
		single.subscribe(data -> System.out.println("first() : " + data));
		
		//last
		single = Observable.fromArray(numbers).last(-1);
		single.subscribe(data -> System.out.println("last() : " + data));
		
		//take
		source = Observable.fromArray(numbers).take(3);
		source.subscribe(data -> System.out.println("take(3) : " + data));
		
		//take last
		source = Observable.fromArray(numbers).takeLast(3);
		source.subscribe(data -> System.out.println("takeLast(3) : " + data));
		
		//skip
		source = Observable.fromArray(numbers).skip(2);
		source.subscribe(data -> System.out.println("skip(2) : " + data));
		
		//skip last
		source = Observable.fromArray(numbers).skipLast(2);
		source.subscribe(data -> System.out.println("skipLast(2) : " + data));
		
	}
	
	public static void main(String[] args) {
		OtherFiltersEx ex = new OtherFiltersEx();
		ex.test();
	}

}
