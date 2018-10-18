package com.seb.cheesechase;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

public class Main2Activity extends Activity {

    Activity context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        context = this;
        context.setTitle("Start GAME !");
        TextView nbcarte = findViewById(R.id.nbCarte);
        TextView score = findViewById(R.id.Score);
        TextView piege = findViewById(R.id.nbPiege);
        Dessin dessin = findViewById(R.id.idDessin);
        ImageButton nextcarte = findViewById(R.id.nextcarte);
        dessin.nextcarte = nextcarte;
        dessin.nbPiegeActif = piege;
        dessin.nbcarte = nbcarte;
        dessin.score = score;
    }
}
