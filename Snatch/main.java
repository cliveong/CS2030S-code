public class main{
	static Booking findBestBooking( Request request, Driver[] array) {
		if (array.length == 1) {
			return new Booking(array[0], request);
		}
		else {
			Driver theChosenOne = array[0];
			for (int i = 0; i < array.length-1; i++) {
				if ((new Booking(array[i+1], request)).compareTo(new Booking(theChosenOne, request)) < 0){
					theChosenOne = array[i+1];
				}
			}
		return new Booking(theChosenOne, request);
		}
	}
	
	
	
	
	
	
	public static void main(String[] args)  {
		System.out.println("Level 1");
		System.out.println(new Request(20, 3,1000));
		System.out.println(new Request(10, 1, 900));
		System.out.println();
		System.out.println("Level 2");
		System.out.println(new JustRide());
		System.out.println(new JustRide().computeFare(new Request(20, 3, 1000)));
		System.out.println(new JustRide().computeFare(new Request(10, 1, 900)));
		System.out.println(new TakeACab());
		System.out.println(new TakeACab().computeFare(new Request(20, 3, 1000)));
		System.out.println(new TakeACab().computeFare(new Request(10, 1, 900)));
		System.out.println();
		System.out.println("Level 3");
		System.out.println(new NormalCab("SHA1234", 5));
		System.out.println(new Booking(new NormalCab("SHA1234", 5), new Request(20, 3, 1000)));
		System.out.println(new NormalCab("SHA2345", 10));
		System.out.println(new Booking(new NormalCab("SHA2345", 10), new Request(10, 1, 900)));
		Booking b1 = new Booking(new NormalCab("SHA2345", 10), new Request(10, 1, 900));
		Booking b2 = new Booking(new NormalCab("SHA2345", 10), new Request(10, 1, 900));
		System.out.println(b1.compareTo(b2) == 0);
		Booking b3 = new Booking(new NormalCab("SHA1234", 5), new Request(10, 1, 900));
		Booking b4 = new Booking(new NormalCab("SHA2345", 10), new Request(10, 1, 900));
		System.out.println(b3.compareTo(b4) < 0);
		System.out.println();
		System.out.println("Level 4");
		System.out.println(new ShareARide());
		System.out.println(new PrivateCar("SMA7890", 5));
		System.out.println(new Booking(new PrivateCar("SMA7890", 5), new Request(20, 3, 1000)));
		System.out.println(new PrivateCar("SLA5678", 10));
		System.out.println(new Booking(new PrivateCar("SLA5678", 10), new Request(10, 1, 900)));
		Booking b5 = new Booking(new PrivateCar("SMA7890", 5), new Request(10, 1, 900));
		Booking b6 = new Booking(new PrivateCar("SLA5678", 10), new Request(10, 1, 900));
		System.out.println(b5.compareTo(b6) < 0);
		System.out.println();
		System.out.println("Level 5");
		System.out.println(findBestBooking(new Request(20, 3, 1000), new Driver[]{new NormalCab("SHA1234", 5), new PrivateCar("SMA7890", 10)}));
		System.out.println(findBestBooking(new Request(10, 1, 900), new Driver[]{new NormalCab("SHA1234", 5), new PrivateCar("SMA7890", 10)}));
	}
}
