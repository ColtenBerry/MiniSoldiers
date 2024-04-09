package com.example.minisoldiers;

import javafx.scene.paint.Color;

public enum Faction {
    PLAYER{
        @Override
        public Color getColor() {
            return Color.DODGERBLUE;
        }
    }, BOT1{
        @Override
        public Color getColor() {
            return Color.DARKRED;
        }
    }, BOT2{
        @Override
        public Color getColor() {
            return Color.NAVY;
        }
    }, BOT3{
        @Override
        public Color getColor() {
            return Color.FIREBRICK;
        }
    }, BOT4{
        @Override
        public Color getColor() {
            return Color.THISTLE;
        }
    };

    public abstract Color getColor();
}
