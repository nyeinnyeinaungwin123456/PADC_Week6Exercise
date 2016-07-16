package xyz.aungpyaephyo.padc.myanmarattractions.data.agents.retrofit;

/**
 * Created by Nyein Nyein on 7/9/2016.
 */
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import xyz.aungpyaephyo.padc.myanmarattractions.data.responses.AttractionListResponse;
import xyz.aungpyaephyo.padc.myanmarattractions.data.vos.Register;
import xyz.aungpyaephyo.padc.myanmarattractions.utils.MyanmarAttractionsConstants;

/**
 * Created by aung on 7/9/16.
 */
public interface AttractionApi {

    @FormUrlEncoded
    //method POST OR GET
    @POST(MyanmarAttractionsConstants.API_GET_ATTRACTION_LIST)
    Call<AttractionListResponse> loadAttractions(
            @Field(MyanmarAttractionsConstants.PARAM_ACCESS_TOKEN) String param);

    @FormUrlEncoded
    //method POST OR GET
    @POST(MyanmarAttractionsConstants.API_GET_ATTRACTION_LIST)
    Call<Register> getRegister();


}