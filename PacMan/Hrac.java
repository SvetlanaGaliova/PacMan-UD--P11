import fri.shapesge.Kruh;
/**
 * Trieda reprezentujuca postavu hraca. Riesi jeho pohyb po hernej ploche.
 * Pre jej spravny chod je potrebne mat nainstalovany ShapesGE engine od p. Janecha z predmetu INF1.
 *
 * @author Matej Komarc
 */

public class Hrac {
    private int x;
    private int y;
    private int zivoty;
    private int skore;
    private int velkostDlazdice;
    private int priemer;
    private Kruh kruh;
    private HernaPlocha plocha;
    
    /**
     * Vytvori a zobrazi graficky kruh reprezentujuci hraca (Pacmana). 
     * @param plocha Reprezentuje plochu na ktorej je zobrazeny hrac.
     * @param velkostDlazdice Predstavuje velkost jedneho pola v mriezke mapy.
     * @param x X-ova pozicia stredu hraca.
     * @param y Y-ova pozicia stredu hraca.
     */
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
    
    /**
     * Vrati int hodnotu skore
     * @return Int velkost skore.
     */
    public int getSkore() {
        return this.skore;
    }
    
    /**
     * Vrati hodnotu X-ovej suradnice stredu hraca.
     * @return Int cislo.
     */
    public int getX() {
        return this.x;
    }
    
    /**
     * Vrati hodnotu Y-ovej suradnice stredu hraca.
     * @return Int cislo.
     */
    public int getY() {
        return this.y;
    }
    
    /**
     * Vrati priemer kruhu hraca.
     * @return Int cislo.
     */
    public int getPriemer() {
        return this.priemer;
    }
    
    /**
     * Pripocita skore o dany pocet bodov.
     * @param kolko Kolko bodov ma pripocitat.
     */
    public void setSkore(int kolko) {
        this.skore += kolko;
    }
    
    /**
     * Metoda zabezpecuje pohyb hraca. Zaroven kontroluje ci hrac nevyjde za hranice mapy a zaroven
     * kontroluje tzv. warp tunel, teda prechod v priblizne polovici mapy z jednej strany na druhu.
     * @param smer Predstavuje enum smer do ktoreho chceme, aby sa hrac pohyboval.
     */
    public void pohniSa(Smer smer) {
        int krok = this.velkostDlazdice;
        int predpokladX = this.x;
        int predpokladY = this.y;
        
        //Nastavenie smeru pohybu Pacmana
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
        
        //Kontrola warp tunelu (iba v riadku s indexom 10)
        if (this.y / this.velkostDlazdice == 10) {
            //Kontrola warp tunelu vlavo
            if (predpokladX <= 0) {
                predpokladX = (this.plocha.getMapa()[10].length() * this.velkostDlazdice) + (this.velkostDlazdice / 2);
                this.x = predpokladX;
                this.y = predpokladY;
                this.kruh.zmenPolohu(this.x - (this.priemer/2), this.y - (this.priemer/2));
                return;
            }
            
            //Kontrola warp tunelu vpravo
            if (predpokladX >= this.plocha.getMapa()[10].length() * this.velkostDlazdice) {
                predpokladX = this.velkostDlazdice / 2;
                this.x = predpokladX - this.velkostDlazdice;
                this.y = predpokladY;
                this.kruh.zmenPolohu(this.x - (this.priemer/2), this.y - (this.priemer/2));
                return;
            }
        }
        
        //Kontrola pohybu a realizacia samotneho pohybu mimo wark tunela
        if (this.mozeSaPohnut(predpokladX, predpokladY, this.plocha.getMapa(), this.velkostDlazdice)) {
            this.x = predpokladX;
            this.y = predpokladY;
            this.kruh.zmenPolohu(this.x - (this.priemer/2), this.y - (this.priemer/2));
            int zisk = this.plocha.vymazePelet(this.x, this.y);
            if (zisk > 0) {
                this.setSkore(zisk);

            }
        }
    }
    
    private boolean mozeSaPohnut(int predpokladX, int predpokladY, String[] mapa, int velkost) {
        if (predpokladX < 0 || predpokladY < 0 || (predpokladX / velkost) >= mapa[0].length() || (predpokladY / velkost) >= mapa.length) {
            return false; //nachadza sa mimo hernej plochy
        }
        
        int cielRiadok = (predpokladY) / velkost;
        int cielStlpec = (predpokladX) / velkost;
        char ciel = mapa[cielRiadok].charAt(cielStlpec);
        
        if (ciel == '#') {
            return false;
        } else {
            return true;
        }
    }
    
    /**
     * Znizuje zivot hraca o hodnotu 1.
     */
    public void znizZivot() {
        this.zivoty--;
    }
}