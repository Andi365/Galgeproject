package com.example.rest;

import java.util.ArrayList;

public class Game {
    String visibleWord;
    ArrayList<String> usedLetters = new ArrayList<>();
    int lives;
    boolean isGameOver;

    public Game(String visibleWord, int lives, ArrayList<String> usedLetters, boolean isGameOver) {
        this.visibleWord = visibleWord;
        this.usedLetters = usedLetters;
        this.lives = lives;
        this.isGameOver = isGameOver;
    }

    public String getVisibleWord() {
        return visibleWord;
    }

    public void setVisibleWord(String visibleWord) {
        this.visibleWord = visibleWord;
    }

    public ArrayList<String> getUsedLetters() {
        return usedLetters;
    }

    public void setUsedLetters(ArrayList<String> usedLetters) {
        this.usedLetters = usedLetters;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }
}
