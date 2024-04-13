package com.example.minisoldiers.Unit;

import com.example.minisoldiers.Faction;
import com.example.minisoldiers.Tile;
import com.example.minisoldiers.Unit.Unit;
import javafx.scene.Parent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class Swordsman extends Parent implements Unit {
    private Faction faction;
    private int attack = 50;
    private int defence = 25;
    private final int movement = 8;
    private final int range = 1;
    private int health = 100;
    private int attackPower = attack * (health / 100);
    private int defencePower = defence * (health / 100);
    private Circle c;
    private boolean hasMoved;
    private boolean hasAttacked;

    public Swordsman(Faction faction) {
        this.faction = faction;
    }
    public Shape getVisual() {
        return new Circle();
    }
    @Override
    public Faction getFaction() {
        return faction;
    }

    @Override
    public double getAttack() {
        return attack;
    }

    @Override
    public double getDefence() {
        return defence;
    }

    @Override
    public int getMovement() {
        return movement;
    }

    @Override
    public int getRange() {
        return range;
    }
    @Override
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public int getDefencePower() {
        return defencePower;
    }

    @Override
    public boolean hasMoved() {
        return hasMoved;
    }

    @Override
    public boolean hasAttacked() {
        return hasAttacked;
    }
    public void setHasMoved(boolean b) {
        hasMoved = b;
    }
    public void setHasAttacked(boolean b) {
        hasAttacked = b;
    }

    public String toString() {
        return "Health: " + health;
    }

}
