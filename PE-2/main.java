import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class main {
	
	
	static Function<Room, Room> takeSword = x -> { 
		List<Items> temp = new ArrayList<>(x.getList());
		for(int i = 0; i < temp.size(); i++) {

			if (temp.get(i).getName().equals("Sword")) {

				if ( ((Sword) temp.get(i)).getTaken()) {
					System.out.println("--> You already have sword."); 
					}

				else if (((Sword) temp.get(i)).getTaken() == false) { 
					temp.set(i, new Sword(0,0,true));
					System.out.println("--> You have taken sword."); 
					}
				return new Room(x.getName(), temp, x.getPrevious());
				} 
			
			}
		System.out.println("--> There is no sword."); 
		return x;
	};
	
	static Function<Room, Room> killTroll = x -> {
		List<Items> temp = new ArrayList<>(x.getList());
		for(int i = 0; i < temp.size(); i++) {
			if (temp.get(i).getName().equals("Troll")) {
				for(int j = 0; j < temp.size(); j++) {
					if (temp.get(j).getName().equals("Sword") && ((Sword) temp.get(j)).getTaken()) {
						System.out.println("--> Troll is killed.");
						temp.remove(i);
						
						return new Room(x.getName(), temp, x.getPrevious());
					}
				}
				System.out.println("--> You have no sword.");
				return x;
			}
		}
		System.out.println("--> There is no troll");
		return x;
	};
	
	static Function<Room, Room> dropSword = x -> { 
		List<Items> temp = new ArrayList<>(x.getList());
		for(int i = 0; i < temp.size(); i++) {

			if (temp.get(i).getName().equals("Sword") && ((Sword) temp.get(i)).getTaken()) {
					temp.set(i, new Sword(0,0, false));
					System.out.println("--> You have dropped sword."); 			
				} 
			}
		return new Room(x.getName(), temp, x.getPrevious());
	};



	public static void main(String[] args) {

		/*
		 * Room i = new Room("a").go(x -> new Room("b").add(new
		 * Sword()).tick(takeSword)).go(x -> new Room("c").add(new Troll())).go(x -> new
		 * Room("d")); Room j = i.back(); Room k = j.back(); Room l = k.back();
		 * System.out.println(i); System.out.println(j); System.out.println(k);
		 * System.out.println(l);
		 */
		Room q = new Room("q"); 
		Room r = q.back().add(new Candle());
		Room s = q.add(new Candle()).go(x -> new Room("s")).back();
		Room t = s.back();
		
		//System.out.println(new Room("foyer").add(new Candle()).add(new Sword()).tick(x -> x));
		//System.out.println(new Room("foyer").add(new Candle()).add(new Sword()).tick(x -> x).tick(x -> x));
		System.out.println(q);
		System.out.println(r);
		System.out.println(s);
		System.out.println(t);
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		
		// TODO Auto-generated method stub

	}

}
