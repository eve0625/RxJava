package rxjava.debug;

import java.util.concurrent.TimeUnit;

import common.CommonUtils;
import common.Log;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class DoOnSubscribeEx {

	public void test() {
		String[] orgs = {"1", "3", "5", "2", "6"};
		Observable<String> source = Observable.fromArray(orgs)
				.zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), (a, b) -> a) //계산 스케쥴러에서 실행
				.doOnSubscribe(d -> Log.d("onSubscribe()")) //구독시 이벤트 발생
				.doOnDispose(() -> Log.d("onDispose()")); //구독해지시 이벤트 발생
		Disposable d = source.subscribe(Log::i);
		
		CommonUtils.sleep(200);
		d.dispose();
		CommonUtils.sleep(300);
	}
	
	public static void main(String[] args) {
		DoOnSubscribeEx ex = new DoOnSubscribeEx();
		ex.test();
	}

}
