package com.example.foodhub.Common;

import android.app.Activity;
import android.widget.Toast;

import java.util.ArrayList;

public class FoodhubUtils {

    public static boolean AreInvalidFields(Activity a, ArrayList<String> list, String pass, String conf) {
        if (AreInvalidFields(a, list)) {
            Toast.makeText(a.getApplicationContext(),"Please Enter Something In All Fields.",Toast.LENGTH_SHORT).show();
            return true;
        }
        if (notHasBetween(pass, 'a', 'z')) {
            Toast.makeText(a.getApplicationContext(),"Password Must Have A Lower Case Letter.",Toast.LENGTH_SHORT).show();
            return true;
        } if (notHasBetween(pass, 'A', 'Z')) {
            Toast.makeText(a.getApplicationContext(),"Password Must Have A Upper Case Letter.",Toast.LENGTH_SHORT).show();
            return true;
        } if (notHasBetween(pass, '0', '9')) {
            Toast.makeText(a.getApplicationContext(),"Password Must Have A Number.",Toast.LENGTH_SHORT).show();
            return true;
        } if (!hasSymbol(pass)) {
            Toast.makeText(a.getApplicationContext(),"Password Must Have A Symbol.",Toast.LENGTH_SHORT).show();
            return true;
        } if (pass.length() < 12) {
            Toast.makeText(a.getApplicationContext(),"Password must have at least 12 characters.",Toast.LENGTH_SHORT).show();
            return true;
        } if (!pass.equals(conf)) {
            Toast.makeText(a.getApplicationContext(),"Passwords Do Not Match",Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    public static boolean AreInvalidFields(Activity a, ArrayList<String> list) {
        for (String str : list) {
            if (str == null || str.length() == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if the string does not have a character within the bounds
     *      between the specified start and end values.
     * @param str The string to be tested
     * @param start The starting character of the bounds
     * @param end The ending character of the bounds
     * @return If the string does not have a character within the specified bounds
     */
    public static boolean notHasBetween(String str, char start, char end) {
        int i;
        for (i = 0; i < str.length(); i++) {
            if (start <= str.charAt(i) && str.charAt(i) <= end)
                return false;
        }
        return true;
    }

    /**
     * Returns true if the string has a symbol
     * @param str The string to be tested
     * @return If the string has a symbol
     */
    public static boolean hasSymbol(String str) {
        int i;
        for (i = 0; i < str.length(); i++) {
            if (!('a' <= str.charAt(i) && str.charAt(i) <= 'z') &&
                    !('A' <= str.charAt(i) && str.charAt(i) <= 'Z') &&
                    !('0' <= str.charAt(i) && str.charAt(i) <= '9'))
                return true;
        }
        return false;
    }

}
