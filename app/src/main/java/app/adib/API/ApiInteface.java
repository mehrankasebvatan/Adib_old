package app.adib.API;


import java.util.List;

import app.adib.DataModel.Drama;
import app.adib.DataModel.PersianPoem;
import app.adib.DataModel.PersianStory;
import app.adib.DataModel.TranslateStory;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInteface {



    @GET("getPersianStory.php")
    Call<List<PersianStory>> getPersianStory();



    @GET("getPersianPoem.php")
    Call<List<PersianPoem>> getPersianPoem();

    @GET("getTranslateStory.php")
    Call<List<TranslateStory>> getTranslateStory();

    @GET("getDrama.php")
    Call<List<Drama>> getDrama();


}
