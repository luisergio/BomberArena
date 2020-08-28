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

    private List<Position> playerStartPositions;

    public MapBusiness(Integer mapSize){

        //The size of the map must be even and bigger or equal then five.
        if(mapSize % 2 == 0 || mapSize < 5) {
            throw new InvalidMapSizeException();
        }

        this.size = mapSize;

        this.playerStartPositions = this.getPlayerStartPositions();

    }

    /**
     * Generate randomicaly obstacles on the map
     * @param numberOfObstacles
     */
    public void generateObstacles(Integer numberOfObstacles) {
        this.obstacles = new ArrayList<Position>();
        
        for(int index = 0; index < numberOfObstacles; index ++) {
            Position obstacle = this.getValidRandomPosition();
            this.obstacles.add(obstacle);
        }
    }


    /**
     * Get valid position on the map.
     * A valid position is where:
     *  1) is not a fixed wall (a fix wall is where X and Y are both even)
     *  2) there is no obstacle
     *  3) is not a position a player needs to start
     * @return the position
     */
    public Position getValidRandomPosition(){
        Position position;
        boolean valid;

        do {
            Position positionTemp = Position.getRandom(this.size, 1);

            //1) Check if there is a fixed wall
            valid = positionTemp.getX() % 2 != 0 || positionTemp.getY() % 2 != 0;

            //2) Check if there is a obstacle.
            if(valid) {
                valid = !this.obstacles.stream().anyMatch(obstacle -> positionTemp.equals(obstacle));
            }

            //3) Check if there is not a position a player needs to start
            if(valid) {
                valid = !this.playerStartPositions.stream().anyMatch(obstacle -> positionTemp.equals(obstacle));
            }

            position = positionTemp;

        } while(!valid);

        return position;
    }

    private List<Position> getPlayerStartPositions() {
        List<Position> playerStartPositions = new ArrayList<Position>();

        //Upper left corner
        playerStartPositions.add(new Position(1,2));
        playerStartPositions.add(new Position(1,1));
        playerStartPositions.add(new Position(2,1));

        //Upper right corner
        playerStartPositions.add(new Position(this.size-1,1));
        playerStartPositions.add(new Position(this.size,1));
        playerStartPositions.add(new Position(this.size,2));

        //Lower right corner
        playerStartPositions.add(new Position(this.size,this.size-1));
        playerStartPositions.add(new Position(this.size,this.size));
        playerStartPositions.add(new Position(this.size-1,this.size));

        //Lower left corner
        playerStartPositions.add(new Position(2,this.size));
        playerStartPositions.add(new Position(1,this.size));
        playerStartPositions.add(new Position(1,this.size-1));

        return playerStartPositions;
    }

}