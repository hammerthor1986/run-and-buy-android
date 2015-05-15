package com.example.ams2_21.registrom13;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class LoginActivity extends Activity implements OnClickListener {
    private static Firebase ref = new Firebase("https://run-n-buy.firebaseio.com");

    private EditText user, pass;
    private Button mSubmit, mRegister;

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