package rxJava;

import io.reactivex.Observable;

public class Hello {

	public void emit() {
		Observable.just("Hello", "RxJava2", "!!!!")
			.subscribe(System.out::println);
	}
	
	public static void main(String[] args) {
		Hello hello = new Hello();
		hello.emit();
	}
}
