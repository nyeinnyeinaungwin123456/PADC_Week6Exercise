package xyz.aungpyaephyo.padc.myanmarattractions.data.agents.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Nyein Nyein on 7/16/2016.
 */
public class RetrofitDataAgent2 {

    public static final String BASE_URL = "http://aungpyaephyo.xyz/myanmar_attractions/";
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
