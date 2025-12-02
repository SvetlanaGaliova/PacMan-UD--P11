import fri.shapesge.Kruh;

/**
 * Write a description of class Pellet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pellet {
    public static final int HODNOTA_SKORE = 20;
    //Hráčovi sa defaultne zvyšuje skóre o 20
    private Kruh kruh;
    private boolean jeZjedeny;
    private int x;
    private int y;
    private int velkost;
    
    public Pellet(int x, int y, int velkostDlazdice) {
        this.x = x;
        this.y = y;
        this.velkost = velkostDlazdice;
        
        this.kruh = new Kruh();
        this.kruh.zmenPriemer((this.velkost/4));
        this.kruh.zmenPolohu(this.x, this.y);
        this.kruh.zmenFarbu("Pink");
        this.jeZjedeny = false;
        this.kruh.zobraz();
    }
    
        public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }

        public Kruh getKruh() {
        return kruh;
    }
 
    public boolean jeAktivny() {
        return !jeZjedeny;
    }
    
    
    public int eat() {
        if (!jeZjedeny) {
            this.jeZjedeny = true;
            
            this.kruh.skry();
            return HODNOTA_SKORE;
        }
        
        return 0;    
    }
    
    
    
    
}
