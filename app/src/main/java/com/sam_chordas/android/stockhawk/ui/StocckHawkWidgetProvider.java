package com.sam_chordas.android.stockhawk.ui;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

import com.sam_chordas.android.stockhawk.R;
import com.sam_chordas.android.stockhawk.service.QuoteWidgetRemoteViewsService;

/**
 * Implementation of App Widget functionality.
 */
public class StocckHawkWidgetProvider extends AppWidgetProvider {



    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {


    //CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_collection);
        //views.setTextViewText(R.id.appwidget_text, widgetText);
        //views.setTextViewText(R.id.TodaysMarketx,"ihasjdkaskjdnak");


        Intent i =new Intent(context, QuoteWidgetRemoteViewsService.class);
        i.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,appWidgetId);
        i.setData(Uri.parse(i.toUri(Intent.URI_INTENT_SCHEME)));
        //views.setRemoteAdapter(widgetId, R.id.widgetCollectionList, intent);
        views.setRemoteAdapter(R.id.widget_list,i);

        context.startService(i);





        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

