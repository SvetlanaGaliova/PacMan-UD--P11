 

import fri.shapesge.Stvorec;
import fri.shapesge.Kruh;

/**
 * HernaPlocha:
 * - v konstruktore len vykresli PacMan mapu podla daneho Stringu
 */
public class HernaPlocha{
    
    private int sirkaOkna = 608;
    private int vyskaOkna = 704;
    private int velkostDlazky = 32;
    private Hrac hrac;
    // 19 x 21
    // '#' = stena, '.' = pellet, ' ' = prazdne
    private String[] MAPA = {
        "###################",
        "#........#........#",
        "#.##.###.#.###.##.#",
        "#.##.###.#.###.##.#",
        "#.................#",
        "#.##.#.#####.#.##.#",
        "#....#...#...#....#",
        "####.###.#.###.####",
        "   #.#.......#.#   ",
        "####.#.## ##.#.####",
        ".......#   #.......",
        "####.#.#####.#.####",
        "   #.#...H...#.#   ",
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

    public HernaPlocha() {
        super();
        this.vykresliMapu();
    }

    /**
     * Prejde celu textovu a pre kazdy znak vytvori prislusny shape.
     */
    private void vykresliMapu() {
        for (int riadok = 0; riadok < MAPA.length; riadok++) {
            String r = MAPA[riadok];
            for (int stlpec = 0; stlpec < r.length(); stlpec++) {
                char ch = r.charAt(stlpec);

                int x = stlpec * this.velkostDlazky;
                int y = riadok * this.velkostDlazky;

                if (ch == '#') {
                    Stvorec stena = new Stvorec(velkostDlazky,velkostDlazky);
                    stena.zmenPolohu(x, y);
                    stena.zmenFarbu("blue");
                    stena.zobraz();
                } else if (ch == '.') {
                    Pellet pellet = new Pellet(x + (this.velkostDlazky/2), y + (this.velkostDlazky/2), this.velkostDlazky);
                } else if (ch == 'H'){
                    this.hrac = new Hrac(this, this.velkostDlazky, x + (this.velkostDlazky/2), y + (this.velkostDlazky/2));
                }
            }
        }
    }
    
    public String[] getMapa(){
        return this.MAPA;
    }
    
    public Hrac getHrac(){
        return this.hrac;
    }
    
    public void vymazePelet(){
        //posli skrySa(), a najdi na pozici, a na pozicii nastav null
    }
    
}