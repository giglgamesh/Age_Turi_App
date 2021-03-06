package pe.oranch.agenciaturismo.utilities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

import pe.oranch.agenciaturismo.activities.ContactenosActivity;
import pe.oranch.agenciaturismo.activities.OfertaActivity;
import pe.oranch.agenciaturismo.activities.PrincipalActivity;

/**
 * Created by Daniel on 05/11/2017.
 */

public class Utils {
    private static Typeface fromAsset;
    private static Fonts currentTypeface;
    public static OfertaActivity activity;
    private static SpannableString spannableString;
    public static void psLog(String log){
        Log.d("TEAMPS", log);
    }

    public Utils(Context context){
        this.activity = (OfertaActivity) context;
    }
    public static void psErrorLogE(String log, Exception e) {
        try {
            StackTraceElement l = e.getStackTrace()[0];
            Log.d("TEAMPS", log);
            Log.d("TEAMPS", "Line : " + l.getLineNumber());
            Log.d("TEAMPS", "Method : " + l.getMethodName());
            Log.d("TEAMPS", "Class : " + l.getClassName());
        }catch (Exception ee){}

    }
    public static SpannableString getSpannableString(String str) {
        spannableString = new SpannableString(str);
        return spannableString;
    }
    public static boolean isGooglePlayServicesOK(Activity activity) {

        final int GPS_ERRORDIALOG_REQUEST = 9001;

        int isAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(activity);

        if (isAvailable == ConnectionResult.SUCCESS) {
            return true;
        }
        else if (GooglePlayServicesUtil.isUserRecoverableError(isAvailable)) {
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(isAvailable, activity, GPS_ERRORDIALOG_REQUEST);
            dialog.show();
        }
        else {
            Toast.makeText(activity, "Can't connect to Google Play services", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
    public static Typeface getTypeFace(Fonts fonts) {

        if(currentTypeface == fonts) {
            if (fromAsset == null) {
                if(fonts == Fonts.NOTO_SANS) {
                    fromAsset = Typeface.createFromAsset(activity.getAssets(), "fonts/NotoSans-Regular.ttf");
                }else if(fonts == Fonts.NUNITO){
                    fromAsset = Typeface.createFromAsset(activity.getAssets(), "fonts/NUNITO-REGULAR.ttf");
                }
            }
        }else{
            if(fonts == Fonts.NOTO_SANS){
                fromAsset = Typeface.createFromAsset(activity.getAssets(), "fonts/NotoSans-Regular.ttf");
            }else if(fonts == Fonts.NUNITO){
                fromAsset = Typeface.createFromAsset(activity.getAssets(), "fonts/NUNITO-REGULAR.ttf");
            }else{
                fromAsset = Typeface.createFromAsset(activity.getAssets(), "fonts/NUNITO-REGULAR.ttf");
            }

            //fromAsset = Typeface.createFromAsset(activity.getAssets(), "fonts/Roboto-Italic.ttf");
            currentTypeface = fonts;
        }
        return fromAsset;
    }
    public enum Fonts{
        ROBOTO,
        NOTO_SANS,
        NUNITO
    }
}
