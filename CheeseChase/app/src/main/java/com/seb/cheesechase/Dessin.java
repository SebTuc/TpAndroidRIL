package com.seb.cheesechase;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import static android.view.MotionEvent.INVALID_POINTER_ID;

public class Dessin extends View implements View.OnTouchListener {

    public TextView nbcarte;
    public TextView score;
    public ImageButton nextcarte;
    public TextView nbPiegeActif;
    Bitmap  mBitmap;
    public class Chat{
        int xc,yc;
        private Paint paint;
        Bitmap  mBitmap;
        Bitmap chat = BitmapFactory.decodeResource(getResources(), R.drawable.cat);
        public Chat(int x, int y){
            xc = x;
            yc = y;
            mBitmap = Bitmap.createBitmap(x, y, Bitmap.Config.ARGB_8888);
            mBitmap = Bitmap.createScaledBitmap(chat, dimmensionCarte, dimmensionCarte, true);
        }
        public void Draw(Canvas canvas){
            canvas.drawBitmap(mBitmap,yc,xc,null);
        }
    }

    public class Souris{
        int xc,yc;
        private Paint paint;

        Bitmap souris = BitmapFactory.decodeResource(getResources(), R.drawable.mouse);
        public Souris(int x, int y){
            xc = x;
            yc = y;
            mBitmap = Bitmap.createBitmap(x, y, Bitmap.Config.ARGB_8888);
            mBitmap = Bitmap.createScaledBitmap(souris, dimmensionCarte, dimmensionCarte, true);
        }

        public void Draw(Canvas canvas){
            canvas.drawBitmap(mBitmap,yc,xc,null);
        }
    }
    public class Cellajouer{
        int xc,yc;
        private Paint paint;
        Bitmap ajouer = BitmapFactory.decodeResource(getResources(), R.drawable.ajout);
        public Cellajouer(int x, int y){
            xc = x;
            yc = y;
            mBitmap = Bitmap.createBitmap(x, y, Bitmap.Config.ARGB_8888);
            mBitmap = Bitmap.createScaledBitmap(ajouer, dimmensionCarte, dimmensionCarte, true);
        }

        public void Draw(Canvas canvas){
            canvas.drawBitmap(mBitmap,yc,xc,null);
        }
    }
    public class Piege{
        int xc,yc;
        private Paint paint;
        Bitmap piege = BitmapFactory.decodeResource(getResources(), R.drawable.mousetrap);
        public Piege(int x, int y){
            xc = x;
            yc = y;
            mBitmap = Bitmap.createBitmap(x, y, Bitmap.Config.ARGB_8888);
            mBitmap = Bitmap.createScaledBitmap(piege, dimmensionCarte, dimmensionCarte, true);
        }
        public void Draw(Canvas canvas){
            canvas.drawBitmap(mBitmap,yc,xc,null);
        }
    }
    public class Fromage{
        int xc,yc;
        private Paint paint;
        Bitmap cheese = BitmapFactory.decodeResource(getResources(), R.drawable.cheese);
        public Fromage(int x, int y){
            xc = x;
            yc = y;
            mBitmap = Bitmap.createBitmap(x, y, Bitmap.Config.ARGB_8888);
            mBitmap = Bitmap.createScaledBitmap(cheese, dimmensionCarte, dimmensionCarte, true);
        }
        public void Draw(Canvas canvas){
            canvas.drawBitmap(mBitmap,yc,xc,null);
        }
    }
    public class SourisAnnuler{
        int xc,yc;
        private Paint paint;
        Bitmap sourisannuler = BitmapFactory.decodeResource(getResources(), R.drawable.mouseannuler);
        public SourisAnnuler(int x, int y){
            xc = x;
            yc = y;
            mBitmap = Bitmap.createBitmap(x, y, Bitmap.Config.ARGB_8888);
            mBitmap = Bitmap.createScaledBitmap(sourisannuler, dimmensionCarte, dimmensionCarte, true);
        }
        public void Draw(Canvas canvas){
            canvas.drawBitmap(mBitmap,yc,xc,null);
        }
    }

    public class PiegeAnnuler{
        int xc,yc;
        private Paint paint;
        Bitmap piegeannuler = BitmapFactory.decodeResource(getResources(), R.drawable.piegeannuler);
        public PiegeAnnuler(int x, int y){
            xc = x;
            yc = y;
            mBitmap = Bitmap.createBitmap(x, y, Bitmap.Config.ARGB_8888);
            mBitmap = Bitmap.createScaledBitmap(piegeannuler, dimmensionCarte, dimmensionCarte, true);
        }
        public void Draw(Canvas canvas){
            canvas.drawBitmap(mBitmap,yc,xc,null);
        }
    }

    int dimmensionCarte = 200;
    static final int MIN_CARTE_SIZE = 100;
    static final int MAX_CARTE_SIZE = 500;

    JeuCarte jeu = new JeuCarte();
    int deltax = 37*dimmensionCarte;
    int deltay = 37*dimmensionCarte;
    int predx;
    int predy;
    boolean gameover =false;


