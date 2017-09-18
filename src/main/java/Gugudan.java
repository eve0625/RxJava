import java.util.Scanner;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class Gugudan {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		System.out.println("Gugudan Input: ");
		int dan = Integer.parseInt(in.nextLine());
		
		//1.Observable을 사용하여 구구단 출력하기
		//range(start, count)는 1(start)부터 9(count)개의 갯수만큼 숫자값을 발행
		Observable<Integer> source = Observable.range(1, 9); 
		source.subscribe(row -> System.out.println(dan + " * " + row + " = " + dan * row));
		
		//2.사용자함수 정의하여 구구단 출력하기
		Function<Integer, Observable<String>> gugudan = num ->
			Observable.range(1, 9).map(row -> num + " * " + row + " = " + num * row);
		Observable<String> source2 = Observable.just(dan).flatMap(gugudan);
		source2.subscribe(System.out::println);
		
		//3.함수를 인라인으로 넣어보기
		Observable<String> source3 = Observable.just(dan).flatMap(num ->
				Observable.range(1, 9).map(row -> num + " * " + row + " = " + num * row));
		source3.subscribe(System.out::println);
		
		//4.다른 형태의 flatMap 함수 사용해보기
		//입력 파라미터가 2개 (dan , row)
		Observable<String> source4 = Observable.just(dan).flatMap(gugu ->
			Observable.range(1, 9), (gugu, i) -> gugu + " * " + i + " = " + gugu * i);
		source4.subscribe(System.out::println);
		
		in.close();
	}

}
