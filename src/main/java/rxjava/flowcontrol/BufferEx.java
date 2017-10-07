package rxjava.flowcontrol;

import java.util.List;
import java.util.concurrent.TimeUnit;

import common.CommonUtils;
import common.Log;
import io.reactivex.Observable;

public class BufferEx {

	/**
	 * buffer : 일정 시간동안 or 일정 갯수만큼 데이터를 모아두었다가 한꺼번에 발행해 줌
	 */
	public void test() {
		String[] data = {"1", "2", "3", "4", "5", "6"};
		CommonUtils.exampleStart();
		
		Observable<String> earlySource = Observable.fromArray(data)
				.take(3)
				.zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), (a, b) -> a);
		
		Observable<String> middleSource = Observable.just(data[3])
				.zipWith(Observable.timer(300L, TimeUnit.MILLISECONDS), (a, b) -> a);
		
		Observable<String> lateSource = Observable.just(data[4], data[5])
				.zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), (a, b) -> a);
		
		Observable<List<String>> source = Observable.concat(earlySource, middleSource, lateSource)
				//.buffer(3); //데이터를 3개씩 모았다가 발행
				.buffer(2, 3); //2개의 데이터를 모으고 1개는 skip (두번째 파라미터가 더 커야함)
		source.subscribe(Log::it);
		
		CommonUtils.sleep(1000);
	}
	
	public static void main(String[] args) {
		BufferEx ex = new BufferEx();
		ex.test();
	}

}
