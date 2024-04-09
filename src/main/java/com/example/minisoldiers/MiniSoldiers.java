package com.example.minisoldiers;

import com.example.minisoldiers.Unit.Unit;

public class MiniSoldiers {
    private static final MiniSoldiers game = new MiniSoldiers();
    private Map map;
    private int turns;
    private boolean playerTurn;
    private double gameHeight = 600;
    private double gameWidth = 600;
    private int tileSize = 25;
    private int numTiles = (int) gameHeight / tileSize;

    public MiniSoldiers() {
        map = new Map(numTiles, numTiles, tileSize);
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

    public int getNumTiles() {
        return numTiles;
    }
    public void spawnUnit(Unit unit, int x, int y) {
        map.getTile(x, y).setUnit(unit);
    }
}
