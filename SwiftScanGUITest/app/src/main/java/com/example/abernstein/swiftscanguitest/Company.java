package com.example.abernstein.swiftscanguitest;

import android.content.Context;

/**
 * Created by alyxb on 4/26/2017.
 */

    class Company {

    public Company(){

    }
    public Company(Context c, int id, String ugn){
        ID = id;
        userGivenName = ugn;
        String[] companies = c.getResources().getStringArray(R.array.companies);
        realName = companies[ID];
    }

    private static int ID;
    private static String realName;
    private static String userGivenName;

    public static String getUserGivenName(){
        return userGivenName;
    }
    public static String getRealName(){
        return realName;
    }
}
