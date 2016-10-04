package util;

import net.egemsoft.updater.ui.MyLogger;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by enbiya on 22.06.2016.
 */
public class StopKioskTest {


    public static void main(String[] args) {
        execute();
    }


    public static void execute(){


        String taskString = "iexplore.exe";
        List<Integer> pIdList = new ArrayList<Integer>();

        //find process id
        try {

            Process p = Runtime.getRuntime().exec("cmd /C tasklist");
            BufferedReader bf = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));

            String line = "";
            while ((line = bf.readLine()) != null){

                if (line.contains(taskString)){
                    line = line.substring(taskString.length());
                    int taskID = Integer.parseInt(line.trim().split(" ")[0]);
                    pIdList.add(taskID);
                    System.out.println(taskID +" eklendi.");
                }

            }

        } catch (IOException e) {
        }


        if (pIdList.size() > 0){

            for (int i = 0; i < pIdList.size(); i++) {

                try {

                    Process p2 = Runtime.getRuntime().exec("cmd /C taskkill /f /pid "+ pIdList.get(i));
                    System.out.println(pIdList.get(i) + " durduruldu..");

                } catch (IOException e) {
                }
            }
        } else {

        }

    }


}
