package com.example.ams2_21.registrom13;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;

import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;
import java.util.List;


import org.apache.http.message.BasicNameValuePair;

public class LoginActivity extends Activity implements OnClickListener {

    private EditText user, pass;
    private Button mSubmit, mRegister;
    private ProgressDialog pDialog;
    private static Firebase ref = new Firebase("https://run-n-buy.firebaseio.com");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_login);
        // setup input fields

        user = (EditText) findViewById(R.id.username);
        pass = (EditText) findViewById(R.id.password);

        // setup buttons
        mSubmit = (Button) findViewById(R.id.login);
        mRegister = (Button) findViewById(R.id.register);

        // register listeners
        mSubmit.setOnClickListener(this);
        mRegister.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.login:
                mSubmit.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ref.authWithPassword(
                            user.getText().toString(),
                            pass.getText().toString(),
                            new Firebase.AuthResultHandler() {
                                @Override
                                public void onAuthenticated(AuthData authData) {
                                    System.out.println("User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());
                                    Toast toast = Toast.makeText(getApplicationContext(),
                                            "User ID: " + authData.getUid() + ", Provider: " + authData.getProvider(),
                                            Toast.LENGTH_LONG);
                                    toast.show();
                                }
                                @Override
                                public void onAuthenticationError(FirebaseError firebaseError) {
                                    Toast toast = Toast.makeText(getApplicationContext(),
                                            firebaseError.getMessage(),
                                            Toast.LENGTH_LONG);
                                    toast.show();
                                }
                            });
                    }
                });
                break;
            case R.id.register:
                Intent i = new Intent(this, RegisterActivity.class);
                startActivity(i);
                break;

            default:
                break;
        }
    }
}