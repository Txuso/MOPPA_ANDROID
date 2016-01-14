package com.example.txuso.moppa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class LoginActivity extends AppCompatActivity {
    /**
     * the callbackManager that is necessary to initialize the Login Service
     */
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_login);

        /**
         * We initialize the login button
         */
        LoginButton loginB = (LoginButton)findViewById(R.id.login_button);
        /**
         * We the information we need from the MOPPA users
         */
        loginB.setReadPermissions("public_profile");

        /**
         * We do the login using the callbackManager
         */
        loginB.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                /**
                 * if it is success we launch the Main Activity
                 */
                Intent menu = new Intent(LoginActivity.this, MainActivity.class);
                menu.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(menu);
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code

            }
        });
    }

    /**
     *
     * @param requestCode from the Facebook Login
     * @param resultCode from the Facebook Login
     * @param data from the Facebook Login
     *            
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

    }


}
