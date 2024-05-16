package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Signup1Activity extends AppCompatActivity {
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference();

    private TextView Next_Btn;
    private EditText Name;
    private EditText Id;
    private EditText Passwd;
    private EditText Passwd_Check;
    private EditText Email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup1);

        Name=(EditText)findViewById(R.id.name_editText);
        Id=(EditText)findViewById(R.id.name_editText);
        Passwd=(EditText)findViewById(R.id.PW_editText);
        Passwd_Check=(EditText)findViewById(R.id.PW_check_editText);
        Email=(EditText)findViewById(R.id.email_editText);
        Next_Btn=(TextView)findViewById(R.id.next_textView);

        Next_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String NAME=Name.getText().toString();
                String ID=Id.getText().toString();
                String PW=Passwd.getText().toString();
                String PW_CHECK=Passwd_Check.getText().toString();
                String EMAIL=Email.getText().toString();

                myRef.child("UserData").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if(snapshot.child(ID).exists()){
                            Toast.makeText(getApplicationContext(),"Sign Fail- id", Toast.LENGTH_LONG).show();
                        }
                        else {
                            addUserData(NAME, ID, PW, EMAIL);
                            Toast.makeText(getApplicationContext(),"register success", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
    }
    public void addUserData(String Name, String Id, String passwd, String email){
        UserData userData=new UserData();
        userData.setUserName(Name);
        userData.setId(Id);
        userData.setPasswd(passwd);
        userData.setEmail(email);
        myRef.child("UserData").child(Id).setValue(userData);
    }
}