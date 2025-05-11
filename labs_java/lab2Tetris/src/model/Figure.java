package model;

import java.awt.Color;
import java.util.Random;

public enum Figure {
    I1(Color.BLUE, new int[][]{{1, 1, 1, 1}}),
    I2(Color.BLUE, new int [][]{{1}, {1}, {1}, {1}}),

    J1(Color.CYAN,new int[][]{{0, 1}, {0, 1}, {1, 1}}),
    J2(Color.CYAN, new int[][]{{1, 0, 0}, {1, 1, 1}}),
    J3(Color.CYAN, new int[][]{{1, 1}, {1, 0}, {1, 0}}),
    J4(Color.CYAN, new int[][]{{1, 1, 1}, {0, 0, 1}}),

    L1(Color.ORANGE, new int[][]{ {1, 0}, {1, 0}, {1, 1} }),
    L2(Color.ORANGE, new int[][]{ {1, 1, 1}, {1, 0, 0} }),
    L3(Color.ORANGE, new int[][]{ {1, 1}, {0, 1}, {0, 1} }),
    L4(Color.ORANGE, new int[][]{ {0, 0, 1}, {1, 1, 1} }),

    O(Color.YELLOW, new int[][]{ {1, 1}, {1, 1} }),

    S1(Color.GREEN, new int[][]{ {0, 1, 1}, {1, 1, 0} }),
    S2(Color.GREEN, new int[][]{ {1, 0}, {1, 1}, {0, 1} }),

    Z1(Color.RED, new int[][]{ {1, 1, 0}, {0, 1, 1} }),
    Z2(Color.RED, new int[][]{ {0, 1}, {1, 1}, {1, 0} }),

    T1(Color.PINK, new int[][]{ {0, 1, 0}, {1, 1, 1} }),
    T2(Color.PINK, new int[][]{ {1, 0}, {1, 1}, {1, 0} }),
    T3(Color.PINK, new int[][]{ {1, 1, 1}, {0, 1, 0} }),
    T4(Color.PINK, new int[][]{ {0, 1}, {1, 1}, {0, 1} });

    private final Color color;
    private final int[][] shape;
    private final int height;
    private final int width;

    Figure(Color color, int [][] shape) {
        this.color = color;
        this.shape = shape;
        height = shape.length;
        width = shape[0].length;
    }
    public Figure turnRight() {
        return switch (this) {
            case I1 -> I2;
            case I2 -> I1;
            case J1 -> J2;
            case J2 -> J3;
            case J3 -> J4;
            case J4 -> J1;
            case L1 -> L2;
            case L2 -> L3;
            case L3 -> L4;
            case L4 -> L1;
            case O -> O;
            case S1 -> S2;
            case S2 -> S1;
            case T1 -> T2;
            case T2 -> T3;
            case T3 -> T4;
            case T4 -> T1;
            case Z1 -> Z2;
            default -> Z1;
        };
    }

    public Figure turnLeft() {
        return this.turnRight().turnRight().turnRight();
    }

    private static final Random RANDOM = new Random();

    public static Figure getRandomFigure() {
        return values()[RANDOM.nextInt(values().length)];
    }

    public int[][] getShape(){
        return shape;
    }

    public Color getColor(){
        return color;
    }

    public int getHeight(){
        return height;
    }
    public int getWidth(){
        return width;
    }

}