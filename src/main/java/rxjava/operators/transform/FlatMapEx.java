package rxjava.operators.transform;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class FlatMapEx {

	/**
	 * flatMap : 결과가 Observable로 반환되는 함수 (1:1 혹은 1:N 함수)
	 */
	public void test() {
		
		//입력값에 다이아몬드를 붙여 두번 발행하는 함수
		Function<String, Observable<String>> getDoubleDiamonds = 
				ball -> Observable.just(ball + "♢", ball + "♢");
				
		String[] balls = {"1", "3", "5"};
		Observable<String> source = Observable.fromArray(balls).flatMap(getDoubleDiamonds);
		source.subscribe(System.out::println);
		
		Observable<String> source2 = Observable.fromArray(balls);
		source2.flatMap(ball -> Observable.just(ball + "♢", ball +"♢")).subscribe(System.out::println); //람다 표현식 사용
	}
	
	public static void main(String[] args) {
		FlatMapEx ex = new FlatMapEx();
		ex.test();
	}

}
