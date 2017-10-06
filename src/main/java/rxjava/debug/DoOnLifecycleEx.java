package rxjava.debug;

import java.util.concurrent.TimeUnit;

import common.CommonUtils;
import common.Log;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class DoOnLifecycleEx {
	
	public void test() {
		String[] orgs = {"1", "3", "5", "2", "6"};
		Observable<String> source = Observable.fromArray(orgs)
				.zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), (a, b) -> a)
				.doOnLifecycle(
						d -> Log.d("onSubscribe()"),
						() -> Log.d("onDispose()"));
		Disposable d = source.subscribe(Log::i);
		
		CommonUtils.sleep(200);
		d.dispose();
		CommonUtils.sleep(300);
	}

	public static void main(String[] args) {
		DoOnLifecycleEx ex = new DoOnLifecycleEx();
		ex.test();
	}

}
