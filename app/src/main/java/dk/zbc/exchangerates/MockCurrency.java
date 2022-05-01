package dk.zbc.exchangerates;

import android.content.Context;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * This class represents a DAO which has hardcoded currencies used for testing purposes
 */
public class MockCurrency implements CurrencyDAO {

    private  ArrayList<Rate> Rates;

    private static final String TAG = "MockCurrency";

    private final Context context;

    public MockCurrency(Context context){

        this.context = context;
        setRates(JsonConverter.collectRatesListFromJsonResponse(debugJsonResult()));
    }

    /**
     * this method collects the currency rates
     * @param base the base currency rate name
     * @return a list of currency rates based on the base currency code provided
     */
    @Override
    public ArrayList<Rate> getRates(String base) {
        return getRates();
    }

    /**
     * this method is used to return a JSONObject instead of getting it from an api
     * @return      : JSONObject
     */

    public JSONObject debugJsonResult(){

        String json = "{\"timestamp\":1651258503982,\"source\":\"USD\",\"date\":\"2022-04-29\",\"rates\":{\"AED\":3.6725,\"AFN\":86,\"ALL\":114.4734,\"AMD\":453.26,\"ANG\":1.802194,\"AOA\":405.6175,\"ARS\":115.3156,\"AUD\":1.41188,\"AWG\":1.8005,\"AZN\":1.7,\"BAM\":1.851253,\"BBD\":2.019044,\"BDT\":86.66376,\"BGN\":1.850613,\"BHD\":0.376996,\"BIF\":2017,\"BMD\":1,\"BND\":1.379663,\"BOB\":6.884873,\"BRL\":4.9361,\"BSD\":1.000009,\"BTN\":76.40508,\"BWP\":12.1065,\"BYN\":3.366525,\"BZD\":2.015637,\"CAD\":1.28294,\"CDF\":2013,\"CHF\":0.97095,\"CLP\":850.24,\"CNY\":6.6085,\"COP\":3959.67,\"CRC\":661.7005,\"CUP\":23.99966,\"CVE\":104.75,\"CZK\":23.2665,\"DJF\":177.72,\"DKK\":7.0373,\"DOP\":55.05,\"DZD\":144.7438,\"EGP\":18.4914,\"ETB\":51.4,\"EUR\":0.94596,\"FJD\":2.175,\"GBP\":0.79381,\"GEL\":3.055,\"GHS\":7.55,\"GMD\":54.1,\"GNF\":8795,\"GTQ\":7.659893,\"GYD\":209.2495,\"HKD\":7.84639,\"HNL\":24.5,\"HRK\":7.1735,\"HTG\":108.9987,\"HUF\":357.5045,\"IDR\":14525.4,\"ILS\":3.34241,\"INR\":76.4918,\"IQD\":1460,\"IRR\":42250,\"ISK\":130.35,\"JMD\":154.7076,\"JOD\":0.709,\"JPY\":129.386,\"KES\":115.85,\"KGS\":82.1014,\"KHR\":4047,\"KMF\":465.75,\"KRW\":1260.965,\"KWD\":0.30655,\"KYD\":0.8333254,\"KZT\":444.8451,\"LAK\":12387.5,\"LBP\":1513.467,\"LKR\":349.9952,\"LRD\":151.5,\"LSL\":15.88,\"LYD\":4.785,\"MAD\":9.975,\"MDL\":18.61135,\"MGA\":4002,\"MKD\":58.44328,\"MMK\":1851.474,\"MOP\":8.082802,\"MRU\":36.45,\"MUR\":43.30114,\"MVR\":15.45,\"MWK\":813.5,\"MXN\":20.3505,\"MYR\":4.3535,\"MZN\":63.83,\"NAD\":15.88,\"NGN\":415.26,\"NIO\":35.75,\"NOK\":9.347885,\"NPR\":122.2482,\"NZD\":1.54616,\"OMR\":0.3849921,\"PAB\":1.000009,\"PEN\":3.8275,\"PGK\":3.51,\"PHP\":52.3415,\"PKR\":185.7,\"PLN\":4.42019,\"PYG\":6841.901,\"QAR\":3.641,\"RON\":4.6805,\"RSD\":111.19,\"RUB\":71.75,\"RWF\":1023.5,\"SAR\":3.751051,\"SBD\":8.028936,\"SCR\":12.70316,\"SDG\":447.5,\"SEK\":9.784785,\"SGD\":1.381095,\"SLL\":12600,\"SOS\":579.5,\"SRD\":20.821,\"SVC\":8.749728,\"SZL\":15.75,\"THB\":34.237,\"TJS\":12.45494,\"TMT\":3.5,\"TND\":3.0525,\"TOP\":2.3125,\"TRY\":14.85,\"TTD\":6.795521,\"TWD\":29.4225,\"TZS\":2324.963,\"UAH\":30.2496,\"UGX\":3554.979,\"USD\":1,\"UYU\":41.45898,\"UZS\":11200,\"VES\":4.4608,\"VND\":22963.5,\"XAF\":620.8833,\"XCD\":2.70255,\"XOF\":622.5,\"XPF\":113.325,\"YER\":250.3,\"ZAR\":15.74685,\"ZMW\":17.02477}}";
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * this method is used to return the rates
     * @return  : list of rate
     */
    public ArrayList<Rate> getRates() {
        return Rates;
    }

    /**
     * this method is used to set the list of rate
     * @param rates : the list of rates to be set
     */
    public void setRates(ArrayList<Rate> rates) {
        Rates = rates;
    }
}
