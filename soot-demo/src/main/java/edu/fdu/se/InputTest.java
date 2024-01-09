package edu.fdu.se;

import javax.sql.rowset.serial.SerialClob;
import java.util.Map;

public class InputTest {

    public int get(){
        return 5;
    }

    public Object test(int x, int[] y, Integer z, String s,  Map<String, String>[] maps ,Map<String, String> map, SerialClob serialClob) {
        System.out.println("Hello");
        int gg = get();
        System.out.println(gg);
        return null;
    }
}
