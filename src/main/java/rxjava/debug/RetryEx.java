package rxjava.debug;

import common.CommonUtils;
import common.Log;
import common.OkHttpHelper;
import io.reactivex.Observable;

public class RetryEx {
	
	public void test() {
		CommonUtils.exampleStart();
		
		String url = "https://api.github.com/zen";
		/*
		Observable<String> source = Observable.just(url)
				.map(OkHttpHelper::getT)
				.retry(5) //에러발생시 5회까지 재시도
				.onErrorReturn(e -> CommonUtils.ERROR_CODE);
		*/
		
		//재시도 시 일정한 대기시간을 주고 재시도 하도록 함
		final int RETRY_MAX = 5;
		final int RETRY_DELAY = 1000;
		Observable<String> source = Observable.just(url)
				.map(OkHttpHelper::getT)
				.retry((retryCnt, e) -> {
					Log.e("retryCnt = " + retryCnt);
					CommonUtils.sleep(RETRY_DELAY);
					
					return retryCnt < RETRY_MAX ? true : false;
				})
				.onErrorReturn(e -> CommonUtils.ERROR_CODE);
		
		source.subscribe(data -> Log.it("result : " + data));
	}

	public static void main(String[] args) {
		RetryEx ex = new RetryEx();
		ex.test();
	}

}
