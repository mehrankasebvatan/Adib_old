package app.adib.Activity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import com.adivery.sdk.Adivery;
import com.adivery.sdk.AdiveryAdListener;
import com.adivery.sdk.AdiveryBannerAdView;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.navigation.NavigationView;
import com.pushpole.sdk.PushPole;

import app.adib.R;
import de.hdodenhof.circleimageview.CircleImageView;


public class MainActivity extends AppCompatActivity {

    ImageView img_menu, img_d;
    CircleImageView img_ps, img_ts, img_pp;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    CardView cv_persianStory, cv_persianPoem, cv_translateStory, cv_drama;
    String psUrl = "https://kasebvatan.ir/AdibAPI/images/PersianStory.png";
    String tsUrl = "https://kasebvatan.ir/AdibAPI/images/TranslateStory.png";
    String ppUrl = "https://kasebvatan.ir/AdibAPI/images/PersianPoem.jpg";
    String dUrl = "https://kasebvatan.ir/AdibAPI/images/drama.jpg";
    String url_rate = "bazaar://details?id=" + "app.adib";
    String url_apps = "bazaar://collection?slug=by_author&aid=" + "kasebvatan";
    String APP_ID = "5a0e893f-f6bd-4ea4-a664-a65129ce4004";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Adivery.configure(getApplication(), APP_ID);
        PushPole.initialize(this,false);


        cast();
        onClick();

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
    }

    private void cast() {

        img_menu = findViewById(R.id.img_menu);
        navigationView = findViewById(R.id.nv_main);
        drawerLayout = findViewById(R.id.dl_main);
        img_ps = findViewById(R.id.img_ps);
        img_ts = findViewById(R.id.img_ts);
        img_pp = findViewById(R.id.img_pp);
        img_d = findViewById(R.id.img_d);
        cv_persianStory = findViewById(R.id.cv_persianStory);
        cv_persianPoem = findViewById(R.id.cv_persianPoem);
        cv_translateStory = findViewById(R.id.cv_translateStory);
        cv_drama = findViewById(R.id.cv_drama);
        Glide.with(this).load(psUrl).into(img_ps);
        Glide.with(this).load(tsUrl).into(img_ts);
        Glide.with(this).load(ppUrl).into(img_pp);
        Glide.with(this).load(dUrl).into(img_d);


    }

    private void onClick() {
        img_menu.setOnClickListener(view -> {

            if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                drawerLayout.closeDrawers();
            } else {
                drawerLayout.openDrawer(Gravity.RIGHT);
            }
        });

        cv_persianStory.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, PsActivity.class));
        });

        cv_persianPoem.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, PpActivity.class));
        });

        cv_translateStory.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, TsActivity.class));
        });

        cv_drama.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, DramaActivity.class));
        });


        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                switch (id) {
                    case R.id.menu_developer:
                        developer();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.menu_about:
                        about();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.menu_apps:
                        Intent apps = new Intent(Intent.ACTION_VIEW);
                        apps.setData(Uri.parse(url_apps));
                        apps.setPackage("com.farsitel.bazaar");
                        startActivity(apps);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.menu_rate:
                        Intent rate = new Intent(Intent.ACTION_EDIT);
                        rate.setData(Uri.parse(url_rate));
                        rate.setPackage("com.farsitel.bazaar");
                        startActivity(rate);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.menu_share:
                        share();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.menu_telegram_channel:
                        u("https://t.me/FindSalvation");
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.menu_telegram_group:
                        u("https://t.me/Adib_App");
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.menu_donate:
                        donate();
                        drawerLayout.closeDrawers();
                        break;

                }

                return false;
            }
        });


        ////////////////////// END of ON CLICK!!!! //////////////
    }


    @SuppressLint("RtlHardcoded")
    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
            drawerLayout.closeDrawers();
        } else {
            finish();
        }

    }


    public void u(String urls) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(urls));
        startActivity(i);
    }


    public void about() {

        final BottomSheetDialog bd_about = new BottomSheetDialog(this);
        bd_about.setContentView(R.layout.bs_about);


        bd_about.show();

    }

    public void developer() {

        final BottomSheetDialog bd_developer = new BottomSheetDialog(this);
        bd_developer.setContentView(R.layout.bs_developer);
        ImageView img_instagram = bd_developer.findViewById(R.id.img_instagram);
        ImageView img_telegram = bd_developer.findViewById(R.id.img_telegram);
        ImageView img_github = bd_developer.findViewById(R.id.img_github);
        ImageView img_vigool = bd_developer.findViewById(R.id.img_vigool);
        CardView cv_order = bd_developer.findViewById(R.id.cv_order);
        CardView cv_resume = bd_developer.findViewById(R.id.cv_resume);

        assert img_instagram != null;
        img_instagram.setOnClickListener(view -> {
            u("https://instagram.com/mkv.dev");
            bd_developer.dismiss();
        });
        assert img_telegram != null;
        img_telegram.setOnClickListener(view -> {
            u("https://t.me/mkv_dev");
            bd_developer.dismiss();
        });
        assert img_github != null;
        img_github.setOnClickListener(view -> {
            u("https://github.com/MehranKasebvatan");
            bd_developer.dismiss();
        });
        assert img_vigool != null;
        img_vigool.setOnClickListener(view -> {
            u("https://virgool.io/@mehran.kasebvatan");
            bd_developer.dismiss();
        });
        assert cv_order != null;
        cv_order.setOnClickListener(view -> {
            u("https://wa.me/989396547272");
            bd_developer.dismiss();
        });
        assert cv_resume != null;
        cv_resume.setOnClickListener(view -> {
            u("https://yek.link/MehranKasebvatan");
            bd_developer.dismiss();
        });


        bd_developer.show();

    }


    public void donate() {

        final BottomSheetDialog bd_about = new BottomSheetDialog(this);
        bd_about.setContentView(R.layout.bs_donate);
        CardView cv_zarinpal = bd_about.findViewById(R.id.cv_zarinpal);
        TextView tv_show = bd_about.findViewById(R.id.tv_show);

        assert cv_zarinpal != null;
        cv_zarinpal.setOnClickListener(view -> {
            u("https://zarinp.al/mehrankasebvatan");
            bd_about.dismiss();
        });
        assert tv_show != null;
        tv_show.setOnClickListener(view -> {


            AlertDialog.Builder alert = new AlertDialog.Builder(this, R.style.AD);
            alert.setCancelable(true);

            WebView wv = new WebView(this);
            wv.loadUrl("https://kasebvatan.ir/AdibAPI/support/support.html");
            wv.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                }
            });
            alert.setView(wv);
            alert.setNegativeButton("فهمیدم", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    dialog.dismiss();
                }
            });


            alert.show();


            bd_about.dismiss();
        });

        bd_about.show();

    }


    private void share() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_app));
        sendIntent.setType("text/plain");
        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);
    }

}