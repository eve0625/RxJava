package rxJava;

import common.Order;
import io.reactivex.Observable;
import io.reactivex.Single;

public class Single1 {

	public void emit() {
		/**
		 * Single 클래스는 RxJava 1.x 부터 존재하는 Observable의 특수한 형태
		 * 무제한 데이터를 발행할 수 있는 Observable과 달리, 오직 1개의 데이터만 발행할 수 있음 (서버 API 등을 호출시 사용)
		 * 데이터 하나가 발행과 동시에 종료하므로 onNext와 onComplete가 통합된 onSuccess(T value)와 onError를 제공
		 * 아래의 예제들은 모두 1개의 값이 출력됨
		 */
		//정적 메서드 just()를 사용하여 데이터 발행
		Single<String> source = Single.just("Hello Single");
		source.subscribe(System.out::println);
		
		//Observable로 변환하여 사용하기
		//1.fromObservable() 메서드를 사용하여 Observable to Single
		Observable<String> source2 = Observable.just("Hello Single");
		Single.fromObservable(source2).subscribe(System.out::println);
		
		//2.single() 메서드를 사용하여 Observable to Single
		//single() 메서드는 default value를 인자로 가지며, Observable이 데이터를 발행하지 않을때 기본값을 대신 발행
		//데이터가 여러개인 Observable을 single() 메서드로 변환 시도시 에러발생 (두번째 값을 발행하는 onNext에서 에러 발생함)
		Observable.just("Hello Single").single("default item").subscribe(System.out::println);
		
		//3.empty() 메서드를 통해 Single 객체를 생성. 데이터 발행 후 onSuccess 이벤트 발생
		Observable.empty().single("default value").subscribe(System.out::println);
		
		//4.first() 메서드를 호출하면 Observable이 Single로 변환됨
		String[] colors = {"Red", "Blue", "Gold"};
		Observable.fromArray(colors).first("default value").subscribe(System.out::println);
		
		//5.take() 메서드를 사용하여 Single 객체 생성
		Observable.just(new Order("coffee"), new Order("juice")).take(1).subscribe(System.out::println);
		
	}
	
	public static void main(String[] args) {
		Single1 observable = new Single1();
		observable.emit();
	}

}
