package subject;

import io.reactivex.subjects.PublishSubject;

public class PublishSubjectEx {

	/**
	 * PublishSubject는 오직 해당시간에 발행한 데이터를 그대로 구독자에게 전달
	 */
	public void emit() {
		
		PublishSubject<String> subject = PublishSubject.create();
		subject.subscribe(data -> System.out.println("Subscriber #1 => " + data));
		subject.onNext("1");
		subject.onNext("3");
		subject.subscribe(data -> System.out.println("Subscriber #2 => " + data));
		subject.onNext("5");
		subject.onComplete();
	}
	
	public static void main(String[] args) {
		PublishSubjectEx ex = new PublishSubjectEx();
		ex.emit();
	}

}
