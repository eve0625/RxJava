package rxjava.operators;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class MapEx {
	
	/**
	 * map : 어떤 함수에 입력값을 넣어서 원하는 값으로 변환하는 함수
	 */
	private void test() {
		String[] balls = {"1", "2", "3", "5"};
		Observable<String> source = Observable.fromArray(balls);
		source.map(ball -> ball + "ㅁ").subscribe(System.out::println);
		
		Function<String, String> getDiamond = ball -> ball + "ㅁ";
		Observable<String> source2 = Observable.fromArray(balls);
		source2.map(getDiamond).subscribe(System.out::println);
	
		//String 인자를 받아 Integer를 리턴하는 Function
		Function<String, Integer> ballToIndex = ball -> {
			switch(ball) {
			case "RED" : return 1;
			case "YELLOW" : return 2;
			case "GREEN" : return 3;
			case "BLUE" : return 5;
			default : return -1;
			}
		};
		String[] balls3 = {"RED", "GREEN", "BLUE", "GRAY", "YELLOW"};
		Observable<String> source3 = Observable.fromArray(balls3);
		source3.map(ballToIndex).subscribe(System.out::println);
	
	}

	public static void main(String[] args) {
		MapEx ex = new MapEx();
		ex.test();
	}

}
