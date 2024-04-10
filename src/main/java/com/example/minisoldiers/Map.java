package com.example.minisoldiers;

import com.example.minisoldiers.Unit.Unit;
import javafx.scene.Parent;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Map extends Parent {
    public final Tile[][] map;
    private Tile selected;
    private Tile previouslySelected;
    private boolean unitSelected = false;
    private ArrayList<Tile> moveOptions = new ArrayList<>();
    private int width;
    private int height;

    public Map(int width, int height, int tileSize) {
        this.width = width;
        this.height = height;
        map = new Tile[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Tile t = new Tile(Terrain.GRASS, new Location(i, j), tileSize);
                map[i][j] = t;
            }
        }
    }
    public Tile getTile(Location location) {
        if (locationInBounds(location)) {
            return map[location.getX()][location.getY()];
        }
        return new Tile(Terrain.BORDER, location, 50);
    }
    public void draw() {
        for (Tile[] column: map) {
            for (Tile t: column) {
                getChildren().add(t);
            }
        }
    }

    public Tile getSelected() {
        return selected;
    }

    public void select(Tile t) {
        //If there is not currently a unit s
        if (!unitSelected) {
            if (this.selected != null) {
                previouslySelected = selected;
                previouslySelected.setSelected(false);
                previouslySelected.highlight(false);
            }
            selected = t;
            selected.setSelected(true);
            selected.highlight(true);
            if (selected.containsUnit()) {
                highlightMoves(t);
                unitSelected = true;
            }
        }
        //if there is a unit selected
        else {
            if (moveOptions.contains(t)) {
                //move unit to s
                previouslySelected = selected;
                selected = t;
                Unit unit = previouslySelected.popUnit();
                selected.addUnit(unit);
            }
            for (Tile tile: moveOptions) {
                tile.highlight(false);
            }
            moveOptions.clear();
            unitSelected = false;
        }
    }
    public boolean locationInBounds(Location l) {
        return l.getX() >= 0 && l.getX() < width && l.getY() >= 0 && l.getY() < height;
    }
    public void highlightMoves(Tile t) {
        Unit unit = t.getUnit();
        int moves = unit.getMovement();
        /*
        go up moves
        go up moves - 1. go left and right 1
        go up moves - 2. go left and right 2
        ...
         */
        highlightPotentialMoves(moves, t);

        //highlight all potential moves
        for (Tile tile: moveOptions) {
            tile.highlight(true);
        }
    }
    public void highlightPotentialMoves(int moves, Tile t) {
        int original_moves = moves;
        moveOptions.add(t);
        //go up until out of moves
        Tile above = getTile(t.getAbove());
        while (moves >= above.getTerrain().getMovementCost()) {
            moveOptions.add(above);
            moves -= above.getTerrain().getMovementCost();
            above = getTile(above.getAbove());
        }
        //go down until out of moves
        moves = original_moves;
        Tile below = getTile(t.getBelow());
        while (moves >= below.getTerrain().getMovementCost()) {
            moveOptions.add(below);
            moves -= below.getTerrain().getMovementCost();
            below = getTile(below.getBelow());
        }
        //recursive calls:
        //go left
        moves = original_moves;
        Tile left = getTile(t.getLeft());
        if (moves >= left.getTerrain().getMovementCost()) {
            moves -= left.getTerrain().getMovementCost();
            highlightPotentialMoves(moves, left);
        }

        //go right
        moves = original_moves;
        Tile right = getTile(t.getRight());
        if (moves >= right.getTerrain().getMovementCost()) {
            moves -= right.getTerrain().getMovementCost();
            highlightPotentialMoves(moves, right);
        }

    }
}
