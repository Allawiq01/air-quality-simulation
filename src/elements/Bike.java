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
 * Klassen Bike representerar en cykel i luftkvalitetssimuleringen.
 * Cykeln rör sig i en slumpmässig riktning varje tidssteg men släpper inte ut föroreningar.
 */
public class Bike extends Elements {

    /**
     * Konstruktor för Bike.
     *
     * @param x             X-koordinat där cykeln skapas.
     * @param y             Y-koordinat där cykeln skapas.
     * @param airQualityApp Referens till applikationen.
     * @param definition    Referens till simuleringens definition.
     */
    public Bike(int x, int y, AirQualityApp airQualityApp, Definition definition) {
        super(x, y, airQualityApp, definition);
        this.random = new Random();
    }

    /**
     * Flyttar cykeln i en slumpmässigt vald riktning om det är en giltig plats.
     *
     * @throws MovedOutOfGridException Om cykeln rör sig utanför rutnätet.
     */
    @Override
    public void moveElements() throws MovedOutOfGridException {
        // Välj en ny slumpmässig riktning varje tidssteg
        Direction direction = Direction.values()[random.nextInt(4)];

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
            throw new MovedOutOfGridException("Bike removed from grid: (" + newX + ", " + newY + ") - out of bounds");
        }

        if (!isValidPlace(newX, newY)) {
            System.out.println("Bike cannot move to: (" + newX + ", " + newY + ") - water detected");
            return; // Avbryt rörelsen om det är vatten
        }

        // Uppdatera position
        x = newX;
        y = newY;
    }

    /**
     * Hämtar ikonen som representerar cykeln.
     *
     * @return En BufferedImage för cykelikonen.
     */
    @Override
    public BufferedImage getIcon() {
        return new ImageResources().getBikeImage();
    }

    /**
     * Hämtar radpositionen för cykeln.
     *
     * @return X-koordinaten för cykeln.
     */
    @Override
    public int getRow() {
        return x;
    }

    /**
     * Hämtar kolumnpositionen för cykeln.
     *
     * @return Y-koordinaten för cykeln.
     */
    @Override
    public int getColumn() {
        return y;
    }
}
