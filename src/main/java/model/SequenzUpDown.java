package model;

import java.util.ArrayList;
import java.util.List;

public class SequenzUpDown implements Guetekriterium {
    @Override
    public double berechneGuetekriterium(Zufallszahlengenerator generator,float[] zahlenfolge) {
        String bitfolge = "";
        int i = 0;
        while(i< zahlenfolge.length){
            double x1 = zahlenfolge[i++];
            double x2 = zahlenfolge[i++];
            if (x1 <x2){
                bitfolge += "1";
            }else{
                bitfolge += "0";
            }
            System.out.println(bitfolge);

        }
        List<Integer> length = new ArrayList<>();
        for(int j = 0 ; i< bitfolge.length(); i++){


        }



        return 0;
    }
}
