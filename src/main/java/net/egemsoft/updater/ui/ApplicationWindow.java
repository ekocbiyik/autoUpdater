package net.egemsoft.updater.ui;

import com.sun.awt.AWTUtilities;
import net.egemsoft.updater.util.*;
import net.miginfocom.swing.MigLayout;
import org.springframework.core.io.ClassPathResource;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by enbiya on 22.06.2016.
 */
public class ApplicationWindow {

    final static MyLogger logger = new MyLogger(ApplicationWindow.class);

    private JFrame mainJFrame;

    private int MAIN_WINDOW_HEIGHT = 200;
    private int MAIN_WINDOW_WIDTH = 400;
    private String MLDebugMode = ""; // miglayout için parametre: debug



    private String updaterTxtUrl = "http://egemsoft.net/superonlinekiosk/updater.txt"; // updater.txt dosyanın bulunduğu url
    private String fileUrl = "http://egemsoft.net/superonlinekiosk/"; // dosyanın bulunduğu url
    private String tempFile = "C:\\Users\\" + System.getProperty("user.name") + "\\.superonline\\tepmFile\\";//ilk indirilecek yol
    private String jarFileName = "kiosk-client-ui-sol-1.0.0-SNAPSHOT-executable-win32_x86.jar";// dosyanın ismi


    private String kioskPath = "C:\\Program Files (x86)\\Superonline\\Superonline Kiosk\\";// kiosk.exe dizini
    private String oldVersionPath = "C:\\Users\\" + System.getProperty("user.name") + "\\.superonline\\oldVersions\\";//ilk indirilecek yol



    public ApplicationWindow(){


        mainJFrame = new JFrame("Kiosk Updater");

        try {

            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//            mainJFrame.setContentPane(new JLabel(new ImageIcon(new ClassPathResource("/images/turkcell.ico").getURL())));//background için
            mainJFrame.setIconImage(new ImageIcon(new ClassPathResource("/images/turkcell.png").getURL()).getImage());


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        mainJFrame.setUndecorated(true); //bu kısımtransparent için
        AWTUtilities.setWindowOpacity(mainJFrame, 0.85f);

        mainJFrame.setMaximumSize(new Dimension(MAIN_WINDOW_WIDTH,MAIN_WINDOW_HEIGHT));
        mainJFrame.setMinimumSize(new Dimension(MAIN_WINDOW_WIDTH-1, MAIN_WINDOW_HEIGHT-1));
        mainJFrame.setResizable(false);


        int windowHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        int windowWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        mainJFrame.setLocation((windowWidth - mainJFrame.getWidth())/2, (windowHeight - mainJFrame.getHeight())/2);// ekranı ortala



        // components..
        mainJFrame.setLayout(new MigLayout(MLDebugMode));
        mainJFrame.getContentPane().add(new CenterPanel(MLDebugMode),"width 100%, height 100%, cell 0 1, grow");


        mainJFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainJFrame.setVisible(true);
        mainJFrame.pack();


        final SwingWorker<Void, Void> startUpdate = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {

                TimeUnit.SECONDS.sleep(1);
                stopKiosk();
                internetTestConnection();
                downloadFiles();
                moveOldFiles2backup();
                moveNewFiles2Path();
                startKiosk();
                closeUpdater();

                return null;
            }
        };

        startUpdate.execute();

    }


    private void stopKiosk(){

        ((JProgressBar)((JPanel)mainJFrame.getContentPane().getComponent(0)).getComponent(1)).setIndeterminate(true);

        logger.info("Kiosk durdurulacak...");
        if ((new StopKiosk().execute()) == true ){
            logger.info("Internet Testi başlatılacak..");
        } else {
            logger.error("bir hata oluştu program sonlandırılacak..");
            systemExit();
        }
    }

    private void internetTestConnection(){

        if ((new InternetTestConnection().execute()) == true ){
            logger.info("Dosya indirme işlemi başlatılacak..");
        } else {
            logger.error("Bir hata oluştu program sonlandırılacak..");
            systemExit();
        }
    }

    private void downloadFiles(){

        if ((new DownloadFiles().execute(tempFile, fileUrl, jarFileName)) == true ){
            logger.info("Dosyalar yedeklenecek!");
        } else {
            logger.error("bir hata oluştu program sonlandırılacak..");
            systemExit();
        }
    }

    private void moveOldFiles2backup(){

        String srcFile = kioskPath;
        String destFile = oldVersionPath;

        if ((new MoveOldFiles2Backup().execute(srcFile, destFile, updaterTxtUrl, jarFileName)) == true ){
            logger.info("Kiosk güncelleme dosyası yüklenecek!");
        } else {
            logger.error("bir hata oluştu program sonlandırılacak..");
            systemExit();
        }
    }

    private void moveNewFiles2Path(){

        if ((new MoveNewFiles2Path().execute(tempFile, kioskPath, jarFileName)) == true ){
            logger.info("Kiosk başlatılacak..");
        } else {
            logger.error("bir hata oluştu program sonlandırılacak..");
            systemExit();
        }

    }

    private void startKiosk(){

        if ((new StartKiosk().execute(kioskPath)) == true ){
            logger.info("Güncelleme işlemi tamamladı!");
        } else {
            logger.error("bir hata oluştu program sonlandırılacak..");
            systemExit();
        }
    }

    private void closeUpdater(){

        //eğer tasklistte kisok gözükürse updater kapansın
        System.exit(0);
    }

    private void systemExit(){

        // jar dosyasını sil..
        File tmpFile = new File(tempFile + jarFileName);
        if (tmpFile.exists()){
            tmpFile.delete();
            logger.info("Indirme dosyası silindi!");
        }

        // temp dosyasını sil..
        File tmpFolder = new File(tempFile);
        if (tmpFolder.exists()){
            tmpFolder.delete();
            logger.info("Temp klasörü silindi!");
        }

        System.exit(0);
    }




}
