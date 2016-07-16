package xyz.aungpyaephyo.padc.myanmarattractions.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.aungpyaephyo.padc.myanmarattractions.R;
import xyz.aungpyaephyo.padc.myanmarattractions.data.agents.retrofit.RetrofitDataAgent2;
import xyz.aungpyaephyo.padc.myanmarattractions.data.agents.retrofit.AttractionApi;
import xyz.aungpyaephyo.padc.myanmarattractions.data.vos.Register;

/**
 * Created by Nyein Nyein on 7/15/2016.
 */
public class RegisterResultActivity extends AppCompatActivity {

    TextView name, email, password, date_of_birth, country_of_origin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultregister);

//        getRegister();
    }

//    public void getRegister(){
//
//        AttractionApi apiService =
//                RetrofitDataAgent2.getClient().create(AttractionApi.class);
//
//        Call<Register> call = apiService.getRegister();
//
//        call.enqueue(new Callback<Register>() {
//            @Override
//            public void onResponse(Call<Register> call, Response<Register> response) {
//                if (response.body() != null) {
//
//                    Register register = new Register();
//                    register = response.body();
//
//
//                    name = (TextView)findViewById(R.id.tv_name);
//                    email = (TextView)findViewById(R.id.tv_email);
//                    password = (TextView)findViewById(R.id.tv_password);
//                    date_of_birth = (TextView)findViewById(R.id.tv_date_of_birth);
//                    country_of_origin = (TextView)findViewById(R.id.tv_country_of_origin);
//
//                    name.setText(register.getName());
//                    email.setText(register.getEmail());
//                    password.setText(register.getPassword());
//                    date_of_birth.setText(register.getDate_of_birth());
//                    country_of_origin.setText(register.getCoutry_of_origin());
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Register> call, Throwable t) {
//
//            }
//
//
//        });
//
//
//
//            }


}

