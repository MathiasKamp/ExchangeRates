package dk.zbc.exchangerates;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;

import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

/**
 * This class is representing a service which is using volley to perform http requests.
 */
public class VolleyService {
    VolleyCallBack volleyCallBack;
    Context context;

    public VolleyService(VolleyCallBack volleyCallBack, Context context) {
        this.volleyCallBack = volleyCallBack;
        this.context = context;

    }

    /**
     * this method is used to collect any data from a source and returns the result by using the VolleyCallback interface
     * @param url   : the url of the source
     */
    public void getDataVolley(String url){
        try {
            RequestQueue queue = Volley.newRequestQueue(context);

            JsonObjectRequest jsonObj = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
                if (volleyCallBack != null)
                    volleyCallBack.onSuccess(response);
            }, error -> {
                if(volleyCallBack != null)
                    volleyCallBack.onError(error);
            });

            queue.add(jsonObj);

        }catch(Exception e){

            e.printStackTrace();
        }
    }
}
