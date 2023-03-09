package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class BulletPlugin implements IGamePluginService {
    @Override
    public void start(GameData gameData, World world) {
        System.out.println("Created");
        //TODO Create something
    }




    @Override
    public void stop(GameData gameData, World world) {
        System.out.println("Destroyed");
        //TODO Make Bullet Disappear
    }
}
