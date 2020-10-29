import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

interface Logger<T>{
    public static <T> Logger<T> make(T t){
        if (t instanceof Logger){
            throw new IllegalArgumentException("already a Logger");
        } else if ( t == null){
            throw new IllegalArgumentException("argument cannot be null");
        }
        return new LoggerImpl<T>(t);
    }
    
    public void printlog();
    
    public <U> LoggerImpl<U> map(Function<? super T, ? extends U> func);
    
    public <U> LoggerImpl<U> flatMap(Function<? super T, ? extends Logger<? extends U>> func);
    
    public T getValue();
    
    public List<String> gethalp();
    
    public boolean test(Predicate<? super T> tester);
    //public boolean equals(Object something);
}
