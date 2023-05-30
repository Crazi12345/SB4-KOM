import dk.sdu.mmmi.cbse.bulletsystem.BulletControlSystem;
import dk.sdu.mmmi.cbse.bulletsystem.BulletPlugin;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import org.example.bullets.BulletSPI;

module Bullet {
    requires Common;
    requires CommonBullet;


    provides IGamePluginService with BulletPlugin;
    provides BulletSPI with BulletControlSystem;
    provides IEntityProcessingService with BulletControlSystem;
}