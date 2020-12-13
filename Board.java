package com.tn.board;

import com.tn.field.IGameField;
import com.tn.field.WaterField;
import com.tn.ship.Ship;
import com.tn.field.ShipField;
import com.tn.ship.ShipSize;

import java.awt.*;
import java.util.Scanner;


public class Board {
    private static final char WATER = '~';
    private static final int BOARD_SIZE = 10;
    private static final char[] BOARD_LETTERS = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
    private static final String HORIZONTAL = "H";
    private static final String VERTICAL = "V";

    private Scanner scanner;
    private IGameField[][] board;
    private static final Ship[] ships;

    static {
        ships = new Ship[] {
                new Ship("Carrier", ShipSize.CARRIER),
                new Ship("Battleship", ShipSize.BATTLESHIP),
                new Ship("Cruiser", ShipSize.CRUISER),
                new Ship("Submarine", ShipSize.SUBMARINE),
                new Ship("Destroyer", ShipSize.DESTROYER)
        };
    }

    public Board() {
        this.scanner = new Scanner(System.in);
        this.board = new IGameField[BOARD_SIZE][BOARD_SIZE];
        for(int i = 0; i < BOARD_SIZE; i++) {
            for(int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = new WaterField();
            }
        }

    }

    public void placeShipsOnBoard() {
        for(Ship ship : ships) {
            boolean horizontal = askValidShipDirection();
            Point startingPoint = askValidStartingPoint(ship, horizontal);
            placeValidShip(ship, startingPoint, horizontal);

            printBoard();
        }
    }

    public IGameField getField(int x, int y) {
        if(!isInsideBoard(x, y)) {
            throw new IllegalArgumentException("Outside board - try again: ");
        }
        return board[y][x];
    }

    public void printBoard() {
        System.out.print("\t");

        for(int i = 0; i < BOARD_SIZE; i++) {
            System.out.print(BOARD_LETTERS[i] + "\t");
        }

        System.out.println();

        for(int i = 0; i < BOARD_SIZE; i++) {
            System.out.print((i+1) + "\t");
            for(int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j].getIcon() + "\t");
            }
            System.out.println();
        }
    }

    private boolean askValidShipDirection() {
        System.out.printf("%nDo you want to place the ship horizontally (H) or vertically (V)?");
        String direction;
        do {
            direction = scanner.nextLine().trim();
        }while (!HORIZONTAL.equals(direction) && !VERTICAL.equals(direction));

        return HORIZONTAL.equals(direction);
    }

    private Point askValidStartingPoint(Ship ship, boolean horizontal) {
        Point from;
        do {
            System.out.printf("%nEnter position of %s (length  %d): ", ship.getName(), ship.getSize());
            from = new Point(scanner.nextInt(), scanner.nextInt());
        } while(!isValidStartingPoint(from, ship.getSize(), horizontal));

        return from;
    }

    private boolean isValidStartingPoint(Point from, int length, boolean horizontal) {
        int xDiff = 0;
        int yDiff = 0;
        if (horizontal) {
            xDiff = 1;
        } else {
            yDiff = 1;
        }

        int x = (int) from.getX() - 1;
        int y = (int) from.getY() - 1;
        if (!isInsideBoard(x, y) ||
                (!isInsideBoard(x + length, y) && horizontal) ||
                (!isInsideBoard(x, y + length) && !horizontal)
        ) {
            return false;
        }


    }