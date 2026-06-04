package elements;

import se.mau.DA343A.VT25.assignment1.AirQualityApp;
import se.mau.DA343A.VT25.assignment1.Direction;
import se.mau.DA343A.VT25.assignment1.ImageResources;
import se.mau.DA343A.VT25.assignment1.MovedOutOfGridException;
import view.Definition;

import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Ali Akartei
 * Klassen AirPlane representerar ett flygplan i luftkvalitetssimuleringen.
 * Flygplanet rör sig i en slumpmässigt vald riktning och sprider föroreningar.
 */
public class AirPlane extends Elements {
    private final Direction direction;

    /**
     * Konstruktor för AirPlane.
     *
     * @param x             X-koordinat där flygplanet skapas.
     * @param y             Y-koordinat där flygplanet skapas.
     * @param airQualityApp Referens till applikationen.
     * @param definition    Referens till simuleringens definition.
     */
    public AirPlane(int x, int y, AirQualityApp airQualityApp, Definition definition) {
        super(x, y, airQualityApp, definition);
        this.random = new Random();
        this.direction = Direction.values()[random.nextInt(Direction.values().length)]; // Väljer en slumpmässig riktning
    }

    /**
     * Flyttar flygplanet i den förutbestämda riktningen och lägger till föroreningar.
     *
     * @throws MovedOutOfGridException Om flygplanet rör sig utanför rutnätet.
     */
    @Override
    public void moveElements() throws MovedOutOfGridException {
        // Lägg till +10 förorening på nuvarande position (innan flytt)
        definition.pollutionGrid[x][y] += 10;

        // Beräkna ny position efter 5 steg
        int newX = x;
        int newY = y;
        switch (direction) {
            case NORTH: newY -= 5; break;
            case SOUTH: newY += 5; break;
            case WEST: newX -= 5; break;
            case EAST: newX += 5; break;
        }

        // Kontrollera om nya positionen är utanför gridet
        if (!isInsideGrid(newX, newY)) {
            throw new MovedOutOfGridException("AirPlane removed from grid: (" + newX + ", " + newY + ") - out of bounds");
        }

        // Uppdatera position
        x = newX;
        y = newY;
    }

    /**
     * Hämtar ikonen som representerar flygplanet.
     *
     * @return En BufferedImage för flygplansikonen.
     */
    @Override
    public BufferedImage getIcon() {
        return new ImageResources().getAirPlaneImage();
    }

    /**
     * Hämtar radpositionen för flygplanet.
     *
     * @return X-koordinaten för flygplanet.
     */
    @Override
    public int getRow() {
        return x;
    }

    /**
     * Hämtar kolumnpositionen för flygplanet.
     *
     * @return Y-koordinaten för flygplanet.
     */
    @Override
    public int getColumn() {
        return y;
    }
}
