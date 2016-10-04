package util;

import net.egemsoft.updater.util.DownloadFiles;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by enbiya on 25.07.2016.
 */
public class DownloadFileTest {


    private String fileUrl = "http://egemsoft.net/superonlinekiosk/"; // dosyanın bulunduğu url
    private String tempFile = "C:\\Users\\" + System.getProperty("user.name") + "\\.superonline\\tepmFile\\";//ilk indirilecek yol
    private String fileName = "kiosk-client-ui-sol-1.0.0-SNAPSHOT-executable-win32_x86.jar";// dosyanın ismi


    @Test
    public void startTest(){

        if ((new DownloadFiles().execute(tempFile, fileUrl, fileName)) == true ){

            Assert.assertTrue(true);
        } else {

            Assert.assertTrue(false);

        }


    }



}
