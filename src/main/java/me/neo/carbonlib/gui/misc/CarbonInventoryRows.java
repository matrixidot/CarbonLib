package me.neo.carbonlib.gui.misc;

public enum CarbonInventoryRows {
    ROWS_1(9),
    ROWS_2(18),
    ROWS_3(27),
    ROWS_4(36),
    ROWS_5(45),
    ROWS_6(54);

    private final int i;

    CarbonInventoryRows(int i) {
        this.i = i;
    }

    /**
     * Gets the size of the inventory in rows. Multiply by 9 to get number of slots
     * @return The number of rows the inventory has
     */
    public int getSize() {
        return i;
    }
}
