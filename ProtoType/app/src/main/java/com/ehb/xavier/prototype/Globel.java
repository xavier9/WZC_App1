package com.ehb.xavier.prototype;

import com.ehb.xavier.api.rolEndpoint.model.Rol;
import com.ehb.xavier.api.userEndpoint.model.User;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by xavier on 28/01/2015.
 */
public class Globel {
    public static String URL = "https://logical-grammar-829.appspot.com/_ah/api/";
    public static User USER_G = new User();
    public static int rols  = 0;
    public static User customer = new User();
    public static Calendar CALENDAR = Calendar.getInstance();
    public static User personcal = new User();
    public static Rol testrol = new Rol();
    public static ArrayList<User> USER_LIST = new ArrayList<User>();
    public static ArrayList<Rol> ROL_LIST = new ArrayList<Rol>();
    public static ArrayList<User> USER_GO = new ArrayList<User>();
    public static boolean addglobal = false;
    public static User customer1 = new User();
}
