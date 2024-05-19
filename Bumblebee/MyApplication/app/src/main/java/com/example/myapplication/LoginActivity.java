package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class LoginActivity extends AppCompatActivity {
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference REF = database.getReference();

    private EditText id;
    private EditText pw;
    private ImageView next_page;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        id =(EditText)findViewById(R.id.ID_editText);
        pw =(EditText) findViewById(R.id.PW_editText);
        next_page=(ImageView) findViewById(R.id.login_imageView);

        //google login & sign up 추가?
        //firebase Auth 사용?

        next_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userID = id.getText().toString();
                String userPW = pw.getText().toString();

                REF.child("UserDate").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.child(userID).exists()){
                            UserData user = snapshot.child(userID).getValue(UserData.class);
                            if(user.getPasswd() !=null && user.getPasswd().equals(userPW)){
                                Toast toast = Toast.makeText(LoginActivity.this, "login success", Toast.LENGTH_SHORT);
                                toast.show();
                            }
                            else{
                                Toast toast2 = Toast.makeText(LoginActivity.this, "login fail - password error", Toast.LENGTH_SHORT);
                                toast2.show();
                            }
                        }
                        else {
                            Toast toast3 = Toast.makeText(LoginActivity.this, "login fail - ID error", Toast.LENGTH_SHORT);
                            toast3.show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

    }
}