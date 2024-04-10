package com.example.minisoldiers;

import com.example.minisoldiers.Unit.Swordsman;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class GameController {
    @FXML
    Pane gamePane;
    MiniSoldiers game = MiniSoldiers.getGame();
    Map map = game.getMap();
    @FXML
    private void initialize() {
        map.draw();
        gamePane.getChildren().add(map);
        game.spawnUnit(new Swordsman(Faction.PLAYER), new Location(5, 5));
        game.spawnUnit(new Swordsman(Faction.BOT2), new Location(0,0));
    }

    @FXML
    private void click(MouseEvent e) {
        double x = e.getX();
        double y = e.getY();
        Location clickLocation = game.getLocation(x, y);
        Tile selectedTile = map.getTile(clickLocation);
        System.out.println(clickLocation);
        map.select(selectedTile);
    }
}