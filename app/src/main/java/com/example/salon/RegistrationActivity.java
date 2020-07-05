package com.example.salon;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class RegistrationActivity extends AppCompatActivity {

    private TextInputLayout userName, userPassword,userEmail,userAge;
    private Button regButton;
    private TextView userLogin;
    private FirebaseAuth firebaseAuth;
    String email,name,age,password;


    //  Uri imagePath;
    //   private StorageReference storageReference;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  requestWindowFeature(Window.FEATURE_NO_TITLE);
        // this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_registration);
        setupUIViews();
        firebaseAuth=FirebaseAuth.getInstance();




        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate())

                {   //  Toast.makeText(RegistrationActivity.this,"process start successfully",Toast.LENGTH_SHORT).show();
                    //upload this data to database
                    String user_email=userEmail.getEditText().getText().toString().trim();
                    String user_password=userPassword.getEditText().getText().toString().trim();
                    firebaseAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()) {
                                // Toast.makeText(RegistrationActivity.this, "Registration done", Toast.LENGTH_SHORT).show();
                                //(new Intent(RegistrationActivity.this,MainActivity.class));
                                sendEmailVerification();
                                sendUserData();
                                Toast.makeText(RegistrationActivity.this," After verifying you need to login again....",Toast.LENGTH_SHORT).show();
                                //firebaseAuth.signOut();
                                finish();

                            }
                            else
                            {
                                Toast.makeText(RegistrationActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                }

            }
        });

        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this,MainActivity.class));
            }
        });



    }

    private void setupUIViews(){
        userName=(TextInputLayout) findViewById(R.id.etUserName);
        userPassword=(TextInputLayout) findViewById(R.id.etUserPassword);
        userEmail=(TextInputLayout) findViewById(R.id.etUserEmail);
        regButton=(Button)findViewById(R.id.btnRegister);
        userLogin=(TextView)findViewById(R.id.tvUserLogin);
        userAge=(TextInputLayout) findViewById(R.id.etAge);




    }
    /*  private boolean validateAge()
      {
          String ageInput=userAge.getEditText().getText().toString().trim();
          if(ageInput.isEmpty()){
              userAge.setError("Field can't be empty");
              return false;
          }
          else if(ageInput.length()>2){
              userName.setError("Age too more");
              return false;
          }
          else
          {
              userAge.setError(null);
              return true;
          }
      }
      private boolean validateEmail()
      {
          String emailInput=userEmail.getEditText().getText().toString().trim();
          if(emailInput.isEmpty()){
              userEmail.setError("Field can't be empty");
              return false;
          }
          else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
              userEmail.setError("Enter a valid email");
              return false;
          }
          else
          {
              userEmail.setError(null);
              return true;
          }
      }
      private boolean validatePassword()
      {
          String PasswordInput=userPassword.getEditText().getText().toString().trim();
          if(PasswordInput.isEmpty()){
              userPassword.setError("Field can't be empty");
              return false;
          }
          else if(PasswordInput.length()<7){
              userPassword.setError("Password too short");
              return false;
          }
          else
          {
              userPassword.setError("");
              return true;
          }
      }
      private boolean validateUsername()
      {
          String usernameInput=userName.getEditText().getText().toString().trim();
          if(usernameInput.isEmpty()){
              userName.setError("Field can't be empty");
              return false;
          }
          else if(usernameInput.length()>20){
              userName.setError("Username too long");
              return false;
          }
          else
          {
              userName.setError("");
              return true;
          }
      }*/




    private Boolean validate()
    {
        Boolean result=false;

        String email2=userEmail.getEditText().getText().toString().trim();
        name= userName.getEditText().getText().toString();

        //new change in 130 to 140
        password =userPassword.getEditText().getText().toString();
        email =userEmail.getEditText().getText().toString();
        age=userAge.getEditText().getText().toString();
      /*  if(!validateAge() | !validateEmail() | !validatePassword() | !validateUsername()){
            Toast.makeText(RegistrationActivity.this,"enter the details",Toast.LENGTH_SHORT).show();


        }*/

        if(name.isEmpty() || password.isEmpty() || email.isEmpty() ||age.isEmpty()){
            Toast.makeText(this,"Please enter all the details ",Toast.LENGTH_SHORT).show();

        }
        //  else if(Integer.parseInt(String.valueOf(userAge))<5||Integer.parseInt(String.valueOf(userAge))>120) {
        //  Toast.makeText(RegistrationActivity.this,"Please enter correct age",Toast.LENGTH_SHORT).show();

        //  }

        else if(!Patterns.EMAIL_ADDRESS.matcher(email2).matches())
        {  // userEmail.setError("enter a valid mail");
           Toast.makeText(RegistrationActivity.this,"Please enter a valid email",Toast.LENGTH_SHORT).show();
        }
        else if(password.length()<6){
           // userPassword.setError("Password must be more than 6 characters");
            Toast.makeText(RegistrationActivity.this,"enter password more than 6 characters",Toast.LENGTH_SHORT).show();
        }
        else if(name.length()>20)
        {  // userName.setError("username too long");
            Toast.makeText(RegistrationActivity.this, "Username too long", Toast.LENGTH_SHORT).show();
        }
        else if(age.length()!=12)
        {
            //userAge.setError("Enter a valid Adhar");

           Toast.makeText(RegistrationActivity.this,"Enter a valid Aadhhar detail",Toast.LENGTH_SHORT).show(); }
        else
        {
           /* name= userName.getEditText().getText().toString();


            //new change in 130 to 140
            password =userPassword.getEditText().getText().toString();
            email =userEmail.getEditText().getText().toString();
            age=userAge.getEditText().getText().toString();*/

            result=true;
        }
        return result;
    }


    private void sendEmailVerification(){

        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
        if(firebaseUser!=null)
        {
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {


                    if(task.isSuccessful()){
                        sendUserData();
                        Toast.makeText(RegistrationActivity.this," verification mail has been sent Verify it to Continue...",Toast.LENGTH_LONG).show();
                        firebaseAuth.signOut();
                        finish();
                        startActivity(new Intent(RegistrationActivity.this,MainActivity.class));

                    }
                    else
                    {
                        Toast.makeText(RegistrationActivity.this,"Verification mail has not been sent",Toast.LENGTH_LONG).show();
                    }

                }
            });
        }
    }


    private void sendUserData(){
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference myRef=firebaseDatabase.getReference(firebaseAuth.getUid());
        //User Id /Images/Profile Pic


        UserProfile userProfile=new UserProfile(age,email,name);
        myRef.setValue(userProfile);
    }
}




