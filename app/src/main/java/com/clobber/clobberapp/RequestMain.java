package com.clobber.clobberapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Member;


public class RequestMain extends AppCompatActivity {
    EditText productType, description, colour, size, quantity, address1, address2;
    Button btnRequest,btnRefresh,btnPayNow;
    TextView verify;
    DatabaseReference reff;
    Request member;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        Toast.makeText(RequestMain.this, "Connected To Firebase",Toast.LENGTH_LONG).show();

        btnPayNow = (Button) findViewById(R.id.btnPayNow);
        btnPayNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPayNow();
            }
        });

        productType=(EditText)findViewById(R.id.productType);
        description=(EditText)findViewById(R.id.description);
        colour=(EditText)findViewById(R.id.colour);
        size=(EditText)findViewById(R.id.size);
        quantity=(EditText)findViewById(R.id.quantity);
        address1=(EditText)findViewById(R.id.address1);
        address2=(EditText)findViewById(R.id.address2);
        btnRequest=(Button)findViewById(R.id.btnRequest);
        btnPayNow=(Button)findViewById(R.id.btnPayNow);
        verify=(TextView)findViewById(R.id.verify);
        member=new Request();
        reff= FirebaseDatabase.getInstance().getReference().child("Request");


        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantit=Integer.parseInt(quantity.getText().toString().trim());

                member.setProductType(productType.getText().toString().trim());
                member.setDescription(description.getText().toString().trim());
                member.setColour(colour.getText().toString().trim());
                member.setSize(size.getText().toString().trim());
                member.setQuantity(quantit);
                member.setAddress1(address1.getText().toString().trim());
                member.setAddress2(address2.getText().toString().trim());
                reff.push().setValue(member);
                Toast.makeText(RequestMain.this,"Your request is being processed. Please press Pay Now to see your price",Toast.LENGTH_LONG).show();
            }
        });


    }

    public void openPayNow(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}

