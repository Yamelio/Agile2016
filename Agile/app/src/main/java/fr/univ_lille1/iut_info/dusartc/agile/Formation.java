package fr.univ_lille1.iut_info.dusartc.agile;

/**
 * Created by dusartc on 24/03/16.
 */
public class Formation {

    private String domaine;
    private String diplome;

    public Formation(String diplome, String domaine) {
        this.diplome = diplome;
        this.domaine = domaine;
    }

    public String getDiplome() {
        return diplome;
    }

    public String getDomaine() {
        return domaine;
    }

    @Override
    public String toString() {
        return "domaine: "+domaine+", diplome: "+diplome+"\n";
    }
}
