package com.example.softwaretraining;

public class Checker {
    static public boolean isNumber(String str){
        try {
            if(Integer.parseInt(str)<=0)return false;
        }catch (Exception e){
            return false;
        }
        return true;
    }

    static public String shrink(String str,int limit){
        if (str.length() > limit) {
            return str.substring(0, limit+1);
        }
        return str;
    }
}
