import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import io.reactivex.Maybe;
import io.reactivex.Observable;

public class QuerySales {

	public static void main(String[] args) {
		
		List<Pair<String, Integer>> sales = new ArrayList<>();
		sales.add(Pair.of("TV", 2500));
		sales.add(Pair.of("Camera", 300));
		sales.add(Pair.of("TV", 1600));
		sales.add(Pair.of("Phone", 800));
		
		//매출데이터 중 TV의 매출액 합 구하기
		Maybe<Integer> tvSales = Observable.fromIterable(sales)
				.filter(sale -> "TV".equals(sale.getLeft())) //TV만 걸러내기
				.map(sale -> sale.getRight()) //매출액 가져오기
				.reduce((sale1, sale2) -> sale1 + sale2); //매출 합 구하기
		tvSales.subscribe(total -> System.out.println("TV Sales : " + total));
	}

}
