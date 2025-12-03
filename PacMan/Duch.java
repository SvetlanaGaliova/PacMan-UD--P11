/*
Trieda Duch reprezentuje ducha (nepriatela) v hre PacMan.
 */
public class Duch {
    private int x;
    private int y;
    private Hrac hrac;

    public Duch(Hrac hrac) {
        this.hrac = hrac;
        this.x = 0;
        this.y = 0;
    }

    //Vrati X-ovu suradnicu ducha.//
    public int getX() {
        return this.x;
    }

    //Vrati Y-ovu suradnicu ducha.//
    public int getY() {
        return this.y;
    }

    //Pohne duchom//
    public void pohniSa() {

    }
}
