package com.example.lms.utils;

import java.util.ArrayList;
import java.util.List;

public class CSVUtils {

    public static String getCSV(List<String> list){
        StringBuilder sb = new StringBuilder("");
        for(int i = 0; i < list.size(); i++){
            sb.append(list.get(i) + ",");
            if(i == list.size()-1){
                sb.setLength(sb.length()-1);
            }
        }


        return sb.toString();
    }


}
