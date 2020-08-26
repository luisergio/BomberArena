package unit.basics

import br.com.luisergio.bomberarena.basics.Position
import spock.lang.Specification

class PositionGetRandomTest extends Specification{

    def "Position.getRandom() result on valid Position" () {
        when:
            Position position = Position.getRandom(a,b)

        then: "respect the max and min value"
            position.getX() <= a
            position.getX() >= b
            position.getY() <= a
            position.getY() >= b

        where:
            a | b
            500 | 200
            100 | 10
            50 | 0
    }
}
