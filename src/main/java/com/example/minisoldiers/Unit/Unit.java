package com.example.minisoldiers.Unit;

import com.example.minisoldiers.Faction;
import javafx.scene.shape.Shape;

public interface Unit {
    public Faction getFaction();
    public double getAttack();
    public double getDefence();
    public int getMovement();
    public int getRange();
    public int getHealth();
    public Shape getVisual();
    public void setHealth(int health);
    public int getAttackPower();
    public int getDefencePower();

}
