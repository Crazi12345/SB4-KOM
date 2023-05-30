import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

module Common {

    exports dk.sdu.mmmi.cbse.common.data;
    exports dk.sdu.mmmi.cbse.common.services;
    exports dk.sdu.mmmi.cbse.common.data.entityparts;
    exports dk.sdu.mmmi.cbse.common.util.SPILocator;

    uses IEntityProcessingService;
    uses IGamePluginService;
    uses IPostEntityProcessingService;

}