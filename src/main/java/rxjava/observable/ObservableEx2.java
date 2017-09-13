package rxjava.observable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import common.Order;
import io.reactivex.Observable;

public class ObservableEx2 {

	public void emit() {
		/**
		 * 배열에 들어있는 데이터 처리시 fromArray()를 사용
		 */
		Integer[] arr = {100, 200, 300};
		Observable<Integer> source = Observable.fromArray(arr);
		source.subscribe(System.out::println);
		
		/**
		 * int[]을 사용했을때, 내부적으로 Integer[]로 변환되면서 문제발생!
		 */
		int[] arr2 = {400, 500};
		Observable.fromArray(arr2).subscribe(System.out::println);
		//명시적으로 Integer[]로 변환 (boxed()는 스트림을 Integer 배열로 만들어 줌)
		Integer[] arr3 = IntStream.of(arr2).boxed().toArray(Integer[]::new);
		Observable.fromArray(arr3).subscribe(System.out::println);
		
		/**
		 * Iterator 인터페이스를 구현하는 클래스를 통해 Observable 생성
		 */
		//List 객체로 Observable 생성
		List<String> source2 = new ArrayList<>();
		source2.add("Jelly");
		source2.add("Candy");
		source2.add("Snack");
		Observable.fromIterable(source2).subscribe(System.out::println);
		
		//Set 객체로 Observable 생성
		Set<String> cities = new HashSet<>();
		cities.add("Seoul");
		cities.add("London");
		cities.add("Paris");
		Observable.fromIterable(cities).subscribe(System.out::println);
		
		//BlockingQueue 객체로 Observable 생성
		BlockingQueue<Order> orders = new ArrayBlockingQueue<>(10);
		orders.add(new Order("Coffee"));
		orders.add(new Order("Sandwich"));
		orders.add(new Order("Sugar"));
		Observable.fromIterable(orders).subscribe(System.out::println);
		
		/**
		 * Callable 인터페이스를 구현하는 클래스를 통해 Observable 생성
		 * Callable은 Java5에서 추가된 동시성 API로, 비동기 실행후 결과를 반환하는 call() 메서드를 정의함
		 * Runnable과 비슷하지만 generic을 사용한 리턴값을 가지고 있는 차이점이 있음.
		 */
		Callable<String> callable = () -> {
			Thread.sleep(1000);
			return "Hello Callable";
		}; //람다 표현식 사용 : 인자가 없으므로 () -> {} 으로 나타냄
		Observable.fromCallable(callable).subscribe(System.out::println);
		
		/**
		 * Future 인터페이스를 구현하는 클래스를 통해 Observable 생성
		 * Future 역시 Java5에서 추가된 동시성 API로, 비동기 계산의 결과를 구할때 사용
		 * 보통 Executor 인터페이스를 구현한 Callable 객체를 인자로 넣어 Future 객체를 반환
		 */
		Future<String> future = Executors.newSingleThreadExecutor().submit(() -> {
			Thread.sleep(1000);
			return "Hello Future";
		});
		Observable.fromFuture(future).subscribe(System.out::println);
		
		/**
		 * Publisher를 사용하여 Observable을 생성
		 * publisher는 Java9의 표준인 Flow API의 일부
		 */
		Publisher<String> publisher = (Subscriber<? super String> s) -> {
			s.onNext("Hello Observable.fromPublisher()");
			s.onComplete();
		};
		Observable.fromPublisher(publisher).subscribe(System.out::println);
	}
	
	public static void main(String[] args) {
		ObservableEx2 observable = new ObservableEx2();
		observable.emit();
	}

}
