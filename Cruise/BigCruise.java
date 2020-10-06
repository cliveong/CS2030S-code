
class BigCruise extends Cruise{
    BigCruise(String id, int time, int length, int passengers){
        super(id, time, length%40 != 0 ? (length/40)+1 : length/40, passengers%50 != 0 ? (passengers/50)+1 : passengers/50);
    	//super(id, time, (int)Math.ceil((double)length/40),(int)Math.ceil((double)passengers/50));
    }
}
