package com.sajidur.swe_stp.Backend;

public class MyUrl {
    public static final String Host="http://diuswetest-001-site1.itempurl.com";
    private static final String ApiURl=Host+"/Api";

    public static final String Registration=ApiURl+"/SignUp";
    public static final String Verification=ApiURl+"/VerifyUser";
    public static final String Login=ApiURl+"/Login";
    public static final String ALLEVENTS=ApiURl+"/getallevents";
    public static final String EventByID=ApiURl+"/sweevents/"; //+ID
    public static final String AddEvent=ApiURl+"/sweevents/";
    public static final String DeleteEvent=ApiURl+"/sweevents/"; //+ID deleteMethod
    public static final String UpdateEvent=ApiURl+"/sweevents/"; //+ID PutMethod

}
