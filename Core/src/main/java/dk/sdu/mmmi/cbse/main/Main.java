package dk.sdu.mmmi.cbse.main;


import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ModuleConfig.class);
        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        cfg.setTitle("Asteroids");
        for (String beanName : ctx.getBeanDefinitionNames()) {
            System.out.println(beanName);
        }
        int width = 1920;
        int height = 1080;
        cfg.setWindowSizeLimits(width, height, width, height);
        cfg.setWindowedMode(width, height);
        cfg.setResizable(false);
        new Lwjgl3Application(ctx.getBean(Game.class), cfg);

    }

}
