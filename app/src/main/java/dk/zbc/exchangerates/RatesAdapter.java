package dk.zbc.exchangerates;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

/**
 * this class is representing an adapter to show a rates in a view.
 */

public class RatesAdapter extends ArrayAdapter<Rate> {


    public RatesAdapter(Context context, int resource, ArrayList<Rate> rates) {
        super(context, resource, rates);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View currentItemView = convertView;

        Rate rate = getItem(position);

        if (convertView == null){
           currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_elements, parent, false);

        }

        TextView tvCurrencyCode = (currentItemView).findViewById(R.id.Tv_CurrencyName);
        TextView tvCurrencySpotRate = (currentItemView).findViewById(R.id.Tv_SpotRate);



        tvCurrencyCode.setText(rate.getName());
        tvCurrencySpotRate.setText(String.valueOf(rate.getSpotRate()));


        return currentItemView;

    }
}
