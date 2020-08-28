package unit.business

import br.com.luisergio.bomberarena.basics.Position
import br.com.luisergio.bomberarena.business.MapBusiness

import spock.lang.Specification

class MapBusinessGenerateObstaclesTest extends Specification {

    def "Get Valid Random Position"() {
        given:
            MapBusiness mapBusiness = new MapBusiness(5)

        when:
            Position position = mapBusiness.getValidRandomPosition()

        then: 
            (
                //1) Check if there is a fixed wall
                position.getX() % 2 != 0 || position.getY() % 2 != 0;
                &&
                //2) Check if there is a obstacle.
                !mapBusiness.obstacles.stream().anyMatch(obstacle -> position.equals(obstacle))
            ) == true

    }
}
