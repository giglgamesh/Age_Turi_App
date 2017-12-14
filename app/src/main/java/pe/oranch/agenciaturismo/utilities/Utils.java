package pe.oranch.agenciaturismo.utilities;

import android.content.Context;
import android.text.SpannableString;
import android.util.Log;

import pe.oranch.agenciaturismo.activities.ContactenosActivity;
import pe.oranch.agenciaturismo.activities.OfertaActivity;
import pe.oranch.agenciaturismo.activities.PrincipalActivity;

/**
 * Created by Daniel on 05/11/2017.
 */

public class Utils {
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
}
