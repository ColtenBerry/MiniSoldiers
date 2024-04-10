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

    public boolean locationInBounds(Location l) {
        return l.getX() >= 0 && l.getX() < width && l.getY() >= 0 && l.getY() < height;
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


}
