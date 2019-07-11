package Server;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Graphic extends Application {
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
        Parent root;
        stage = primaryStage;

        root = FXMLLoader.load(getClass().getResource("ServerShopfxml.fxml"));
        primaryStage.setTitle("Shop menu");
        scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

