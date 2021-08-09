package z02_tim_hanshubiancheng;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;


public class ReduceDemo {
	
	public static <T,R> R reduce(BiFunction<R, T, R> accumulator,  Collection<T> datas, R initValue){
		R ret  =  initValue;
		for (T data : datas) {
			ret = accumulator.apply(ret, data);
		}
		return ret;
	}
	
	public static int sum(Collection<Integer> list) {
		return list.stream().reduce(0, (acc, curr) -> acc + curr);
	}
	
	public static Integer max(Collection<Integer> list) {
		return list.stream().reduce(Math::max).orElse(null);
	}	
	
	public static String join(Collection<Integer> list, String delimiter) {
		return list.stream().map(String::valueOf).reduce( (acc, curr)-> String.join(delimiter, acc, curr)).orElse("");
	}
	
	public static <T> List<T> toList(Stream<T> stream) {
		List<T> ret = new ArrayList<>();
		
		return stream.reduce(ret, (acc, curr )->{ acc.add(curr); return acc; }, (list1, list2) -> {list1.addAll(list2); return list1;} );
	}
	//haskell
	public static <T,R> List<R> map(List<T> list, Function<T,R> mapFn) {//mapFn.apply() == mapFn()
		List<R> ret = new ArrayList<>();
		return list.stream().reduce(ret, (acc, curr)->{
											R newValue = mapFn.apply(curr);//mapFn(curr)
											acc.add(newValue); //list.add(mapFn(curr))//map.put()
											return acc; 
										},
				(list1, list2) -> {list1.addAll(list2); return list1;} );

	}

	public static <T, R> List<T> filter(List<T> list, Predicate<T> p) {
		List<T> ret = new ArrayList<>();
		return list.stream().reduce(ret, (acc, curr)->{
											if (p.test(curr)) {
												acc.add(curr); 
											}
											
											return acc; 
										},
				(list1, list2) -> {list1.addAll(list2); return list1;} );
	}
	
	
	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(2,3,4,7,5);
		System.out.println(sum(list));
		
		List<Integer> list1 = filter(list, i->i>3);
		List<Integer> list2 = map(list1, i->i*2);
		System.out.println(list2);
		
	}
	
}
