package rxjava.operators.combine;

import java.util.concurrent.TimeUnit;

import common.CommonUtils;
import common.Log;
import io.reactivex.Observable;

public class MergeEx {

	/**
	 * merge : 단순 결합 함수. 어느것이든 업스트림에서 먼저 입력되는 데이터를 그대로 발행함
	 */
	public void test() {
		String[] data1 = {"1", "3"};
		String[] data2 = {"2", "4", "6"};
		
		Observable<String> source1 = Observable.interval(0L,  100L, TimeUnit.MILLISECONDS)
				.map(Long::intValue)
				.map(idx -> data1[idx])
				.take(data1.length);
		Observable<String> source2 = Observable.interval(50L, TimeUnit.MILLISECONDS)
				.map(Long::intValue)
				.map(idx -> data2[idx])
				.take(data2.length);
		Observable<String> source = Observable.merge(source1, source2);
		
		source.subscribe(Log::i);
		CommonUtils.sleep(1000);
	}
	
	public static void main(String[] args) {
		MergeEx ex = new MergeEx();
		ex.test();
	}

}
