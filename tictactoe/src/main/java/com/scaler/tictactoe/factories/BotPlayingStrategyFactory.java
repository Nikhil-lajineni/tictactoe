package com.scaler.tictactoe.factories;

import com.scaler.tictactoe.models.BotDifficultyLevel;
import com.scaler.tictactoe.strategies.botplayingstrategy.BotPlayingStrategy;
import com.scaler.tictactoe.strategies.botplayingstrategy.EasyBotPlayingStrategy;

public class BotPlayingStrategyFactory {

    public static BotPlayingStrategy getBotPlayingStrategyForDifficultyLevel(BotDifficultyLevel difficultyLevel) {
        if (difficultyLevel.equals(BotDifficultyLevel.EASY)) {
            return new EasyBotPlayingStrategy();
        }
//        else if (difficultyLevel.equals(BotDifficultyLevel.MEDIUM)) {
//            //return new MediumBotPlayingStrategy();
//            return
//        }
        return null;
    }
}
