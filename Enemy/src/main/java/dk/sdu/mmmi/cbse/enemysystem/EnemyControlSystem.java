package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.util.SPILocator.SPILocator;
import org.example.bullets.BulletSPI;
import org.example.enemies.Enemy;

import java.util.Random;

public class EnemyControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {

        for (Entity enemy : world.getEntities(Enemy.class)) {
            PositionPart positionPart = enemy.getPart(PositionPart.class);
            MovingPart movingPart = enemy.getPart(MovingPart.class);
            Random rand = new Random();
            int number = rand.nextInt(10);
            movingPart.setUp(false);
            movingPart.setRight(false);
            movingPart.setLeft(false);
            switch (number){
                case 0:
                    movingPart.setLeft(true);
                    break;
                case 1:
                    movingPart.setRight(true);
                    break;
                case 2:
                    movingPart.setUp(true);
                    break;
                case 11:
                    for (BulletSPI bullet : SPILocator.locateAll(BulletSPI.class)) {
                        world.addEntity(bullet.createBullet(enemy, gameData));
                    }
                    break;
                default:
                    break;

            }

            movingPart.process(gameData, enemy);
            positionPart.process(gameData, enemy);

            updateShape(enemy);
        }
    }

    private void updateShape(Entity entity) {
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();

        shapex[0] = (float) (x + Math.cos(radians) * 5);
        shapey[0] = (float) (y + Math.sin(radians) * 5);

        shapex[1] = (float) (x + Math.cos(radians - 4 * 3.1415f / 5) * 5);
        shapey[1] = (float) (y + Math.sin(radians - 4 * 3.1145f / 5) * 5);

        shapex[2] = (float) (x + Math.cos(radians + 3.1415f) * 5);
        shapey[2] = (float) (y + Math.sin(radians + 3.1415f) * 5);

        shapex[3] = (float) (x + Math.cos(radians + 4 * 3.1415f / 5) * 5);
        shapey[3] = (float) (y + Math.sin(radians + 4 * 3.1415f / 5) * 5);

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }

}

