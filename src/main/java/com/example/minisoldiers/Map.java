package com.example.minisoldiers;

import javafx.scene.Parent;

public class Map extends Parent {
    public final Tile[][] map;

    public Map(int width, int height, int tileSize) {
        map = new Tile[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Tile t = new Tile(Terrain.GRASS, i, j, tileSize);
                map[i][j] = t;
            }
        }
    }
    public Tile getTile(int x, int y) {
        return map[x][y];
    }
    public void draw() {
        for (Tile[] column: map) {
            for (Tile t: column) {
                getChildren().add(t);
            }
        }
    }

}
