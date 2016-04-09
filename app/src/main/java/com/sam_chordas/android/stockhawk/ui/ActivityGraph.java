package com.sam_chordas.android.stockhawk.ui;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

import com.sam_chordas.android.stockhawk.R;
import com.sam_chordas.android.stockhawk.data.Quote;
import com.sam_chordas.android.stockhawk.data.StockResults;
import com.sam_chordas.android.stockhawk.ours.ApiCalls;
import com.sam_chordas.android.stockhawk.ours.Constants;

import org.eazegraph.lib.charts.ValueLineChart;
import org.eazegraph.lib.models.ValueLinePoint;
import org.eazegraph.lib.models.ValueLineSeries;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class ActivityGraph extends Activity {

    private Context mContext=this;
    boolean isConnected;
    String symbol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        symbol=getIntent().getStringExtra("stock_symbol");

        ConnectivityManager cm =
                (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();


    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        final ValueLineChart mCubicValueLineChart = (ValueLineChart) findViewById(R.id.cubiclinechart);

        final ValueLineSeries series = new ValueLineSeries();
        series.setColor(0xFF56B7F1);






        if(isConnected){

            ApiCalls apiCalls= Constants.getRetrofitInstance();
            Call<StockResults> personWiseBreakup= apiCalls.getStockDetails(Constants.getQuery(symbol),"json","store://datatables.org/alltableswithkeys");
            personWiseBreakup.enqueue(new Callback<StockResults>() {
                @Override
                public void onResponse(Response<StockResults> response, Retrofit retrofit) {


                    if (response.isSuccess()) {

                        StockResults stockResults = response.body();
                        List<Quote> quoteList=stockResults.getQuery().getResults().getQuote();
                        for (Quote q :quoteList){

                            series.addPoint(new ValueLinePoint(q.getDate(), Float.parseFloat(q.getClose())));
                        }

                        mCubicValueLineChart.addSeries(series);
                        mCubicValueLineChart.startAnimation();

                    }
                }

                @Override
                public void onFailure(Throwable t) {


                }
            });

        }else{
            Toast.makeText(mContext,getResources().getString(R.string.network_toast),Toast.LENGTH_LONG).show();
        }


    }
}
