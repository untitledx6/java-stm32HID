package pub.unt;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

import pub.unt.jnaMethod.*;

public class DataDeal {
    HidDeviceStructure device;
    
	public DataDeal(HidDeviceStructure dev) {
        device = dev;
    }

    public String receiveData() {
        byte[] buffer = new byte[100];
        HidApi.read(device, buffer, -1);
        char[] charData = getChars (buffer);
        return String.valueOf(charData);
    }

    private  char[] getChars (byte[] bytes) {
        Charset cs = Charset.forName ("UTF-8");
        ByteBuffer bb = ByteBuffer.allocate (bytes.length);
        bb.put (bytes);
                   bb.flip ();
         CharBuffer cb = cs.decode (bb);
    
     return cb.array();
  }

    public void transmitData(String str) {
        char [] strBuffer = str.toCharArray();
        byte[] data = new byte[2];
        if(strBuffer.length == 0) return;
        for(int i = 0; i < strBuffer.length / 2; i++) {
            data[0] = (byte)strBuffer[i * 2];
            data[1] = (byte)strBuffer[i * 2 + 1];
            HidApi.write(device, data, 2, (byte)0);
        }   
        if(strBuffer.length % 2 != 0) {
            data[0] = (byte)strBuffer[strBuffer.length - 1];
            data[1] = 0;
            HidApi.write(device, data, 2, (byte)0);
            return;
        } else {
            data[0] = 0; data[1] = 0;
            HidApi.write(device, data, 2, (byte)0);
            return;
        }

    }
}