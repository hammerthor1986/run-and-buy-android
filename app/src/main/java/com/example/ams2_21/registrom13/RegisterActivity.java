package com.example.ams2_21.registrom13;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

public class RegisterActivity extends Activity implements OnClickListener {

    private static Firebase ref = new Firebase("https://run-n-buy.firebaseio.com/");

    private EditText emailTextField, passTextField;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_register);

        emailTextField = (EditText) findViewById(R.id.emailTextField);
        passTextField = (EditText) findViewById(R.id.passTextField);
        registerButton = (Button) findViewById(R.id.registerButton);

        registerButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        registerButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ref.createUser(emailTextField.getText().toString(),
                        passTextField.getText().toString(),
                        new Firebase.ValueResultHandler<Map<String, Object>>() {
                            @Override
                            public void onSuccess(Map<String, Object> result) {
                                Toast toast = Toast.makeText(getApplicationContext(),
                                        "Successfully created user account with uid: " + result.get("uid"),
                                        Toast.LENGTH_LONG);
                                toast.show();
                            }

                            @Override
                            public void onError(FirebaseError firebaseError) {
                                Toast toast = Toast.makeText(getApplicationContext(),
                                        firebaseError.getMessage(),
                                        Toast.LENGTH_LONG);
                                toast.show();
                            }
                        });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
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
}
