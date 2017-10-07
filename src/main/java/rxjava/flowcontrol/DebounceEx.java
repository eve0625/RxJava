package rxjava.flowcontrol;

import java.util.concurrent.TimeUnit;

import common.CommonUtils;
import common.Log;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;

public class DebounceEx {
	
	/**
	 * debounce : 어떤 이벤트가 입력되고 timeout에서 지정한 시간동안 추가 이벤트가 발생하지 않으면 마지막 이벤트를 최종적으로 발행
	 * 버튼의 여러번 클릭등의 처리 등의 UI 처리에 적합
	 */
	
	public void test() {
		String[] data = {"1", "2", "3", "5"};
		
		Observable<String> source = Observable.concat(
				Observable.timer(100L, TimeUnit.MILLISECONDS).map(i -> data[0]),
				Observable.timer(300L, TimeUnit.MILLISECONDS).map(i -> data[1]),
				Observable.timer(100L, TimeUnit.MILLISECONDS).map(i -> data[2]),
				Observable.timer(300L, TimeUnit.MILLISECONDS).map(i -> data[3])
				).debounce(200L, TimeUnit.MILLISECONDS);
		
		source.subscribe(Log::i);
		CommonUtils.sleep(1000);
	}

	public static void main(String[] args) {
		DebounceEx ex = new DebounceEx();
		ex.test();
	}

}
