package com.example.booklisting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.List;

public class BookAdapter extends ArrayAdapter<Book> {

    public BookAdapter(@NonNull Context context,  @NonNull List<Book> books) {
        super(context, 0, books);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Book currentbook = getItem(position);

        TextView titleView = (TextView) listItemView.findViewById(R.id.title_view);
        titleView.setText(currentbook.getMtitle());

        TextView priceView = (TextView) listItemView.findViewById(R.id.price_view);
        priceView.setText(currentbook.getMprice());

        TextView authorView = (TextView) listItemView.findViewById(R.id.author_view);
        authorView.setText(currentbook.getMauthor());

        TextView descriptionView = (TextView) listItemView.findViewById(R.id.description_view);
        descriptionView.setText(currentbook.getMdescription());

        return listItemView;

    }
}
