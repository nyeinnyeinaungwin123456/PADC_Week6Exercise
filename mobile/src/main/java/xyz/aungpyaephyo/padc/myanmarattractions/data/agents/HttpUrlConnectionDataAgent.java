package xyz.aungpyaephyo.padc.myanmarattractions.data.agents;

/**
 * Created by Nyein Nyein on 7/9/2016.
 */
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import xyz.aungpyaephyo.padc.myanmarattractions.MyanmarAttractionsApp;
import xyz.aungpyaephyo.padc.myanmarattractions.data.models.AttractionModel;
import xyz.aungpyaephyo.padc.myanmarattractions.data.responses.AttractionListResponse;
import xyz.aungpyaephyo.padc.myanmarattractions.data.vos.AttractionVO;
import xyz.aungpyaephyo.padc.myanmarattractions.utils.CommonInstances;
import xyz.aungpyaephyo.padc.myanmarattractions.utils.MyanmarAttractionsConstants;

/**
 * Created by aung on 7/9/16.
 */
public class HttpUrlConnectionDataAgent implements AttractionDataAgent {

    private static HttpUrlConnectionDataAgent objInstance;

    private HttpUrlConnectionDataAgent() {

    }

    public static HttpUrlConnectionDataAgent getInstance() {
        if (objInstance == null) {
            objInstance = new HttpUrlConnectionDataAgent();
        }
        return objInstance;
    }

    @Override
    public void loadAttractions() {
        new AsyncTask<Void, Void, List<AttractionVO>>() {

            @Override
            protected List<AttractionVO> doInBackground(Void... voids) {
                URL url;
                BufferedReader reader = null;
                StringBuilder stringBuilder;

                try {
                    // create the HttpURLConnection   .1
                    url = new URL(MyanmarAttractionsConstants.ATTRACTION_BASE_URL + MyanmarAttractionsConstants.API_GET_ATTRACTION_LIST);

                    //retrieve http url obj .2
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                    // just want to do an HTTP POST here, to ask developer "GET or POST" .3
                    connection.setRequestMethod("POST");

                    // uncomment this if you want to write output to this url
                    //connection.setDoOutput(true);

                    // give it 15 seconds to respond .4
                    connection.setReadTimeout(15 * 1000);

                    //need request or pass parameters .5
                    connection.setDoInput(true);
                    connection.setDoOutput(true);

                    //put the request parameter into NameValuePair list. .6
                    List<NameValuePair> params = new ArrayList<>();

                    //can add as API needed with name and value .6
                    params.add(new BasicNameValuePair(MyanmarAttractionsConstants.PARAM_ACCESS_TOKEN, MyanmarAttractionsConstants.ACCESS_TOKEN));

                    //write the parameters from NameValuePair list into connection obj.
                    //don't change anyway with Http Url connection  .7
                    OutputStream outputStream = connection.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(
                            new OutputStreamWriter(outputStream, "UTF-8"));
                    writer.write(getQuery(params));
                    writer.flush();
                    writer.close();
                    outputStream.close();
                //don't change anyway with Http Url connection

                 //connect url .8 start request
                    connection.connect();


                    // read line by line the output from the server * reusable .9
                    reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    stringBuilder = new StringBuilder();

                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        stringBuilder.append(line + "\n");
                    }
                    // read the output from the server * reusable

                    //all strings above like json body string  //9
                    String responseString = stringBuilder.toString();
                    AttractionListResponse response = CommonInstances.getGsonInstance().fromJson(responseString, AttractionListResponse.class);
                    List<AttractionVO> attractionList = response.getAttractionList();

                    return attractionList;
                    //finish network agent job


                } catch (Exception e) {
                    Log.e(MyanmarAttractionsApp.TAG, e.getMessage());
                    AttractionModel.getInstance().notifyErrorInLoadingAttractions(e.getMessage());
                } finally {
                    // close the reader; this can throw an exception too, so
                    // wrap it in another try/catch block.
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException ioe) {
                            ioe.printStackTrace();
                        }
                    }
                }

                return null;
            }


            //do in UI thread
            @Override
            protected void onPostExecute(List<AttractionVO> attractionList) {
                super.onPostExecute(attractionList);
                if (attractionList != null || attractionList.size() > 0) {
                    AttractionModel.getInstance().notifyAttractionsLoaded(attractionList);
                }
            }
        }.execute();
    }

    //no need to change with Http Url Connection method
    private String getQuery(List<NameValuePair> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (NameValuePair pair : params) {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(pair.getName(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
        }

        return result.toString();
    }
}