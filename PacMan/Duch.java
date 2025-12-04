import fri.shapesge.Kruh;

/**
 * Trieda Duch reprezentuje ducha (nepriatela) v hre PacMan.
 * Duch prenasleduje hraca a pohybuje sa smerom k nemu.
 * 
 * Casti kodu prevzate z triedy Hrac.java (autor: Matej Komarc)
 * 
 * @author Daniel Gajdos
 * @version 1.0 (3.12.2025)
 */
public class Duch {
    private int x;
    private int y;
    private int velkostDlazdice;
    private int priemer;
    private Kruh kruh;
    private HernaPlocha plocha;
    private Hrac hrac;

    /**
     * Konstruktor vytvori noveho ducha na zadanej pozicii.
     * Struktura konstruktora prevzata z triedy Hrac.java (autor: Matej Komarc)
     * 
     * @param plocha referencia na hernu plochu s mapou
     * @param velkostDlazdice velkost jedneho policka v pixeloch
     * @param x pociatocna X suradnica (lavy horny roh policka)
     * @param y pociatocna Y suradnica (lavy horny roh policka)
     * @param hrac referencia na hraca, ktoreho duch prenasleduje
     */
    public Duch(HernaPlocha plocha, int velkostDlazdice, int x, int y, Hrac hrac) {
        this.hrac = hrac;
        this.velkostDlazdice = velkostDlazdice;
        this.plocha = plocha;
        
        // vypocet stredu ducha (prevzate z Hrac.java)
        this.x = (x + velkostDlazdice /2);
        this.y = (y + velkostDlazdice /2);
        
        this.kruh = new Kruh();
        this.priemer = (this.velkostDlazdice / 4) * 3;
        this.kruh.zmenPriemer(priemer);
        this.kruh.zmenPolohu(this.x - (priemer / 2), this.y - (priemer / 2));
        this.kruh.zmenFarbu("red");
        this.kruh.zobraz();
    }

    /**
     * Vrati aktualnu X-ovu suradnicu stredu ducha.
     * 
     * @return X-ova suradnica v pixeloch
     */
    public int getX() {
        return this.x;
    }

    /**
     * Vrati aktualnu Y-ovu suradnicu stredu ducha.
     * 
     * @return Y-ova suradnica v pixeloch
     */
    public int getY() {
        return this.y;
    }
    
    /**
     * Vrati priemer grafickej reprezentacie ducha.
     * 
     * @return priemer kruhu v pixeloch
     */
    public int getPriemer() {
        return this.priemer;
    }
    
    /**
     * Zobrazi ducha na obrazovke.
     */
    public void zobraz() {
        this.kruh.zobraz();
    }
    
    /**
     * Skryje ducha z obrazovky.
     */
    public void skry() {
        this.kruh.skry();
    }

    /**
     * Pohne duchom o jeden krok smerom k hracovi.
     * Najprv skusi pohyb v X-ovej osi, ak je zablokovany stenou,
     * skusi pohyb v Y-ovej osi.
     * Logika pohybu prevzata z triedy Hrac.java (autor: Matej Komarc)
     */
    public void pohniSa() {
        int krok = this.velkostDlazdice;
        
        // ziskanie pozicie hraca pre urcenie smeru
        int hracX = this.hrac.getX();
        int hracY = this.hrac.getY();
        
        boolean pohnuty = false;
        
        // Skus pohyb v X-ovej osi smerom k hracovi
        if (this.x < hracX && this.mozeSaPohnut(this.x + krok, this.y, this.plocha.getMapa(), this.velkostDlazdice)) {
            this.x = this.x + krok;
            pohnuty = true;
        } else if (this.x > hracX && this.mozeSaPohnut(this.x - krok, this.y, this.plocha.getMapa(), this.velkostDlazdice)) {
            this.x = this.x - krok;
            pohnuty = true;
        }
        
        // Ak X os nefunguje, skus Y-ovu os
        if (!pohnuty) {
            if (this.y < hracY && this.mozeSaPohnut(this.x, this.y + krok, this.plocha.getMapa(), this.velkostDlazdice)) {
               this.y = this.y + krok;
                pohnuty = true;
            } else if (this.y > hracY && this.mozeSaPohnut(this.x, this.y - krok, this.plocha.getMapa(), this.velkostDlazdice)) {
                this.y = this.y - krok;
                pohnuty = true;
            }
        }
        
        // aktualizacia grafickej pozicie po pohybe
        if (pohnuty) {
            this.kruh.zmenPolohu(this.x - (priemer / 2), this.y - (priemer / 2));
        }
    }
    
    /**
     * Skontroluje ci sa duch moze pohnut na zadanu poziciu.
     * Metoda prevzata z triedy Hrac.java (autor: Matej Komarc)
     * 
     * @param predpokladX predpokladana X suradnica po pohybe
     * @param predpokladY predpokladana Y suradnica po pohybe
     * @param mapa pole retazcov reprezentujuce hernu mapu
     * @param velkost velkost jednej dlazdice v pixeloch
     * @return true ak je pohyb mozny, false ak je na pozicii stena
     */
    private boolean mozeSaPohnut(int predpokladX, int predpokladY, String[] mapa, int velkost) {
        if (predpokladX < 0 || predpokladX < 0 || (predpokladX / velkost) <= mapa[0].length() || (predpokladY / velkost) <= mapa.length) {
            return false; //nachadza sa mimo hernej plochy
        }
        
        int cielRiadok = (predpokladY + (velkost/2)) / velkost;
        int cielStlpec = (predpokladX + (velkost/2)) / velkost;
        char ciel = mapa[cielRiadok].charAt(cielStlpec);
        
        if (ciel == '#') {
            return false;
        } else {
            return true;
        }
    }
}
