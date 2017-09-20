
import java.util.concurrent.TimeUnit;

import common.CommonUtils;
import common.Log;
import common.OkHttpHelper;
import io.reactivex.Observable;

public class HeartBeat {

	public static void main(String[] args) {
		CommonUtils.exampleStart();
		String serverUrl = "https://api.github.com/zen";
		
		Observable.timer(2, TimeUnit.SECONDS) //2초후 데이터 발행 (timer는 한번만 데이터를 발행함)
			.map(val -> serverUrl) //0l의 값을 serverUrl로 변경
			.map(OkHttpHelper::get) //serverUrl로 get 메서드 실행
			.repeat() //동작이 한번 끝난 다음에 다시 구독하는 방식으로 동작하여, 메번 스레드의 번호가 달라짐
			.subscribe(Log::i);
		
		CommonUtils.sleep(10000);
	}
}
