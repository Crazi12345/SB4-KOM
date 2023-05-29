package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import org.example.Enemy;

public class EnemyPlugin  implements IGamePluginService {

    private Entity enemy;

    public EnemyPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {

        // Add entities to the world
        enemy = createEnemyShip(gameData);
        world.addEntity(enemy);
    }

    private Entity createEnemyShip(GameData gameData) {

        float deacceleration = 30;
        float acceleration = 200;
        float maxSpeed = 600;
        float rotationSpeed = 5;
        float x = gameData.getDisplayWidth() / 2+50;
        float y = gameData.getDisplayHeight() / 2+50;
        float radians = 3.1415f / 2;


        Entity playerShip = new Enemy();
        playerShip.add(new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed));
        playerShip.add(new PositionPart(x, y, radians));
        playerShip.add(new LifePart(2,1000000));
        playerShip.setRadius(6);
        return playerShip;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(enemy);
    }
}
