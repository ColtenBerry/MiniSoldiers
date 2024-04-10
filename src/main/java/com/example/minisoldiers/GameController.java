package com.example.minisoldiers;

import com.example.minisoldiers.Unit.Swordsman;
import com.example.minisoldiers.Unit.Unit;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class GameController {
    @FXML
    Pane gamePane;
    MiniSoldiers game = MiniSoldiers.getGame();
    Map map = game.getMap();
    Tile previouslySelectedTile;
    Tile selectedTile;
    Unit selectedUnit;
    @FXML
    private void initialize() {
        map.draw();
        gamePane.getChildren().add(map);
        game.spawnUnit(new Swordsman(Faction.PLAYER), new Location(5, 5));
        game.spawnUnit(new Swordsman(Faction.BOT2), new Location(0,0));
        game.spawnUnit(new Swordsman(Faction.BOT1), new Location(10, 0));
        game.spawnUnit(new Swordsman(Faction.PLAYER), new Location(6, 6));
    }

    @FXML
    private void click(MouseEvent e) {
        double x = e.getX();
        double y = e.getY();
        Location clickLocation = game.getLocation(x, y);
        Tile selectedTile = map.getTile(clickLocation);
        System.out.println(clickLocation);
        select(selectedTile);


    }
    //TODO: fix units moving on top of each other
    //Friendly units should be able to move past, but not onto each other
    //Opposing units should not be able to move past or onto each other
    //I think this would best be done in the algorithm.
    ArrayList<Tile> moveOptions = new ArrayList<>();
    public void select(Tile t) {

        //If there is not currently a unit s
        if (selectedUnit == null) {
            //if there is a selected tile
            if (selectedTile != null) {
                previouslySelectedTile = selectedTile; // set previously selected tile
                previouslySelectedTile.setSelected(false); // unselect the previously selected tile
                previouslySelectedTile.highlight(false); // unhighlight the previously selected tile
            }
            selectedTile = t; // set selected tile
            selectedTile.setSelected(true); // select the selected tile
            selectedTile.highlight(true); // highlight the selected tile
            //if the newly selected tile contains a unit
            if (selectedTile.containsUnit()) {
                selectedUnit = selectedTile.getUnit(); // sets the selectedUnit field
                highlightMoves(t); //highlight any potential moves of the newly selected unit
            }
        }
        //if there is currently a unit selected
        else {
            // if the selected tile is a valid movement option
            if (moveOptions.contains(t)) {
                previouslySelectedTile = selectedTile; // sets previouslySelectedTile
                previouslySelectedTile.highlight(false); // unhighlights the previouslySelectedTile
                selectedTile = t; // sets the selectedTile
                Unit unit = previouslySelectedTile.popUnit(); // removes the selected unit from the previously selected tile
                selectedTile.addUnit(unit); // adds the selected unit to the newly selected tile
            }
            for (Tile tile: moveOptions) {
                tile.highlight(false); // unhighlights all the possible moves of the selected unit
            }
            moveOptions.clear(); // clears the arraylist so it can be re-used
            selectedUnit = null; // unselects the unit
        }
    }
    public void highlightMoves(Tile t) {
        Unit unit = t.getUnit();
        int moves = unit.getMovement();
        getPotentialUnitMovesV2(moves, t);

        //highlight all potential moves
        for (Tile tile: moveOptions) {
            tile.highlight(true);
        }
    }
    public void getPotentialUnitMovesV2(int moves, Tile t) {
        if (!t.containsUnit()) {
            moveOptions.add(t);
        }
        Tile above = map.getTile(t.getAbove());
        Tile below = map.getTile(t.getBelow());
        Tile left = map.getTile(t.getLeft());
        Tile right = map.getTile(t.getRight());
        if (moves >= above.getTerrain().getMovementCost() && (above.getUnit() == null || above.getUnit().getFaction() == selectedUnit.getFaction())) {
            getPotentialUnitMovesV2(moves - above.getTerrain().getMovementCost(), above);
        }
        if (moves >= below.getTerrain().getMovementCost() && (below.getUnit() == null || below.getUnit().getFaction() == selectedUnit.getFaction())) {
            getPotentialUnitMovesV2(moves - below.getTerrain().getMovementCost(), below);
        }
        if (moves >= left.getTerrain().getMovementCost() && (left.getUnit() == null || left.getUnit().getFaction() == selectedUnit.getFaction())) {
            getPotentialUnitMovesV2(moves - left.getTerrain().getMovementCost(), left);
        }
        if (moves >= right.getTerrain().getMovementCost() && (right.getUnit() == null || right.getUnit().getFaction() == selectedUnit.getFaction())) {
            getPotentialUnitMovesV2(moves - right.getTerrain().getMovementCost(), right);
        }
    }
    public void getPotentialUnitMoves(int moves, Tile t) {
        /*
        go up moves
        go up moves - 1. go left and right 1
        go up moves - 2. go left and right 2
        ...
        PROBLEM:
        if there is a hostile enemy, the algorithm does not function correctly
         */
        int original_moves = moves;
        moveOptions.add(t);
        //go up until out of moves
        Tile above = map.getTile(t.getAbove());
        while (moves >= above.getTerrain().getMovementCost() && (above.getUnit() == null || above.getUnit().getFaction() == selectedUnit.getFaction())) {
            moveOptions.add(above);
            moves -= above.getTerrain().getMovementCost();
            above = map.getTile(above.getAbove());
        }
        //go down until out of moves
        moves = original_moves;
        Tile below = map.getTile(t.getBelow());
        while (moves >= below.getTerrain().getMovementCost() && (below.getUnit() == null || below.getUnit().getFaction() == selectedUnit.getFaction())) {
            moveOptions.add(below);
            moves -= below.getTerrain().getMovementCost();
            below = map.getTile(below.getBelow());
        }
        //recursive calls:
        //go left
        moves = original_moves;
        Tile left = map.getTile(t.getLeft());
        if (moves >= left.getTerrain().getMovementCost() && (left.getUnit() == null || left.getUnit().getFaction() == selectedUnit.getFaction())) {
            moves -= left.getTerrain().getMovementCost();
            getPotentialUnitMoves(moves, left);
        }

        //go right
        moves = original_moves;
        Tile right = map.getTile(t.getRight());
        if (moves >= right.getTerrain().getMovementCost() && (right.getUnit() == null || right.getUnit().getFaction() == selectedUnit.getFaction())) {
            moves -= right.getTerrain().getMovementCost();
            getPotentialUnitMoves(moves, right);
        }

    }

}