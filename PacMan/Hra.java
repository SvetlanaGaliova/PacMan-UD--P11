 
import fri.shapesge.Manazer;
/**
 * Zakladna trieda hry.
 * - drzi Manazer
 * - ma tik(), aby fungoval timer z .ini
 * - ma spust(), pauza(), ukonci()
 */
public class Hra {
    private HernaPlocha hernaPlocha;
    private boolean bezi;
    private int celkoveSkore;
    private Manazer manazer;
    private OvladanieHraca ovladac;
    public Hra() {
        this.bezi = false;
        this.celkoveSkore = 0;
        this.manazer = new Manazer();
        this.manazer.spravujObjekt(this);
        this.spust();
    }
    public void spust() {
        this.bezi = true;
        this.hernaPlocha = new HernaPlocha();
        this.ovladac = new OvladanieHraca(this.hernaPlocha.getHrac());
    }
    public void pauza() {
        this.bezi = !this.bezi;
    }
    public void ukonci() {
        System.exit(0);
    }
    
    public void tik() {
        // zatial prazdne
    }
    public int getCelkoveSkore() {
        return this.celkoveSkore;
    }
    public void pridajSkore(int hodnota) {
        this.celkoveSkore += hodnota;
    }
}