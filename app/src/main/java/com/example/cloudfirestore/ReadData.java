package com.example.cloudfirestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

public class ReadData extends AppCompatActivity {

    MaterialButton read;
    FirebaseFirestore db;
    SwitchMaterial male_female;
    String switchstatus = "";
    static final String TAG = "Read Data Activity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_data);
        db = FirebaseFirestore.getInstance();
        read = findViewById(R.id.readbtn);
        male_female = findViewById(R.id.male_female);
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (male_female.isChecked()){

                    switchstatus = "Female";

                }else {

                    switchstatus = "Male";
                }

                db.collection("user")
                        .whereEqualTo("Gender",switchstatus)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {

                                if (task.isSuccessful()){

                                    Toast.makeText(ReadData.this,"Successful",Toast.LENGTH_LONG).show();
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        Log.d(TAG, document.getId() + " => " + document.getData());
                                    }



                                }else {

                                    Toast.makeText(ReadData.this,"Failed",Toast.LENGTH_LONG).show();

                                }

                            }
                        });

            }
        });
    }
}