package pub.unt;

import java.util.Scanner;

public class RunnableRxTx implements Runnable {
   
   private Thread t;

   private String workType;
   private DataDeal dataDealClass;
    
   public RunnableRxTx(DataDeal dataRxTx, String threadName) {
      workType = threadName;
      dataDealClass = dataRxTx;
      //System.out.println("Creating " +  threadName);
   }
    
   public void run() {
      //System.out.println("Running " +  workType);
      if(workType.equals("transmit")) {
         Scanner scan = new Scanner(System.in);
         while(true) {
            if (scan.hasNextLine()) {
               String scanStr = scan.nextLine();
               dataDealClass.transmitData(scanStr);
               if(scanStr.equals("$exit")) {
                  scan.close();
                  System.exit(0);
               }
            }
         } 
      }
      if(workType.equals("receive")) {
         while(true) {
            System.out.println(dataDealClass.receiveData());
         }
      }
   }
    
   public void start () {
      //System.out.println("Starting " +  workType);
      if (t == null) {
         t = new Thread(this, workType);
         t.start ();
       }
    }
}