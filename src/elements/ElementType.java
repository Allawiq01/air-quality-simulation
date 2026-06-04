package elements;

public enum ElementType {
    CAR("Car"),
    BUS("Buss"),
    AIRPLANE("AirPlane"),
    BIKE("Bike"),
    WOODLAND("WoodLand");

    private final String displayName;

    ElementType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static String[] displayNames() {
        ElementType[] values = values();
        String[] names = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            names[i] = values[i].displayName;
        }
        return names;
    }

    public static ElementType fromDisplayName(String displayName) {
        for (ElementType type : values()) {
            if (type.displayName.equals(displayName)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown element type: " + displayName);
    }
}
