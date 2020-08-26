package br.com.luisergio.bomberarena.business;

import java.util.ArrayList;
import java.util.List;

import br.com.luisergio.bomberarena.basics.Position;
import br.com.luisergio.bomberarena.exception.InvalidMapSizeException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type Map business.
 */
@NoArgsConstructor
public class MapBusiness {

    @Getter
    @Setter
    private String name;

    @Getter
    private Integer size;

    @Getter //TODO: Remover getter
    private List<Position> obstacles;

    public MapBusiness(Integer mapSize){

        //Se o tamanho for par OU menor que 3
        if(mapSize % 2 == 0 || mapSize < 3) {
            throw new InvalidMapSizeException();
        }

        this.size = mapSize;
    }


    public void GenerateObstacles(Integer numberOfObstacles) {

        this.obstacles = new ArrayList<Position>();
        
        for(int index = 0; index < numberOfObstacles; index ++) {
            Position obstacle = this.getValidPosition();
            this.obstacles.add(obstacle);
        }
    }


    /**
     * Get valid position on the map.
     * A valid position is where:
     *  1) is not a fixed stone (a fix stone is where X and Y are both even)
     *  2) there is no obstacle
     *  3) TODO: Do not block the positions a player needs to start
     * @return the position
     */
    private Position getValidPosition(){
        Position position;
        boolean valid;

        do {
            Position positionTemp = Position.getRandom(this.size, 0);

            //1) Check if there is a fixed stone
            valid = positionTemp.getX() % 2 == 0 && positionTemp.getY() % 2 == 0;

            //2) Check if there is a obstacle.
            if(valid) {
                valid = !this.obstacles.stream().anyMatch(obstacle -> positionTemp.equals(obstacle));
            }

            position = positionTemp;

        } while(!valid);

        return position;
    }
}