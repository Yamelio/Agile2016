package fr.univ_lille1.iut_info.dusartc.agile;

/**
 * Created by dusartc on 24/03/16.
 */
public class Voeu {

    private Etablissement etablissement;
    private Formation formation;

    public Voeu(String diplome, String domaine, String ville, String univ){
        etablissement = new Etablissement(ville, univ);
        formation = new Formation(diplome, domaine);
    }

    public Etablissement getEtablissement() {
        return etablissement;
    }

    public Formation getFormation() {
        return formation;
    }

    @Override
    public String toString() {
        return etablissement.toString() +", "+formation.toString()+"\n";
    }
}
