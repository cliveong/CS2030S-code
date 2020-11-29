import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;
import java.util.stream.Collectors;

class LazyList<T extends Comparable<T>> {
	private List<Lazy<T>> list;
    
	LazyList(List<Lazy<T>> list) {
        this.list = list;
    }

    static <T extends Comparable<T>> LazyList<T> generate(int n, T seed, UnaryOperator<T> f) {
    	Lazy<T> seedling = Lazy.of(seed);
    	//seedling.get();
        return new LazyList<T>(
                Stream.iterate((Lazy<T>) seedling, x -> x.map(f))
                .limit(n)
                .collect(Collectors.toList())
                );
    }

    public T get(int i) {
        return this.list.get(i).get();
    }

    public int indexOf(T v) {
        return this.list.indexOf(Lazy.of(v));
    }
}
/*
 * public class LazyList {
 * 
 * }
 */
