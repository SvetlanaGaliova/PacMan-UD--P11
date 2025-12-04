/**
 * Trieda reprezentujuca postavu hraca.
 *
 * @author Matej Komarc
 * @version (a version number or a date)
 */
import fri.shapesge.Kruh;

public class Hrac {
    private int x;
    private int y;
    private int zivoty;
    private int skore;
    private int velkostDlazdice;
    private int priemer;
    private Kruh kruh;
    private HernaPlocha plocha;
    
    public Hrac(HernaPlocha plocha, int velkostDlazdice, int x, int y) {
        this.zivoty = 3;
        this.skore = 0;
        
        //vypocet stredu hraca (kvoli tomu aby bol hrac s tred konkrotneho policka)
        this.x = x;
        this.y = y;
        
        this.velkostDlazdice = velkostDlazdice;
        this.plocha = plocha;
        
        this.kruh = new Kruh();
        this.priemer = (this.velkostDlazdice/4) * 3;
        this.kruh.zmenPriemer(priemer);
        this.kruh.zmenPolohu(this.x - (this.priemer/2), this.y - (this.priemer/2));
        this.kruh.zmenFarbu("yellow");
        this.kruh.zobraz();
    }
    
    public int getSkore() {
        return this.skore;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public int getPriemer() {
        return this.priemer;
    }
    
    public void setSkore(int kolko) {
        this.skore =+ kolko;
    }
    
    public void pohniSa(Smer smer) {
        int krok = this.velkostDlazdice;
        int predpokladX = this.x;
        int predpokladY = this.y;
        
        switch (smer) {
            case HORE :
                predpokladY -= krok;
                break;
            case DOLE :
                predpokladY += krok;
                break;
            case DOLAVA:
                predpokladX -= krok;
                break;
            case DOPRAVA :
                predpokladX += krok;
                break;
        }
        
        if (this.mozeSaPohnut(predpokladX, predpokladY, this.plocha.getMapa(), this.velkostDlazdice)) {
            this.x = predpokladX;
            this.y = predpokladY;
        }
    }
    
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
    
    public void znizZivot() {
        this.zivoty--;
    }
}