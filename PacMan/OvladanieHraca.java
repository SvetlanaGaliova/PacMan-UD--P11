/**
 * Trieda OvladanieHraca zistuje smer, ktorym sa chce hrac posunut.
 * 
 * @author Ivana Neuschlová
 */

import fri.shapesge.Manazer;

public class OvladanieHraca {
    private Manazer manazer;
    private Hrac pacman;
    private Smer zvolenySmer;
    
    public OvladanieHraca(Hrac pacman) {
        this.manazer = new Manazer();
        this.pacman = pacman;
        this.zvolenySmer = Smer.HORE;
        this.manazer.spravujObjekt(this);
    }
    
    public void posunVlavo() {
        this.zvolenySmer = Smer.DOLAVA;
    }
    
    public void posunVpravo() {
        this.zvolenySmer = Smer.DOPRAVA;
    }
    
    public void posunHore() {
        this.zvolenySmer = Smer.HORE;
    }
    
    public void posunDole() {
        this.zvolenySmer = Smer.DOLE;
    }
    
    public void tik() {
        this.pacman.pohniSa(this.zvolenySmer); 
    }
}