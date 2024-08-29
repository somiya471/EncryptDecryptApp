package com.example.encryptdecryptapp;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Encoder extends AppCompatActivity {
    EditText etenc;
    TextView enctv;
    ClipboardManager cpb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_encoder);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        etenc = findViewById(R.id.etVar1);
        enctv = findViewById(R.id.tvVar1);

        // create a clipboard manager variable to copy text
        cpb = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
    }

    // onClick function of encrypt button
    public void enc(View view) {
        // get text from edittext
        String temp = etenc.getText().toString();

        // pass the string to the encryption
        // algorithm and get the encrypted code
        String rv = Encode.encode(temp);

        // set the code to the edit text
        enctv.setText(rv);
    }

    // onClick function of copy text button
    public void cp2(View view) {
        // get the string from the textview and trim all spaces
        String data = enctv.getText().toString().trim();

        // check if the textview is not empty
        if (!data.isEmpty()) {

            // copy the text in the clip board
            ClipData temp = ClipData.newPlainText("text", data);
            cpb.setPrimaryClip(temp);

            // display message that the text has been copied
            Toast.makeText(this, "Copied", Toast.LENGTH_SHORT).show();
        }
    }

}
