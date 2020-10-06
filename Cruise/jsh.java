
public class jsh {
	/*static void serveCruises(Cruise[] array){
		int loaderPurchase = 1;
		int[] intArray = new int[270];
		intArray[0] = 0;
		for (int i = 0; i < array.length; i++) { //loop all ships
			int chkloader = array[i].getNumOfLoadersRequired(); //loaders for each ship
			for (int j = 0; j < array[i].getNumOfLoadersRequired(); j++) { //for each loader
				//for (int z = 0; z < loaderPurchase; z++) {
					//System.out.println((z+1)+ "/"+ loaderPurchase + " " + intArray[z] + " " + array[i].getArrivalTime() + " " + array[i].getServiceCompletionTime());
				//}
				for (int k = 0; k < loaderPurchase; k++) { //check avail in each existing loader
					
					if (chkloader > 0) {
						if (intArray[k] <= array[i].getArrivalTime()) {
							
							intArray[k] = array[i].getServiceCompletionTime();
							chkloader--;
							System.out.println(new Loader(k+1, array[i]));
						}
					}
				}
				if (chkloader > 0) {
					loaderPurchase++;
					chkloader--;
					intArray[loaderPurchase-1] = array[i].getServiceCompletionTime();
					System.out.println(new Loader(loaderPurchase, array[i]));
					
				}
			}
		}
	}*/
	static void serveCruises(Cruise[] aarray){
		int loaderPurchase = 1;
		int[] intArray = new int[400];
		intArray[0] = 0;
		for (int i = 0; i < aarray.length; i++) { //loop all ships
			int chkloader = aarray[i].getNumOfLoadersRequired(); //loaders for each ship
			for (int j = 0; j < aarray[i].getNumOfLoadersRequired(); j++) { //for each loader
				/*for (int z = 0; z < loaderPurchase; z++) {
					System.out.println((z+1)+ "/"+ loaderPurchase + " " + intArray[z] + " " + array[i].getArrivalTime() + " " + array[i].getServiceCompletionTime());
				}*/
				for (int k = 0; k < loaderPurchase; k++) { //check avail in each existing loader
					
					if (chkloader > 0) {
						if (intArray[k] <= aarray[i].getArrivalTime()) {
							if((k+1)%3 == 0) {
								intArray[k] = aarray[i].getServiceCompletionTime()+60;
								System.out.println(new RecycledLoader(k+1, aarray[i]));
							} else {
								intArray[k] = aarray[i].getServiceCompletionTime();
								System.out.println(new Loader(k+1, aarray[i]));
							}
							chkloader--;
							
						}
					}
				}
				if (chkloader > 0) {
					loaderPurchase++;
					chkloader--;
					if((loaderPurchase)%3 == 0) {
						intArray[loaderPurchase-1] = aarray[i].getServiceCompletionTime() + 60;
						System.out.println(new RecycledLoader(loaderPurchase, aarray[i]));
					} else {
						intArray[loaderPurchase-1] = aarray[i].getServiceCompletionTime();
						System.out.println(new Loader(loaderPurchase, aarray[i]));
					}
					
				}
			}
		}
	}
	public static void main(String[] args) {
		//Cruise[] cruises = {new BigCruise("B1111", 1300, 80, 3000),new SmallCruise("S1111", 1359), new SmallCruise("S1112", 1400), new SmallCruise("S1113", 1429)};
		//Cruise[] cruises = {new SmallCruise("S1111", 900),new BigCruise("B1112", 901, 100, 1),new BigCruise("B1113", 902, 20, 4500),new SmallCruise("S2030", 1031), new BigCruise("B0001", 1100, 30, 1500),new SmallCruise("S0001", 1130)};
		Cruise[] cruises = {new BigCruise("B1111", 0, 60, 1500),new SmallCruise("S1112", 0), new BigCruise("B1113", 30, 100, 1500),new BigCruise("B1114", 100, 100, 1500),new BigCruise("B1115", 130, 100, 1500),new BigCruise("B1116", 200, 100, 1500)};
		serveCruises(cruises);
	

		
	}
}
 