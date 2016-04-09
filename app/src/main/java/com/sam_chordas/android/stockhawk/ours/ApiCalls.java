package com.sam_chordas.android.stockhawk.ours;


import com.sam_chordas.android.stockhawk.data.StockResults;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by kartikshah on 25/12/15.
 */
public interface ApiCalls {


    @GET("yql")
    Call<StockResults> getStockDetails(@Query("q")String query,@Query("format")String format,@Query("env")String env);

}
