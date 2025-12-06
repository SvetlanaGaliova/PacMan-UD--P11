
import fri.shapesge.Manazer;
/**
 * Trieda Hra predstavuje hlavnú logiku hry.
 * Jej úlohou je:
 * - vytvoriť hernú plochu
 * - vytvoriť ovládanie hráča
 * - držať informáciu, či hra beží alebo je pauznutá
 * - držať a upravovať celkové skóre
 * @author (Lukas Blahut)
 * @version 3.0 (4.12.2025)
 */
public class Hra {
    private HernaPlocha hernaPlocha;
    private boolean bezi;
    private int celkoveSkore;
    private Manazer manazer;
    private Duch duch;
    private OvladanieHraca ovladac;
    public Hra() {
        this.bezi = false;
        this.celkoveSkore = 0;
        // vytvorenie Manazera a registrácia tohto objektu Hra
        this.manazer = new Manazer();
        this.manazer.spravujObjekt(this);
        // vytvorenie hernej plochy a ovládania hráča
        this.spust();
    }

    /**
     * Spustí hru.
     * 
     * Nastaví príznak bezi na true, vytvorí novú hernú plochu a nový ovládač hráča
     * Hráč sa získa z hernej plochy pomocou getHrac().
     */

    public void spust() {
        this.bezi = true;
        // vytvorenie novej hernej plochy (vykreslí mapu a postavy)
        this.hernaPlocha = new HernaPlocha();
        // vytvorenie ovládača na základe hráča z hernej plochy
        this.ovladac = new OvladanieHraca(this.hernaPlocha.getHrac());
    }

    /**
     * Preklopí stav pauzy hry.
     * 
     * Ak hra beží, nastaví sa na pauzu.
     * Ak je pauza, hra sa opäť spustí.
     */

    public void pauza() {
        this.bezi = !this.bezi;
    }

    /**
     * Ukončí hru a celý program.
     * 
     * Volá System.exit(0), čím sa ukončí beh aplikácie.
     */

    public void ukonci() {
        System.exit(0);
    }

    public void tik() {
        if(!this.bezi){
            return;
        }
        for(Duch d : this.hernaPlocha.getDuchovia()){
            d.pohniSa();
        }
        
    }

    /**
     * Vráti aktuálne celkové skóre hráča.
     * 
     * @return celkové skóre
     */

    public int getCelkoveSkore() {
        return this.celkoveSkore;
    }

    /**
     * Pripočíta zadanú hodnotu k celkovému skóre.
     * 
     * @param hodnota hodnota, ktorá sa má pripočítať k skóre
     */

    public void pridajSkore(int hodnota) {
        this.celkoveSkore += hodnota;
    }
}