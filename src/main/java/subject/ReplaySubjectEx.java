package subject;

import io.reactivex.subjects.ReplaySubject;

public class ReplaySubjectEx {

	/**
	 * ReplaySubject : Hot Observable을 활용하기 위한 Subject 클래스이지만, ReplaySubject는 Cold Observable처럼 동작함
	 * 구독자가 생기면 항상 데이터의 처음부터 끝까지 발행하므로, 메모리 누수가 발생할 수 있어 사용시 주의가 필요함
	 */
	public void emit() {
		
		ReplaySubject<String> subject = ReplaySubject.create();
		subject.subscribe(data -> System.out.println("Subscriber #1 => " + data));
		subject.onNext("1");
		subject.onNext("3");
		subject.subscribe(data -> System.out.println("Subscriber #2 => " + data));
		subject.onNext("5");
		subject.onComplete();
	}
	
	public static void main(String[] args) {
		ReplaySubjectEx ex = new ReplaySubjectEx();
		ex.emit();
	}

}
