package dk.sdu.mmmi.cbse.bulletsystem;

import com.badlogic.gdx.graphics.Color; import com.badlogic.gdx.math.MathUtils; import dk.sdu.mmmi.cbse.common.data.Entity; import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;


public class BulletPlugin implements IGamePluginService {
    private Entity bullet;
    private Entity shooter;

    public BulletPlugin(Entity shooter) {
        this.shooter = shooter;
    }

    @Override
    public void start(GameData gameData, World world) {
        bullet = new Bullet();
        createBullet(gameData,world);
        world.addEntity(bullet);
    }

    public void createBullet(GameData gameData,World world){
        this.bullet.setRadius(0.8f);
        PositionPart shooterPos = this.shooter.getPart(PositionPart.class);
        float offset = this.shooter.getRadius()+1 + this.bullet.getRadius()+1;
        float bulletX = shooterPos.getX() + MathUtils.cos(shooterPos.getRadians()) * offset;
        float bulletY = shooterPos.getY() + MathUtils.sin(shooterPos.getRadians()) * offset;
        PositionPart bulletPos = new PositionPart(bulletX,bulletY,shooterPos.getRadians());
        MovingPart bulletMov = new MovingPart(5f,2000f,3000f,50f);
        LifePart bulletLife = new LifePart(1,1);
        this.bullet.add(bulletPos);
        this.bullet.add(bulletLife);
        this.bullet.add(bulletMov);

    }


    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(bullet);
    }
}
