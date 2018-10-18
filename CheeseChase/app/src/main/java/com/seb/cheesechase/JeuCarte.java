package com.seb.cheesechase;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class JeuCarte {

    public int tabJeu[][] = new int[80][80];
    
    static int SOURIS= 1;
    static int PIEGE=2;
    static int CHAT=3;
    static int FROMAGE=4;
    static int PIEGE_ANNULER=20;
    static int SOURIS_ANNULER=10;
    static int EMPLACEMENT_A_JOUER=5;

    public int Score = 0;
    
    public int nombreCarteSOURIS = 20;
    public int nombreCartePIEGE = 4;
    public int nombreCarteCHAT = 7;
    public int nombreCarteFROMAGE = 9;

    public int nombreCarteTOTAL = 40;

    public int nbPiegeActif = 0;

    public int prochaineCarteAJouer = 0;

    public void nextCarte(){
        int random = 1;
        boolean validation = false;
        
        while(validation != true){
            random=(int)(Math.ceil((Math.random()*100)/25));
            switch (random){
                case 1:
                    if(nombreCarteSOURIS == 0){
                        continue;
                    }else{
                        validation = true;
                    }
                    break;
                case 2:
                    if(nombreCartePIEGE == 0){
                        continue;
                    }else{
                        validation = true;
                    }
                    break;
                case 3:
                    if(nombreCarteCHAT == 0){
                        continue;
                    }else{
                        validation = true;
                    }
                    break;
                case 4:
                    if(nombreCarteFROMAGE == 0){
                        continue;
                    }else{
                        validation = true;
                    }
                    break;
            }
        }
        prochaineCarteAJouer = random;
        Log.e("ProchaineCarte: ",""+prochaineCarteAJouer);
    }
    public void startJeu(int x, int y , int typeCarte){
        tabJeu[y][x] = typeCarte;
        if(typeCarte == PIEGE){
            nbPiegeActif++;
        }
        MiseAJourScore();
        MiseAJourNbCarte(typeCarte);
        ajoutCaseAJouer(x,y);
        nextCarte();
    }

    public int PlayCarte(int x , int y , int typeCarte){
        int result = 0;
        if(x > 0 && y > 0) {
            if (tabJeu[y][x] == EMPLACEMENT_A_JOUER) {
                tabJeu[y][x] = typeCarte;
                if (typeCarte == PIEGE) {
                    nbPiegeActif++;
                }
                MiseAJourNbCarte(typeCarte);
                VerifAnnulation(x, y);
                MiseAJourScore();
                if(nombreCarteTOTAL != 0){
                    nextCarte();
                    ajoutCaseAJouer(x, y);
                }
                result = verificationFinJeu();
            }
        }

        return result;
    }

    private void MiseAJourScore(){
        //Compter le nombre de sourie actif
        //Compter le nombre de sourie qui sont a coter d'un fromage
        //Compter les Fromage encercler ( si plusieur regrouper -> multiplier )
        Score = 0;

        for(int i = 0; i < tabJeu.length; i++){
            for(int j = 0; j < tabJeu[i].length; j++){
                if(tabJeu[j][i] == 1){
                    Score++;
                    if(tabJeu[j-1][i] == 4){
                        Score++;
                    }
                    if(tabJeu[j][i-1] == 4){
                        Score++;
                    }
                    if(tabJeu[j][i+1] == 4){
                        Score++;
                    }
                    if(tabJeu[j+1][i] == 4){
                        Score++;
                    }
                }else if(tabJeu[j][i] == 4){
                    int countSourie=0;
                    if(tabJeu[j-1][i] == 1){
                        countSourie++;
                    }
                    if(tabJeu[j][i-1] == 1){
                        countSourie++;
                    }
                    if(tabJeu[j][i+1] == 1){
                        countSourie++;
                    }
                    if(tabJeu[j+1][i] == 1){
                        countSourie++;
                    }
                    if(countSourie == 4){
                        Score+=10;
                    }
                }
                //Reste a faire plusieur fromage encercler...
            }
        }
    }

    public void ajoutCaseAJouer(int x, int y){
        //On enleve tout les autre 5 -> zone de jeu
        enleverLesZoneAJouer();
        for(int i = 0 ; i < 3;i++){
            for(int j = 0 ; j < 3;j++){
                if(tabJeu[(y-1)+i][(x-1)+j] == 0){
                    tabJeu[(y-1)+i][(x-1)+j] = EMPLACEMENT_A_JOUER ;
                }
            }
        }
    }

    private void enleverLesZoneAJouer(){
        for(int i = 0; i < tabJeu.length; i++){
            for(int j = 0; j < tabJeu[i].length; j++){
                if(tabJeu[i][j] == EMPLACEMENT_A_JOUER){
                    tabJeu[i][j] = 0;
                }
            }
        }
    }

    private void VerifAnnulation(int x, int y){
        int countSouris = 0;
        ////////////////////////////////////////////////////////////////////////////////////////
        if(tabJeu[y][x] == CHAT){
            if(tabJeu[y-1][x] == SOURIS){
                tabJeu[y-1][x] = SOURIS_ANNULER;
                VerifAnnulation(x,y-1);
            }
            if(tabJeu[y][x-1] == SOURIS){
                tabJeu[y][x-1] = SOURIS_ANNULER;
                VerifAnnulation(x-1,y);
            }
            if(tabJeu[y][x+1] == SOURIS){
                tabJeu[y][x+1] = SOURIS_ANNULER;
                VerifAnnulation(x+1,y);
            }
            if(tabJeu[y+1][x] == SOURIS){
                tabJeu[y+1][x] = SOURIS_ANNULER;
                VerifAnnulation(x,y+1);
            }
         ////////////////////////////////////////////////////////////////////////////////////////
        }else if(tabJeu[y][x] == PIEGE){
            if(tabJeu[y-1][x] == SOURIS){
                countSouris++;
            }
            if(tabJeu[y][x-1] == SOURIS){
                countSouris++;
            }
            if(tabJeu[y][x+1] == SOURIS){
                countSouris++;
            }

            if(tabJeu[y+1][x] == SOURIS){
                countSouris++;
            }
            if(countSouris == 4){
                tabJeu[y][x] = PIEGE_ANNULER;
                nbPiegeActif--;
            }
         ////////////////////////////////////////////////////////////////////////////////////////
        }else if(tabJeu[y][x] == SOURIS_ANNULER){
            if(tabJeu[y+1][x] == PIEGE_ANNULER){
                nbPiegeActif++;
                tabJeu[y+1][x] = PIEGE;
            }
            if(tabJeu[y][x-1] == PIEGE_ANNULER){
                nbPiegeActif++;
                tabJeu[y][x-1] = PIEGE;
            }
            if(tabJeu[y][x+1] == PIEGE_ANNULER){
                nbPiegeActif++;
                tabJeu[y][x+1] = PIEGE;
            }
            if(tabJeu[y-1][x] == PIEGE_ANNULER){
                nbPiegeActif++;
                tabJeu[y-1][x] = PIEGE;
            }
        ////////////////////////////////////////////////////////////////////////////////////////
        }else if(tabJeu[y][x] == SOURIS){
            if(tabJeu[y+1][x] == CHAT){
                tabJeu[y][x] = SOURIS_ANNULER;
                VerifAnnulation(x,y+1);
            }
            if(tabJeu[y][x-1] == CHAT){
                tabJeu[y][x] = SOURIS_ANNULER;
                VerifAnnulation(x-1,y);
            }
            if(tabJeu[y][x+1] == CHAT){
                tabJeu[y][x] = SOURIS_ANNULER;
                VerifAnnulation(x+1,y);
            }
            if(tabJeu[y-1][x] == CHAT){
                tabJeu[y][x] = SOURIS_ANNULER;
                VerifAnnulation(x,y-1);
            }
            //------  PIEGE -------
            if(tabJeu[y+1][x] == PIEGE){
                VerifAnnulation(x,y+1);
            }
            if(tabJeu[y][x-1] == PIEGE){
                VerifAnnulation(x-1,y);
            }
            if(tabJeu[y][x+1] == PIEGE){
                VerifAnnulation(x+1,y);
            }
            if(tabJeu[y-1][x] == PIEGE){
                VerifAnnulation(x,y-1);
            }

        }
    }
    
    private int verificationFinJeu(){
        //Sois plus de carte
        //Sois il y a 3 piege
        //Impossibilité de jouer (aucun 5)
        //0-> R.A.S. 1-> Victoire 2->Defaite
        int retourFinDeJeu = 0;
        if(nbPiegeActif == 3){
            Score = 0;
            retourFinDeJeu = 2;
        }
        if(!PossibleJouer()){
            Score = 0;
            retourFinDeJeu = 2;
        }
        if(nombreCarteTOTAL == 0){
            retourFinDeJeu = 1;
        }
        return retourFinDeJeu;
    }

    private boolean PossibleJouer(){
        for(int i = 0; i < tabJeu.length; i++){
            for(int j = 0; j < tabJeu[i].length; j++){
                if(tabJeu[i][j] == EMPLACEMENT_A_JOUER){
                    return true;
                }
            }
        }
        return false;
    }
    
    
    private void MiseAJourNbCarte(int typeCarte){
        switch (typeCarte){
            case 1:
                nombreCarteSOURIS--;
                break;
            case 2:
                nombreCartePIEGE--;
                break;
            case 3:
                nombreCarteCHAT--;
                break;
            case 4:
                nombreCarteFROMAGE--;
                break;
        }
        nombreCarteTOTAL--;
    }
    /*
    Renvoie la liste de valeur des nombre de carte
    1->SOURIS
    2->PIEGE
    3->CHAT
    4->FROMAGE
    5->NB TOTAL DE CARTE

    Return TabValeur
     */
    public List<Integer> GetNombreCarteRestante(){
        ArrayList<Integer> liste = new ArrayList<Integer>();
        liste.add(nombreCarteSOURIS);
        liste.add(nombreCartePIEGE);
        liste.add(nombreCarteCHAT);
        liste.add(nombreCarteFROMAGE);
        liste.add(nombreCarteTOTAL);
        return liste;
    }
}
