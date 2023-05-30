import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

module Collision {
    requires Common;
    requires CommonBullet;
    requires Player;


    provides IPostEntityProcessingService with dk.sdu.mmmi.cbse.common.collision.CollisionDetector;
}