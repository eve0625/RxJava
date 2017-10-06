package rxjava.debug;

import common.CommonUtils;
import common.Log;
import common.OkHttpHelper;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class RetryUntilEx {
	
	/**
	 * retryUntil : 특정 조건이 충족될 때까지 재시도 하는 함수
	 */
	
	public void test() {
		CommonUtils.exampleStart();
		
		String url = "https://api.github.com/zen";
		Observable<String> source = Observable.just(url)
				.map(OkHttpHelper::getT)
				.subscribeOn(Schedulers.io())
				.retryUntil(() -> { //결과값이 true가 나올때까지 계속 시도
					if (CommonUtils.isNetworkAvailable())
						return true; //중단
					
					CommonUtils.sleep(1000);
					return false; //재시도
				});
		source.subscribe(Log::i);
		
		CommonUtils.sleep(50000);
	}

	public static void main(String[] args) {
		RetryUntilEx ex = new RetryUntilEx();
		ex.test();
	}

}
