import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.BiFunction;

class Lazy<T extends Comparable<T>> {
	private final Supplier<? extends T> supp;
	private Optional<T> value = Optional.empty();// ?? how is this used?

	Lazy(Supplier<T> supp) {
		this.supp = supp;
	}

	static <T extends Comparable<T>> Lazy<T> of(Supplier<T> supp) { // takes in supplier that supplies value when needed
		Optional.ofNullable(supp).orElseThrow(() -> new java.util.NoSuchElementException("No value present"));
		return new Lazy<T>(supp);
	}

	static <T extends Comparable<T>> Lazy<T> of(T v) { // wrap it and do not evaluate it?
		return new Lazy<T>(() -> v); // yet to be evaluated
	}

	T get() {
		value = value.or(() -> Optional.of(supp.get())); // if value,return, else evaluate supplier and convert it into
															// optional and make it the value
		return value.orElseThrow();
		// return supp.get(); this means it will always be evaluated
	}

	public <R extends Comparable<R>> Lazy<R> map(Function<? super T, ? extends R> fn) {
		return new Lazy<R>(() -> fn.apply(this.get()));
	}


	<U extends Comparable<U>> Lazy<U> flatMap(Function<? super T, ? extends Lazy<U>> f) {
		return new Lazy<U>(() -> f.apply(supp.get()).get());
	}

	<R extends Comparable<R>, U extends Comparable<U>> Lazy<R> combine(Lazy<U> other,
			BiFunction<? super T, ? super U, ? extends R> combiner) {
		return new Lazy<R>(() -> combiner.apply(this.get(), other.get()));
	}

	Lazy<Boolean> test(Predicate<? super T> testerino) {
		return new Lazy<Boolean>(() -> testerino.test(this.get()));
	}// return null; }

	@Override
	public boolean equals(Object lazyobj) {
		if (lazyobj == this) {
			return true;
		} else if (!(lazyobj instanceof Lazy)) {
			return false;
		} else {
			return this.get().equals(((Lazy<?>) lazyobj).get());
		}
	}

	Lazy<Integer> compareTo(Lazy<T> other) {
		return new Lazy<Integer>(() -> this.get().compareTo(other.get()));
	}

	@Override
	public String toString() { //
		return value.map(v -> v.toString()).orElse("?");
	}
}//
