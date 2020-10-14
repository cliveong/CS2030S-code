import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class ImmutableList<T> {
    private final List<T> list;
    
    ImmutableList(List<T> list){
    	List<T> array = new ArrayList<T>(list); 
    	this.list = array;
    }
    
    @SafeVarargs
    ImmutableList(T ...args){
    	this.list = new ArrayList<T>(Arrays.asList(args));
    }
    
    ImmutableList<T> add(T something){
    	List<T> templist = new ArrayList<T>(list);
    	templist.add(something);
    	return new ImmutableList<T>(templist);
    }
    
    ImmutableList<T> remove(T something){
    	List<T> templist = new ArrayList<T>(list);
    	templist.remove(something);
    	return new ImmutableList<T>(templist);
    }
    
    ImmutableList<T> replace(T out, T in){
    	List<T> templist = new ArrayList<T>(list);
    	for(int i = 0; i < list.size(); i++) {
    		if (list.get(i) == out) {
    			templist.set(i, in);
    		}
    	}
    	return new ImmutableList<T>(templist);
    }
    
    ImmutableList<T> limit(long four){ //haha
    	List<T> templist = new ArrayList<T>();
    	if (four<0) {
    		throw new IllegalArgumentException ("limit size < 0");
    	} else {
    		for (int i = 0; i < four; i++) {
    			if (i == list.size()) {
    				break;     
    			}
    			templist.add(list.get(i));
    		}
    		return new ImmutableList<T>(templist);
    	}
    }
    
    @Override
    public boolean equals(Object object){
    	if (object instanceof ImmutableList == false) {
    		return false;
    	} else {
    		if ( ((ImmutableList<?>) object).list.equals(list)) {
    			return true;
    		}
    		return false;
    	}

    }
    
    ImmutableList<T> sorted(Comparator<T> comparator){
    	if (comparator == null) {
    		throw new NullPointerException("Comparator is null");
    	} else {
    		List<T> templist = new ArrayList<T>(list);
    		templist.sort(comparator);
    		return new ImmutableList<T>(templist);
    	}
    }
    
    @Override
    public String toString() {
    	String k = "[";
    	
    	for(T temp: list) {
    		k = k + temp + ", ";
    	}
    	
    	if (k.length() > 1) {
    		k = k.substring(0, k.length() - 2);
    	}
    	k = k + "]";
    	return k;
    }
    
    public Object[] toArray() {
    	List<Object> templist = new ArrayList<Object>(list);
    	Object[] temp = templist.toArray();
    	return temp;
    }
    
    public <A> A[] toArray(A[] a) {
        if (a == null) {
            throw new NullPointerException("Input array cannot be null");
        }
        try {
        	List<T> templist = new ArrayList<T>(list);
            return templist.toArray(a);
        } catch (Exception e) {
            throw new ArrayStoreException("Cannot add element to array as it is the wrong type");
        }
    }
}
