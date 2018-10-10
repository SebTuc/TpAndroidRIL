package com.imoroney.testintent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends Activity {

    Activity lecontext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Button button2 = findViewById(R.id.button2);
        lecontext = this;
        lecontext.setTitle("fenetre 2");
        final TextView textchampsaisie = (TextView) findViewById(R.id.textView);
        Bundle objetbunble = this.getIntent().getExtras();
        final String InfoPasse= objetbunble.getString("passInfo");
        textchampsaisie.setText(InfoPasse);

        button2.setOnClickListener(new Button.OnClickListener(){

            public void onClick(View view){
                Intent defineIntent = new Intent(lecontext,MainActivity.class);
                Bundle objetbunble = new Bundle();
                objetbunble.putString("passInfo","Hello World");
                defineIntent.putExtras(objetbunble);
                int codeRetour = 1;
                setResult(codeRetour,defineIntent);
                finish();
            }
        });
    }
}
