package com.example.minisoldiers;

import com.example.minisoldiers.Unit.Swordsman;
import javafx.fxml.FXML;
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
        game.spawnUnit(new Swordsman(Faction.PLAYER), 5, 5);
        game.spawnUnit(new Swordsman(Faction.BOT2), 10, 10);
    }
}