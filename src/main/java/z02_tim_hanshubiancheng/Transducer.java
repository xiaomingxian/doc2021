package z02_tim_hanshubiancheng;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Transducer {
	public static <T,R> R reduce(BiFunction<R, T, R> accumulator,  Collection<T> datas, R initValue){
		R ret  =  initValue;
		for (T data : datas) {
			ret = accumulator.apply(ret, data);
		}
		return ret;
	}
	
	
	public static <T> BiFunction<List, T, List> toListReduceFn() {
		return (acc, curr)->{
			acc.add(curr);
			return acc;
		};
	}
	
	
	public static <ACC,T, R>  BiFunction<ACC, T,ACC> toMapReduceFn(Function<T,R> mapFn, BiFunction<ACC, R, ACC> accumulator){
		return (acc,curr)->{
			return accumulator.apply(acc, mapFn.apply(curr));
		};
	}
	
	public static <ACC,R, T>  Function<BiFunction<ACC, R, ACC>, BiFunction<ACC, T,ACC>> toMapReduceFn(Function<T,R> mapFn){
		return acc -> toMapReduceFn(mapFn, acc);
	}
	
	public static <ACC,T>  BiFunction<ACC, T,ACC> filterReduceFn(Predicate<T> p, BiFunction<ACC, T, ACC> accumulator){
		return (acc,curr)->{
			if (p.test(curr)) {
				return accumulator.apply(acc, curr);
			}else {
				return acc;
			}
		};
	}
	
	public static <ACC,R, T>  Function<BiFunction<ACC, T, ACC>, BiFunction<ACC, T,ACC>> toFilterReduceFn(Predicate<T> p){
		return acc -> filterReduceFn(p, acc);
	}
	
	public static <ACC,T>  Function<BiFunction<ACC, T, ACC>, BiFunction<ACC, T,ACC>> toPeekReduceFn(Consumer<T> mapFn){
		return reduceFn -> {
			return (acc, curr) ->{
				mapFn.accept(curr);
				return reduceFn.apply(acc, curr);
			};
		};
	}
	
	public static <ACC,T,R> BiFunction<List, ?, List> transducerCombine(Function<? extends BiFunction, ? extends BiFunction> ...fns) {

		Function< BiFunction, BiFunction> ret = Function.identity();
		for(Function fn: fns) {
			ret = ret.compose(fn);
		}
		return ret.apply(toListReduceFn());
		
	}
	
	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(2,3,4,7,5);
		
		Function<BiFunction<List,Integer,List>, BiFunction<List,Integer,List>> p =  toFilterReduceFn(i->i%2==0);
		Function<BiFunction<List, Integer, List>, BiFunction<List, Integer, List>> m = toMapReduceFn(i->i*3);
		Function<BiFunction<List, Integer, List>, BiFunction<List, Integer, List>> m2 = toMapReduceFn(i->i-10);

		Function<BiFunction<List,Integer,List>, BiFunction<List,Integer,List>> p2 =  toFilterReduceFn(i->i>10);
		Function<BiFunction<List,Integer,List>, BiFunction<List,Integer,List>> log =  toPeekReduceFn(System.out::print);
		Function<BiFunction<List,Integer,List>, BiFunction<List,Integer,List>> log2 =  toPeekReduceFn(i->System.out.println("!"));

		
		BiFunction reduceFn = transducerCombine(p, log,m , log2,  p2, m2);
		List<Integer> result =  reduce(reduceFn, list, new ArrayList());
		System.out.println(result);
	}
}
