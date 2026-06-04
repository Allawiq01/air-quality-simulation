package view;

import elements.*;
import se.mau.DA343A.VT25.assignment1.AirQualityApp;
import se.mau.DA343A.VT25.assignment1.IsLand;
import se.mau.DA343A.VT25.assignment1.MovedOutOfGridException;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Ali Akartei
 * Klassen Definition hanterar interaktionen mellan GUI och simuleringen.
 * Den ansvarar för att hantera element, sprida föroreningar och uppdatera GUI.
 */
public class Definition extends AirQualityApp {
    private final List<Elements> placedElements = new ArrayList<>();
    private final IsLand isLand = new IsLand();
    public double[][] pollutionGrid;

    /**
     * Konstruktor för Definition-klassen.
     *
     * @param elementSelectorTypeNames En lista över elementtyper.
     * @param mapImage Bilden som används för att visa kartan.
     */
    @SuppressWarnings("this-escape")
    public Definition(String[] elementSelectorTypeNames, BufferedImage mapImage) {
        super(elementSelectorTypeNames, mapImage);
        pollutionGrid = new double[GRID_SIZE][GRID_SIZE];
        startGUIOnNewThread();
    }

    /**
     * Tar bort ett element från listan över placerade element.
     *
     * @param element Elementet som ska tas bort.
     */
    public void removeElement(Elements element) {
        placedElements.remove(element);
    }

    /**
     * Lägger till ett element av angiven typ på en specifik position.
     *
     * @param x   X-koordinat.
     * @param y   Y-koordinat.
     * @param typ Typen av element som ska läggas till.
     */
    public void addElements(int x, int y, String typ) {
        ElementType elementType;
        try {
            elementType = ElementType.fromDisplayName(typ);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Okänd elementtyp: " + typ);
            return;
        }

        if (!canPlaceElement(elementType, x, y)) {
            repaint();
            return;
        }

        placedElements.add(createElement(elementType, x, y));
        repaint();
    }

    private boolean canPlaceElement(ElementType elementType, int x, int y) {
        if (elementType == ElementType.AIRPLANE) {
            return true;
        }

        if (!isLand.isLand(x, y)) {
            JOptionPane.showMessageDialog(null, "Ogiltig position: " + elementType.getDisplayName() + " kan endast placeras på land");
            return false;
        }

        if (elementType == ElementType.WOODLAND && hasNonMovableElementAt(x, y)) {
            JOptionPane.showMessageDialog(null, "Kan inte placera skog på en upptagen ruta");
            return false;
        }

        return true;
    }

    private Elements createElement(ElementType elementType, int x, int y) {
        return switch (elementType) {
            case CAR -> new Car(x, y, this, this);
            case BUS -> new Buss(x, y, this, this);
            case AIRPLANE -> new AirPlane(x, y, this, this);
            case BIKE -> new Bike(x, y, this, this);
            case WOODLAND -> new WoodLand(x, y, this, this);
        };
    }

    private boolean hasNonMovableElementAt(int x, int y) {
        return placedElements.stream()
                .anyMatch(element -> element.isNonMovable()
                        && element.getRow() == x
                        && element.getColumn() == y);
    }

    /**
     * Sprider föroreningarna över kartans rutnät enligt diffusionmodellen.
     */
    public void diffusePollution() {
        double[][] newGrid = new double[GRID_SIZE][GRID_SIZE];

        for (int x = 0; x < GRID_SIZE; x++) {
            for (int y = 0; y < GRID_SIZE; y++) {
                double sum = pollutionGrid[x][y];
                int count = 1;

                if (x > 0) {
                    sum += pollutionGrid[x - 1][y];
                    count++;
                }
                if (x < GRID_SIZE - 1) {
                    sum += pollutionGrid[x + 1][y];
                    count++;
                }
                if (y > 0) {
                    sum += pollutionGrid[x][y - 1];
                    count++;
                }
                if (y < GRID_SIZE - 1) {
                    sum += pollutionGrid[x][y + 1];
                    count++;
                }

                newGrid[x][y] = sum / count;
            }
        }
        pollutionGrid = newGrid;
    }

    /**
     * Uppdaterar GUI och föroreningsnivåerna på kartan.
     */
    public void update() {
        for (int x = 0; x < GRID_SIZE; x++) {
            for (int y = 0; y < GRID_SIZE; y++) {
                int alpha = getAlphaForPollution(pollutionGrid[x][y]);
                setPollution(x, y, alpha);
            }
        }
        repaint();
    }

    /**
     * Nollställer föroreningsrutnätet till 0.
     */
    public void resetPollutionGrid() {
        for (int x = 0; x < GRID_SIZE; x++) {
            for (int y = 0; y < GRID_SIZE; y++) {
                pollutionGrid[x][y] = 0.0;
            }
        }
        update();
    }

    /**
     * Hanterar musens klickhändelse och placerar element på kartan.
     *
     * @param i  X-koordinat där musen klickades.
     * @param i1 Y-koordinat där musen klickades.
     */
    @Override
    protected void mouseClicked(int i, int i1) {
        System.out.println("Mouse clicked (" + i + ")(" + i1 + ")");
        String typ = getSelectedElementType();
        addElements(i, i1, typ);
        repaint();
    }

    /**
     * Hanterar händelsen för nästa tidssteg i simuleringen.
     */
    @Override
    protected void buttonNextTimeStepClicked() {
        List<Elements> elementsToRemove = new ArrayList<>();

        for (Elements element : new ArrayList<>(placedElements)) {
            try {
                element.moveElements();
            } catch (MovedOutOfGridException e) {
                elementsToRemove.add(element);
                System.out.println(e.getMessage());
            }
        }

        placedElements.removeAll(elementsToRemove);
        diffusePollution();
        update();
        repaint();
    }

    /**
     * Returnerar en lista över element som ska renderas i GUI.
     *
     * @return Lista över element som ska ritas ut.
     */
    @Override
    protected List<Elements> elementIconsToPaint() {
        return new ArrayList<>(placedElements);
    }
}
