package rxjava.operators.combine;

import java.util.concurrent.TimeUnit;

import common.CommonUtils;
import common.Log;
import io.reactivex.Observable;
import io.reactivex.functions.Action;

public class ConcatEx {

	/*
	 * concat : 2개 이상의 Obsevable을 이어 붙여주는 함수
	 * 첫번째 Observable에서 onComplete 이벤트가 발생해야, 다음 Observable을 구독함
	 * 첫번째 Observable이 완료되지 않으면 두번째 Observable은 영원히 대기하므로 잠재적인 메모리 누수가 있을 수 있다.
	 */
	
	public void test() {
		//OnComplete 이벤트 발생을 확인하기 위한 Action
		Action onCompleteAction = () -> Log.d("onComplete()");
		
		String[] data1 = {"1", "3", "5"};
		String[] data2 = {"2", "4", "6"};
		
		Observable<String> source1 = Observable.fromArray(data1)
				.doOnComplete(onCompleteAction);
		Observable<String> source2 = Observable.interval(100L, TimeUnit.MILLISECONDS)
				.map(Long::intValue)
				.map(idx -> data2[idx])
				.take(data2.length)
				.doOnComplete(onCompleteAction);
		
		Observable<String> source = Observable.concat(source1, source2)
				.doOnComplete(onCompleteAction);
		source.subscribe(Log::i);
		CommonUtils.sleep(1000);
			
	}
	
	public static void main(String[] args) {
		ConcatEx ex = new ConcatEx();
		ex.test();
	}

}
