package unit.business

import spock.lang.Specification
import br.com.luisergio.bomberarena.business.MapBusiness

class MapBusinessGenerateObstaclesTest extends Specification {

    def "Generate Obstacles"() {
        given:
            MapBusiness mapBusiness = new MapBusiness(5)

        when:
            mapBusiness.GenerateObstacles(5)

        then: " to get a random locations of obstacles on the map"
            mapBusiness.obstacles.size() == 5
    }
}
