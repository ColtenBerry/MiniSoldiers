package com.example.minisoldiers;

import com.example.minisoldiers.Unit.Swordsman;
import com.example.minisoldiers.Unit.Unit;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class GameController {
    @FXML
    Pane gamePane;
    @FXML
    Button endTurn;
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
    private void endTurn() {
        ArrayList<Unit> units = game.getUnits();
        for (Unit u: units) {
            u.setHasAttacked(false);
            u.setHasMoved(false);
        }
    }

    @FXML
    private void click(MouseEvent e) {
        double x = e.getX();
        double y = e.getY();
        Location clickLocation = game.getLocation(x, y);
        Tile selectedTile = map.getTile(clickLocation);
        System.out.println(clickLocation);
        selectV2(selectedTile);


    }
    //TODO: create a way to attack the enemy
    /*
    move the unit
    allow the unit to attack anything within its range:
        - get all tiles in the range
        - highlight all tiles with hostile units
        - allow for an additional click that will attack chosen unit
     */
    ArrayList<Tile> tileOptions = new ArrayList<>();
    public void displayMoves() {
        highlightPotentialMoves(selectedTile);
        for (Tile tile: tileOptions) {
            tile.highlight(Color.GREENYELLOW);
        }
    }
    public void doMovement() {
        Unit unit = previouslySelectedTile.popUnit();
        selectedTile.addUnit(unit);
        selectedUnit.setHasMoved(true);
    }
    public void unHighlight() {
        for (Tile t: tileOptions) {
            t.unHighlight();
        }
        tileOptions.clear();
    }
    public void displayTargets() {
        highlightPotentialTargets(selectedTile);
        if (tileOptions.isEmpty()) {
             selectedUnit = null;
        }
        else {
            for (Tile t : tileOptions) {
                t.highlight(Color.RED);
            }
        }
    }
    public void commenceAttack() {
        System.out.println("Attack");
        previouslySelectedTile.attack(selectedTile);
        System.out.println(previouslySelectedTile.getUnit());
        System.out.println(selectedTile.getUnit());
        selectedUnit.setHasAttacked(true);
        if (selectedUnit.getHealth() < 0) {
            previouslySelectedTile.popUnit();
        }
        if (selectedTile.getUnit().getHealth() < 0) {
            selectedTile.popUnit();
        }
        selectedUnit = null;
    }
    public void selectV2(Tile t) {
        previouslySelectedTile = selectedTile;
        selectedTile = t;
        if (previouslySelectedTile != null) {
            previouslySelectedTile.unHighlight();
        }
        selectedTile.highlight(Color.GREENYELLOW);
        if (selectedUnit == null && selectedTile.containsUnit()) {
            selectedUnit = selectedTile.getUnit();
            if (!selectedUnit.hasMoved()) {
                displayMoves();
            }
            else if (!selectedUnit.hasAttacked()) {
                displayTargets();
            }
        }
        //these else ifs make it so that you have to wait until the next turn to activate.
        else if (selectedUnit != null) {
            if (!selectedUnit.hasMoved() && tileOptions.contains(selectedTile)) {
                doMovement();
                unHighlight();
                displayTargets();
            }
            else if (!selectedUnit.hasAttacked() && tileOptions.contains(selectedTile)) {
                commenceAttack();
                unHighlight();
            }
            else {
                unHighlight();
                selectedUnit = null;
            }
        }
    }
//    public void select(Tile t) {
//        /*
//        There should be 3 scenarios (so far) for a click:
//            - No unit is currently selected:
//                - An empty tile is clicked on:
//                    select the tile
//                    highlight the tile
//                - A unit is clicked on :
//                    select the tile
//                    highlight the tile
//                    select the unit
//                    get potential unit moves
//                    highlight potential unit moves
//            - A unit is currently selected:
//                - If the unit has not yet moved
//                    let it choose its move
//                    show the potential attacks
//                - If the unit has already moved
//                    let it choose its potential attack
//                    unselect the unit
//
//
//         */
//        System.out.println("Selected Unit: " + selectedUnit);
//
//        // No unit is currently selected
//        if (selectedUnit == null) {
//            // Select / highlight the tile
//            //if there is a selected tile
//            if (selectedTile != null) {
//                previouslySelectedTile = selectedTile; // set previously selected tile
//                previouslySelectedTile.setSelected(false); // unselect the previously selected tile
//                previouslySelectedTile.highlight(false); // unhighlight the previously selected tile
//            }
//            selectedTile = t; // set selected tile
//            selectedTile.setSelected(true); // select the selected tile
//            selectedTile.highlight(true); // highlight the selected tile
//            //if the newly selected tile contains a unit
//            if (selectedTile.containsUnit() && selectedUnit == null) {
//                selectedUnit = selectedTile.getUnit(); // sets the selectedUnit field
//                if (!selectedUnit.hasMoved()) {
//                    highlightPotentialMoves(selectedTile); //highlight any potential moves of the newly selected unit
//                }
//                else {
//                    selectedUnit = null;
//                }
//            }
//        }
//        //if there is currently a unit selected
//        else {
//            //if the unit has not yet moved
//            if (!selectedUnit.hasMoved()) {
//                // if the selected tile is a valid movement option
//                if (tileOptions.contains(t)) {
//                    previouslySelectedTile = selectedTile; // sets previouslySelectedTile
//                    previouslySelectedTile.highlight(false); // unhighlights the previouslySelectedTile
//                    selectedTile = t; // sets the selectedTile
//                    Unit unit = previouslySelectedTile.popUnit(); // removes the selected unit from the previously selected tile
//                    selectedTile.addUnit(unit); // adds the selected unit to the newly selected tile
//                }
//                else { // selected tile is not a valid movement option
//                    for (Tile tile : tileOptions) {
//                        tile.highlight(false); // unhighlights all the possible moves of the selected unit
//                    }
//                    tileOptions.clear(); // clears the arraylist so it can be re-used
//                    selectedUnit = null;
//                    return;
//                }
//
//
//                for (Tile tile : tileOptions) {
//                    tile.highlight(false); // unhighlights all the possible moves of the selected unit
//                }
//                tileOptions.clear(); // clears the arraylist so it can be re-used
//                selectedUnit.setHasMoved(true); // sets the unit to have already moved
//            }
//            //if the unit has moved and not yet attacked
//            if (!selectedUnit.hasAttacked()) {
//                highlightPotentialTargets(selectedTile); //get potential attack options
//                // if there are no valid attacks, then go ahead and set the hasAttacked to be true. since there are no attacks to make
//                if (tileOptions.isEmpty()) {
//                    System.out.println("no valid attack options");
//                    selectedUnit.setHasAttacked(true); //sets the unit to have already attacked. see above comment
//                    tileOptions.clear(); //clear the tileOptions
//                    selectedUnit = null; //unselect the unit since it is done
//                    return;
//                }
//                System.out.println("reached");
//                if (tileOptions.contains(t)) { // attacking a valid target
//                    for (Tile tile: tileOptions) {
//                        tile.highlight(false); //unhighlight everything
//                    }
//                    tileOptions.clear();
//                }
//                else { // not valid tile chosen.
//                    selectedUnit = null; // deselect the unit;
//                }
//                //Now we need to determine if the unit can attack and highlight potential targets
////                highlightPotentialTargets(selectedTile); //highlights any potential targets
//
//            }
//        }
//    }

    public void highlightPotentialTargets(Tile t) {
        Unit unit = t.getUnit();
        int range = unit.getRange();
        getPotentialUnitAttacks(range, t);
        for (Tile tile: tileOptions) {
            tile.highlight(Color.RED);
        }
    }

    public void getPotentialUnitAttacks(int range, Tile t) {
        if (t.containsUnit()) {
            if (t.getUnit().getFaction() != selectedUnit.getFaction()) {
                tileOptions.add(t);
            }
        }
        Tile above = map.getTile(t.getAbove());
        Tile below = map.getTile(t.getBelow());
        Tile left = map.getTile(t.getLeft());
        Tile right = map.getTile(t.getRight());
        if (range > 0) {
            getPotentialUnitAttacks(range - 1, above);
        }
        if (range > 0) {
            getPotentialUnitAttacks(range - 1, below);
        }
        if (range > 0) {
            getPotentialUnitAttacks(range - 1, left);
        }
        if (range > 0) {
            getPotentialUnitAttacks(range - 1, right);
        }
    }

    public void highlightPotentialMoves(Tile t) {
        Unit unit = t.getUnit();
        int moves = unit.getMovement();
        //gets all potential moves. Adds them to moveOptions
        getPotentialUnitMovesV2(moves, t);

        //highlight all potential moves
        for (Tile tile: tileOptions) {
            tile.highlight(Color.GREENYELLOW);
        }
    }
    public void getPotentialUnitMovesV2(int moves, Tile t) {
        if (!t.containsUnit()) {
            tileOptions.add(t);
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
        tileOptions.add(t);
        //go up until out of moves
        Tile above = map.getTile(t.getAbove());
        while (moves >= above.getTerrain().getMovementCost() && (above.getUnit() == null || above.getUnit().getFaction() == selectedUnit.getFaction())) {
            tileOptions.add(above);
            moves -= above.getTerrain().getMovementCost();
            above = map.getTile(above.getAbove());
        }
        //go down until out of moves
        moves = original_moves;
        Tile below = map.getTile(t.getBelow());
        while (moves >= below.getTerrain().getMovementCost() && (below.getUnit() == null || below.getUnit().getFaction() == selectedUnit.getFaction())) {
            tileOptions.add(below);
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