package rxjava.debug;

import common.Log;
import io.reactivex.Observable;

public class DoOnXxxEx {
	
	public void test() {
		Integer[] orgs = {10, 5, 0};
		
		Observable<Integer> source = Observable.fromArray(orgs);
		source
			.map(div -> 1000 / div)
			.doOnNext(data -> Log.d("onNext()", data))
			.doOnComplete(() -> Log.d("onComplete()"))
			.doOnError(e -> Log.e("onError()", e.getMessage()))
			.subscribe(Log::i);
	}

	public static void main(String[] args) {
		DoOnXxxEx ex = new DoOnXxxEx();
		ex.test();
	}

}
