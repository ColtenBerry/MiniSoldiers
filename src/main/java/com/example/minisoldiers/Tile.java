package com.example.minisoldiers;

import com.example.minisoldiers.Unit.Unit;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Tile extends Parent {
    private Terrain terrain;
    private Unit unit;
    private Rectangle r;
    private int x;
    private int y;
    private int tileSize;
    private Shape s;
    public Tile(Terrain terrain, int x, int y, int tileSize) {
        this.terrain = terrain;
        this.x = x;
        this.y = y;
        this.tileSize = tileSize;
        this.r = new Rectangle(x * tileSize, y * tileSize, tileSize, tileSize);
        r.setFill(terrain.getColor());
        r.setStroke(Color.BLACK);
        getChildren().add(r);
    }
    public void draw() {
        if (unit != null) {
            s = unit.getVisual();
            s = new Circle(x * tileSize + tileSize / 2, y * tileSize + tileSize / 2, tileSize / 2);
            s.setFill(unit.getFaction().getColor());
            getChildren().add(s);
        }
    }

    public Terrain getTerrain() {
        return terrain;
    }
    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    public Unit getUnit() {
        return unit;
    }
    public void setUnit(Unit unit) {
        this.unit = unit;
        draw();
    }
}
