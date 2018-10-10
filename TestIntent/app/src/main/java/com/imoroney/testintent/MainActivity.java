package com.imoroney.testintent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity{

    private Button button;
    Activity lecontext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        lecontext = this;
        final EditText textChampSaisie = (EditText)findViewById(R.id.editText);
        button = (Button)findViewById(R.id.button);

        button.setOnClickListener(new Button.OnClickListener(){

            public void onClick(View view){
                Intent defineIntent = new Intent(lecontext,Main2Activity.class);
                Bundle objetbunble = new Bundle();
                objetbunble.putString("passInfo",textChampSaisie.getText().toString());
                defineIntent.putExtras(objetbunble);
                lecontext.startActivity(defineIntent);
            }
        });
    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == 1) {
                final EditText textChampSaisie = (EditText) findViewById(R.id.editText);
                textChampSaisie.setText(data.getStringExtra("passInfo"));
            }
        }
    }*/
    /*public void onClick(View v){
        if(v==button){
            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
            intent.putExtra(SearchManager.QUERY,"Tommy Guy");
            startActivity(intent);
            Intent intent = new Intent(this,Main2Activity.class);
            startActivity(intent);
        }
    }*/
}
