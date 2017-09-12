package rxJava;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class Observable1 {
	
	public void emit() {
		//just() : Observable을 생성하여 인자로 넣은 데이터를 차례로 발행함. (subscribe를 호출해야 시작됨)
		//	모두 같은 데이터 타입을 열개까지 넣을 수 있음
		Observable.just(1,2,3,4,5).subscribe(System.out::println);
		 
		//onNext, onError, onComplete 모두 설정한 예제
		//dispose()는 Observable이 더이상 데이터를 발행하지 않도록 구독을 해지하는 함수
		//onComplete 알림을 보냈을때는 자동으로 dispose()를 호출해 구독자 관계를 해지하므로, 구독자가 별도로 호출할 필요가 없
		Observable<String> source = Observable.just("RED", "GREEN", "YELLOW");
		Disposable d = source.subscribe(
				v -> System.out.println("onNext() : value : " + v),
				err -> System.out.println("onError() : err : " + err.getMessage()),
				() -> System.out.println("onComplete()")
		);
		System.out.println("isDisposed() : " + d.isDisposed());
		
		//create()를 사용하여 데이터 발행하기
		//직접 onNext, onComplete 등의 알림을 호출해야 함
		//아래에서 source2는 차가운 Observable, 즉 subscribe() 함수를 호출하기 전까지는 아무것도 출력되지 않음
		Observable<Integer> source2 = Observable.create(
				(ObservableEmitter<Integer> emitter) -> {
					emitter.onNext(100);
					emitter.onNext(200);
					emitter.onNext(300);
					emitter.onComplete();
				}
		);
		source2.subscribe(new Consumer<Integer>() {
			@Override
			public void accept(Integer data) throws Exception {
				System.out.println("Result : " + data);
			}
		});
		//위의 익명객체로 표현된 부분을 람다 표현식으로 축약
		//자바 컴파일러가 추론하여 해당하는 메서드 (accept)를 호출해 줌
		source2.subscribe(data -> System.out.println("Result : " + data));
	}

	public static void main(String[] args) {
		Observable1 observable = new Observable1();
		observable.emit();
	}

}
