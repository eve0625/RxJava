package rxjava.operators.create;

import common.Log;
import io.reactivex.Observable;

public class RepearEx {

	/**
	 * repeat : 단순한 반복실행
	 */
	public void test() {
		String[] balls = {"1", "3", "5"};
		Observable<String> source = Observable.fromArray(balls).repeat(3); //3번 반복, 인자가 없을 경우 무한 반복
		source.doOnComplete(() -> Log.d("onComplete")).subscribe(Log::i);	
	}
	
	public static void main(String[] args) {
		RepearEx ex = new RepearEx();
		ex.test();
	}

}
