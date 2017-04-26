package com.example.abernstein.swiftscanguitest;

/**
 * Created by alyxb on 4/26/2017.
 */

    class Company {

    public Company(){

    }
    public Company(int id, String rn, String ugn){
        ID = id;
        realName = rn;
        userGivenName = ugn;

    }
    private static int ID;
    private static String realName = "";
    private static String userGivenName;

    public static String getUserGivenName(){
        return userGivenName;
    }
    public static String getRealName(){
        return realName;
    }
}
