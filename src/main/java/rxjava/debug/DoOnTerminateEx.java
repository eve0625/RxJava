package rxjava.debug;

import common.Log;
import io.reactivex.Observable;

public class DoOnTerminateEx {
	
	/**
	 * doOnTerminate : Observable이 끝나는 조건인 onComplete 혹은 onError 이벤트 발생 직전에 호출되는 함수
	 */
	
	public void test() {
		String[] orgs = {"1", "3", "5"};
		Observable<String> source = Observable.fromArray(orgs);
		
		source.doOnTerminate(() -> Log.d("onTerminate()"))
		.doOnComplete(() -> Log.d("onComplete()"))
		.doOnError(e -> Log.e("onError", e.getMessage()))
		.subscribe(Log::i);
	}

	public static void main(String[] args) {
		DoOnTerminateEx ex = new DoOnTerminateEx();
		ex.test();
	}

}
