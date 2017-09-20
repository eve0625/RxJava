package rxjava.operators.create;

import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.Callable;

import common.CommonUtils;
import common.Log;
import common.Shape;
import io.reactivex.Observable;

public class DeferEx {

	/**
	 * defer : timer와 비슷하지만, 데이터 흐름생성을 구독자가 subscribe 함수를 호출할 때까지 미룰수 있음
	 * 구독시 Observable이 생성되며, 최신 데이터를 얻을 수 있음
	 */
	
	Iterator<String> colors = Arrays.asList("1", "3", "5", "6").iterator();
	
	public void test() {
		Callable<Observable<String>> supplier = () -> getObservable();
		//defer 함수를 호출하면 subscribe() 함수를 호출할때의 상황을 반영하여 데이터 흐름의 생성을 지연하는 효과
		//즉 첫번째 구독자가 1번을, 두번째 구독자가 3번을 수신하게 된다.
		//바로 getObservable 함수를 호출한 아래 예제 결과는 첫번째, 두번째 구독자 모두 동일하게 5번을 수신하게 된다.
		Observable<String> source = Observable.defer(supplier);
		source.subscribe(val -> Log.i("Subscriber #1 : " + val));
		source.subscribe(val -> Log.i("Subscriber #2 : " + val));
		
		source = getObservable();
		source.subscribe(val -> Log.i("Subscriber #1 : " + val));
		source.subscribe(val -> Log.i("Subscriber #2 : " + val));
		
		CommonUtils.exampleComplete();
	}
	
	private Observable<String> getObservable() {
		if (colors.hasNext()) {
			String color = colors.next();
			return Observable.just(
					Shape.getString(color, Shape.BALL),
					Shape.getString(color, Shape.RECTANGLE),
					Shape.getString(color, Shape.PENTAGON));
		}
		return Observable.empty();
	}
	
	public static void main(String[] args) {
		DeferEx ex = new DeferEx();
		ex.test();
	}

}
