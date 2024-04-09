package com.example.minisoldiers;

import javafx.scene.paint.Color;

public enum Terrain {
    ROAD{
        @Override
        public Color getColor() {
            return Color.BROWN;
        }
    }, GRASS{
        @Override
        public Color getColor() {
            return Color.LIGHTGREEN;
        }
    }, WOODS{
        @Override
        public Color getColor() {
            return Color.DARKGREEN;
        }
    }, RIVER{
        @Override
        public Color getColor() {
            return Color.BLUE;
        }
    }, HILL{
        @Override
        public Color getColor() {
            return Color.GREENYELLOW;
        }
    }, MOUNTAIN{
        @Override
        public Color getColor() {
            return Color.WHITE;
        }
    };

    public abstract Color getColor();
}
