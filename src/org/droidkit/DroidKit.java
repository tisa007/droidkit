package org.droidkit;

import java.io.File;
import java.util.Locale;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.util.TypedValue;

public class DroidKit {
    
    private static final String SDCARD_PATH_FORMAT = "Android/data/%s";
    
    private static Context sApplicationContext = null;
    private static ContentResolver sContentResolver = null;
    private static String sPackageName = null;
    private static Float sScreenDensity = null;
    private static Float sTextScale = null;
    private static File sExternalStorageDirectory = null;
    
    public static void onApplicationCreate(Context context) {
        if (sApplicationContext == null) {
            sApplicationContext = context.getApplicationContext();
        }
    }
    
    public static void onApplicationTerminate() {
        sApplicationContext = null;
        sContentResolver = null;
        sPackageName = null;
        sScreenDensity = null;
        sTextScale = null;
        sExternalStorageDirectory = null;
    }
    
    public static Context getContext() {
        return sApplicationContext;
    }
    
    public static ContentResolver getContentResolver() {
        if (sContentResolver == null) {
            sContentResolver = sApplicationContext.getContentResolver();
        }
        return sContentResolver;
    }
    
    public static String getPackageName() {
        if (sPackageName == null) {
            sPackageName = sApplicationContext.getPackageName();
        }
        return sPackageName;
    }
    
    public static float getScreenDensity() {
        if (sScreenDensity == null) {
            sScreenDensity = getDisplayMetrics().density;
        }
        return sScreenDensity.floatValue();
    }
    
    public static float getTextScale() {
        if (sTextScale == null) {
            sTextScale = getDisplayMetrics().scaledDensity;
        }
        return sTextScale.floatValue();
    }
    
    public static File getExternalStorageDirectory() {
        if (sExternalStorageDirectory == null) {
            sExternalStorageDirectory = new File(Environment.getExternalStorageDirectory(), String.format(Locale.US, SDCARD_PATH_FORMAT, getPackageName()));
        }
        return sExternalStorageDirectory;
    }
    
    public static Resources getResources() {
        return sApplicationContext.getResources();
    }
    
    public static DisplayMetrics getDisplayMetrics() {
        return getResources().getDisplayMetrics();
    }
    
    public static SharedPreferences getSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(sApplicationContext);
    }
    
    public static String getString(int resource) {
        return sApplicationContext.getString(resource);
    }

    public static String getString(int resource, Object... formatArgs) {
        return sApplicationContext.getString(resource, formatArgs);
    }

    public static int getResourceId(String name) {
        return getResources().getIdentifier(name, null, sApplicationContext.getPackageName());
    }

    public static Drawable getDrawable(int resource) {
        return getResources().getDrawable(resource);
    }

    public static Bitmap getBitmap(int resource) {
        return BitmapFactory.decodeResource(getResources(), resource);
    }
    
    public static int getPixels(int dip) {
        return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, getDisplayMetrics());
    }
    
    public static void registerReceiver(BroadcastReceiver receiver, IntentFilter filter) {
        sApplicationContext.registerReceiver(receiver, filter);
    }
    
    public static boolean tryUnregisterReceiver(BroadcastReceiver receiver) {
        try {
            sApplicationContext.unregisterReceiver(receiver);
            return true;
        } catch (Throwable t) {
            return false;
        }
    }
    
    public static Object getSystemService(String name) {
        return sApplicationContext.getSystemService(name);
    }
    
    public static boolean isHoneycomb() {
        // Can use static final constants like HONEYCOMB, declared in later versions
        // of the OS since they are inlined at compile time. This is guaranteed behavior.
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;
    }

    public static boolean isHoneycombTablet() {
        // Can use static final constants like HONEYCOMB, declared in later versions
        // of the OS since they are inlined at compile time. This is guaranteed behavior.
        return isHoneycomb() && (getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                == Configuration.SCREENLAYOUT_SIZE_XLARGE;
    }
    
    
    
    public static boolean getBoolPreference(String key, boolean defValue) {
        return getSharedPreferences().getBoolean(key, defValue);
    }
    public static int getIntPreference(String key, int defValue) {
        return getSharedPreferences().getInt(key, defValue);
    }
    public static float getFloatPreference(String key, float defValue) {
        return getSharedPreferences().getFloat(key, defValue);
    }
    public static long getLongPreference(String key, long defValue) {
        return getSharedPreferences().getLong(key, defValue);
    }
    public static String getStringPreference(String key, String defValue) {
        return getSharedPreferences().getString(key, defValue);
    }
    public static void setPreference(String key, boolean value) {
        SharedPreferences.Editor edit = getSharedPreferences().edit();
        edit.putBoolean(key, value);
        edit.commit();
    }
    public static void setPreference(String key, int value) {
        SharedPreferences.Editor edit = getSharedPreferences().edit();
        edit.putInt(key, value);
        edit.commit();
    }
    public static void setPreference(String key, float value) {
        SharedPreferences.Editor edit = getSharedPreferences().edit();
        edit.putFloat(key, value);
        edit.commit();
    }
    public static void setPreference(String key, long value) {
        SharedPreferences.Editor edit = getSharedPreferences().edit();
        edit.putLong(key, value);
        edit.commit();
    }
    public static void setPreference(String key, String value) {
        SharedPreferences.Editor edit = getSharedPreferences().edit();
        edit.putString(key, value);
        edit.commit();
    }
    
}