    public Dessin(Context context, AttributeSet attrs) {
        super(context, attrs);
        jeu.startJeu(40,40,1);
        this.setOnTouchListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        /*
        SOURIS= 1
        PIEGE=2
        CHAT=3
        FROMAGE=4
        PIEGE_ANNULER=20
        SOURIS_ANNULER=10
        EMPLACEMENT_A_JOUER=5
         */
        score.setText("Score : " + jeu.Score);
        nbcarte.setText("Cartes : " + jeu.nombreCarteTOTAL);
        nbPiegeActif.setText("actif piege : " + jeu.nbPiegeActif);
        nextcarte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"Chat : " + jeu.nombreCarteCHAT
                                + "\nSourie : " + jeu.nombreCarteSOURIS
                                + "\nFromage : " + jeu.nombreCarteFROMAGE
                                + "\nPiege : " + jeu.nombreCartePIEGE,
                        Toast.LENGTH_SHORT).show();
            }
        });
        if(jeu.prochaineCarteAJouer == 1){
            Bitmap souris = BitmapFactory.decodeResource(getResources(), R.drawable.mouse);
            mBitmap = Bitmap.createScaledBitmap(souris, dimmensionCarte, dimmensionCarte, true);
            nextcarte.setImageBitmap(mBitmap);
        }
        if(jeu.prochaineCarteAJouer == 2){
            Bitmap piege = BitmapFactory.decodeResource(getResources(), R.drawable.mousetrap);
            mBitmap = Bitmap.createScaledBitmap(piege, dimmensionCarte, dimmensionCarte, true);
            nextcarte.setImageBitmap(mBitmap);
        }
        if(jeu.prochaineCarteAJouer == 3){
            Bitmap chat = BitmapFactory.decodeResource(getResources(), R.drawable.cat);
            mBitmap = Bitmap.createScaledBitmap(chat, dimmensionCarte, dimmensionCarte, true);
            nextcarte.setImageBitmap(mBitmap);
        }
        if(jeu.prochaineCarteAJouer == 4){
            Bitmap fromage = BitmapFactory.decodeResource(getResources(), R.drawable.cheese);
            mBitmap = Bitmap.createScaledBitmap(fromage, dimmensionCarte, dimmensionCarte, true);
            nextcarte.setImageBitmap(mBitmap);
        }
        for(int i = 0; i < jeu.tabJeu.length; i++){
            for(int j = 0; j < jeu.tabJeu[i].length; j++){
                int posX = (i*dimmensionCarte)-deltay;
                int posY = (j*dimmensionCarte)-deltax;
                if(posX > 0) {
                    if (posY > 0) {
                        if (jeu.tabJeu[i][j] == 1) {
                            Souris s = new Souris(posX, posY);
                            s.Draw(canvas);
                        }
                        if (jeu.tabJeu[i][j] == 2) {
                            Piege p = new Piege(posX, posY);
                            p.Draw(canvas);

                        }
                        if (jeu.tabJeu[i][j] == 3) {
                            Chat c = new Chat(posX, posY);
                            c.Draw(canvas);
                        }
                        if (jeu.tabJeu[i][j] == 4) {
                            Fromage f = new Fromage(posX, posY);
                            f.Draw(canvas);
                        }
                        if (jeu.tabJeu[i][j] == 5) {
                            //Log.e("Test Tab = ",""+jeu.tabJeu[j][i]);
                            if (gameover == false) {
                                Cellajouer ajouer = new Cellajouer(posX, posY);
                                ajouer.Draw(canvas);
                            }
                        }
                        if (jeu.tabJeu[i][j] == 10) {
                            SourisAnnuler sourisannuler = new SourisAnnuler(posX, posY);
                            sourisannuler.Draw(canvas);
                        }
                        if (jeu.tabJeu[i][j] == 20) {
                            PiegeAnnuler piegeannuler = new PiegeAnnuler(posX, posY);
                            piegeannuler.Draw(canvas);
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int x = (int)motionEvent.getX();
        int y = (int)motionEvent.getY();


        switch ((motionEvent.getAction())){
            case MotionEvent.ACTION_DOWN:

                predx = deltax + x;
                predy = deltay + y;

                if(gameover == false) {
                    int result = jeu.PlayCarte((x + deltax) / dimmensionCarte, (y+deltay) / dimmensionCarte, jeu.prochaineCarteAJouer);

                    Log.e("Test : ",""+jeu.nbPiegeActif);
                    if(result == 2){
                        Toast.makeText(getContext(),"Défaite",
                                Toast.LENGTH_SHORT).show();
                        gameover = true;
                    }else if(result == 1){
                        Toast.makeText(getContext(),"Victoire ! \nScore final : " + jeu.Score,
                                Toast.LENGTH_SHORT).show();
                    }
                }
                break;
                case MotionEvent.ACTION_MOVE:

                    this.deltax = predx - x ;
                    this.deltay = predy - y;


                    break;
        }
        this.invalidate();
        return true;
    }


}