package rxjava.operators.math;

import common.Log;
import hu.akarnokd.rxjava2.math.MathFlowable;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;

public class MathEx {
	
	public void test() {
		Integer[] data = {1, 2, 3, 4};
		
		//count
		Single<Long> source = Observable.fromArray(data).count();
		source.subscribe(count -> Log.i("count is " + count));
		
		//max & min
		Flowable.fromArray(data).to(MathFlowable::max).subscribe(max -> Log.i("max is " + max));
		Flowable.fromArray(data).to(MathFlowable::min).subscribe(min -> Log.i("min is " + min));
		
		//sum & average
		Flowable.fromArray(data).to(MathFlowable::sumInt).subscribe(sum -> Log.i("sum is " + sum));
		Observable.fromArray(data).toFlowable(BackpressureStrategy.BUFFER).to(MathFlowable::averageDouble)
			.subscribe(avg -> Log.i("average is " + avg));

	}

	public static void main(String[] args) {
		MathEx ex = new MathEx();
		ex.test();
	}

}
