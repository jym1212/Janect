package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register1Activity extends AppCompatActivity {
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference();

    private Button RegisterBtn;
    private EditText Name;
    private EditText passwd;
    private EditText email;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register1);

        Name=(EditText)findViewById(R.id.name);
        passwd=(EditText)findViewById(R.id.pw);
        email=(EditText)findViewById(R.id.email);

        RegisterBtn=(Button)findViewById(R.id.registerBtn);

        RegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String NAME=Name.getText().toString();
                String PW=passwd.getText().toString();
                String EMAIL=email.getText().toString();

                myRef.child("UserData").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        UserData userCheck = snapshot.child(snapshot.getKey().toString()).getValue(UserData.class);

                        if(snapshot.child(EMAIL).exists()){
                            Toast.makeText(getApplicationContext(),"Sign Fail- id", Toast.LENGTH_LONG).show();
                        }
                        else {
                            addUserData(NAME, PW,EMAIL);
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
    public void addUserData(String Name, String passwd, String email){
        UserData userData=new UserData();
        userData.setUserName(Name);
        userData.setPasswd(passwd);
        userData.setEmail(email);
        myRef.child("UserData").setValue(userData);
    }
}