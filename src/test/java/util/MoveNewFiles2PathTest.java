package util;

import net.egemsoft.updater.util.MoveNewFiles2Path;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by enbiya on 26.07.2016.
 */
public class MoveNewFiles2PathTest {



    private String tempFile = "C:\\Users\\" + System.getProperty("user.name") + "\\.superonline\\tepmFile\\";//ilk indirilecek yol
    private String jarFileName = "kiosk-client-ui-sol-1.0.0-SNAPSHOT-executable-win32_x86.jar";// dosyanın ismi
    private String kioskPath = "C:\\Program Files (x86)\\Superonline\\Superonline Kiosk\\";// kiosk.exe dizini



    @Test
    public void run(){

        Assert.assertTrue(new MoveNewFiles2Path().execute(tempFile, kioskPath, jarFileName));

    }



}
