package org.example.asteroids;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import org.example.Asteroid;

public class AsteroidControlSystem implements IEntityProcessingService{

    private boolean isSplit = false;
    @Override
    public void process(GameData gameData, World world) {
        for(Entity asteroid : world.getEntities(Asteroid.class)){
            PositionPart asterPos = asteroid.getPart(PositionPart.class);
            MovingPart asterMov = asteroid.getPart(MovingPart.class);
            LifePart asterLife = asteroid.getPart(LifePart.class);
            asterMov.setAcceleration(5);
            asterMov.setMaxSpeed(200);
            asterMov.setUp(true);
            asterMov.process(gameData,asteroid);
            asterPos.process(gameData,asteroid);
            UpdateShape(asteroid,asterLife.getLife()*5);


            if(asterLife.isIsHit()){
                System.out.println("hit");
            splitter(world,asteroid);
            }



        }
    }

    public void UpdateShape(Entity entity, int numPoints  ){
        PositionPart position = entity.getPart(PositionPart.class);
        float[] shapex = new float[numPoints];
        float[] shapey = new float[numPoints];
        float radians = position.getRadians();
        float x = position.getX();
        float y = position.getY();
        float radius = entity.getRadius();

        float angle = 0;

        for (int i = 0; i < numPoints; i++) {
            shapex[i] = x + (float) Math.cos(angle + radians) * radius;
            shapey[i] = y + (float) Math.sin(angle + radians) * radius;
            angle += 2 * 3.1415f / numPoints;
        }

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }

    public void splitter(World world, Entity aster){
        world.removeEntity(aster);
        float[] newShapeX = new float[aster.getShapeX().length];
        float[] newShapeY = new float[aster.getShapeY().length];
        for(int i = 0; i<aster.getShapeX().length;i++){
            newShapeX[i]=aster.getShapeX()[i]/2;
            newShapeY[i]=aster.getShapeY()[i]/2;
        }
        PositionPart positionPart = aster.getPart(PositionPart.class);
        float newPosX = positionPart.getX();
        float newPosY = positionPart.getY();

        Entity split1 = new Asteroid();
        split1.setRadius(aster.getRadius()/2);
        split1.setShapeX(newShapeX);
        split1.setShapeY(newShapeY);
        split1.add(new PositionPart(newPosX+50,newPosY+50,5));
        split1.add(new MovingPart(0,5,5,5));
        split1.add(new LifePart(1,10000));
        world.addEntity(split1);
        Entity split2 = new Asteroid();
        split2.setRadius(aster.getRadius()/2);
        split2.setShapeX(newShapeX);
        split2.setShapeY(newShapeY);
        split2.add(new PositionPart(newPosX,newPosY,5));
        split2.add(new MovingPart(0,5,5,5));
        split2.add(new LifePart(1,10000));
        world.addEntity(split2);

    }
}
