import java.util.function.Function;
import java.util.List;
import java.util.ArrayList;

public class Main {

	public static LoggerImpl<Integer> add(Logger<Integer> a, int b) {
	    return a.map(x -> x + b);
	}
	
	public static int summing(int n) {
	    if (n == 0) {
	        return 0;
	    } else {
	        return summing(n - 1) + n;
	    }
	}
	public static Logger<Integer> sum(int eyesWideOpen){
		List<Integer> newlist = new ArrayList<>();
		for(int i = 1; i <= eyesWideOpen; i++) {
			newlist.add(i);
		}
		Logger<Integer> start = Logger.make(0);
		for(Integer e: newlist) {
			start = add(start, e);
		}
		return start;
	}
	
	int fing(int n) {
		if (n == 1) {
			return 1;
		} else if (n % 2 == 0) {
			return fing(n / 2);
		} else {
		    return fing(3 * n + 1);
		} 
	}
	
	public static Logger<Integer> f(int feelSpecial){
		Logger<Integer> start = Logger.make(feelSpecial);
		List<Integer> newlist = new ArrayList<>();
		int temp = feelSpecial;
		//newlist.add(temp);
		while(temp != 1) {
			if (temp % 2 == 0) {
				temp = temp/2;
				newlist.add(temp);
			} else {
				newlist.add(temp*3);
				temp = 3*temp+1;
				newlist.add(temp);
			}
		}
		//newlist.add(1);
		
		for(Integer e: newlist) {
			start = start.map(x -> e);
		}
		return start;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * System.out.println("Level 3"); System.out.println(Logger.make(5));
		 * Logger.make(5).printlog(); System.out.println(Logger.make(5).map(x -> x +
		 * 1)); Logger.make(5).map(x -> x + 1).printlog(); Logger<Integer> a =
		 * Logger.make(5); a.printlog(); System.out.println(a.map(x -> x + 1));
		 * a.printlog(); System.out.println(Logger.make(5).map(x -> x));
		 * Logger.make(5).map(x -> x).printlog();
		 * 
		 * System.out.println(Logger.make(5).equals(Logger.make(5).map(x -> x)));
		 * System.out.println(Logger.make(5).map(x -> x).equals(Logger.make(5)));
		 * System.out.println(Logger.make(5).map(x -> x + 1).map(x -> x - 1));
		 * System.out.println(Logger.make(5).map(x -> x).map(x -> x));
		 * System.out.println(Logger.make(5)); Function<Object, Boolean> f = x ->
		 * x.equals(x); System.out.println(Logger.make("hello").map(f));
		 * Function<String, Number> g = x -> x.length(); Logger<Number> lognum =
		 * Logger.make("hello").map(g); System.out.println(lognum);
		 * Logger.make("hello").map(String::length).printlog(); System.out.println();
		 */
		/*
		 * System.out.println("Level 4"); //System.out.println(Logger.make(5).make(7));
		 * System.out.println(Logger.make(5).flatMap(x -> Logger.make(x)));
		 * Logger.make(5).flatMap(x -> Logger.make(x)).printlog();
		 * System.out.println(Logger.make(5).flatMap(x ->
		 * Logger.make(x)).equals(Logger.make(5))); Logger<Integer> a =
		 * Logger.make(5).flatMap(x -> Logger.make(x).map(y -> y + 2)).flatMap(y ->
		 * Logger.make(y).map(z -> z * 10)); Logger<Integer> b =
		 * Logger.make(5).flatMap(x -> Logger.make(x).map(y -> y + 2).flatMap(y ->
		 * Logger.make(y).map(z -> z * 10))); a.printlog(); b.printlog();
		 * Logger<Integer> c = Logger.make(5).map(x -> x + 2).map(x -> x * 10);
		 * System.out.println(a.equals(b)); System.out.println(a.equals(c));
		 * Function<Object, Logger<Boolean>> f = x -> Logger.make(x).map(y ->
		 * y.equals(y)); System.out.println(Logger.make("hello").flatMap(f));
		 * Function<String, Logger<Number>> g = x -> Logger.make(x).map(y ->
		 * y.length()); Logger<Number> lognum = Logger.make("hello").flatMap(g);
		 * System.out.println(lognum);
		 */
		

		/*
		 * add(Logger.make(5), 6).printlog(); add(Logger.make(5).map(x -> x * 2),
		 * 6).printlog(); System.out.println(sum(0)); sum(0).printlog();
		 * System.out.println(sum(5)); sum(5).printlog();
		 */
		System.out.println(Logger.make(5).test(x -> x == 5));
		System.out.println(Logger.make(5).map(x -> x + 1).test(x -> x % 2 != 0));
		System.out.println(Logger.make("hello").test(x -> x.length() == 5));
		System.out.println(f(16));
		f(16).printlog();
		System.out.println(f(10));
		f(10).printlog();
		System.out.println();
		System.out.println();
		System.out.println();
		
		
	}

}
