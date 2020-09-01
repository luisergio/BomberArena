package br.com.luisergio.bomberarena.util;

import java.util.Random;

import br.com.luisergio.bomberarena.exception.InvalidPercentageValue;

public class NumberUtil {

    public static void validatePercentage(float percentage) {
        if(percentage < 0f || percentage > 1) {
            throw new InvalidPercentageValue();
        }
    }

    public static Integer getRandomInteger(Integer min, Integer max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

}