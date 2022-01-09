package app.adib.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import app.adib.API.ApiClient;
import app.adib.API.ApiInteface;
import app.adib.Adapter.RvPpAdapter;
import app.adib.Adapter.RvTsAdapter;
import app.adib.Application.Application;
import app.adib.DataModel.PersianPoem;
import app.adib.DataModel.TranslateStory;
import app.adib.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TsActivity extends AppCompatActivity {


    LinearLayoutCompat loading;
    RecyclerView rv_list;
    RvTsAdapter adapter;
    List<TranslateStory> list = new ArrayList<>();
    TextView tv_toolbar;
    ApiInteface request;
    String url = "https://kasebvatan.ir/AdibAPI/";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        request = ApiClient.getApiClient(url).create(ApiInteface.class);


        cast();
        getData();








    }

    private void cast(){
        rv_list = findViewById(R.id.rv_list);
        rv_list.setHasFixedSize(true);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        rv_list.setLayoutManager(lm);
        loading = findViewById(R.id.loading);
        tv_toolbar = findViewById(R.id.tv_toolbar);
        tv_toolbar.setText(R.string.translate_story);


    }



    private void getData(){

        request.getTranslateStory().enqueue(new Callback<List<TranslateStory>>() {
            @Override
            public void onResponse(@NonNull Call<List<TranslateStory>> call, @NonNull Response<List<TranslateStory>> response) {
                loading.setVisibility(View.GONE);
                list = response.body();
                adapter = new RvTsAdapter(getApplicationContext(), list);
                rv_list.setAdapter(adapter);
            }

            @Override
            public void onFailure(@NonNull Call<List<TranslateStory>> call, @NonNull Throwable t) {

                Application.l("Error", t.getMessage() + "");

            }
        });

    }





}

