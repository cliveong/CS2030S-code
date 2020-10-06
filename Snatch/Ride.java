/*interface payment{
    public int computeFare(Request something);
} */

class Ride /*implements payment*/{
    private final int feePerKm;
    private final int bookingFee;
    private final int surcharge;
   
    Ride(int fpKm, int book, int extras){
        this.feePerKm = fpKm;
        this.bookingFee = book;
        this.surcharge = extras;
    }

    public int computeFare(Request something){
        int tentative =  (feePerKm * something.giveDistance()) + bookingFee;
        if (600 <= something.giveTime() && something.giveTime() <= 900){
            tentative = tentative + surcharge;
        }
        return tentative;
    }
    
    public int ShareARidecomputeFare(Request something) {
        int tentative =  (feePerKm * something.giveDistance()) + bookingFee;
        if (600 <= something.giveTime() && something.giveTime() <= 900){
            tentative = tentative + surcharge;
        }
        return tentative/something.giveppl();
    }
        
}






