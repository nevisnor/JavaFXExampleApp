package main;

import java.net.URL;
import java.util.ResourceBundle;

import screenOne.ScreenOneView;
import screenThree.ScreenThreeView;
import screenTwo.ScreenTwoView;
import navigation.NavigationPresenter;
import navigation.NavigationView;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 *
 * @author Ron Siven
 */
public class MainPresenter implements Initializable  {

    @FXML BorderPane borderPane;
    @FXML StackPane  stackPane;
    
    private AnchorPane navPane;

    private static final double TRANSITION_TIMER = 200;

    @Override
	public void initialize(URL location, ResourceBundle resources) {

        HBox.setHgrow(borderPane, Priority.ALWAYS);
        VBox.setVgrow(borderPane, Priority.ALWAYS);

        borderPane.setPrefSize(1024, 768);
        
		NavigationView navigationView = new NavigationView();
        NavigationPresenter navPaneController = (NavigationPresenter) navigationView.getPresenter();
        
		final ScreenOneView screenOneView = new ScreenOneView();
		final ScreenTwoView screenTwoView = new ScreenTwoView();
		final ScreenThreeView screenThreeView = new ScreenThreeView();
    	setScreen(screenOneView.getView());
		
		navPaneController.btnScreenOneTool.setSelected(true);
        navPaneController.btnScreenOneTool.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setScreen(screenOneView.getView());
            }
        });
        navPaneController.btnScreenTwoTool.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	setScreen(screenTwoView.getView());
            }
        });
        navPaneController.btnScreenThreeTool.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	setScreen(screenThreeView.getView());
            }
        });
        
        
        final double expandedWidth = 175;
        navPane = (AnchorPane) navigationView.getView();
        navPane.setPrefWidth(expandedWidth);
        VBox.setVgrow(navPane, Priority.ALWAYS);
        borderPane.setLeft(navPane);
    }

    /**
     * Set Screen 
     * 
     * @param name
     * @return
     */
    public boolean setScreen(Parent view) {       
    	final DoubleProperty opacity = stackPane.opacityProperty();

    	if (!stackPane.getChildren().isEmpty()) {    //if there is more than one screen
    		Timeline fade = new Timeline(
    				new KeyFrame(Duration.ZERO, new KeyValue(opacity, 1.0)),
    				new KeyFrame(new Duration(TRANSITION_TIMER), new EventHandler<ActionEvent>() {
    					@Override
    					public void handle(ActionEvent t) {
    						stackPane.getChildren().remove(0);        //remove the displayed screen
    						stackPane.getChildren().add(0, view);     //add the screen
    						Timeline fadeIn = new Timeline(
    								new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
    								new KeyFrame(new Duration(TRANSITION_TIMER), new KeyValue(opacity, 1.0)));
    						fadeIn.play();
    					}
    				}, new KeyValue(opacity, 0.0)));
    		fade.play();

    	} else {
    		stackPane.setOpacity(0.0);
    		stackPane.getChildren().add(view);       //no one else been displayed, then just show
    		Timeline fadeIn = new Timeline(
    				new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
    				new KeyFrame(new Duration(TRANSITION_TIMER), new KeyValue(opacity, 1.0)));
    		fadeIn.play();
    	}
    	return true;
    }

}
