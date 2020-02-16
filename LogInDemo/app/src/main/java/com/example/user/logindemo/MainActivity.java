package com.example.user.logindemo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private int counter = 5;
    private TextView registerLink;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private TextView forgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = (EditText) findViewById(R.id.textEmail);
        Password = (EditText) findViewById(R.id.textPW);
        Info = (TextView) findViewById(R.id.textIncorrect);
        Login = (Button) findViewById(R.id.btnLogin);
        registerLink = (TextView) findViewById(R.id.textRegister);
        forgotPassword = (TextView) findViewById(R.id.tvForgotPassword);

        Info.setText(" ");

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        if (user != null) {
            finish();
            startActivity(new Intent(MainActivity.this, HomePage.class));
        }

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(emptyField()){
                    validate(Name.getText().toString(), Password.getText().toString());
                }
            }
        });

        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Registration.class));
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, PasswordForgot.class));
            }
        });

    }

    //validate will make sure all fields are full
    private Boolean emptyField() {

        Boolean result = false;
        if(Name.getText().toString().isEmpty() || Password.getText().toString().isEmpty()){
            Toast.makeText(MainActivity.this,"Please Fill All Empty Fields", Toast.LENGTH_SHORT).show();
        }else {
            result = true;
        }
        return result;
    }
    //will validate to make sure the username and password matches
    private void validate(String userName, String userPassword){

        progressDialog.setMessage("Verifying User");
        progressDialog.show();


       firebaseAuth.signInWithEmailAndPassword(userName,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
           @Override
           public void onComplete(@NonNull Task<AuthResult> task) {
               if(task.isSuccessful()){
                   progressDialog.dismiss();
                   checkEmailVerification();
               } else{
                   progressDialog.dismiss();
                   Toast.makeText(MainActivity.this,"Login Failed", Toast.LENGTH_SHORT).show();
                   counter--;
                   Info.setText("Number of Attempts Remaining: " +counter);
                   if(counter == 0){
                       Login.setEnabled(false);

                   }
               }
           }
       });
    }

    private void checkEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        Boolean emailflag = firebaseUser.isEmailVerified();

        if(emailflag){
            finish();
            startActivity(new Intent(MainActivity.this,HomePage.class));
            Toast.makeText(MainActivity.this,"Login Successful", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Verify your email",Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();

        }
    }
}
