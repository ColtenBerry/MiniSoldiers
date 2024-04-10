package com.example.minisoldiers;

import com.example.minisoldiers.Unit.Unit;
/*
So, i need to decide if I want the tile to store the unit, or if the unit should be aware of where it is.
I will think about it and make a pro / con list in the morning.
Intuitively, the unit should know its location, but the game seems to be functional without it
 */

public class MiniSoldiers {
    private static final MiniSoldiers game = new MiniSoldiers();
    private Map map;
    private int turns;
    private boolean playerTurn;
    private double gameHeight = 600;
    private double gameWidth = 600;
    private int tileSize = 40;
    private int numTilesWidth = (int) gameWidth / tileSize;
    private int numTilesHeight = (int) gameHeight / tileSize;

    public MiniSoldiers() {
        map = new Map(numTilesWidth, numTilesHeight, tileSize);
    }

    public static MiniSoldiers getGame() {
        return game;
    }
    public double getGameHeight() {
        return gameHeight;
    }
    public void setGameHeight(double gameHeight) {
        this.gameHeight = gameHeight;
    }

    public double getGameWidth() {
        return gameWidth;
    }

    public void setGameWidth(double gameWidth) {
        this.gameWidth = gameWidth;
    }

    public boolean isPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(boolean playerTurn) {
        this.playerTurn = playerTurn;
    }

    public int getTurns() {
        return turns;
    }
    public void incrementTurns() {
        turns ++;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public int getNumTilesWidth() {
        return numTilesWidth;
    }

    public int getNumTilesHeight() {
        return numTilesHeight;
    }

    public void spawnUnit(Unit unit, Location location) {
        map.getTile(location).addUnit(unit);
    }
    public Location getLocation(double x, double y) {
        int locX = (int) x / tileSize;
        int locY = (int) y / tileSize;
        return new Location(locX, locY);
    }
}
