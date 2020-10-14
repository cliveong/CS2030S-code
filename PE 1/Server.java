package cs2030.simulator;
/**
 * 	private final int id;
	private final boolean avail;
	private final boolean anyoneWaiting;
	private final double whenFree;
 *  
 *  as parameters for the class
 */
public class Server {
	private final int id;
	private final boolean avail;
	private final boolean anyoneWaiting;
	private final double whenFree;


	public Server(int serverid, boolean serveravail, boolean serveranyone, double serverwhenfree) {
		this.id = serverid;
		this.avail = serveravail;
		this.anyoneWaiting = serveranyone;
		this.whenFree = serverwhenfree;
	}

	int giveID() {
		return this.id;
	}

	boolean giveAvail() {
		return this.avail;
	}

	boolean giveWaiting() {
		return this.anyoneWaiting;
	}

	double whenFree() {
		return this.whenFree;
	}

	/**
	 * @return status of server, whether can serve/queue/or cust leaves
	 */
	int giveStatus() {
		if (avail) {
			return 1;
		} else if (!anyoneWaiting) {
			return 2;
		} else {
			return 3;
		}
	}


	@Override
	public String toString() {
		if (giveStatus() == 1) {
			return String.format("%d is available", id);
		} else if (giveStatus() == 2) {
			return String.format("%d is busy; available at %.3f", id, whenFree);			
		} else {
			return String.format("%d is busy; waiting customer to be served at %.3f", id, whenFree);
		}
	}

    public Server updateServer(boolean isAvaliable, boolean hasWaitingCustomer, double newTime) {
        return new Server(this.giveID(), isAvaliable, hasWaitingCustomer, newTime);
    }

}
