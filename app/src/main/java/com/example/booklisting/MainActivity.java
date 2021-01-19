package com.example.booklisting;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>> {
    private BookAdapter madapter;

    private TextView memptyStateTextView;

    private final static int BOOK_LOADER_ID = 1;

    private final static String BOOKS_REQUEST_URL = "https://www.googleapis.com/books/v1/volumes?q=search+terms";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.app.LoaderManager loaderManager = getLoaderManager();

        ListView bookslistview =  (ListView) findViewById(R.id.list);


        madapter = new BookAdapter(this, new ArrayList<Book>());

        bookslistview.setAdapter(madapter);

        memptyStateTextView = (TextView) findViewById(R.id.emptyView);
        bookslistview.setEmptyView(memptyStateTextView);

        View searchView = findViewById(R.id.search_viewr);
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get a reference to the ConnectivityManager to check state of network connectivity
                ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

                // Get details on the currently active default data network
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

                // If there is a network connection, fetch data
                if (networkInfo != null && networkInfo.isConnected()) {
                    // Initialize the loader. Pass in the int ID constant defined above and pass in null for
                    // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
                    // because this activity implements the LoaderCallbacks interface).
                    loaderManager.initLoader(BOOK_LOADER_ID, null, this);
                }

                else{
                    View progressindicator = findViewById(R.id.loading_indicator);
                    progressindicator.setVisibility(View.GONE);

                    memptyStateTextView.setText("NO INTERNET CONNECTION!");
                }
            }
        });

    }

    @NonNull
    @Override
    public Loader<List<Book>> onCreateLoader(int id, @Nullable Bundle args) {
        // Create a new loader for the given URL
        return new BookLoader(this, BOOKS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Book>> loader, List<Book> books) {
        // Clear the adapter of previous earthquake data
        madapter.clear();

        // If there is a valid list of {@link book}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (books != null && !books.isEmpty()) {
            madapter.addAll(books);
        }
        memptyStateTextView.setText("NO BOOKS FOUND!");

        // Hide loading indicator because the data has been loaded
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Book>> loader) {
        // Loader reset, so we can clear out our existing data.
        madapter.clear();
    }
}