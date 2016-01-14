package com.example.txuso.moppa;
import android.app.Activity;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.Timer;
import java.util.TimerTask;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class MainActivity extends Activity {
    /**
     * This activity
     */
    MainActivity activity;

    /**
     * text view that will show up if MOPPA app is working or not according to the three conditions
     */
    TextView stateTextView;

    /**
     * text view that will show up if the device's wifi is not on
     */
    static TextView wifi;

    /**
     * text view that will show up if the device is not in idle state
     */
    TextView idle;

    /**
     * text view that will show up if the device is not charging
     */
    static TextView charge;

    /**
     * the Async http client that will be called to use the MOPPA API
     */
    private AsyncHttpClient client;

    /**
     * The device's name that will be sent by the MOPPA API
     */
    String phoneName;

    /**
     * Extra attribute that will include the number to factorize if it exists or an error message if there is an error.
     */
    String numberS;
    /**
     * the task ID that will be sent by the MOPPA API
     */
    String phoneID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * We initialize the extra attribute, the phone id, and the Async http client
         */
        numberS = "";
        phoneID = "";
        client = new AsyncHttpClient();

        /**
         * We get the phone name from the device MANUFACTURER and the PRODUCT name
         */
        phoneName = android.os.Build.MANUFACTURER + android.os.Build.PRODUCT;

        /**
         * We specify that we are in the Main Activity
         */
        activity = this;

        /**
         * We assign all the text views.
         */
        stateTextView = (TextView)findViewById(R.id.state);
        wifi = (TextView)findViewById(R.id.wifiTextView);
        idle = (TextView)findViewById(R.id.idleTextView);
        charge = (TextView)findViewById(R.id.energyTextView);

        /**
         * We initialize the power manager that says if the screen is on/off
         */
        final PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);

        /**
         * this thread will be launched every 3 seconds in order to test if the 3 conditions are fulfilled
         */
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {

                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        /**
                         * We check the three conditions using the power manager and the UTIL class methods
                         * if it is true we change the statetextview showing that MOPPA is working
                         */
                        if (Util.isConnected(getApplicationContext()) && Util.isWifi(getApplicationContext()) && !powerManager.isScreenOn()) {
                            stateTextView.setText("MOPPA WORKING");
                            idle.setVisibility(View.INVISIBLE);
                            /**
                             * numberToCalculate contains the factorial number that will be obtained from the MOPPA API
                             * throw the Async http client
                             */
                            String numberToCalculate = getTask(client);
                            /**
                             * if the numberToCalculate doesn't have errors we proceed to calculate its factorial
                             */
                            if (!numberToCalculate.equals("Error"))
                            /**
                             * we assign the value to numberToCalculate
                             */
                                numberToCalculate = calculateResult(numberToCalculate);
                            /**
                             * The task is sent to using the MOPPA API throw the Async http client
                             */
                            sendTask(client, numberToCalculate);


                        } else {
                            /**
                             * If the condition is not fulfilled the MOPPA state textview shows the MOPPA NOT WORKING message
                             */
                            stateTextView.setText("MOPPA NOT WORKING");
                            /**
                             * it checks if the screen is on or not
                             */
                            if (!powerManager.isScreenOn())
                                idle.setVisibility(View.VISIBLE);
                            /**
                             * it checks if the wifi is on or not
                             */
                            if (!Util.isWifi(getApplicationContext()))
                                wifi.setVisibility(View.VISIBLE);

                        }

                    }
                });
            }
        }, 0, 3000);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     *
     * @param client the Async http client that will be called to use the MOPPA API
     * @return the number to be calculated
     */
    public String getTask(AsyncHttpClient client){
        try {
            /**
             * we create a JSONObject containing the phoneName necessary to get a task
             */
            JSONObject obj_result = new JSONObject();
            obj_result.put("phoneName", phoneName);
            StringEntity str_result = new StringEntity(obj_result.toString());

            /**
             * POST method from the API will return the number if it is possible
             */
            client.post(getApplicationContext(),
                    "http://10.0.2.2:8080/moppa/v1/mobiletask/getTask",
                    str_result,
                    "application/json",
                    new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers,
                                              byte[] responseBody) {

                            try {
                                /**
                                 * we assign the numberS with the response body from the petition
                                 */
                                numberS = new String(responseBody, "UTF-8");
                                /**
                                 * We create a JSONObject containing the taskValue and the taskID
                                 */
                                JSONObject jobj = new JSONObject(numberS);
                                numberS = jobj.getString("taskValue");
                                phoneID = jobj.getString("taskID");
                            }
                            catch (UnsupportedEncodingException exception){
                                exception.printStackTrace();
                                numberS = "Error";
                            }
                            catch (JSONException e){
                                e.printStackTrace();
                                numberS = "Error";

                            }


                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers,
                                              byte[] responseBody, Throwable error) {
                            /**
                             * if there is any error we send the Error
                             */
                            numberS = "Error";

                        }
                    });

        }
        catch (JSONException e){
            e.printStackTrace();

        }
        catch (UnsupportedEncodingException e2){
            e2.printStackTrace();
        }


        return numberS;


    }

    /**
     *
     * @param client the Async http client that will be called to use the MOPPA API
     * @param number the number that will be sent throw the API
     */
    public void sendTask(AsyncHttpClient client, String number){

        try {
            /**
             * we create a JSONObject containing all the necessary attributes
             * to call the returnTask methodAPI from the MOPPA API
             */
            JSONObject obj_result = new JSONObject();
            obj_result.put("phoneName", phoneName);
            obj_result.put("taskID", phoneID);
            obj_result.put("taskResult", number);
            /**
             * We create the StringEntity that will be sent throw the API
             */
            StringEntity str_result = new StringEntity(obj_result.toString());

            client.post(getApplicationContext(),
                    "http://10.0.2.2:8080/moppa/v1/mobiletask/returnTask",
                    str_result,
                    "application/json",
                    new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers,
                                              byte[] responseBody) {

                            stateTextView.setText("MOPPA WORKING AND TASK SENT SUCCESSFULLY");


                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers,
                                              byte[] responseBody, Throwable error) {

                            stateTextView.setText("MOPPA WORKING BUT THE TASK COULDN'T BE SENT");

                        }
                    });

        }
        catch (JSONException e){
            e.printStackTrace();

        }
        catch (UnsupportedEncodingException e2){
            e2.printStackTrace();
        }


    }

    /**
     *
     * @param number the String number received by the MOPPA API that is going to be calculated
     * @return the number calculated thanks to the factorial method
     */
    public String calculateResult (String number){
        int n = Integer.parseInt(number);
        Factorial f = new Factorial();
        BigInteger val = f.process(n);
        return val+"";

    }



}
