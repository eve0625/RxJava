import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import common.Log;
import common.Shape;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;

@RunWith(JUnitPlatform.class)
public class JUnit5Basic {
	@DisplayName("JUnit5 FirstExample")
	@Test
	void testFirst() {
		int expected = 3;
		int actual = 1 + 2;
		assertEquals(expected, actual);
	}
	
	@DisplayName("test getShape() Observable")
	@Test
	void testGetShapeObservable() {
		String[] data = {"1", "2-R", "3-T"};
		Observable<String> source = Observable.fromArray(data)
				.map(Shape::getShape);
		
		String[] expected = {Shape.BALL, Shape.RECTANGLE, Shape.TRIANGLE};
		List<String> actual = new ArrayList<>();
		source.doOnNext(Log::d).subscribe(actual::add);
		
		assertEquals(Arrays.asList(expected), actual);
	}
	
	@DisplayName("test getShape() using TestObserver")
	@Test
	void testGetShapeUsingTestObserver() {
		String[] data = {"1", "2-R", "3-T"};
		Observable<String> source = Observable.fromArray(data)
				.map(Shape::getShape);
		
		
		String[] expected = {Shape.BALL, Shape.RECTANGLE, Shape.TRIANGLE};
		source.test().assertResult(expected); //test() 함수는 TestObserver 객체를 리턴함
	}
	
	@DisplayName("assertFailure() example")
	@Test
	void assertFailureExample() {
		String[] data = {"100", "200", "%300"};
		Observable<Integer> source = Observable.fromArray(data)
				.map(Integer::parseInt);
		source.test().assertFailure(NumberFormatException.class, 100, 200);
	}
	
	@DisplayName("assertFailureAndMessage() example")
	@Test
	void assertFailureAndMessageExample() {
		String[] data = {"100", "200", "%300"};
		Observable<Integer> source = Observable.fromArray(data)
				.map(Integer::parseInt);
		source.test().assertFailureAndMessage(NumberFormatException.class, 
				"For input string: \"%300\"", 100, 200); //에러메세지가 동일한지까지 확인함
	}
	
	@DisplayName("assertComplete() example")
	@Test
	void assertComplete() {
		Observable<String> source = Observable.create(
				(ObservableEmitter<String> emitter) -> {
					emitter.onNext("Hello RxJava");
					emitter.onComplete();
				}
			);
		source.test().assertComplete(); //정상적으로 완료되었는지 확인
	}
}
