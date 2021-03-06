package com.example.user.logindemo;

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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registration extends AppCompatActivity {

    private EditText userName, userPassword, userEmail;
    private Button userRegister;
    private TextView userLogin;
    private FirebaseAuth firebaseAuth;
    String email, password, name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setupViews();

        firebaseAuth = FirebaseAuth.getInstance();

        userRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){
                    //Will check if true, now upload into database
                    String user_email = userEmail.getText().toString().trim();
                    String user_password = userPassword.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                sendEmailVerification();
                            } else {
                                Toast.makeText(Registration.this, "Registration " +
                                        "failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        //on click the already signed up account text will bring you to main login
        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Registration.this, MainActivity.class));
            }
        });



    }

    //easier way to set up XML
    private void setupViews(){
        userName = (EditText)findViewById(R.id.textUserName);
        userPassword = (EditText)findViewById(R.id.etUserPassword);
        userEmail = (EditText)findViewById(R.id.etUserEmail);
        userRegister = (Button) findViewById(R.id.btnRegister);
        userLogin = (TextView) findViewById(R.id.textLogIn);
    }

    //validate will make sure all fields are full
    private Boolean validate() {

        Boolean result = false;

        name = userName.getText().toString();
        password = userPassword.getText().toString();
        email = userEmail.getText().toString();

        if(name.isEmpty() || password.isEmpty() || email.isEmpty()){
            Toast.makeText(this,"Please Fill All Empty Fields", Toast.LENGTH_SHORT).show();
        }else {
            result = true;
        }
        return result;
    }
    //will send email verification
    private void sendEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser!=null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
               if(task.isSuccessful()){
                   sendToDataBase();
                   Toast.makeText(Registration.this,"Email Verification Sent. Please Verify.", Toast.LENGTH_LONG).show();
                   firebaseAuth.signOut();
                   finish();
                   startActivity(new Intent(Registration.this, MainActivity.class));
               }else{
                   Toast.makeText(Registration.this,"Error With Registration, Please Try Again.", Toast.LENGTH_SHORT).show();
               }
                }
            });
        }
    }
    private void sendToDataBase(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getCurrentUser().getUid());
        UserProfile userProfile = new UserProfile(name,email);
        myRef.setValue(userProfile);

    }
}
