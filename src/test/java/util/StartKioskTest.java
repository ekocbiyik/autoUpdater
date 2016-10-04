package util;

import net.egemsoft.updater.util.StartKiosk;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by enbiya on 26.07.2016.
 */
public class StartKioskTest {


    private String kioskPath = "C:\\Program Files (x86)\\Superonline\\Superonline Kiosk\\";// kiosk.exe dizini

    @Test
    public void run(){

        Assert.assertTrue(new StartKiosk().execute(kioskPath));


    }


}
