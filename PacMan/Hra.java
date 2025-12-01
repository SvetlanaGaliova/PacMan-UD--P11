 
import fri.shapesge.Manazer;
/**
 * Zakladna trieda hry.
 * - drzi Manazer
 * - ma tik(), aby fungoval timer z .ini
 * - ma spust(), pauza(), ukonci()
 */
public class Hra {
    protected boolean bezi;
    protected int celkoveSkore;
    protected Manazer manazer;
    public Hra() {
        this.bezi = false;
        this.celkoveSkore = 0;
        this.manazer = new Manazer();
        this.manazer.spravujObjekt(this);
    }
    public void spust() {
        this.bezi = true;
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