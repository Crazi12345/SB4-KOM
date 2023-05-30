module Core {
    requires com.badlogic.gdx;
    requires Common;
    requires java.desktop;
    requires spring.context;
    requires spring.beans;
    requires spring.core;
    exports dk.sdu.mmmi.cbse.main;
    opens dk.sdu.mmmi.cbse.main to spring.core;


    uses dk.sdu.mmmi.cbse.common.services.IGamePluginService;
    uses dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
    uses dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
}