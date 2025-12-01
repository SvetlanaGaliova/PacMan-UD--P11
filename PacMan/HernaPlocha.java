 

import fri.shapesge.Stvorec;
import fri.shapesge.Kruh;

/**
 * HernaPlocha:
 * - v konstruktore len vykresli PacMan mapu podla daneho Stringu
 */
public class HernaPlocha extends Hra {

    
    private int sirkaOkna = 608;
    private int vyskaOkna = 704;
    private int velkostDlazky = 32;

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

    public HernaPlocha() {
        super();
        this.vykresliMapu();
        this.spust();
    }

    /**
     * Prejde celu textovu a pre kazdy znak vytvori prislusny shape.
     */
    private void vykresliMapu() {
        for (int riadok = 0; riadok < MAPA.length; riadok++) {
            String r = MAPA[riadok];
            for (int stlpec = 0; stlpec < r.length(); stlpec++) {
                char ch = r.charAt(stlpec);

                int x = stlpec * velkostDlazky;
                int y = riadok * velkostDlazky;

                if (ch == '#') {
                   
                    Stvorec stena = new Stvorec(
                        velkostDlazky,
                        velkostDlazky
                    );
                    stena.zmenPolohu(x, y);
                    stena.zmenFarbu("blue");
                    stena.zobraz();
                } else if (ch == '.') {
                    //totok som len testoval, mozno sa to vyuzije kto vie
                    // pellet = maly kruh v strede dlazdice
                    //int priemer = velkostDlazky / 4;
                    //int pelletX = x + (velkostDlazky - priemer) / 2;
                    //int pelletY = y + (velkostDlazky - priemer) / 2;

                    //Kruh pellet = new Kruh();
                    //pellet.zmenPriemer(priemer);
                    //pellet.zmenPolohu(pelletX, pelletY);
                    //pellet.zmenFarbu("yellow");
                    //pellet.zobraz();
                }
                
            }
        }
    }

    //spusti len vykreslenie mapy
    public static void main(String[] args) {
        new HernaPlocha();
    }
}