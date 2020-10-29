/open Logger.java
/open LoggerImpl.java

LoggerImpl<Integer> add(Logger<Integer> a, int b) {
	return a.map(x -> x + b);
}
	
int summing(int n) {
	if (n == 0) {
	    return 0;
	} else {
	    return summing(n - 1) + n;
	}
}
	Logger<Integer> sum(int eyesWideOpen){
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
	
Logger<Integer> f(int feelSpecial){
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