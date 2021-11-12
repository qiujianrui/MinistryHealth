package com.library.basemodule.util;

import android.text.TextUtils;

/**
 * @author qjr
 * @create 2019/9/12
 * @Describe
 */
public class ConcealAccountUtils {
    /**
     * 手机加****
     * */
    public  static String phoneConceal(String phone){
        if(!TextUtils.isEmpty(phone) && phone.length() > 6 ){
            StringBuilder sb  =new StringBuilder();
            for (int i = 0; i < phone.length(); i++) {
                char c = phone.charAt(i);
                if (i >= 3 && i <= 6) {
                    sb.append('*');
                } else {
                    sb.append(c);
                }
            }
            return sb.toString();
        }
        return phone;
    }
    /**
     * 邮箱用****号隐藏前面的字母
     *
     * @return
     */
    public static String emailConceal(String email) {
        String s1=email.substring(0,email.indexOf("@"));
        String s2=email.substring(email.indexOf("@"),email.length());
        StringBuilder sb  =new StringBuilder();
        for (int i = 0; i < s1.length(); i++) {
            char c = s1.charAt(i);
            if (i >= s1.length()-3) {
                sb.append('*');
            } else {
                sb.append(c);
            }
        }
        sb.append(s2);
        return sb.toString();
//        String emails = email.replaceAll("(\\w?)(\\w+)(\\w)(@\\w+\\.[a-z]+(\\.[a-z]+)?)", "$1****$3$4");
    }

}
