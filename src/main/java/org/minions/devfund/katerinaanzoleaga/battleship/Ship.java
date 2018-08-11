package org.minions.devfund.katerinaanzoleaga.battleship;

/**
 * Abstract class Ship.
 */
public abstract class Ship {

    private int bowRow;
    private int bowColumn;
    private int length;
    private boolean horizontal;
    private boolean[] hit;

    protected static final int HITLENGTH = 8;


    public int getBowRow() {
        return bowRow;
    }

    public int getBowColumn() {
        return bowColumn;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public boolean isHorizontal() {
        return horizontal;
    }

    public boolean[] getHit() {
        return hit;
    }

    protected void setHit(boolean[] hit) {
        this.hit = hit;
    }


    abstract String getShipType();


    /**
     * Given the starting position, either row or columen, this fuction returns
     * true if the ship's larger posstion will feet in the ocean.
     * @param position either row or column
     * @param ocean the ocean reference.
     * @return ture if feets.
     */
    private boolean fitsInDirection (int position, Ocean ocean) {
        if (position >= 0 && position <= this.getLength() - 1) {
            return position + this.getLength() - 1 < ocean.getShips().length;
        } else {
            return false;
        }
    }

    private boolean isValidPosition (int row, int column, Ocean ocean) {
        int maxPosition = ocean.getShips().length - 1;
        return row >= 0 && row <= maxPosition && column >= 0 && column <= maxPosition;
    }


    /**
     * Given the current ship.
     * @param row
     * @param column
     * @param horizontal
     * @param ocean
     * @return True if it is possible to place a ship in the defined position.
     */
    boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
        if (isValidPosition(row, column, ocean)) {
            if (horizontal) {
                if (fitsInDirection(column, ocean)) {
                    for (int i = row - 1; i <= row + 1; ++i)
                        for (int j = column - 1; j <= column + this.getLength(); ++j)
                            if (isValidPosition(i, j, ocean)) {
                                if (ocean.isOccupied(i, j)) {
                                    return false;
                                }
                            }
                    return true;
                } else {
                    return false;
                }

            } else {
                if (fitsInDirection(row, ocean)) {
                    for (int i = row - 1; i <= row + this.getLength(); ++i )
                        for (int j = column - 1; j <= column + 1; ++j)
                            if (isValidPosition(i, j, ocean)) {
                                if (ocean.isOccupied(i, j)) {
                                    return false;
                                }
                            }
                    return true;
                } else {
                    return false;
                }

            }
        } else {
            return false;
        }
    }




    void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
        ;
    }

    boolean shootAt (int row, int column) {
        return true;
    }

    boolean isSunk() {
        return true;
    }

    @Override
    public String toString() {
        if (this.isSunk()){
            return "X";
        } else return "S";

    }









}
