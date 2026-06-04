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
 * Klassen Buss representerar en buss i luftkvalitetssimuleringen.
 * Bussen rör sig i en slumpmässig riktning varje tidssteg och släpper ut föroreningar.
 */
public class Buss extends Elements {

    /**
     * Konstruktor för Buss.
     *
     * @param x             X-koordinat där bussen skapas.
     * @param y             Y-koordinat där bussen skapas.
     * @param airQualityApp Referens till applikationen.
     * @param definition    Referens till simuleringens definition.
     */
    public Buss(int x, int y, AirQualityApp airQualityApp, Definition definition) {
        super(x, y, airQualityApp, definition);
        this.random = new Random();
    }

    /**
     * Flyttar bussen i en slumpmässig riktning och lägger till föroreningar.
     *
     * @throws MovedOutOfGridException Om bussen rör sig utanför rutnätet.
     */
    @Override
    public void moveElements() throws MovedOutOfGridException {
        // Välj en ny slumpmässig riktning varje tidssteg
        Direction direction = Direction.values()[random.nextInt(4)];

        // Lägg till +7 förorening på nuvarande position
        definition.pollutionGrid[x][y] += 7;

        // Beräkna ny position
        int newX = x;
        int newY = y;
        switch (direction) {
            case NORTH: newY -= 1; break;
            case SOUTH: newY += 1; break;
            case WEST: newX -= 1; break;
            case EAST: newX += 1; break;
        }

        // Kontrollera om nya positionen är giltig
        if (!isInsideGrid(newX, newY)) {
            throw new MovedOutOfGridException("Bus removed from grid: (" + newX + ", " + newY + ") - out of bounds");
        }

        if (!isValidPlace(newX, newY)) {
            System.out.println("Bus cannot move to: (" + newX + ", " + newY + ") - water detected");
            return; // Avbryt rörelsen om det är vatten
        }

        // Uppdatera position
        x = newX;
        y = newY;
    }

    /**
     * Hämtar ikonen som representerar bussen.
     *
     * @return En BufferedImage för bussikonen.
     */
    @Override
    public BufferedImage getIcon() {
        return new ImageResources().getBusImage();
    }

    /**
     * Hämtar radpositionen för bussen.
     *
     * @return X-koordinaten för bussen.
     */
    @Override
    public int getRow() {
        return x;
    }

    /**
     * Hämtar kolumnpositionen för bussen.
     *
     * @return Y-koordinaten för bussen.
     */
    @Override
    public int getColumn() {
        return y;
    }
}
