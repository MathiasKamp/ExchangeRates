package dk.zbc.exchangerates;

import com.android.volley.VolleyError;

import org.json.JSONObject;

/**
 * callback interface used to collect the JSONObject result of the a request.
 */
public interface VolleyCallBack {
     /**
      * this method is used to collect the JSONObject response when the response was successful
      * @param response  : the JSONObject of the response
      */
     void onSuccess(JSONObject response);

     /**
      * This method is used to collect the error response when the response has failed
      * @param error     : the error response
      */
     void onError(VolleyError error);
}
