package elements;

import se.mau.DA343A.VT25.assignment1.AirQualityApp;
import se.mau.DA343A.VT25.assignment1.IElementIcon;
import se.mau.DA343A.VT25.assignment1.IsLand;
import se.mau.DA343A.VT25.assignment1.MovedOutOfGridException;
import view.Definition;

import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Ali Akartei
 * Abstrakt klass som representerar ett element i luftkvalitetssimuleringen.
 * Alla element som kan placeras i simuleringen ska ärva från denna klass.
 */
public abstract class Elements implements IElementIcon {
    protected int x;
    protected int y;
    protected Random random;
    protected AirQualityApp airQualityApp;
    protected Definition definition;

    /**
     * Konstruktor för Elements.
     *
     * @param x             X-koordinat för elementet.
     * @param y             Y-koordinat för elementet.
     * @param airQualityApp Referens till applikationen.
     * @param definition    Referens till definitionen av simuleringen.
     */
    public Elements(int x, int y, AirQualityApp airQualityApp, Definition definition) {
        this.x = x;
        this.y = y;
        this.airQualityApp = airQualityApp;
        this.definition = definition;
    }

    /**
     * Kontrollerar om en given position är inom rutnätet.
     *
     * @param x X-koordinat som ska kontrolleras.
     * @param y Y-koordinat som ska kontrolleras.
     * @return true om positionen är inom rutnätet, annars false.
     */
    public boolean isInsideGrid(int x, int y) {
        return x >= 0 && x < AirQualityApp.GRID_SIZE && y >= 0 && y < AirQualityApp.GRID_SIZE;
    }

    /**
     * Kontrollerar om en given position är en giltig plats (land).
     *
     * @param x X-koordinat som ska kontrolleras.
     * @param y Y-koordinat som ska kontrolleras.
     * @return true om positionen är land, annars false.
     */
    public boolean isValidPlace(int x, int y) {
        IsLand isLand = new IsLand();
        return isLand.isLand(x, y);
    }

    /**
     * Kontrollerar om elementet är icke-flyttbart.
     *
     * @return true om elementet inte kan flyttas, annars false.
     */
    public boolean isNonMovable() {
        return false; // Standardvärde för flyttbara element
    }

    /**
     * Abstrakt metod för att flytta elementet i simuleringen.
     *
     * @throws MovedOutOfGridException Om elementet rör sig utanför rutnätet.
     */
    public abstract void moveElements() throws MovedOutOfGridException;

    /**
     * Hämtar ikonen för elementet.
     *
     * @return Ikonen som används för att representera elementet.
     */
    @Override
    public abstract BufferedImage getIcon();

    /**
     * Hämtar radpositionen för elementet.
     *
     * @return X-koordinaten för elementet.
     */
    @Override
    public int getRow() {
        return x;
    }

    /**
     * Hämtar kolumnpositionen för elementet.
     *
     * @return Y-koordinaten för elementet.
     */
    @Override
    public int getColumn() {
        return y;
    }
}
