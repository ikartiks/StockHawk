package com.sam_chordas.android.stockhawk.service;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.sam_chordas.android.stockhawk.R;
import com.sam_chordas.android.stockhawk.data.QuoteColumns;
import com.sam_chordas.android.stockhawk.data.QuoteProvider;

public class QuoteWidgetRemoteViewsService extends RemoteViewsService {

    Context mContext=this;
    boolean isConnected;
    Cursor cursor;

    public QuoteWidgetRemoteViewsService() {

    }

    /**
     * To be implemented by the derived service to generate appropriate factories for
     * the data.
     *
     * @param intent
     */
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new StackRemoteViewsFactory();
    }

    class StackRemoteViewsFactory implements RemoteViewsFactory {

        /**
         * Called when your factory is first constructed. The same factory may be shared across
         * multiple RemoteViewAdapters depending on the intent passed.
         */
        @Override
        public void onCreate() {
            cursor= mContext.getContentResolver().query(QuoteProvider.Quotes.CONTENT_URI,
                    new String[]{ QuoteColumns._ID, QuoteColumns.SYMBOL, QuoteColumns.BIDPRICE,
                            QuoteColumns.PERCENT_CHANGE, QuoteColumns.CHANGE, QuoteColumns.ISUP},
                    QuoteColumns.ISCURRENT + " = ?",
                    new String[]{"1"},
                    null);
        }

        /**
         * Called when notifyDataSetChanged() is triggered on the remote adapter. This allows a
         * RemoteViewsFactory to respond to data changes by updating any internal references.
         * <p/>
         * Note: expensive tasks can be safely performed synchronously within this method. In the
         * interim, the old data will be displayed within the widget.
         *
         * @see AppWidgetManager#notifyAppWidgetViewDataChanged(int[], int)
         */
        @Override
        public void onDataSetChanged() {

        }

        /**
         * Called when the last RemoteViewsAdapter that is associated with this factory is
         * unbound.
         */
        @Override
        public void onDestroy() {

        }

        /**
         * See {@ Adapter#getCount()}
         *
         * @return Count of items.
         */
        @Override
        public int getCount() {
            return cursor.getCount();
        }

        /**
         * See {@ Adapter#getView(int, View, ViewGroup)}.
         * <p/>
         * Note: expensive tasks can be safely performed synchronously within this method, and a
         * loading view will be displayed in the interim. See {@link #getLoadingView()}.
         *
         * @param position The position of the item within the Factory's data set of the item whose
         *                 view we want.
         * @return A RemoteViews object corresponding to the data at the specified position.
         */
        @Override
        public RemoteViews getViewAt(int position) {
            cursor.moveToPosition(position);
            RemoteViews rv=new RemoteViews(mContext.getPackageName(), R.layout.widget_collection_item);
            rv.setTextViewText(R.id.stock_symbol, cursor.getString(cursor.getColumnIndex(QuoteColumns.SYMBOL)));
            rv.setTextViewText(R.id.change,cursor.getString(cursor.getColumnIndex(QuoteColumns.CHANGE)));
            return rv;
        }

        /**
         * This allows for the use of a custom loading view which appears between the time that
         * {@link #getViewAt(int)} is called and returns. If null is returned, a default loading
         * view will be used.
         *
         * @return The RemoteViews representing the desired loading view.
         */
        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        /**
         * See {@ Adapter#getViewTypeCount()}.
         *
         * @return The number of types of Views that will be returned by this factory.
         */
        @Override
        public int getViewTypeCount() {
            return 1;
        }

        /**
         * See {@ Adapter#getItemId(int)}.
         *
         * @param position The position of the item within the data set whose row id we want.
         * @return The id of the item at the specified position.
         */
        @Override
        public long getItemId(int position) {
            return 0;
        }

        /**
         * See {@ Adapter#hasStableIds()}.
         *
         * @return True if the same id always refers to the same object.
         */
        @Override
        public boolean hasStableIds() {
            return false;
        }

        //... include adapter-like methods here. See the StackView Widget sample.
    }

}
