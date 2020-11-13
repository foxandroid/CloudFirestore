package com.example.cloudfirestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText firstName, lastName, age;
    MaterialButton Registerbtn;
    FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = FirebaseFirestore.getInstance();
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        age = findViewById(R.id.age);
        Registerbtn = findViewById(R.id.btnRegister);
        Registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Firstname = firstName.getText().toString();
                String Lastname = lastName.getText().toString();
                String Age = age.getText().toString();
                Map<String,Object> user = new HashMap<>();
                user.put("First Name",Firstname);
                user.put("Last Name",Lastname);
                user.put("Age",Age);

                db.collection("user")
                        .add(user)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(MainActivity.this,"Successful",Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull @NotNull Exception e) {

                        Toast.makeText(MainActivity.this,"Failed",Toast.LENGTH_SHORT).show();


                    }
                });

            }
        });

    }
}