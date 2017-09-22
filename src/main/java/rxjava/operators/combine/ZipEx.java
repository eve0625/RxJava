package rxjava.operators.combine;

import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.tuple.Pair;

import common.CommonUtils;
import common.Log;
import io.reactivex.Observable;

public class ZipEx {
	
	/**
	 * zip : 각각의 Observable을 모두 활용해 2개, 혹은 그 이상의 Observable을 결합함.
	 * 각각의 Observable에서 모두 데이터를 발행해야 결합할 수 있음.
	 */
	public void test() {
		String[] names = {"문재인", "정우성", "이소라"};
		String[] jobs = {"대통령", "배우", "가수"};
		String[] genders = {"남", "남", "여"};
		
		Observable<String> source = Observable.zip(
				Observable.fromArray(names),
				Observable.fromArray(jobs),
				Observable.fromArray(genders),
				(name, job, gender) -> job + "::" + name + "(" + gender + ")");
		source.subscribe(Log::i);
		
		//숫자 결합
		Integer[] student1 = {100, 80, 20};
		Integer[] student2 = {30, 60, 40};
		Integer[] student3 = {90, 100, 100};
		Observable<Integer> source2 = Observable.zip(
				Observable.fromArray(student1),
				Observable.fromArray(student2),
				Observable.fromArray(student3),
				(s1, s2, s3) -> s1 + s2 + s3);
		source2.subscribe(Log::i);
		
		//시간 결합 (zipInterval 기법)
		Observable<String> source3 = Observable.zip(
				Observable.just("RED", "GREEN", "BLUE"),
				Observable.interval(200L, TimeUnit.MILLISECONDS),
				(value, i) -> value);
		CommonUtils.exampleStart();
		source3.subscribe(Log::it);
		CommonUtils.sleep(1000);
		
		//전기요금 계산
		/**
		 * 200 이하 : 기본요금 910, 전력량 요금 93.3
		 * 201 ~ 400 이하 : 기본요금 1600, 전력량 요금 187.9
		 * 400 초과 : 기본요금 7300, 전력량 요금 280.6
		 */
		Integer[] data = {100, 140, 300};
		
		Observable<Integer> basePrice = Observable.fromArray(data)
				.map(amount -> {
					if (amount <= 200) return 910;
					if (amount <= 400) return 1600;
					return 7300;
				});
		
		Observable<Integer> usagePrice = Observable.fromArray(data)
				.map(amount -> {
					int result = 0;
					if (amount > 400) {
						result += (amount - 400) * 280.6;
						amount = 400;
					}
					if (amount > 200) {
						result += (amount - 200) * 187.9;
						amount = 200;
					}
					result += amount * 93.3;
					
					return result;
				});
		
		Observable<Pair<Integer, Integer>> source4 = Observable.zip(
				basePrice, 
				usagePrice,
				Observable.fromArray(data),
				(v1, v2, i) -> Pair.of(i, v1 + v2));
		
		source4.map(val -> Pair.of(val.getLeft(), new DecimalFormat("#,###").format(val.getRight())))
			.subscribe(val -> {
				StringBuilder sb = new StringBuilder();
				sb.append("Usage : " + val.getLeft() + "kWh => ");
				sb.append("Price : " + val.getRight() +"원");
				Log.i(sb.toString());
			});
		
				
	}

	public static void main(String[] args) {
		ZipEx ex = new ZipEx();
		ex.test();
	}

}
