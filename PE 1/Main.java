import cs2030.simulator.Customer;
import cs2030.simulator.Event;
import cs2030.simulator.ArriveEvent;
import cs2030.simulator.Server;
import cs2030.simulator.ComparatorEvents;
import cs2030.simulator.DoneEvent;
import cs2030.simulator.WaitEvent;
import cs2030.simulator.LeaveEvent;
import cs2030.simulator.ServeEvent;
import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.List;

public class Main {

    /**
     * Used to make a queue system
     * Scanner takes in number for number of servers, then doubles as customers
     */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numOfServers = sc.nextInt();

        List<Server> serverList = new ArrayList<>();
        for (int i = 0; i < numOfServers; i++) {
            serverList.add(new Server(i + 1, true, false, 0));
        }

        
        PriorityQueue<Event> pq = new PriorityQueue<>(new ComparatorEvents());
        //int i = 0;
        int custIndex = 0;
        while (sc.hasNextDouble()) {
            double arrivalTime = sc.nextDouble();
            custIndex++;
            Customer addCust = new Customer(custIndex, arrivalTime);
            pq.add(new ArriveEvent(addCust, serverList));
            //i++;
        }
        
 
        double[] doubleArray = {0,0,0};
        while (pq.size() > 0) {
            Event out = pq.poll();
            System.out.println(out);
            Event in = out.execute();
            if (out.fakegetClass().equals("Wait")) {
            	doubleArray[0] = doubleArray[0] + (in.getCurrentTime() - out.getCurrentTime());
            } else if (out.fakegetClass().equals("Leave")){
            	doubleArray[2]++;
            }
            serverList = out.updatedList();

            if (in != null) {
                pq.add(in);
            }
        }
        System.out.println(String.format("[%.3f %.0f %.0f]", doubleArray[0]/(custIndex-doubleArray[2]),custIndex-doubleArray[2], doubleArray[2]));
    }
}