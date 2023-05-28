package org.example;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class AsteroidPlugin implements IGamePluginService {
    @Override
    public void start(GameData gameData, World world) {
            Asteroid asteroid = (Asteroid) createAsteroid();
            world.addEntity(asteroid);
    }

    public Entity createAsteroid(){
        Asteroid asteroid = new Asteroid();
        System.out.println("Created");
        PositionPart asterPos = new PositionPart(0, 0,100);
        LifePart asterLife = new LifePart(3,1000000);
        MovingPart asterMov = new MovingPart(0f,5f,5f,5f);
        asteroid.add(asterMov);
        asteroid.add(asterPos);
        asteroid.add(asterLife);
        float[] shapex = asteroid.getShapeX();
        float[] shapey = asteroid.getShapeY();
        float x = asterPos.getX();
        float y = asterPos.getY();
        float scaleFactor = 2.0f; // Modify this value to scale the rectangle
        float rectangleWidth = 1 * scaleFactor;
        float rectangleHeight = 2 * scaleFactor;
        float radians = asterPos.getRadians();
        shapex[0] = (float) (x + Math.cos(radians) * rectangleWidth);
        shapey[0] = (float) (y + Math.sin(radians) * rectangleWidth);

        shapex[1] = (float) (x + Math.cos(radians) * rectangleWidth);
        shapey[1] = (float) (y + Math.sin(radians) * rectangleHeight);

        shapex[2] = (float) (x + Math.cos(radians) * rectangleHeight);
        shapey[2] = (float) (y + Math.sin(radians) * rectangleHeight);

        shapex[3] = (float) (x + Math.cos(radians) * rectangleHeight);
        shapey[3] = (float) (y + Math.sin(radians) * rectangleWidth);

        asteroid.setShapeX(shapex);
        asteroid.setShapeY(shapey);
        asteroid.setRadius(40);
        return  asteroid;
    }

    @Override
    public void stop(GameData gameData, World world) {

    }
}
