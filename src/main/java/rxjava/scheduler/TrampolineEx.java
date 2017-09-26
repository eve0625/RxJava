package rxjava.scheduler;

import common.CommonUtils;
import common.Log;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class TrampolineEx {
	
	/**
	 * trampoline :새로운 스레드를 생성하지 않고 현재 스레드에 무한한 크기의 대기행렬을 생성하는 스케줄러
	 * 모든 작업이 main thread에서 실행되며, 큐에 작업을 넣은 후 1개씩 꺼내어 동작하므로 구독자의 실행서가 바뀌지 않는다.
	 */
	public void test() {
		String[] orgs = {"1", "3", "5"};
		Observable<String> source = Observable.fromArray(orgs);
		
		source.subscribeOn(Schedulers.trampoline())
			.map(data -> "<<" + data + ">>")
			.subscribe(Log::i);
		
		source.subscribeOn(Schedulers.trampoline())
			.map(data -> "##" + data + "##")
			.subscribe(Log::i);
		
		CommonUtils.sleep(1000);
	}

	public static void main(String[] args) {
		TrampolineEx ex = new TrampolineEx();
		ex.test();
	}

}
