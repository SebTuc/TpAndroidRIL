package com.imoroney.testintent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main3Activity extends Activity {

    Activity lecontext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Button b = findViewById(R.id.button3);
        lecontext = this;
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent defineIntent = new Intent(lecontext,Main2Activity.class);
                int codeRetour = 200;
                setResult(codeRetour,defineIntent);
                finish();
            }
        });
    }
}
