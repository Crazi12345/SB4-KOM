package dk.sdu.mmmi.cbse.playersystem;


import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerPluginTest {
    PlayerPlugin player;
    @BeforeEach
    void setup(){
        player = new PlayerPlugin();
    }
    @Test
    void start() {
        World world = new World();
        GameData gameData = mock(GameData.class);


        int numberOfEntities = world.getEntities().size();
        player.start(gameData,world);
        assertEquals(numberOfEntities+1, world.getEntities().size());
    }

    @Test
    void createPlayerShip() {
        GameData gameData = mock(GameData.class);
        Entity p = player.createPlayerShip(gameData);
        assertNotNull(p.getPart(PositionPart.class));
        assertNotNull(p.getPart(LifePart.class));
        assertNotNull(p.getPart(MovingPart.class));
    }

    @Test
    void stop() {  World world = new World();
        GameData gameData = mock(GameData.class);

        player.start(gameData,world);
        int numberOfEntities = world.getEntities().size();
        player.stop(gameData,world);
        assertEquals(numberOfEntities-1, world.getEntities().size());
    }
}