import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.ArrayList;

class LoggerImpl<T> implements Logger<T>{
    private final T value;
    private final List<String> sendhalp;

    LoggerImpl(T theItemToMake){
        this.value = theItemToMake;
        this.sendhalp = new ArrayList<String>();
        sendhalp.add(String.format("Value initialized. Value = %s", theItemToMake.toString()));
    }
    
    LoggerImpl(T theItemToMake, List<String> newList ){
    	this.value = theItemToMake;
    	this.sendhalp = newList;
    }
    
    public T getValue() {
    	return value;
    }
    
    public List<String> gethalp(){
    	return sendhalp;
    }

    public String toString(){
        return String.format("Logger[%s]",value.toString());
    }

    public void printlog(){
        //String result = "";
        for(String temp: sendhalp){
        	System.out.println(temp);
			/*
			 * result = result + temp; result = result + "\n"; if
			 * (temp.equals(sendhalp.get(sendhalp.size()-1))){ result = result.substring(0,
			 * result.length()-1);
			 */
            //}
        }
        //System.out.println(result);
    }
    
    public <U> LoggerImpl<U> map(Function<? super T, ? extends U> func){
        U result = func.apply(value);
        if (this.value.equals(result)) {
        	String k = "Value unchanged. Value = " + result.toString();
        	List<String> newList = new ArrayList<>(sendhalp);
        	newList.add(k); 
        	return new LoggerImpl<U>(result, newList);
        } else {
        	String k = "Value changed! New value = " + result.toString();
        	List<String> newList = new ArrayList<>(sendhalp);
        	newList.add(k);
        	return new LoggerImpl<U>(result, newList);
        }
        //return null;//new LoggerImpl<T>(this, result);
    }
    
    public <U> LoggerImpl<U> flatMap(Function<? super T, ? extends Logger<? extends U>> func){
    	List<String> newlist = new ArrayList<>(sendhalp);
    	Logger<? extends U> newthing = func.apply(value);
    	List<String> newerlist = new ArrayList<>(newthing.gethalp());
    	newlist.addAll(newerlist);
    	newlist.remove(this.sendhalp.size());
    	//newlist.remove(newlist.size()-1);
    	return new LoggerImpl<U>(newthing.getValue(), newlist);
    	
    	
    	//return null;
    }
    
    public boolean test(Predicate<? super T> tester) {
        return tester.test(value);
    }
    
    @Override
    public boolean equals(Object something){
    	if (something == this) {
    		return true;
    	} else if(!(something instanceof Logger)){
        	//System.out.println(something.getClass() + "dasdasdsa");
        	//System.out.println(this.value.getClass() + "dasdasdsa");
            return false;
        } else {
        	//System.out.println(this.toString() + "AsD");
        	//System.out.println(something.toString());
            return (((LoggerImpl<?>) something).gethalp()).equals(this.gethalp());
        }
    }

	
}
