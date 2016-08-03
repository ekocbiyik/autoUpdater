package util;

import com.ekocbiyik.updaterExample.util.MoveNewFiles2Path;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by enbiya on 26.07.2016.
 */
public class MoveNewFiles2PathTest {



    private String updaterTxtUrl = "http://www.ekocbiyik.com/denemeKiosk/updater.txt"; // updater.txt dosyanın bulunduğu url
    private String fileUrl = "http://www.ekocbiyik.com/denemeUpdater/"; // dosyanın bulunduğu url
    private String tempFile = "C:\\Users\\" + System.getProperty("user.name") + "\\.myAppFolder\\tepmFile\\";//ilk indirilecek yol
    private String jarFileName = "my-updater-executable-jar-file-for-exe.jar";// dosyanın ismi


    private String applicationPath = "C:\\Program Files (x86)\\My Company\\My Application\\";// myApp.exe dizini
    private String oldVersionPath = "C:\\Users\\" + System.getProperty("user.name") + "\\.myAppFolder\\oldVersions\\";//ilk indirilecek yol


    @Test
    public void run(){

        Assert.assertTrue(new MoveNewFiles2Path().execute(tempFile, applicationPath, jarFileName));

    }



}
