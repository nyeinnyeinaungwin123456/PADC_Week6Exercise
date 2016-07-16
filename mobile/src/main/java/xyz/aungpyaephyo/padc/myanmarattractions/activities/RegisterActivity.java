package xyz.aungpyaephyo.padc.myanmarattractions.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import xyz.aungpyaephyo.padc.myanmarattractions.R;

/**
 * Created by Nyein Nyein on 7/13/2016.
 */
public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace_black_24dp);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Button btn_register = (Button)findViewById(R.id.btn_registerevent);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentToRegisterRA = new Intent(RegisterActivity.this, RegisterResultActivity.class);
                startActivity(intentToRegisterRA);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
