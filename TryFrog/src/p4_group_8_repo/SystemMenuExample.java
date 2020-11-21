package p4_group_8_repo;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SystemMenuExample extends Application {

    @Override
    public void start(Stage primaryStage) {
        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("File");
        MenuItem quit = new MenuItem("Quit");
        quit.setOnAction(e -> Platform.exit());
        menu.getItems().add(quit);
        menuBar.getMenus().add(menu);
        menuBar.setUseSystemMenuBar(true);

        BorderPane root = new BorderPane();
        root.setTop(menuBar);
        Scene scene = new Scene(root, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}