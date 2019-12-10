package com.test.junket.Utils;

import android.util.Patterns;

import java.util.regex.Pattern;

public class CommonFunctions {

    public static boolean checkString(String s) {

        if (s != null && s!= "" && s.length() > 0) {
            return true;
        }
        else {
            return false;
        }


    }

    public static boolean checkMobileNo(String s) {

        if (s != null && s!= "" && s.length() == 10) {
            return true;
        }
        else {
            return false;
        }


    }

    public static boolean checkPassword(String s) {

        if (s != null && s!= "" && s.length() > 5) {
            return true;
        }
        else {
            return false;
        }


    }




    public static boolean checkEmail(String s) {

        if (s != null && s!= "" && s.length() > 0 && Pattern.matches(Patterns.EMAIL_ADDRESS.pattern(),s)) {
            return true;
        }
        else {
            return false;
        }


    }

}
