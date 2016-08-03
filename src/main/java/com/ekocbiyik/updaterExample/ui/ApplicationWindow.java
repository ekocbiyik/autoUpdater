package com.ekocbiyik.updaterExample.ui;

import com.sun.awt.AWTUtilities;
import com.ekocbiyik.updaterExample.util.*;
import net.miginfocom.swing.MigLayout;
import org.springframework.core.io.ClassPathResource;

import javax.swing.*;
import java.awt.*;
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



    private String updaterTxtUrl = "http://www.ekocbiyik.com/denemeKiosk/updater.txt"; // updater.txt dosyanın bulunduğu url
    private String fileUrl = "http://www.ekocbiyik.com/denemeUpdater/"; // dosyanın bulunduğu url
    private String tempFile = "C:\\Users\\" + System.getProperty("user.name") + "\\.myAppFolder\\tepmFile\\";//ilk indirilecek yol
    private String jarFileName = "my-updater-executable-jar-file-for-exe.jar";// dosyanın ismi


    private String applicationPath = "C:\\Program Files (x86)\\My Company\\My Application\\";// myApp.exe dizini
    private String oldVersionPath = "C:\\Users\\" + System.getProperty("user.name") + "\\.myAppFolder\\oldVersions\\";//ilk indirilecek yol



    public ApplicationWindow(){


        mainJFrame = new JFrame("Application Updater");

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

        logger.info("Program durdurulacak...");
        if ((new StopApplication().execute()) == true ){
            logger.info("Internet Testi başlatılacak..");
        } else {
            logger.error("bir hata oluştu program sonlandırılacak..");
            System.exit(0);
        }
    }

    private void internetTestConnection(){

        if ((new InternetTestConnection().execute()) == true ){
            logger.info("Dosya indirme işlemi başlatılacak..");
        } else {
            logger.error("Bir hata oluştu program sonlandırılacak..");
            System.exit(0);
        }
    }

    private void downloadFiles(){

        if ((new DownloadFiles().execute(tempFile, fileUrl, jarFileName)) == true ){
            logger.info("Dosyalar yedeklenecek!");
        } else {
            logger.error("bir hata oluştu program sonlandırılacak..");
            System.exit(0);
        }
    }

    private void moveOldFiles2backup(){

        String srcFile = applicationPath;
        String destFile = oldVersionPath;

        if ((new MoveOldFiles2Backup().execute(srcFile, destFile, updaterTxtUrl, jarFileName)) == true ){
            logger.info("Program güncelleme dosyası yüklenecek!");
        } else {
            logger.error("bir hata oluştu program sonlandırılacak..");
            System.exit(0);
        }
    }

    private void moveNewFiles2Path(){

        if ((new MoveNewFiles2Path().execute(tempFile, applicationPath, jarFileName)) == true ){
            logger.info("Program başlatılacak..");
        } else {
            logger.error("bir hata oluştu program sonlandırılacak..");
            System.exit(0);
        }

    }

    private void startKiosk(){

        if ((new StartApplication().execute(applicationPath)) == true ){
            logger.info("Güncelleme işlemi tamamladı!");
        } else {
            logger.error("bir hata oluştu program sonlandırılacak..");
            System.exit(0);
        }
    }

    private void closeUpdater(){

        //eğer tasklistte kisok gözükürse updater kapansın
        System.exit(0);
    }




}
