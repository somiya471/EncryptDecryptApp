package com.example.encryptdecryptapp;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Decoder extends AppCompatActivity {
    EditText etdec;
    TextView dectv;
    ClipboardManager cplboard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_decoder);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        etdec = findViewById(R.id.etVar1);
        dectv = findViewById(R.id.tvVar2);

        // create a clipboard manager variable to copy text
        cplboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
    }

    // onClick function of encrypt button
    public void dec(View view) {
        // get code from edittext
        String temp = etdec.getText().toString();
        Log.e("dec", "text - " + temp);

        // pass the string to the decryption algorithm
        // and get the decrypted text
        String rv = Decode.decode(temp);

        // set the text to the edit text for display
        dectv.setText(rv);
        Log.e("dec", "text - " + rv);
    }

    // onClick function of copy text button
    public void cpl(View view) {

        // get the string from the textview and trim all spaces
        String data = dectv.getText().toString().trim();

        // check if the textview is not empty
        if (!data.isEmpty()) {

            // copy the text in the clip board
            ClipData temp = ClipData.newPlainText("text", data);

            // display message that the text has been copied
            Toast.makeText(this, "Copied", Toast.LENGTH_SHORT).show();
        }
    }
}