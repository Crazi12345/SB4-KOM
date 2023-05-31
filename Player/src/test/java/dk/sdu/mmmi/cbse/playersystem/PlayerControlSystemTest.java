package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import org.junit.jupiter.api.Test;

import java.util.WeakHashMap;

import static dk.sdu.mmmi.cbse.common.data.GameKeys.UP;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class PlayerControlSystemTest {

    @Test
    void process() {
        World w = new World();
        GameData gameData = new GameData();
        PlayerControlSystem playerControlSystem = new PlayerControlSystem();
        Player p = new Player();
        PositionPart pospart = new PositionPart(1f,1f,1f);
        p.add(pospart);
        MovingPart mv = new MovingPart(0,1000000,10000000,100);
        mv.setUp(true);
        p.add(mv);
        w.addEntity(p);

        playerControlSystem.process(gameData,w);

        PositionPart pp = p.getPart(PositionPart.class);

        assertEquals(pospart.getX(), pp.getX());
    }

}