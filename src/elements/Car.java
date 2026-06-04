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
 * Klassen Car representerar en bil i luftkvalitetssimuleringen.
 * Bilen rör sig i en slumpmässig riktning varje tidssteg och släpper ut föroreningar.
 */
public class Car extends Elements {

    /**
     * Konstruktor för Car.
     *
     * @param x             X-koordinat där bilen skapas.
     * @param y             Y-koordinat där bilen skapas.
     * @param airQualityApp Referens till applikationen.
     * @param definition    Referens till simuleringens definition.
     */
    public Car(int x, int y, AirQualityApp airQualityApp, Definition definition) {
        super(x, y, airQualityApp, definition);
        this.random = new Random();
    }

    /**
     * Flyttar bilen i en slumpmässig riktning och lägger till föroreningar.
     *
     * @throws MovedOutOfGridException Om bilen rör sig utanför rutnätet.
     */
    @Override
    public void moveElements() throws MovedOutOfGridException {
        // Välj en ny slumpmässig riktning varje tidssteg
        Direction direction = Direction.values()[random.nextInt(4)];

        // Lägg till +5 förorening på nuvarande position
        definition.pollutionGrid[x][y] += 5;

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
            throw new MovedOutOfGridException("Car removed from grid: (" + newX + ", " + newY + ") - out of bounds");
        }

        if (!isValidPlace(newX, newY)) {
            System.out.println("Car cannot move to: (" + newX + ", " + newY + ") - water detected");
            return; // Avbryt rörelsen om det är vatten
        }

        // Uppdatera position
        x = newX;
        y = newY;
    }

    /**
     * Hämtar ikonen som representerar bilen.
     *
     * @return En BufferedImage för bilikonen.
     */
    @Override
    public BufferedImage getIcon() {
        return new ImageResources().getCarImage();
    }

    /**
     * Hämtar radpositionen för bilen.
     *
     * @return X-koordinaten för bilen.
     */
    @Override
    public int getRow() {
        return x;
    }

    /**
     * Hämtar kolumnpositionen för bilen.
     *
     * @return Y-koordinaten för bilen.
     */
    @Override
    public int getColumn() {
        return y;
    }
}
