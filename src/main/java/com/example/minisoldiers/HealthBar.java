package com.example.minisoldiers;

import com.example.minisoldiers.Unit.Unit;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class HealthBar extends Parent {
    private int current_health;
    private double health_percent;
    private double damage_percent;
    private Rectangle g, r;
    private Unit unit;
    private int tileSize;
    private Tile tile;
    private double height;
    private double y;
    private double greenX;
    private double redX;
    private double length;
    public HealthBar(Tile tile) {
        this.unit = tile.getUnit();
        this.current_health = unit.getHealth();
        this.tileSize = tile.getTileSize();
        this.tile = tile;
        length = tileSize * 0.75;
        health_percent = current_health / 100.0;
        damage_percent = 1 - health_percent;
        height = tileSize * 0.2;
        y = (tile.getLocation().getY() * tileSize) + (tileSize * 0.8);
        greenX = tile.getLocation().getX() * tileSize + (tileSize * 0.13);
        g = new Rectangle(greenX, y, length * health_percent, height);
        g.setFill(Color.GREEN);
        redX = (greenX + g.getWidth());
        r = new Rectangle(redX, y, length * damage_percent, height);
        r.setFill(Color.RED);
        getChildren().add(g);
        getChildren().add(r);
    }
    public void update() {
        current_health = unit.getHealth();
        health_percent = current_health / 100.0;
        damage_percent = 1 - health_percent;
        System.out.println(current_health);
        System.out.println(health_percent);
        System.out.println(damage_percent);
        height = tileSize * 0.2;
        y = (tile.getLocation().getY() * tileSize) + (tileSize * 0.8);
        greenX = tile.getLocation().getX() * tileSize + (tileSize * 0.2);
        getChildren().remove(g);
        getChildren().remove(r);
        g = new Rectangle(greenX, y, length * health_percent, height);
        g.setFill(Color.GREEN);
        redX = (greenX + g.getWidth());
        r = new Rectangle(redX, y, length * damage_percent, height);
        r.setFill(Color.RED);
        getChildren().add(g);
        getChildren().add(r);
    }
}
