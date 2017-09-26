package rxjava.scheduler;

import common.CommonUtils;
import common.Log;
import common.OkHttpHelper;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class Example01 {

	private static final String FIRST_URL = "https://api.github.com/zen";
	private static final String SECOND_URL =  "https://raw.githubusercontent.com/yudong80/reactivejava/master/samples/callback_hell";

	public static void main(String[] args) {
		CommonUtils.exampleStart();
		
		//첫번째 요청의 응답을 받은 후, 두번째 요청을 하는 예제
		/*
		Observable<String> source = Observable.just(FIRST_URL)
				.subscribeOn(Schedulers.io())
				.map(OkHttpHelper::get)
				.concatWith(Observable.just(SECOND_URL)
						.map(OkHttpHelper::get));
		source.subscribe(Log::i);
		*/
		
		//두개의 요청을 동시에 수행하고 결과값을 결합하여 보여줌
		Observable<String> first = Observable.just(FIRST_URL)
				.subscribeOn(Schedulers.io())
				.map(OkHttpHelper::get);
		Observable<String> second = Observable.just(SECOND_URL)
				.subscribeOn(Schedulers.io())
				.map(OkHttpHelper::get);
		
		Observable.zip(first, second, (a, b) -> ("\n>>" + a + "\n>>" + b))
			.subscribe(Log::it);
		
		//두개의 요청을 동시에 수행하고 결과만 결
		CommonUtils.sleep(5000);
	}

}
