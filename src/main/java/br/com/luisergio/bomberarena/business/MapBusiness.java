package br.com.luisergio.bomberarena.business;

import java.util.ArrayList;
import java.util.List;

import br.com.luisergio.bomberarena.basics.Position;
import br.com.luisergio.bomberarena.exception.InvalidMapSizeException;
import br.com.luisergio.bomberarena.util.NumberUtil;
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

    private List<Position> blockablePositions;

    public MapBusiness(Integer mapSize){

        //The size of the map must be even and bigger or equal then five.
        if(mapSize % 2 == 0 || mapSize < 5) {
            throw new InvalidMapSizeException();
        }

        this.size = mapSize;

        this.playerStartPositions = this.getPlayerStartPositions();

        this.blockablePositions = this.getBlockablePositions();

    }

    /**
     * Generate randomicaly obstacles on the map
     * @param obstacleCoveragePercentage
     */
    public void generateObstacles(Float obstacleCoveragePercentage) {
        
        NumberUtil.validatePercentage(obstacleCoveragePercentage);

        Integer numberOfObstacles = Math.round(this.blockablePositions.size() * obstacleCoveragePercentage);

        this.obstacles = new ArrayList<Position>();

        for(int number = 0; number < numberOfObstacles; number ++) {

            int index = Math.round(NumberUtil.getRandomInteger(1, this.blockablePositions.size())) - 1;

            this.obstacles.add(this.blockablePositions.get(index));

            this.blockablePositions.remove(index);
        }
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

    private List<Position> getBlockablePositions() {

        List<Position> result = new ArrayList<Position>();

        for(Integer x = 1; x <= this.size; x++) {
            for(Integer y = 1; y <= this.size; y++) {
            
                boolean valid;
                Position positionTemp = new Position(x, y);

                //Check if there is a fixed wall
                valid = positionTemp.getX() % 2 != 0 || positionTemp.getY() % 2 != 0;

                if(valid) {

                    //Check if there is not a position a player needs to start
                    valid = !this.playerStartPositions.stream().anyMatch(obstacle -> positionTemp.equals(obstacle));

                    if(valid) {
                        result.add(positionTemp);
                    }
                }

            }   
        }

         

        return result;

    }
}