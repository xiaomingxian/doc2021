package z02_tim_hanshubiancheng;

import xxm.springlearn.pojo.Person;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;


public class LambdaBasic {
	/** lambda syntax sugar*/
	private static Function<Integer, String> int2String = (Integer i) -> {return String.valueOf(i);};
	private static Function<Integer, String> int2String_1 = (Integer i) -> String.valueOf(i);//只有一个表达式，可省略大括号
	private static Function<Integer, String> int2String_2 =  (i) -> String.valueOf(i);//参数类型可推导，可省略
	private static Function<Integer, String> int2String_3 =  i -> String.valueOf(i);//类型可推导且只有一个参数，可省略参数括号
	//think it?
	private static Function<Integer, Function<Integer, String>> twoint2String = x -> y -> x + " " + y;
	
	/** functional interface*/
	//private static Runnable run = () -> System.out.println("hello");
	private static Consumer run = System.out::println;//run.accept(xx);
	private static Supplier<Integer> supplier = () -> 1;
	private static Consumer<?> consumer = a -> {};
	private static Predicate<?> predicate = a -> true;
	private static Function<?, Boolean> fn = a ->true;
	//private static Function fn2 =  predicate;//not allowed
	
	//void-compatibility
	private static Consumer<Integer> consumer2 = a -> a++;
	private static Runnable run2 = () -> Integer.getInteger("11");
	
	
	/** method reference*/
	private Function<Integer, String> staticRef = LambdaBasic::int2StringFn;
	private static Supplier<LambdaBasic> constructor = LambdaBasic::new;
	private static Function<String, LambdaBasic> constructor2 = LambdaBasic::new;//type infer   指的是有参构造
	
	private BiFunction<Integer, Integer, String> instanceRef = this::twoint2String;
	private TriFunction<LambdaBasic, Integer, Integer, String> arbitraryRef = LambdaBasic::twoint2String;
	//arbitraryRef(a,b,c) === a.twoint2String(b,c)
	//do(Consumer<String> s)   do(String::toUpperCase)   do(s->s.toUppderCase)


	public LambdaBasic() {}
	public LambdaBasic(String v) {}

	
	public String twoint2String(int x, int y) {
		return x + " " + y;
	}
	
	public static String int2StringFn(Integer i) {
		 return String.valueOf(i);
	}
	
	public String letLBDo(TriFunction<LambdaBasic, Integer, Integer, String> cb, int i1, int i2) { 
		return cb.apply(this, i1, i2);
	}
	
	
	public static void main(String[] args) {


		System.out.println(int2StringFn(10));
		//twoint2String(1);

		LambdaBasic lb = constructor.get();//new LambdaBasic ()
		System.out.println(lb.staticRef.apply(1));//LambdaBasic.int2StringFn(1)
		System.out.println(lb.instanceRef.apply(1,2));
		
		
		System.out.println(lb.letLBDo(LambdaBasic::twoint2String, 2, 3));//this.twoint2String()
		
		
		
	}
	
}
