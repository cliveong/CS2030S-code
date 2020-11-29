import java.util.List;
import java.util.ArrayList;
import java.util.function.Function;
import java.util.Optional;

class Room{
    private final List<Items> listing;
    private final String name;
    private final Optional<Room> previous;

    Room(String name){
        this.name  = name;
        this.listing = new ArrayList<Items>();
        this.previous = Optional.empty();
    }
    
	
	Room(String name, List<Items> newlist){ 
		this.listing = newlist; this.name = name; 
		this.previous = Optional.empty(); }
	 

    Room(String name, List<Items> newlist, Optional<Room> previous){
        this.listing = newlist;
        this.name = name;
        this.previous = previous;
    }
    
    Room add(Items item){
        List<Items> temp = new ArrayList<>(listing);
        temp.add(item);
        return new Room(this.name, temp, this.previous);
    }
    
    Optional<Room> getPrevious() {
    	return this.previous;
    }
    

    Room tick(){
        List<Items> temp = new ArrayList<>(listing);
        for(int i = 0; i < this.listing.size(); i++){
            if (listing.get(i) instanceof Candle){
                temp.set(i, new Candle(listing.get(i).getMax(), listing.get(i).getCurrent()+1));
            } else if(listing.get(i) instanceof Sword){
                temp.set(i, new Sword(listing.get(i).getMax(), listing.get(i).getCurrent(), ((Sword) listing.get(i)).getTaken()));
            } else if (listing.get(i) instanceof Troll){
                temp.set(i, new Troll(listing.get(i).getMax(), listing.get(i).getCurrent()+ 1));
            }
        }
        return new Room(this.name, temp, this.previous);
    }

	Room tick(Function<Room, Room> fn){
		Room temp = fn.apply(this); 
		return temp.tick(); 
	  }
	 
   
    
    List<Items> getList(){
    	return this.listing;
    }
    
    String getName() {
    	return this.name;
    }
    
    
    Room go(Function<List<Items>, Room> fn) {
    	Room test = fn.apply(this.listing);
    	List<Items> temp = new ArrayList<>(test.getList());
    	List<Items> temptwo = new ArrayList<>(listing);
    	for(int i = 0; i < this.listing.size(); i++) {
    		if (this.listing.get(i) instanceof Sword && ((Sword) this.listing.get(i)).getTaken()){
    			temp.add(0, new Sword(0,0,true));
    			temptwo.remove(i);
    			return new Room(test.getName(), temp, Optional.of(new Room (this.getName(), temptwo, this.getPrevious())));
    		}
    	}
    	return new Room(test.getName(), temp, Optional.of(this));
    }
    
    Room back() {

    	List<Items> temptwo = new ArrayList<>(listing);
    	if (!(this.getPrevious().isEmpty())){ //if not empty
        	List<Items> temp = new ArrayList<>(this.getPrevious().get().getList());
    		for (int k = 0; k < listing.size(); k ++) {
    			if (this.listing.get(k) instanceof Sword && ((Sword)this.listing.get(k)).getTaken()) {
    				temp.add(new Sword(0,0,true));
    				temptwo.remove(k);
    				return new Room(this.getPrevious().get().getName(), temp, this.getPrevious().get().getPrevious()).tick();
    			}
    		}
    		return this.getPrevious().get().tick();
    	}
    	return this;
    }
    

    @Override
    public String toString(){
    	String toprint = "@" + name;
        for(int i = 0; i < this.listing.size(); i++){
        	toprint = toprint + "\n" + this.listing.get(i).getStatus();
        }
        return toprint;
    }
}
