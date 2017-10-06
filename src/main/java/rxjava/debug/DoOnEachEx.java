package rxjava.debug;

import common.Log;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class DoOnEachEx {
	
	/**
	 * doOnEach는 onNext, onComplete, onError 이벤트를 각각 처리하는 것이 아니라, 한번에 처리할 수 있어 편리
	 */

	public void test() {
		String[] data = {"ONE", "TWO", "THREE"};
		Observable<String> source = Observable.fromArray(data);
		
		//Notification<T> 객체를 사용
		source.doOnEach(noti -> {
			if (noti.isOnNext()) Log.d("onNext()", noti.getValue());
			if (noti.isOnComplete()) Log.d("onComplete()");
			if (noti.isOnError()) Log.e("onError()", noti.getError().getMessage());
		}).subscribe(System.out::println);
		
		//Observer 인터페이스 사용 - 잘 사용하지 않음
		/*
		source.doOnEach(new Observer<String>() {

			@Override
			public void onSubscribe(Disposable d) {
				//doOnEach에서는 호출되지 않는 함수
			}

			@Override
			public void onNext(String value) {
				Log.d("onNext()", value);
			}

			@Override
			public void onError(Throwable e) {
				Log.e("onError()", e.getMessage());
			}

			@Override
			public void onComplete() {
				Log.d("onComplete");
			}
			
		}).subscribe(Log::i);
		*/
	}
	
	public static void main(String[] args) {
		DoOnEachEx ex = new DoOnEachEx();
		ex.test();
	}

}
