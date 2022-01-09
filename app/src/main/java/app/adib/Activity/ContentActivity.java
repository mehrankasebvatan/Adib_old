package app.adib.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.adivery.sdk.Adivery;
import com.adivery.sdk.AdiveryAdListener;
import com.adivery.sdk.AdiveryBannerAdView;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import app.adib.Application.Application;
import app.adib.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class ContentActivity extends AppCompatActivity {


    public static String ID = "id" ;
    int id;
    TextView tv_title, tv_author, tv_content;
    CircleImageView img_author;
    Bundle bundle;
    FloatingActionButton btn_share;
    String APP_ID = "5a0e893f-f6bd-4ea4-a664-a65129ce4004";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        Adivery.configure(getApplication(), APP_ID);
        AdiveryBannerAdView bannerAd = findViewById(R.id.banner_ad);
        bannerAd.setBannerAdListener(new AdiveryAdListener() {
            @Override
            public void onAdLoaded() {
                // تبلیغ به‌طور خودکار نمایش داده می‌شود، هر کار دیگری لازم است اینجا انجام دهید.
            }

            @Override
            public void onError(String reason) {
                // خطا را چاپ کنید تا از دلیل آن مطلع شوید
            }

            @Override
            public void onAdClicked() {
                // کاربر روی بنر کلیک کرده
            }
        });
       bannerAd.loadAd();



        cast();
        getData();




    }

    private void getData() {

        id = Integer.parseInt(getIntent().getStringExtra(ID));
        bundle = getIntent().getExtras();
        tv_title.setText(bundle.getString("title"));
        tv_author.setText(bundle.getString("author"));
        tv_content.setText(bundle.getString("content"));
        Glide.with(this).load(bundle.getString("img")).into(img_author);



    }

    private void cast() {

        btn_share = findViewById(R.id.btn_share);
        tv_title = findViewById(R.id.tv_title);
        tv_author = findViewById(R.id.tv_author);
        tv_content = findViewById(R.id.tv_content);
        img_author = findViewById(R.id.img_author);
        btn_share.setOnClickListener(view -> {
            share();
        });

    }

    private void share() {

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, tv_content.getText().toString() + getString(R.string.app_link));
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);

    }
}