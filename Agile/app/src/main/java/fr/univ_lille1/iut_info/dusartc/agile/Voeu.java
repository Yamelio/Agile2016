package fr.univ_lille1.iut_info.dusartc.agile;

import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * Created by dusartc on 24/03/16.
 */
public class Voeu implements Serializable, Comparable<Voeu> {

    private Etablissement etablissement;
    private Formation formation;
    private int order;

    public Voeu(String diplome, String domaine, String ville, String univ, int order){
        etablissement = new Etablissement(ville, univ);
        formation = new Formation(diplome, domaine);
        this.order = order;
    }

    public Voeu(Etablissement etablissement, Formation formation){
        this.formation = formation;
        this.etablissement = etablissement;
    }

    public Etablissement getEtablissement() {
        return etablissement;
    }

    public Formation getFormation() {
        return formation;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public void setEtablissement(Etablissement etablissement) {
        this.etablissement = etablissement;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    @Override
    public String toString() {
        return etablissement.toString() +", "+formation.toString();
    }

    @Override
    public int compareTo(@NonNull Voeu another) {
        if (order < another.order)
            return -1;
        if (order > another.order)
            return 1;
        return 0;
    }
}
