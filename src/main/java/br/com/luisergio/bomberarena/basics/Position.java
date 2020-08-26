package br.com.luisergio.bomberarena.basics;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;
import java.util.Random;

@AllArgsConstructor
@Data
public class Position  {

    private Integer X;
    private Integer Y;

    public static Position getRandom(Integer max, Integer min) {
        Random rand = new Random();
        int X = rand.nextInt((max - min) + 1) + min;
        int Y = rand.nextInt((max - min) + 1) + min;
        return new Position(X, Y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position position = (Position) o;
        return X.equals(position.X) &&
                Y.equals(position.Y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(X, Y);
    }
}