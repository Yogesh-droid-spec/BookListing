package com.example.booklisting;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.util.List;

public class BookLoader extends AsyncTaskLoader<List<Book>> {
    private String mUrl;

    public BookLoader(@NonNull Context context,String Url) {
        super(context);
        mUrl=Url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }


    @Nullable
    @Override
    public List<Book> loadInBackground() {
        if ( mUrl == null) {
            return null;
        }
        List<Book> result = QueryUtils.fetchEarthquakeData(mUrl);
        return result;

    }
}
