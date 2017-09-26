package rxjava.scheduler;

import java.io.File;

import common.CommonUtils;
import common.Log;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class IoEx {

	/**
	 * io : 네트워크상의 요청, 파일 입출력, DB쿼리 등의 각종 입출력 작업을 실행하기 위한 스케쥴러
	 * CPU 개수만큼 스레드를 생성하는 계산스케줄러와 달리, 필요할때마다 스레드를 계속 생성함
	 */
	public void test() {
		String root = "/";
		File[] files = new File(root).listFiles();
		Observable<String> source = Observable.fromArray(files)
				.filter(f -> !f.isDirectory())
				.map(f -> f.getAbsolutePath())
				.subscribeOn(Schedulers.io());
		source.subscribe(Log::i);
		CommonUtils.sleep(1000);
		
	}
	
	public static void main(String[] args) {
		IoEx ex = new IoEx();
		ex.test();
	}

}
