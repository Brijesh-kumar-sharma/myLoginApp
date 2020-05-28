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
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    //new code
    ListView listView;

    private FirebaseAuth firebaseAuth;
   // private Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        firebaseAuth=FirebaseAuth.getInstance();
       // logout=(Button)findViewById(R.id.btnLogout);
//      //      @Override
       //     public void onClick(View v) {
        //        Logout();
      //      }
     //   });

        listView=(ListView)findViewById(R.id.listview);
        ArrayList<String> list=new ArrayList<>();
        list.add("orange");
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
