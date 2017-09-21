package rxjava.operators.transform;

import java.util.concurrent.TimeUnit;

import common.CommonUtils;
import common.Log;
import io.reactivex.Observable;

public class concatMapEx {

	/**
	 * concatMap : flatMap과 유사하나, 인터리빙이 발생할 수 있는 상황에서 데이터 처리 순서가 보장됨
	 * flatMap은 먼저 들어온 데이터를 처리하는 중에 나중에 들어온 데이터의 처리결과가 먼저 출력될 수도 있음
	 */
	public void test() {
		CommonUtils.exampleStart();
		
		String[] balls = {"1", "3", "5"};
		Observable<String> source = Observable.interval(100L, TimeUnit.MILLISECONDS)
				.map(Long::intValue) //0부터 발생하는 long형 값을 int로 변환
				.map(idx -> balls[idx]) //배열에서 해당 인덱스 값으로 값 변환
				.take(balls.length) //배열 크기만큼 반복하고 종료
				.flatMap(ball -> Observable.interval(200L, TimeUnit.MILLISECONDS)
						.map(notUsed -> ball + "♢")
						.take(2)
				);
		//----------------
/*
 * 0ms에 1, 100ms에 3, 200ms에 5 발행. 200ms후인 400ms 즈음부터 다이아몬드가 처리?
RxComputationThreadPool-2 | 464 | value = 1♢
RxComputationThreadPool-2 | 663 | value = 1♢
RxComputationThreadPool-3 | 867 | value = 3♢
RxComputationThreadPool-3 | 1064 | value = 3♢
RxComputationThreadPool-4 | 1265 | value = 5♢
RxComputationThreadPool-4 | 1467 | value = 5♢
		 */
		
		
		//concatMap을 flatMap으로 바꾸어 테스트 해보기
/*
 * flatMap은 인터리빙을 허용하므로 각자 설정된 시간에 처리
 * 100ms -> 1, 200ms -> 3, 300ms -> 5
 * 500ms -> 1♢, 700ms -> 1♢
 * 600ms -> 3♢, 800ms -> 3♢
 * 700ms -> 5♢, 900ms -> 5♢ ??
RxComputationThreadPool-2 | 533 | value = 1♢
RxComputationThreadPool-3 | 629 | value = 3♢
RxComputationThreadPool-4 | 728 | value = 5♢
RxComputationThreadPool-2 | 729 | value = 1♢
RxComputationThreadPool-3 | 832 | value = 3♢
RxComputationThreadPool-4 | 929 | value = 5♢		
 */
		source.subscribe(Log::it);
		
		CommonUtils.sleep(2000);
	}
	
	public static void main(String[] args) {
		concatMapEx ex = new concatMapEx();
		ex.test();
	}

}
