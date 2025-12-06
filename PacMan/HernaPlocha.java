import java.util.ArrayList;
import fri.shapesge.Stvorec;
import fri.shapesge.Kruh;

/**
 * Trieda HernaPlocha reprezentuje mapu hry Pacman.
 * Jej úlohou je:
 * - podľa textoveho poľa mapa vytvori a zobrazi objekty ktore reprezentuju každy svoj symbol.
 * - uchovávať referenciu na hráča.
 * - uchovávať dvojrozmerné pole pelletiek, aby sa dali neskôr z hernej plochy odstraňovať, keď ich hráč zje.
 * - poskytovať metódy na získanie mapy, hráča a peliet.
 * - poskytovať metódu na vymazanie pelletu podľa pozície hráča.
 * 
 * @author (Lukas Blahut)
 * @version 3.0 (4.12.2025)
 */
public class HernaPlocha{

    private int sirkaOkna = 608;
    private int vyskaOkna = 704;
    private int velkostDlazky = 32;
    private Hrac hrac;
    private Duch duch;
    
    private ArrayList<Duch> duchovia = new ArrayList<Duch>();
    
    /**
     * Dvojrozmerne pole peliet.
     * Index[riadok][stlpec] odpovedá pozícii v textovej mape.
     */
    private Pellet[][] peletky;
    /**
     * Textová reprezentácia hernej plochy.
     * Znaky:
     * - '#' = stena
     * - '.' = pellet
     * - ' ' = prázdne políčkp
     * - 'H' = hráč
     * - 'D' = duchovia
     */
    private String[] MAPA = {
            "###################",
            "#........#........#",
            "#.##.###.#.###.##.#",
            "#.##.###.#.###.##.#",
            "#........H........#",
            "#.##.#.#####.#.##.#",
            "#....#...#...#....#",
            "####.###.#.###.####",
            "   #.#.......#.#   ",
            "####.#.## ##.#.####",
            ".......#DDD#.......",
            "####.#.#####.#.####",
            "   #.#.......#.#   ",
            "####.#.#####.#.####",
            "#........#........#",
            "#.##.###.#.###.##.#",
            "#..#...........#..#",
            "##.#.#.#####.#.#.##",
            "#....#...#...#....#",
            "#.######.#.######.#",
            "#.................#",
            "###################"
        };
    /**
     * Konštruktor hernej plochy
     * Inicializuje pole peliet podľa veľkosti mapy a následne zavolá metódu vykresliMapu(), ktorá vytvorí všetky grafické objekty na ploche.
     */
    public HernaPlocha() {
        super();
        //vytvorenie poľa peliet podľa počtu riadkov a stĺpcov v mape
        this.peletky = new Pellet[this.MAPA.length][this.MAPA[0].length()];
        this.vykresliMapu();
    }

    /**
     * Prejde celu textovu mapu a pre každý znak vytvorí prislušný objekt.
     * Pre každý riadok a stĺpec:
     * - znak '#' vytvorí modrú stenu(Stvorec)
     * - znak '.' vytvorí Pellet a uloží ho do poľa peletky
     * - znak 'H' vytvorí hráča v danej pozícii
     * - znak 'D' vytvorí ducha v danej pozícii
     * - medzera je hodnota null
     */
    private void vykresliMapu() {
        for (int riadok = 0; riadok < MAPA.length; riadok++) {
            String r = MAPA[riadok];
            for (int stlpec = 0; stlpec < r.length(); stlpec++) {
                char ch = r.charAt(stlpec);
                // prepočet súradníc políčka (riadok, stĺpec) na pixely
                int x = stlpec * this.velkostDlazky;
                int y = riadok * this.velkostDlazky;

                if (ch == '#') {
                    // vytvorenie steny ako modrého štvorca
                    Stvorec stena = new Stvorec(velkostDlazky,velkostDlazky);
                    stena.zmenPolohu(x, y);
                    stena.zmenFarbu("blue");
                    stena.zobraz();
                    // na stene nemôže byť pellet
                    this.peletky[riadok][stlpec] = null;
                } else if (ch == '.') {
                    // vytvorenie pelletu, jeho stred je v strede dlaždice
                    Pellet pellet = new Pellet(x + (this.velkostDlazky/2), y + (this.velkostDlazky/2), this.velkostDlazky);
                    this.peletky[riadok][stlpec] = pellet;
                } else if (ch == 'H'){
                    // vytvorenie hráča v strede daného políčka
                    this.hrac = new Hrac(this, this.velkostDlazky, x + (this.velkostDlazky/2), y + (this.velkostDlazky/2));
                    this.peletky[riadok][stlpec] = null;
                }else if(ch == 'D'){
                    // vytvorenie ducha
                    Duch duch = new Duch(this, this.velkostDlazky, x, y, this.hrac);
                    this.duchovia.add(duch);
                    this.peletky[riadok][stlpec] = null;
                }else{
                    // prázdne políčko, bez steny a bez pelletu
                    this.peletky[riadok][stlpec] = null;
                }
            }
        }
    }

    /**
     * Vráti textovú reprezentáciu mapy.
     * 
     * @return pole reťazcov, ktoré popisujú mapu (steny, pelletky, hráča, duchov)
     */
    public String[] getMapa(){
        return this.MAPA;
    }

    /**
     * Vráti hráča, ktorý sa nachádza na tejto hernej ploche.
     * 
     * @return objekt Hrac vytvorený pri spracovaní znaku 'H'
     */
    public Hrac getHrac(){
        return this.hrac;
    }

    /**
     * Vráti dvojrozmerné pole peliet.
     * 
     * @return pole Pellet[][], kde peletky[riadok][stlpec] je pellet alebo null
     */
    public Pellet[][] getPelletky(){
        return this.peletky;
    }

    /**
     * Vráti atribút duch.

     * @return referenciu na ducha, ak by bola niekde nastavená, inak null
     */
    public Duch getDuch(){
        return this.duch;
    }
    
    public ArrayList<Duch> getDuchovia(){
        return this.duchovia;
    }

    /**
     * Pokúsi sa odstrániť pellet na pozícii daných súradnic
     * Najprv prepočíta súradnice x, y na index riadku a stĺpca.
     * Potom skontroluje, či v poli peletky na danej pozícii existuje pellet.
     * Ak áno, zavolá jeho metódu eat(), nastaví v poli hodnotu null, a vráti získané skóre.
     * Ak tam pellet nie je, vráti 0.
     * @param x x-ová súradnica v pixeloch (napríklad stred hráča)
     * @param y y-ová súradnica v pixeloch (napríklad stred hráča)
     * @return hodnota skóre za zjednutý pellet alebo 0, ak tam pellet nebol
     */
    public int vymazePelet(int x, int y){
        // prepočet súradníc v pixeloch na index riadku a stĺpca
        int riadok = y / this.velkostDlazky;
        int stlpec = x / this.velkostDlazky;

        // kontrola, či sa indexy nachádzajú v hraniciach poľa
        if(riadok < 0 || riadok >= this.MAPA.length || stlpec < 0 || stlpec >= this.MAPA[0].length()){
            return 0;
        }
        // získanie pelletu z poľa
        Pellet p = this.peletky[riadok][stlpec];
        if(p != null && p.jeAktivny()){
            // pellet existuje a ešte nebol zjedený,
            // zjedz ho a odstráň z poľa
            int zisk = p.eat();
            this.peletky[riadok][stlpec] = null;
            return zisk;
        }
        // na danej pozícii nebol žiadny aktívny pellet
        return 0;
    }
    public void posunDuchov(){
        for(Duch d : this.duchovia){
            d.tik();
        }
    }
}