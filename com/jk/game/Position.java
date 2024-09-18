package com.jk.game;
public class Position {
    private int x;
    private int y;

    Position(int p) {
        switch (p) {
            case 1:
                x = 0;
                y = 0;
                break;

            case 2:
                x = 0;
                y = 1;
                break;

            case 3:
                x = 0;
                y = 2;
                break;

            case 4:
                x = 1;
                y = 0;
                break;

            case 5:
                x = 1;
                y = 1;
                break;

            case 6:
                x = 1;
                y = 2;
                break;

            case 7:
                x = 2;
                y = 0;
                break;

            case 8:
                x = 2;
                y = 1;
                break;

            case 9:
                x = 2;
                y = 2;
                break;

            default:
                System.out.println("invalid");
                break;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
