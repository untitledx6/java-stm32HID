package pub.unt;

import pub.unt.jnaMethod.*;

public class App  
{
    public static void main( String[] args )
    {
        HidDeviceStructure device = HidApi.open(1154, 22355, null);
        DataDeal dataRxTx = new DataDeal(device);
        RunnableRxTx trans = new RunnableRxTx( dataRxTx, "transmit");
        trans.start();
        RunnableRxTx rece = new RunnableRxTx(dataRxTx,  "receive");
        rece.start();
        while(true);
    }
}
