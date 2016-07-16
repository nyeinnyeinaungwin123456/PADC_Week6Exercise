package xyz.aungpyaephyo.padc.myanmarattractions;

import android.app.Application;
import android.content.Context;

/**
 * Created by aung on 7/6/16.
 */
public class MyanmarAttractionsApp extends Application {

    private static Context context;

    public static final String TAG="MyanmarAttractionsApp";

    public static final int ATTRACTION_LIST_LOADER=100;

    @Override
    public void onCreate() {
        super.onCreate();

//        try {
//            Class.forName("android.os.AsyncTask");
//        } catch (ClassNotFoundException e) {}

        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
