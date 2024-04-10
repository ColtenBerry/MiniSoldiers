package com.example.minisoldiers;

import javafx.scene.paint.Color;

public enum Terrain {
    ROAD{
        @Override
        public Color getColor() {
            return Color.BROWN;
        }

        @Override
        public int getMovementCost() {
            return 1;
        }
    }, GRASS{
        @Override
        public Color getColor() {
            return Color.LIGHTGREEN;
        }

        @Override
        public int getMovementCost() {
            return 2;
        }
    }, WOODS{
        @Override
        public Color getColor() {
            return Color.DARKGREEN;
        }

        @Override
        public int getMovementCost() {
            return 3;
        }
    }, RIVER{
        @Override
        public Color getColor() {
            return Color.BLUE;
        }

        @Override
        public int getMovementCost() {
            return 100;
        }
    }, HILL{
        @Override
        public Color getColor() {
            return Color.GREENYELLOW;
        }

        @Override
        public int getMovementCost() {
            return 3;
        }
    }, MOUNTAIN{
        @Override
        public Color getColor() {
            return Color.WHITE;
        }

        @Override
        public int getMovementCost() {
            return 4;
        }
    },
    BORDER{
        @Override
        public Color getColor() {
            return Color.BLACK;
        }

        @Override
        public int getMovementCost() {
            return 10000;
        }
    };

    public abstract Color getColor();
    public abstract int getMovementCost();
}
