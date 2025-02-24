package dk.sdu.mmmi.cbse.bulletsystem;


import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import org.example.bullets.Bullet;
import org.example.bullets.BulletSPI;

public class BulletControlSystem implements IEntityProcessingService, BulletSPI {
    @Override
    public void process(GameData gameData, World world) {
        for (Entity bullet : world.getEntities(Bullet.class)) {
            PositionPart bulletPos = bullet.getPart(PositionPart.class);
            MovingPart bulletMov = bullet.getPart(MovingPart.class);
            LifePart bulletLife = bullet.getPart(LifePart.class);
            bulletMov.setAcceleration(2000000);
            bulletMov.setMaxSpeed(200);
            bulletLife.reduceExpiration(gameData.getDelta());
            bulletMov.setUp(true);
            bulletMov.process(gameData, bullet);
            bulletLife.process(gameData, bullet);
            bulletPos.process(gameData, bullet);
            UpdateShape(bullet);

            if (bulletLife.getExpiration() <= 0 || bulletLife.isDead()) {
                world.removeEntity(bullet);
            }
        }
    }


    public void UpdateShape(Entity bullet) {
        float[] shapex = bullet.getShapeX();
        float[] shapey = bullet.getShapeY();
        PositionPart p = bullet.getPart(PositionPart.class);
        float x = p.getX();
        float y = p.getY();
        float scaleFactor = 2.0f;
        float rectangleWidth = 1 * scaleFactor;
        float rectangleHeight = 2 * scaleFactor;
        float radians = p.getRadians();
        shapex[0] = (float) (x + Math.cos(radians) * rectangleWidth);
        shapey[0] = (float) (y + Math.sin(radians) * rectangleWidth);

        shapex[1] = (float) (x + Math.cos(radians) * rectangleWidth);
        shapey[1] = (float) (y + Math.sin(radians) * rectangleHeight);

        shapex[2] = (float) (x + Math.cos(radians) * rectangleHeight);
        shapey[2] = (float) (y + Math.sin(radians) * rectangleHeight);

        shapex[3] = (float) (x + Math.cos(radians) * rectangleHeight);
        shapey[3] = (float) (y + Math.sin(radians) * rectangleWidth);

        bullet.setShapeX(shapex);
        bullet.setShapeY(shapey);
    }


    @Override
    public Entity createBullet(Entity shooter, GameData gameData) {
        Entity bullet = new Bullet();
        bullet.setRadius(0.8f);
        PositionPart shooterPos = shooter.getPart(PositionPart.class);
        float offset = shooter.getRadius() + 1 + bullet.getRadius() + 1;
        float bulletX = shooterPos.getX() + (float)Math.cos(shooterPos.getRadians()) * offset;
        float bulletY = shooterPos.getY() + (float) Math.sin(shooterPos.getRadians()) * offset;
        PositionPart bulletPos = new PositionPart(bulletX, bulletY, shooterPos.getRadians());
        MovingPart bulletMov = new MovingPart(5f, 2000f, 3000f, 50f);
        LifePart bulletLife = new LifePart(1, 1);
        bullet.add(bulletPos);
        bullet.add(bulletLife);
        bullet.add(bulletMov);
        return bullet;
    }
}
