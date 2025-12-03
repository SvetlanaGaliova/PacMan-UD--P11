import fri.shapesge.Manazer;

public class OvladanieHraca {
    private Manazer manazer;
    private Hrac pacman;
    private Smer aktualnySmer;
    private Smer zvolenySmer;
    
    public OvladanieHraca(Hrac pacman) {
        this.manazer = new Manazer();
        this.pacman = pacman;
        this.manazer.spravujObjekt(this);
    }
    
    // nastavi aktualny smer, ktorym sa pohybuje pacman
    public void zistiAktualnySmer() {
        this.aktualnySmer = this.zvolenySmer;
    }
    
    public void posunVlavo() {
        this.zvolenySmer(Smer.DOLAVA);
    }
    
    public void posunVpravo() {
        this.zvolenySmer(Smer.DOPRAVA);
    }
    
    public void posunHore() {
        this.zvolenySmer(Smer.HORE);
    }
    
    public void posunDole() {
        this.zvolenySmer(Smer.DOLE);
    }
    
    public void tik() {
        this.pacman.pohniSa(this.zvolenySmer); 
    }
}