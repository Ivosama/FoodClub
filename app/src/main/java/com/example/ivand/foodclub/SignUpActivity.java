package com.example.ivand.foodclub;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SignUpActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";

    @InjectView(R.id.editTextFirstName) EditText editTextFirstName;
    @InjectView(R.id.editTextLastName) EditText editTextLastName;
    @InjectView(R.id.editTextEmail) EditText editTextEmail;
    @InjectView(R.id.editTextPassword) EditText editTextPassword;
    @InjectView(R.id.editTextAllergies) EditText editTextAllergies;
    @InjectView(R.id.editProfileDescription) EditText editProfileDescritpion;
    @InjectView(R.id.buttonSave) Button buttonSave;
   // @InjectView(R.id.link_login) TextView _loginLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.inject(this);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

//        _loginLink.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Finish the registration screen and return to the Login activity
//                finish();
//            }
//        });
    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        buttonSave.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignUpActivity.this,
                R.style.Theme_AppCompat_DayNight_Dialog_Alert);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();
        final int id = -0;
        final String firstName = editTextFirstName.getText().toString();
        final String lastName = editTextLastName.getText().toString();
        final String email = editTextEmail.getText().toString();
        final String password = editTextPassword.getText().toString();
        final String allergies = editTextAllergies.getText().toString();
        final String description = editProfileDescritpion.getText().toString();

        // TODO: Implement your own signup logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess(id,firstName,lastName,email,allergies,password,description);
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    public void onSignupSuccess(int id, String firstName, String lastName, String email, String allergies, String password, String description) {
        buttonSave.setEnabled(true);
        setResult(RESULT_OK, null);
        User user = new User(id,firstName,lastName,email, allergies, password,description);
        Bundle data = getIntent().getExtras();
        Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
        intent.putExtra("com.package.userObject",new User(id,firstName,lastName,email, allergies, password,description));
        startActivity(intent);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        buttonSave.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = editTextFirstName.getText().toString();
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            editTextFirstName.setError("at least 3 characters");
            valid = false;
        } else {
            editTextFirstName.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("enter a valid email address");
            valid = false;
        } else {
            editTextEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            editTextPassword.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            editTextPassword.setError(null);
        }

        return valid;
    }

//    private void confirmAccount(final User user){
//        final AlertDialog.Builder confirm = new AlertDialog.Builder(this);
//        confirm.setMessage("Do you wish to create this account?");
//        confirm.setCancelable(false);
//
//        confirm.setPositiveButton("Yup", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Intent intent = new Intent(SignUpActivity.this,MainActivity.class);
//                intent.putExtra("com.package.userObject", user);
//                startActivity(intent);
//            }
//        });
//        confirm.setNegativeButton("NO", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                confirm.setCancelable(true);
//            }
//        });
//        confirm.create().show();

    }
