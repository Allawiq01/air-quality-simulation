import elements.ElementType;
import se.mau.DA343A.VT25.assignment1.ImageResources;
import view.Definition;

/**
 * Ali Akartei
 * Huvudklassen för programmet som startar simuleringen av luftkvalitet.
 */
public class Main {
    /**
     * Huvudmetoden som initierar programmet.
     *
     * @param args Kommandoradsargument (används ej).
     */
    public static void main(String[] args) {
        new Definition(ElementType.displayNames(), new ImageResources().getMapImage());
    }
}
