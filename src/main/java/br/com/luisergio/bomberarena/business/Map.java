package br.com.luisergio.bomberarena.business;

import java.util.List;

import br.com.luisergio.bomberarena.basics.Position;
import br.com.luisergio.bomberarena.exception.InvalidMapSizeException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class Map {

    @Getter
    @Setter
    private String name;

    @Getter
    private Integer size;

    @Getter //TODO: Remover getter
    private List<Position> obstacles;

    public Map(Integer mapSize){

        //Se o tamanho for par OU menor que 3
        if(mapSize % 2 == 0 || mapSize < 3) {
            throw new InvalidMapSizeException();
        }

        this.size = mapSize;
    }

    public void GenerateObstacles(Integer numberOfObstacles) {

        
    }
}