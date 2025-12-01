 

import fri.shapesge.Stvorec;
import fri.shapesge.Kruh;

/**
 * HernaPlocha:
 * - dedi od Hra (podla UML)
 * - v konstruktore len vykresli staticku PacMan mapu
 */
public class HernaPlocha extends Hra {

    
    private static final int SIRKA_OKNA = 608;
    private static final int VYSKA_OKNA = 704;
    private static final int VELKOST_DLAZDICE = 32;

    // 19 x 21
    // '#' = stena, '.' = pellet, ' ' = prazdne
    private static final String[] MAPA = {
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

                int x = stlpec * VELKOST_DLAZDICE;
                int y = riadok * VELKOST_DLAZDICE;

                if (ch == '#') {
                   
                    Stvorec stena = new Stvorec(
                        VELKOST_DLAZDICE,
                        VELKOST_DLAZDICE
                    );
                    stena.zmenPolohu(x, y);
                    stena.zmenFarbu("blue");
                    stena.zobraz();
                } else if (ch == '.') {
                    // pellet = maly kruh v strede dlazdice
                    //int priemer = VELKOST_DLAZDICE / 4;
                    //int pelletX = x + (VELKOST_DLAZDICE - priemer) / 2;
                    //int pelletY = y + (VELKOST_DLAZDICE - priemer) / 2;

                    //Kruh pellet = new Kruh();
                    //pellet.zmenPriemer(priemer);
                    //pellet.zmenPolohu(pelletX, pelletY);
                    //pellet.zmenFarbu("yellow");
                    //pellet.zobraz();
                }
                // medzera = nic
            }
        }
    }

    //spusti len vykreslenie mapy
    public static void main(String[] args) {
        new HernaPlocha();
    }
}