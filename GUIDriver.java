/**
 * [CleanScape]
 * 
 * @author Michelle Nguyen
 * @version 2024-08-02
 */

// IMPORT STATEMENTS
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class GUIDriver extends Application {
	
	@Override
	public void start(Stage stage) throws Exception {
		
		// FRAME
		VBox vbox = new VBox();
		vbox.setAlignment(Pos.CENTER);
		
		// TITLE
		//javafx.scene.text.Font.getFontNames();
		Font titleFont = new Font("Quicksand", 56);
		Text appTitle = new Text("CleanScape");
		appTitle.setFont(titleFont);
		vbox.getChildren().add(appTitle);
		
		// INPUT BOX FOR ADDRESS
		HBox hbox = new HBox();
		hbox.setAlignment(Pos.CENTER);
		hbox.setSpacing(10);
		
		TextField addressInput = new TextField();
		addressInput.setPromptText("Enter Address");
		addressInput.setPrefWidth(200);
		HBox.setMargin(addressInput, new Insets(10, 10, 10, 10));	// (Top, Right, Bottom, Left)

		hbox.getChildren().add(addressInput);
		vbox.getChildren().add(hbox);
		
		Button searchButton = new Button("Search");
		hbox.getChildren().add(searchButton);
		
		
		
		// FINAL SETUP
		Scene scene = new Scene(vbox, 600, 800);	// (Box, Width, Height)
		stage.setScene(scene);						// Adds the scene to the stage
		stage.setTitle("CleanScape");	// Title of Interface
		stage.show();					// Required for Interface to Launch (1/2)
	}

	public static void main(String[] args) {
		launch(args);	// Required for Interface to Launch (2/2)
	}

}
