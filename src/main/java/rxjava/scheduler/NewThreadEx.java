package rxjava.scheduler;

import common.CommonUtils;
import common.Log;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class NewThreadEx {

	/**
	 * newThread : 새로운 스레드를 만들어 어떤 동작을 실행하고 싶을때 사
	 */
	
	public void test() {
		String[] orgs = {"1", "3", "5"};
		
		Observable.fromArray(orgs)
			.doOnNext(data -> Log.v("Original data : " + data))
			.map(data -> "<<" + data + ">>")
			.subscribeOn(Schedulers.newThread())
			.subscribe(Log::i);
		
		//CommonUtils.sleep(500);
		
		Observable.fromArray(orgs)
		.doOnNext(data -> Log.v("Original data : " + data))
		.map(data -> "##" + data + "##")
		.subscribeOn(Schedulers.newThread())
		.subscribe(Log::i);
	
		CommonUtils.sleep(500);
		
	}
	
	public static void main(String[] args) {
		NewThreadEx ex = new NewThreadEx();
		ex.test();
	}

}
