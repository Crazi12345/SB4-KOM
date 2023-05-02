package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class BulletPlugin implements IGamePluginService {


    Bullet bullet;
    @Override
    public void start(GameData gameData, World world) {
        System.out.println("Created");
        world.addEntity(createBullet(gameData));

    }


    public Entity createBullet(GameData data){


        Entity bullet = new Bullet();
        bullet.add(new MovingPart(5, 100, 500, 0));
        bullet.add(new PositionPart(data.getDisplayHeight()/2, data.getDisplayWidth()/2, 3));
        bullet.setShapeX(new float[]{11, 231});
        return bullet;
    }



    @Override
    public void stop(GameData gameData, World world) {
        System.out.println("Destroyed");
        //TODO Make Bullet Disappear
    }
}
