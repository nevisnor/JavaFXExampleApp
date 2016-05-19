package navigation;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.ObjectBinding;
import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Effect;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Ron Siven
 */
public class NavigationPresenter implements Initializable {

	@FXML private VBox toolBar;
	@FXML public ToggleButton btnScreenOneTool;
	@FXML public ToggleButton btnScreenTwoTool;
	@FXML public ToggleButton btnScreenThreeTool;
	@FXML public ToggleButton btnScreenFourTool;
	@FXML public ToggleButton btnScreenFiveTool;
	@FXML public ToggleButton btnScreenSixTool;

	private PersistentButtonToggleGroup toolbarToggleGroup = new PersistentButtonToggleGroup();

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		setupButton(btnScreenOneTool);
		setupButton(btnScreenTwoTool);
		setupButton(btnScreenThreeTool);
		setupButton(btnScreenFourTool);
		setupButton(btnScreenFiveTool);
		setupButton(btnScreenSixTool);
	}

	private void setupButton(ToggleButton button) {

		// Set the text fill color to grey if not selected
		ColorAdjust grey = new ColorAdjust(0, -1, 0, -1);

		button.setToggleGroup(toolbarToggleGroup);
		button.setAlignment(Pos.CENTER_LEFT);
	}

	private class PersistentButtonToggleGroup extends ToggleGroup {
		PersistentButtonToggleGroup() {
			super();
			getToggles().addListener(new ListChangeListener<Toggle>() {
				@Override
				public void onChanged(Change<? extends Toggle> c) {
					while (c.next())
						for (final Toggle addedToggle : c.getAddedSubList())
							((ToggleButton) addedToggle).addEventFilter(
									MouseEvent.MOUSE_RELEASED,
									new EventHandler<MouseEvent>() {
										@Override
										public void handle(MouseEvent mouseEvent) {
											if (addedToggle.equals(getSelectedToggle()))
												mouseEvent.consume();
										}
									});
				}
			});
		}
	}
}
