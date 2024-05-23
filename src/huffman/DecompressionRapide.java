package huffman;

import java.io.IOException;

import gestion.GestionFichierBinaire;

public class DecompressionRapide {

    public static String decompresser(String nomFichierCompresse, Noeud racine) {
        String texteDecompresse = "";
        String texteBinaire = null;
        try {
            texteBinaire = GestionFichierBinaire.lecture(nomFichierCompresse);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        texteDecompresse = decompresserAvecArbre(texteBinaire, racine);
        return texteDecompresse;
    }

    private static String decompresserAvecArbre(String texteBinaire, Noeud racine) {
        StringBuilder texteDecompresse = new StringBuilder();
        Noeud noeudCourant = racine;
        
        for (int i = 0; i < texteBinaire.length(); i++) {
            char bit = texteBinaire.charAt(i);
            noeudCourant = (bit == '0') ? noeudCourant.getGauche() : noeudCourant.getDroite();
            
            if (noeudCourant.getGauche() == null && noeudCourant.getDroite() == null) {
                texteDecompresse.append(noeudCourant.getCaractere());
                noeudCourant = racine; 
            }
        }
        
        return texteDecompresse.toString();
    }
}