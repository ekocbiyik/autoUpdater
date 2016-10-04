package util;

import net.egemsoft.updater.util.MoveOldFiles2Backup;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by enbiya on 25.07.2016.
 */
public class MoveOldFiles2BackupTest {


    private String updaterTxtUrl = "http://egemsoft.net/superonlinekiosk/updater.txt"; // updater.txt dosyanın bulunduğu url
    private String jarFileName = "kiosk-client-ui-sol-1.0.0-SNAPSHOT-executable-win32_x86.jar";// dosyanın ismi

    private String kioskPath = "C:\\Program Files (x86)\\Superonline\\Superonline Kiosk\\";// kiosk.exe dizini
    private String oldVersionPath = "C:\\Users\\" + System.getProperty("user.name") + "\\.superonline\\oldVersions\\";//ilk indirilecek yol


    @Test
    public void init(){

        String srcFile = kioskPath;
        String destFile = oldVersionPath;

        if ((new MoveOldFiles2Backup().execute(srcFile, destFile, updaterTxtUrl, jarFileName)) == true ){

            Assert.assertTrue(true);

        } else {
            Assert.assertTrue(false);
        }


    }


}
