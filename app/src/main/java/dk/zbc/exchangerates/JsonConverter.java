package dk.zbc.exchangerates;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * this class is made to handle the JSONObject response from the api call.
 */
public class JsonConverter {

    /**
     * this method is used to collect the list of rate from the JSONObject
      * @param response : JSONObject provided by the api call
     * @return          : an array list of rate.
     */
    public static ArrayList<Rate> collectRatesListFromJsonResponse(JSONObject response){

        ArrayList<Rate> collectedRates = new ArrayList<>();

        Iterator<String> keys = null;
        if (response != null) {
            keys = response.keys();
        }

        if (keys != null) {

            while (keys.hasNext()){

                String keyValue = keys.next();
                String jsonRates = "";
                if (keyValue.equals("rates")){

                    try {
                        jsonRates = response.getString(keyValue);
                        collectedRates = convertJsonToRateList(jsonRates);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return collectedRates;
    }

    /**
     * this method is used to convert the rates string from the JSONObject. to a list of rates.
     * @param jsonRates : string value of rates. collected from the JSONObject.
     * @return          : List of rate
     */
    private static ArrayList<Rate> convertJsonToRateList(String jsonRates){

        ArrayList<Rate> ratesResult = new ArrayList<>();

        if (!jsonRates.isEmpty()){

            // remove bracket and " from the string
            jsonRates = jsonRates.replace("{", "").replace("}", "").replace("\"", "");

            // split the string into a string array so we get an rate on each index
            String [] rates = jsonRates.split(",");

            for (String rateString : rates){

                String [] rate = rateString.split(":");

                String name = rate[0];
                double spotRate = Double.parseDouble(rate[1]);

                Rate tmpRate = new Rate(name, spotRate);

                ratesResult.add(tmpRate);
            }

        }
        return ratesResult;
    }
}
