package com.scaler.tictactoe.strategies.gamewinningstrategy;

import com.scaler.tictactoe.models.Board;
import com.scaler.tictactoe.models.Move;

public interface GameWinningStrategy {
    boolean checkWinner(Board board, Move move);
}
