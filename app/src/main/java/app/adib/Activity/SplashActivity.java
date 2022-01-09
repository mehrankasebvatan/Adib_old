package app.adib.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;


import app.adib.Application.Application;
import app.adib.R;

public class SplashActivity extends AppCompatActivity {

    ImageView refresh;
    TextView  again;
    ProgressBar progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        setContentView(R.layout.activity_splash);
        refresh = findViewById(R.id.refresh);
        progress = findViewById(R.id.progress);
        again = findViewById(R.id.again);



        if (isWorkingInternetPersent()) {
            splash();
        } else {

            refresh.setVisibility(View.VISIBLE);
            progress.setVisibility(View.GONE);
            again.setVisibility(View.VISIBLE);

            refresh.setOnClickListener(view -> {
                startActivity(new Intent(SplashActivity.this, SplashActivity.class));
                finish();
            });
        }
    }

    public void splash() {
        Thread timerTread = new Thread() {
            public void run() {
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };
        timerTread.start();
    }

    public boolean isWorkingInternetPersent() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getBaseContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }

        }
        return false;
    }


}
