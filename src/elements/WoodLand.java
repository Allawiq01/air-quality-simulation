package elements;

import se.mau.DA343A.VT25.assignment1.AirQualityApp;
import se.mau.DA343A.VT25.assignment1.ImageResources;
import view.Definition;

import java.awt.image.BufferedImage;

/**
 * Ali Akartei
 * Klassen WoodLand representerar skog i luftkvalitetssimuleringen.
 * Skogen reducerar föroreningsnivån på sin plats men kan inte flytta sig.
 */
public class WoodLand extends Elements {

    /**
     * Konstruktor för WoodLand.
     *
     * @param x             X-koordinat där skogen placeras.
     * @param y             Y-koordinat där skogen placeras.
     * @param airQualityApp Referens till applikationen.
     * @param definition    Referens till simuleringens definition.
     */
    public WoodLand(int x, int y, AirQualityApp airQualityApp, Definition definition) {
        super(x, y, airQualityApp, definition);
    }

    /**
     * Minskar föroreningen på platsen med 5, med en nedre gräns på 0.
     */
    @Override
    public void moveElements() {
        double currentPollution = definition.pollutionGrid[x][y];
        currentPollution = Math.max(0, currentPollution - 5);
        definition.pollutionGrid[x][y] = currentPollution;
    }

    /**
     * Hämtar ikonen som representerar skogen.
     *
     * @return En BufferedImage för skogikonen.
     */
    @Override
    public BufferedImage getIcon() {
        return new ImageResources().getTreesImage();
    }

    /**
     * Hämtar radpositionen för skogen.
     *
     * @return X-koordinaten för skogen.
     */
    @Override
    public int getRow() {
        return x;
    }

    /**
     * Hämtar kolumnpositionen för skogen.
     *
     * @return Y-koordinaten för skogen.
     */
    @Override
    public int getColumn() {
        return y;
    }

    /**
     * Anger att detta element inte kan flytta sig.
     *
     * @return true, eftersom skog är ett icke-flyttbart element.
     */
    @Override
    public boolean isNonMovable() {
        return true;
    }
}
