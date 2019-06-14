package View;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Graphic extends Application implements Runnable {
    private static String region = "Account";
    private Stage stage;
    private Scene scene;
    private Group group = new Group();
    private static boolean flag = false;

    public static void setRegion(String region) {
        synchronized (region) {
            Graphic.region = region;
            flag = true;
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Thread thread = new Thread(this);
        thread.start();
        Parent root;
        stage = primaryStage;
        switch (region) {
            case "Account":
                root = FXMLLoader.load(getClass().getResource("Accountfxml.fxml"));
                primaryStage.setTitle("Account");
                scene = new Scene(root, 600, 400);
                break;
            case "MainMenu":
                root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
                primaryStage.setTitle("Hello World");
                scene = new Scene(root, 270, 400);
                break;
            case "HelpAccount":
                root = FXMLLoader.load(getClass().getResource("HelpAccountfxml.fxml"));
                primaryStage.setTitle("HelpAccount");
                scene = new Scene(root, 600, 400);
                break;
            case "HelpMenu":
                root = FXMLLoader.load(getClass().getResource("HelpMenufxml.fxml"));
                primaryStage.setTitle("HelpMenu");
                scene = new Scene(root, 600, 400);
            case "HelpShop":
                root = FXMLLoader.load(getClass().getResource("HelpShopfxml.fxml"));
                primaryStage.setTitle("HelpShop");
                scene = new Scene(root, 600, 400);
            case "HelpCollection":
                root = FXMLLoader.load(getClass().getResource("HelpCollectionfxml.fxml"));
                primaryStage.setTitle("HelpCollection");
                scene = new Scene(root, 600, 400);
            case "HelpBattle":
                root = FXMLLoader.load(getClass().getResource("HelpBattle.fxml"));
                primaryStage.setTitle("HelpBattle");
                scene = new Scene(root, 600, 400);
        }
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (flag) {
                    flag = false;
                    try {
                        Graphic.this.start(primaryStage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        animationTimer.start();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void run() {
        while (true) {
            try {
                synchronized (region) {
                    if (flag) {

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
