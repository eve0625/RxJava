package rxjava.operators.transform;

import common.Shape;
import io.reactivex.Observable;
import io.reactivex.observables.GroupedObservable;

public class GroupByEx {

	/**
	 * groupBy : 어떤 기준으로 단일 Observable 여러개로 이루어진 Observable 그룹으로 만듬
	 */
	public void test() {
		String[] objs = {"6", "4", "2-T", "2", "6-T", "4-T"};
		Observable<GroupedObservable<String, String>> source =
				Observable.fromArray(objs).groupBy(Shape::getShape); //도형별로 그루핑
		//그룹별로 subscribe 함수를 한번 더 호출해야 함. 데이터는 그룹에 상관없이 동시에 발행됨
		source.subscribe(obj -> {
			obj.subscribe(val ->System.out.println("GROUP:" + obj.getKey() + "\t Value:" + val));
		});
		
		//특정 그룹만 출력하고 싶다면 filter 사용
		source.subscribe(obj -> {
			obj.filter(val -> obj.getKey().equals(Shape.BALL))
					.subscribe(val ->System.out.println("GROUP:" + obj.getKey() + "\t Value:" + val));
		});
	}
	
	public static void main(String[] args) {
		GroupByEx ex = new GroupByEx();
		ex.test();
	}

}
