package unit.business

import spock.lang.Specification
import java.util.List

import br.com.luisergio.bomberarena.basics.Position
import br.com.luisergio.bomberarena.business.MapBusiness

class MapBusinessGetPlayerStartPositionsTest extends Specification {

    def "Get Player Start Positions"() {
        given:
            MapBusiness mapBusiness = new MapBusiness(mapSize)

        when:
            List<Position> result = mapBusiness.getPlayerStartPositions()

        then:
            result.size() == 12
            result.contains(new Position(X,Y)) == true

        where:
            mapSize | X  | Y
            5       | 1  | 1
            5       | 2  | 1
            5       | 1  | 2
            9       | 1  | 8
            9       | 1  | 9
            9       | 2  | 9
            15      | 15 | 15
            15      | 14 | 15
            15      | 15 | 14
            51      | 50 | 1
            51      | 51 | 1
            51      | 51 | 2
    }
}
