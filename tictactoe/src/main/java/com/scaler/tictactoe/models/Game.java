package com.scaler.tictactoe.models;

import com.scaler.tictactoe.Exceptions.InvalidDimensionException;
import com.scaler.tictactoe.Exceptions.InvalidPlayersException;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private GameStatus gameStatus;
    private int nextPlayerIndex;

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }
    public static Builder getBuilder(){
        return new Builder();
    }
    private Player winner;

    public Player getWinner() {
        return winner;
    }
    public void displayBoard(){
        board.displayBoard();
    }

    public static class Builder{
        public int getDimensions() {
            return dimensions;
        }

        public Builder setDimensions(int dimensions) {
            this.dimensions = dimensions;
            return this;
        }

        public List<Player> getPlayers() {
            return players;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }
        private boolean isValid() throws InvalidPlayersException, InvalidDimensionException {
            //Validations.
            if (players.size() != dimensions - 1) {
                throw new InvalidPlayersException("Number of players should be 1 less than dimension");
            }

            if (dimensions < 3) {
                throw new InvalidDimensionException("Dimension can't be less than 3");
            }

            //If 2 players have same symbol then invalidate the game.
            return true;
        }

        int dimensions;
        List<Player> players;
        public Game build(){
            try{
                isValid();
            } catch (InvalidDimensionException e) {
                throw new RuntimeException("dimensions invalid");
            } catch (InvalidPlayersException e) {
                throw new RuntimeException("players invalid");
            }
            Game game = new Game();
            game.setGameStatus(GameStatus.IN_PROGRESS);
            game.setPlayers(players);
            game.setBoard(new Board(dimensions));
            game.setMoves(new ArrayList<>());
            game.setNextPlayerIndex(0);
            return game;
        }

    }
}
