package rxjava.operators.create;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import common.CommonUtils;
import common.Log;
import io.reactivex.Observable;

public class TimerEx {

	/**
	 * timer : interval과 유사하지만, 일정시간이 지난 후 한개의 데이터 0을 발행하고 onComplete() 이벤트를 발생시킴
	 */
	public void test() {
		
		CommonUtils.exampleStart();
		Observable<String> source = Observable.timer(500L, TimeUnit.MILLISECONDS)
				.map(notUsed -> {
					return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
				});
		source.subscribe(Log::it);
		CommonUtils.sleep(1000);
	}
	
	public static void main(String[] args) {
		TimerEx ex = new TimerEx();
		ex.test();
	}
}
