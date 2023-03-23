package fr.epita.labsi.poulpy;

public interface IPoulpy {
    /**
     * @return the poulpy name
     */
    String getName();

    /**
     * @return the color of the poulpy
     */
    Color getColor();

    enum Color {
        RED,
        GREEN,
        BLUE
    }

}
