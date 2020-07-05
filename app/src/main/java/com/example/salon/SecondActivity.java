package com.example.salon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    private CheckBox mCutting,mSpa,mTrimming,mBleech,mFacial,mMassage,mShave,mDryer;
    private Button mWriteResult;
    private TextView mResultTextView;
    private ArrayList<String> mResult;

    //new code
  //  ListView listView;

    private FirebaseAuth firebaseAuth;
   // private Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        firebaseAuth=FirebaseAuth.getInstance();
        mCutting=findViewById(R.id.check_Cutting);
        mSpa=findViewById(R.id.check_Spa);
        mShave=findViewById(R.id.check_Shave);
        mBleech=findViewById(R.id.check_bleech);
        mTrimming=findViewById(R.id.check_Trimming);
        mMassage=findViewById(R.id.check_Massage);
        mFacial=findViewById(R.id.check_Facial);
        mDryer=findViewById(R.id.check_Dryer);
        mWriteResult=findViewById(R.id.ServiceSelected);
        mResultTextView=findViewById(R.id.result);
        mResult= new ArrayList<>();
        mResultTextView.setEnabled(false);



        mDryer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mDryer.isChecked())
                    mResult.add("Dryer");
                else
                    mResult.remove("Dryer");
            }
        });

        mCutting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCutting.isChecked())
                    mResult.add("Cutting");
                else
                    mResult.remove("Cutting");
            }
        });
        mShave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mShave.isChecked())
                    mResult.add("Shave");
                else
                    mResult.remove("Shave");
            }
        });

        mSpa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mSpa.isChecked())
                    mResult.add("Spa");
                else
                    mResult.remove("Spa");
            }
        });
        mBleech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mBleech.isChecked())
                    mResult.add("Bleech");
                else
                    mResult.remove("Bleech");
            }
        });
        mMassage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mMassage.isChecked())
                    mResult.add("Massage");
                else
                    mResult.remove("Massage");
            }
        });
        mTrimming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mTrimming.isChecked())
                    mResult.add("Trimming");
                else
                    mResult.remove("Trimming");

            }
        });
        mFacial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mFacial.isChecked())
                    mResult.add("Facial");
                else
                    mResult.remove("Facial");
            }
        });
        mWriteResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder stringBuilder=new StringBuilder();
                for(String s:mResult)
                    stringBuilder.append(s).append("\n");


                mResultTextView.setText(stringBuilder.toString());
                mResultTextView.setEnabled(false);
            }
        });





       // logout=(Button)findViewById(R.id.btnLogout);
//      //      @Override
       //     public void onClick(View v) {
        //        Logout();
      //      }
     //   });

     //   listView=(ListView)findViewById(R.id.listview);
       // ArrayList<String> list=new ArrayList<>();
        /*/list.add("orange");
        list.add("hello");
        list.add("hii");
        list.add("som");
        list.add("billa");
        list.add("hululu");
        list.add("orange");
        list.add("hello");
        list.add("hii");
        list.add("som");
        list.add("billa");
        list.add("hululu");
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(arrayAdapter);
*/


    }

    private void Logout()
    {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(SecondActivity.this,MainActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.logoutMenu: {
                Logout();
            }
            case R.id.profileMenu:{
                startActivity(new Intent(SecondActivity.this,ProfileActivity.class));
            }

        }
        return super.onOptionsItemSelected(item);
    }
}
