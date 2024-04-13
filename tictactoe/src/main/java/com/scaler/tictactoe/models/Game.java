package com.scaler.tictactoe.models;

import com.scaler.tictactoe.Exceptions.InvalidDimensionException;
import com.scaler.tictactoe.Exceptions.InvalidPlayersException;
import com.scaler.tictactoe.strategies.gamewinningstrategy.GameWinningStrategy;
import com.scaler.tictactoe.strategies.gamewinningstrategy.OrderOneGameWinningStrategy;

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
    private GameWinningStrategy gameWinningStrategy;

    public GameWinningStrategy getGameWinningStrategy() {
        return gameWinningStrategy;
    }

    public void setGameWinningStrategy(GameWinningStrategy gameWinningStrategy) {
        this.gameWinningStrategy = gameWinningStrategy;
    }

    public void makeNextMove() {
        //Steps:
        //1. Player should be able to decide the Move.
        //2. Check the validation of the move, if move is valid then make the move.

        Player playerToMove = players.get(nextPlayerIndex);

        System.out.println("It is " + playerToMove.getName() + "'s turn");
        Move move = playerToMove.decideMove(board);

        //validate the Move.
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        if (board.getBoard().get(row).get(col).getCellState().equals(CellState.EMPTY)) {
            //Move is valid.
            board.applyMove(move);
            moves.add(move);

            //Check the winner.
            if (gameWinningStrategy.checkWinner(board, move)) {
                gameStatus = GameStatus.ENDED;
                winner = playerToMove;
            }


            nextPlayerIndex += 1;
            nextPlayerIndex %= players.size();
        } else {
            //throw some exception.
        }
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
            game.setGameWinningStrategy(new OrderOneGameWinningStrategy(dimensions));
            return game;
        }

    }
}
