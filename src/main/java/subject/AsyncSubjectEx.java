package subject;

import io.reactivex.Observable;
import io.reactivex.subjects.AsyncSubject;

public class AsyncSubjectEx {

	public void emit() {
		/**
		 * Subject 클래스는 cold observable을 hot observable로 바꿔줌
		 * observable의 속성과 subscriber의 속정을 모두 가지고 있어, 데이터를 발행할수도 있고 발행된 데이터를 처리할 수도 있음
		 * 
		 * AsyncSubject : Observable에서 발행한 마지막 데이터를 얻어올 수 있는 클래스
		 * 데이터 발행이 완료되기 전까지는 구독자에게 데이터를 전달하지 않다가, 완료함과 동시에 구독자에게 마지막 데이터를 발행하고 종료함
		 */
		//AsyncSubject가 데이터를 발행하는 예제
		AsyncSubject<String> subject = AsyncSubject.create();
		subject.subscribe(data -> System.out.println("Subscriber #1 => " + data));
		subject.onNext("1");
		subject.onNext("3");
		subject.subscribe(data -> System.out.println("Subscriber #2 => " + data));
		subject.onNext("5");
		subject.onComplete();
		
		//AsyncSubject가 구독자로 동작하는 예제
		Float[] temperature = {10.1f, 13.4f, 12.5f};
		Observable<Float> source = Observable.fromArray(temperature);
		
		AsyncSubject<Float> subject2 = AsyncSubject.create();
		subject2.subscribe(data -> System.out.println("Subscriber #1 => " + data));
		
		source.subscribe(subject2);
		
		//onComplete 후에 구독자 등록해보기
		//Observable과 마찬가지로 onCompete후에 호출되는 onNext는 모두 무시되나
		//그 후에 등록된 구독자들도 마지막 발행된 값을 가져올 수 있음
		AsyncSubject<Integer> subject3 = AsyncSubject.create();
		subject3.onNext(10);
		subject3.onNext(11);
		subject3.subscribe(data -> System.out.println("Subscriber #1 => " + data));
		subject3.onNext(12);
		subject3.onComplete();
		subject3.onNext(13);
		subject3.subscribe(data -> System.out.println("Subscriber #2 => " + data));
		subject3.subscribe(data -> System.out.println("Subscriber #3 => " + data));
	}
	
	public static void main(String[] args) {
		AsyncSubjectEx ex = new AsyncSubjectEx();
		ex.emit();
	}
}
