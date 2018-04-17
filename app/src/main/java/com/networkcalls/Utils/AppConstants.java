package com.networkcalls.Utils;

import android.Manifest;

import java.text.DecimalFormat;

/**
 * Created by BookMEds on 02-02-2018.
 */

public interface AppConstants {

    public static String URL = "http://xamprdemo.cloudapp.net/";

    public static String GET_ACT_DETAILS_URL = "api/ActivityParticipation/GetActivityDetailsChanged";

    public static String SOCKET_TIMEOUT = "java.net.SocketTimeoutException";
    public static String INVALID_HOSTNAME = "java.net.UnknownHostException";
    public static String CONNECTION_GONE = "failed to connect";


    public static final String MyPREFERENCES = "share_preference_key";

    public static String THEMECOLOR = "#263238";

    /*Medicine icon colors*/
    public static String TABLETS_THEMECOLOR = "#009688";
    public static String INJECTION_THEMECOLOR = "#ffa000";
    public static String CREAM_THEMECOLOR = "#c51162";
    public static String SYRUP_THEMECOLOR = "#ff5722";
    public static String CAPSULES_THEMECOLOR = "#4caf50";
    public static String DROPS_THEMECOLOR = "#d50000";
    public static String LIQUID_THEMECOLOR = "#ad1457";
    public static String OINTMENT_THEMECOLOR = "#673ab7";
    public static String MISCELLANEOUS_THEMECOLOR = "#896f08";

    public static String SIMPLE_DATE_FORMAT = "dd-MM-yyyy";

    public static String SIMPLE_DATE_TIME_FORMAT = "dd-MM-yyyy , h:mm a";

    /*Fonts*/
    public static String ROBOTO_MEDIUM = "fonts/Roboto-Medium.ttf";
    public static String ROBOTO_ITALIC = "fonts/Roboto-Italic.ttf";
    public static String ROBOTO_MEDIUM_ITALIC = "fonts/Roboto-MediumItalic.ttf";


    public static String SHARE_TITLE = "Hey! Check out this free app that I'm using to organise my pharmacy inventory! https://play.google.com/store/apps/details?id=com.xampr&hl=en";

    public final String DEFAULT_ITEM_FONT = "&#xE8FD;";

    public static DecimalFormat decimalFormatTwoPlace = new DecimalFormat("######.##");

    public static DecimalFormat decimalFormatOnePlace = new DecimalFormat("######.#");

    public static String[] PERMISSIONS_CAMERA = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    public static String[] PERMISSIONS_PHONE_STATE = {Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_CONTACTS, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

}
