import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

module Asteroid {
    requires Common;
    requires CommonAsteroid;
    provides IGamePluginService with org.example.asteroids.AsteroidPlugin;
    provides IEntityProcessingService with org.example.asteroids.AsteroidControlSystem;
}