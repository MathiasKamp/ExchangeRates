package dk.zbc.exchangerates;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.VolleyError;

import org.json.JSONObject;
import java.util.ArrayList;


/**
 * This class represents an DAO which collects the currency rates from fixer.io api
 */

public class FixerCurrency implements CurrencyDAO {

    private final Context context;

    private ArrayList<Rate> rates;

    private static final String TAG = "FixerCurrency";

    /**
     * startGetCurrenciesRequest gets executed in the constructor
     * @param context : context
     */
    FixerCurrency(Context context) {
        this.context = context;
        startGetCurrenciesRequest();
    }

    /**
     * this method collects the currency rates from fixer.io
     *
     * @param base  : the base currency name on fixer.io we can only use USD. therefore base is never used.
     * @return      : list of rates.
     */
    @Override
    public ArrayList<Rate> getRates(String base) {

        return getAllRates();
    }

    /**
     * This method executes the api call to get the currencies.
     * i use VolleyService to make the get request and a VolleyCallBack to collect the response.
     * i collect the rates from the response and convert them to a ArrayList<Rate> via the JsonConverter.
     */
    public void startGetCurrenciesRequest(){

        String url = "currency api url here";

        VolleyCallBack volleyCallBack = new VolleyCallBack() {
            @Override
            public void onSuccess(JSONObject response) {
                Toast.makeText(context, response.toString(), Toast.LENGTH_LONG).show();
                Log.e(TAG, "onSuccess" + response);
                setRates(JsonConverter.collectRatesListFromJsonResponse(response));
            }

            @Override
            public void onError(VolleyError error) {
                Log.e(TAG, "onError: " + error);
            }
        };
        VolleyService volleyService = new VolleyService(volleyCallBack, context);
        volleyService.getDataVolley(url);

    }

    /**
     * Method is used to return the rates collected by the api call
     * @return  : an arrayList of rate
     */
    private ArrayList<Rate> getAllRates() {
        return rates;
    }

    /**
     * method is used to set the rates when collected by the api call.
     * @param rates : list of rates to be set.
     */
    private void setRates(ArrayList<Rate> rates) {
        this.rates = rates;
    }

}
