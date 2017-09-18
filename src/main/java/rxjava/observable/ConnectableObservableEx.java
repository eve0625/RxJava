package rxjava.observable;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

public class ConnectableObservableEx {

	/**
	 * ConnectableObservable : Subject 클래스처럼 차가운 Observable을 뜨거운 Observable로 변환함
	 * publish() 함수를 호출하여 ConnectableObservable 객체를 생성
	 * connect() 함수가 호출된 시점부터 subscribe()를 호출한 구독자에게 데이터를 발행함
	 */
	public void emit() {
		String[] dt = {"1", "3", "5"};
		Observable<String> balls = Observable.interval(100L, TimeUnit.MILLISECONDS) //100ms 단위로 0부터 데이터를 발행
				.map(Long::intValue)
				.map(i -> dt[i]) //배열이 들어있는 데이터를 순서대로 발행
				.take(dt.length);
		ConnectableObservable<String> source = balls.publish();
		source.subscribe(data -> System.out.println("Subscriber #1 => " + data));
		source.subscribe(data -> System.out.println("Subscriber #2 => " + data));
		source.connect();
		
		try {
			Thread.sleep(250);
			//connect()가 호출되었으므로, 다음의 데이터를 바로 수신할 수 있음
			source.subscribe(data -> System.out.println("Subscriber #3 => " + data));
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		ConnectableObservableEx ex = new ConnectableObservableEx();
		ex.emit();
	}

}
