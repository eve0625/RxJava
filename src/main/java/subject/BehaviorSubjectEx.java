package subject;

import io.reactivex.subjects.BehaviorSubject;

public class BehaviorSubjectEx {

	/**
	 * BehaviorSubject는 구독시 가장 최근값 혹은 기본값을 넘겨주는 클래스
	 */
	public void emit() {
		
		//createDefault메서드로 초기값이 없을경우 발행할 기본값을 인자로 받아 BehaviorSubject 클래스를 생성
		//첫번째 구독자는 초기값인 6부터 발행받고, 두번째 구독자는 구독시 최근값인 3부터 발행받는다.
		BehaviorSubject<String> subject = BehaviorSubject.createDefault("6");
		subject.subscribe(data -> System.out.println("Subscriber #1 => " + data));
		subject.onNext("1");
		subject.onNext("3");
		subject.subscribe(data -> System.out.println("Subscriber #2 => " + data));
		subject.onNext("5");
		subject.onComplete();
	}
	
	public static void main(String[] args) {
		BehaviorSubjectEx ex = new BehaviorSubjectEx();
		ex.emit();
	}

}
