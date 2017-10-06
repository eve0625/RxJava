package rxjava.debug;

import common.Log;
import io.reactivex.Observable;

public class OnErrorReturnEx {
	
	/**
	 * onErrorReturn : 에러 발생 시점에 에러를 의미하는 특정한 값으로 대체
	 */

	public void test() {
		String[] grades = {"70", "88", "$100", "93", "83"};
		
		Observable<Integer> source = Observable.fromArray(grades)
				.map(data -> Integer.parseInt(data))
				.onErrorReturnItem(-1); //Throwable 객체를 전달하지 않으므로 코드가 간결해짐
				/*
				.onErrorReturn(e -> {
					if (e instanceof NumberFormatException) {
						e.printStackTrace();
					}
					return -1;
				});
				*/
		source.subscribe(data -> {
			if (data < 0) {
				Log.e("Wrong Data found!!");
				return;
			}
			Log.i("Grage is " + data);
		});
	}
	
	public static void main(String[] args) {
		OnErrorReturnEx ex = new OnErrorReturnEx();
		ex.test();
	}

}
