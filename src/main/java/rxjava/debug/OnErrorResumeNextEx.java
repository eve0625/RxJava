package rxjava.debug;

import common.Log;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class OnErrorResumeNextEx {
	/**
	 * onErrorResumeNext : 에러가 발생했을때 내가 원하는 Observable로 대체
	 * 에러 발생시 데이터를 교체하는 것 뿐만 아니라 관리자에게 이메일을 보낸다던가, 자원을 해제하는 등의
	 * 추가 작업을 해야할 때 유용
	 */
	
	public void test() {
		String[] saleData = {"100", "200", "A300"};
		Observable<Integer> onParseError = Observable.defer(() -> {
			Log.d("send email to administrator");
			return Observable.just(-1);
		}).subscribeOn(Schedulers.io());
		 
		Observable<Integer> source = Observable.fromArray(saleData)
				.map(Integer::parseInt)
				.onErrorResumeNext(onParseError);
		
		source.subscribe(data -> {
			if (data < 0) {
				Log.e("Wrong Data found!!");
				return;
			}
			
			Log.i("Sales data : " + data);
		});
	}

	public static void main(String[] args) {
		OnErrorResumeNextEx ex = new OnErrorResumeNextEx();
		ex.test();
	}

}
