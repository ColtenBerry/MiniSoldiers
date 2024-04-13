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
    private Rectangle r, h;
    private Location location;
    private int x, y;
    private int tileSize;
    private Shape s;
    private boolean selected = false;
    private boolean highlighted = false;
    private HealthBar healthBar;
    public Tile(Terrain terrain, Location location, int tileSize) {
        this.terrain = terrain;
        this.location = location;
        this.x = location.getX();
        this.y = location.getY();
        this.tileSize = tileSize;
        //draw tile
        this.r = new Rectangle(x * tileSize, y * tileSize, tileSize, tileSize);
        r.setFill(terrain.getColor());
        r.setStroke(Color.BLACK);
        getChildren().add(r);
        //draw highlight
        this.h = new Rectangle(x * tileSize, y * tileSize, tileSize, tileSize);
        h.setStroke(Color.BLACK);
        h.setOpacity(.5);
    }
    public void draw() {
        //draw unit
        if (unit != null) {
            s = unit.getVisual();
            s = new Circle(x * tileSize + tileSize / 2, y * tileSize + tileSize / 2, tileSize / 2);
            s.setFill(unit.getFaction().getColor());
            getChildren().add(s);
        }
        //highlight if selected
        if (selected) {
            //this sets the transparency
//        r.setOpacity(.5);
//            r.setFill(Color.YELLOWGREEN);
        }
        //undo highlight
        else {
//            r.setFill(terrain.getColor());
        }
    }
    public void highlight(Color c) {
        highlighted = true;
        h.setFill(c);
        if (!getChildren().contains(h)) {
            getChildren().add(h);
        }
    }
    public void unHighlight() {
        getChildren().remove(h);
        highlighted = false;
    }
    public Location getLocation() {
        return location;
    }
    public void setSelected(Boolean s) {
        this.selected = s;
    }

    public int getTileSize() {
        return tileSize;
    }

    public Terrain getTerrain() {
        return terrain;
    }
    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }
    public boolean containsUnit() {
        return unit != null;
    }
    public Unit getUnit() {
        return unit;
    }
    public Unit popUnit() {
        getChildren().remove(s);
        getChildren().remove(healthBar);
        //this sets unit to null while still returning unit
        Unit u = unit;
        unit = null;
        healthBar = null;
        return u;
    }

    public void attack(Tile tile) {
        //get combatants
        Unit opponent = tile.getUnit();
        Unit friendly = unit;

        //get statistics
        int attackPower = friendly.getAttackPower();
        int defensePower = opponent.getDefencePower();

        //calculate damage to each
        //add on tile bonuses

        //adjust health statistics accordingly
        unit.setHealth(unit.getHealth() - defensePower);
        opponent.setHealth(opponent.getHealth() - attackPower);

        //update health bars
        healthBar.update();
        tile.getHealthBar().update();
    }
    public HealthBar getHealthBar() {
        return healthBar;
    }
    public void addUnit(Unit unit) {
        if (!containsUnit())  {
            s = unit.getVisual();
            s = new Circle(x * tileSize + tileSize / 2, y * tileSize + tileSize / 2, tileSize / 2);
            s.setFill(unit.getFaction().getColor());
            getChildren().add(s);
            this.unit = unit;
            healthBar = new HealthBar(this);
            getChildren().add(healthBar);
        }
    }
    //get the tiles around this. This may be better suited for the map rather than for tile...
    public Location getLeft() {
        return new Location(x - 1, y);
    }
    public Location getRight() {
        return new Location(x + 1, y);
    }
    public Location getAbove() {
        return new Location (x, y - 1);
    }
    public Location getBelow() {
        return new Location(x, y + 1);
    }
}
