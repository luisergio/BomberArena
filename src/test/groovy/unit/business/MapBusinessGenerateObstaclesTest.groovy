package unit.business

import spock.lang.Specification
import br.com.luisergio.bomberarena.business.MapBusiness

class MapBusinessGenerateObstaclesTest extends Specification {

    def "Generate Obstacles"() {
        given:
            MapBusiness mapBusiness = new MapBusiness(mapSize)

        when:
            mapBusiness.generateObstacles(obstacleCoveragePercentage)

        then:
            mapBusiness.obstacles.size() == totalObstacle
            Math.round((Math.pow(mapSize,2) - Math.pow((mapSize-1)/2,2) - 12) * obstacleCoveragePercentage) == totalObstacle

        where:
            mapSize | obstacleCoveragePercentage | totalObstacle
            5       | 0.556f                     | 5
            9       | 0.80f                      | 42
            13      | 0.20f                      | 24
    }
}
