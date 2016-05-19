package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MyApp extends Application {

    private static final String BASE_SCREEN_TITLE = "My Application";
    
    @Override
	public void start(Stage stage) throws Exception {
		AppView appView = new AppView();
		Scene scene = new Scene(appView.getView());
		stage.setTitle(BASE_SCREEN_TITLE);
		stage.setScene(scene);
		stage.show();
	}
    
    public static void main(String[] args) {
    	launch(args);
    }
}
