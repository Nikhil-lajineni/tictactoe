package com.scaler.tictactoe.Controller;

import com.scaler.tictactoe.models.Game;
import com.scaler.tictactoe.models.GameStatus;
import com.scaler.tictactoe.models.Player;

import java.util.List;

public class GameController {
    public Game createGame(int dimension, List<Player> players) {
        Game game = Game.getBuilder().setDimensions(dimension).setPlayers(players).build();
        return game;
    }

    public void undo(Game game) {

    }

    public void executeNextMove(Game game) {
        game.makeNextMove();
    }

    public Player getWinner(Game game) {
        return game.getWinner();
    }

    public GameStatus getGameStatus(Game game) {
        return game.getGameStatus();
    }

    public void displayBoard(Game game) {
        game.displayBoard();
    }
}
