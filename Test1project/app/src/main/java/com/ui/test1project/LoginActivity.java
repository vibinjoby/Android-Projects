package com.ui.test1project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ui.test1project.model.Student;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText loginTxt, pwdTxt, studentNameTxt;
    Button loginBtn, resetBtn;
    String errMessage = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginTxt = (EditText) findViewById(R.id.userId);
        pwdTxt = (EditText) findViewById(R.id.pwdTxt);
        studentNameTxt = (EditText) findViewById(R.id.studentNameTxt);

        loginBtn = (Button) findViewById(R.id.loginbtn);
        resetBtn = (Button) findViewById(R.id.resetBtn);

        loginBtn.setOnClickListener(this);
        resetBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.findViewById(R.id.loginbtn) != null) {
            //Login button logic
            if (validateLoginDetails()) {
                //If the login details are valid navigate to next page
                Intent homePageIntent = new Intent();
                homePageIntent.setClass(this, HomePageActivity.class);

                Bundle extraBundle = new Bundle();
                extraBundle.putString("userName", studentNameTxt.getText().toString());

                homePageIntent.putExtras(extraBundle);

                startActivity(homePageIntent);
            } else {
                if(studentNameTxt != null && studentNameTxt.getText() != null && studentNameTxt.getText().toString().isEmpty()) {
                    errMessage = "Student Name cannot be empty";
                    showAlertWindow(errMessage);
                } else {
                    errMessage = "Invalid UserName or Password";
                    showAlertWindow(errMessage);
                }
            }
        } else {
            //Reset button logic
            loginTxt.setText("");
            pwdTxt.setText("");
            studentNameTxt.setText("");
        }
    }

    private boolean validateLoginDetails() {
        if (loginTxt != null && loginTxt.getText() != null && !loginTxt.getText().toString().isEmpty() &&
                pwdTxt != null && pwdTxt.getText() != null && !pwdTxt.getText().toString().isEmpty()
                && studentNameTxt != null && studentNameTxt.getText() != null && !studentNameTxt.getText().toString().isEmpty()) {

            if (loginTxt.getText().toString().trim().equals("student1") &&
                    pwdTxt.getText().toString().trim().equals("123456")) {
                return true;
            }
        }
        return false;
    }

    private void showAlertWindow(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(message);

        // Set Alert Title
        builder.setTitle("Error");

        builder.setCancelable(false);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        // Create the Alert dialog
        AlertDialog alertDialog = builder.create();

        // Show the Alert Dialog box
        alertDialog.show();
    }
}
