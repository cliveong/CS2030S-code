void serveCruises(Cruise[] array){
    int loaderPurchase = 1;
    int[] intArray = new int[270];
    intArray[0] = 0;
    for (int i = 0; i < array.length; i++) { //loop all ships
        int chkloader = array[i].getNumOfLoadersRequired(); //loaders for each ship
        for (int j = 0; j < array[i].getNumOfLoadersRequired(); j++) { //for each loader
            for (int k = 0; k < loaderPurchase; k++) { //check avail in each existing loader
                if (chkloader > 0) {
                    if (intArray[k] <= array[i].getArrivalTime()) {
                        if((k+1)%3 == 0) {
                            intArray[k] = array[i].getServiceCompletionTime()+60;
                            System.out.println( new RecycledLoader(k+1, array[i]));
                        } else {
                            intArray[k] = array[i].getServiceCompletionTime();
                            System.out.println(new Loader(k+1, array[i]));
                        }
                        chkloader--;
                    }
                }
            }
            if (chkloader > 0) {
                loaderPurchase++;
                chkloader--;
                if((loaderPurchase)%3 == 0) {
                    intArray[loaderPurchase-1] = array[i].getServiceCompletionTime() + 60;
                    System.out.println(new RecycledLoader(loaderPurchase, array[i]));
                } else {
                    intArray[loaderPurchase-1] = array[i].getServiceCompletionTime();
                    System.out.println(new Loader(loaderPurchase, array[i]));
                }
            }
        }
    }
}
