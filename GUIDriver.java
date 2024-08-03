/**
 * [CleanScape]
 * 
 * @author Michelle Nguyen
 * @version 2024-08-03
 */


/* IMPORT STATEMENTS */
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class GUIDriver extends Application {
	
	@Override
	public void start(Stage stage) throws Exception {
		
		/* FRAME */
		VBox vbox = new VBox();			// Page Frame
		vbox.setAlignment(Pos.CENTER);	// Center in the middle
		
		/* FONTS */
		Font titleFont = new Font("Quicksand", 56);
		Font f1 = new Font("Quicksand", 24);
		
		/* APP TITLE */
		Text appTitle = new Text("CleanScapes");
		appTitle.setFont(titleFont);
		vbox.getChildren().add(appTitle);
		
		/* TABS */
		TabPane tabPane = new TabPane();
		tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		Tab homeTab = new Tab("Home");
		Tab addTab = new Tab("Add");
		Tab cleanTab = new Tab("Clean");
		tabPane.getTabs().add(homeTab);
		tabPane.getTabs().add(addTab);
		tabPane.getTabs().add(cleanTab);
		
		// Frames
		VBox homeVBox = new VBox(new Label("[ List Polluted Addresses ]"));
		VBox addVBox = new VBox();
		VBox cleanVBox = new VBox();
		homeTab.setContent(homeVBox);
		addTab.setContent(addVBox);
		cleanTab.setContent(cleanVBox);
		addVBox.setAlignment(Pos.CENTER);
		cleanVBox.setAlignment(Pos.CENTER);
		
		/* F1 - HOME */
		
		// [Waiting on Kathryn's Code]
		
		/* F2 - ADD */
		
		Text addText = new Text("Add an Address");
		addText.setFont(f1);
		
		// Input Box for Username
		
		HBox addUserHBox = new HBox();
		addUserHBox.setAlignment(Pos.CENTER);
		
		Label addUserLabel = new Label("Enter Username:");
		addUserLabel.setFont(f1);
		
		TextField addUserInput = new TextField();
		addUserInput.setFont(f1);
		addUserInput.setPromptText("Name..");
		
		addUserHBox.getChildren().addAll(addUserLabel, addUserInput);
		
		// Input Box for Address
		
		HBox addAddressHBox = new HBox();
		addAddressHBox.setAlignment(Pos.CENTER);
		
		Label addAddressLabel = new Label("Enter Address:");
		addAddressLabel.setFont(f1);
		
		TextField addAddressInput = new TextField();
		addAddressInput.setFont(f1);
		addAddressInput.setPromptText("Search..");
		
		addAddressHBox.getChildren().addAll(addAddressLabel, addAddressInput);
		
		// Submit Button
		
		Button addButton = new Button("Submit");
		
		addVBox.getChildren().addAll(addText, addUserHBox, addAddressHBox,addButton);
		
		/* F3 - CLEAN */
		
		Text cleanText = new Text("Submit a Cleaning");
		cleanText.setFont(f1);

		// Input Box for Username
		
		HBox cleanUserHBox = new HBox();
		cleanUserHBox.setAlignment(Pos.CENTER);
		
		Label cleanUserLabel = new Label("Enter Username:");
		cleanUserLabel.setFont(f1);
		
		TextField cleanUserInput = new TextField();
		cleanUserInput.setFont(f1);
		cleanUserInput.setPromptText("Name..");
		
		cleanUserHBox.getChildren().addAll(cleanUserLabel, cleanUserInput);
		
		// Input Box for Address
		
		HBox cleanAddressHBox = new HBox();
		cleanAddressHBox.setAlignment(Pos.CENTER);
		
		Label cleanAddressLabel = new Label("Enter Address:");
		cleanAddressLabel.setFont(f1);
		
		TextField cleanAddressInput = new TextField();
		cleanAddressInput.setFont(f1);
		cleanAddressInput.setPromptText("Search..");
		
		cleanAddressHBox.getChildren().addAll(cleanAddressLabel, cleanAddressInput);
		
		// Submit Button
		
		Button cleanButton = new Button("Submit");
		
		cleanVBox.getChildren().addAll(cleanText, cleanUserHBox, cleanAddressHBox, cleanButton);
		
		/* BUTTONS CODE */
		
		// Add Button
		
		Text addResultText = new Text();
		addButton.setOnAction(e -> {
			try {
				addResultText.setText("Location has been added");
				addResultText.setFont(f1);
				addVBox.getChildren().add(addResultText);
				
				// Code for "refreshing" the tab 
				SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
				selectionModel.clearSelection();	// Clears selected tabs
				selectionModel.select(1);			// Selects addTab again

			} catch (Exception error) {}
		});
		
		// Clean Button
		
		Text cleanResultText = new Text();
		cleanButton.setOnAction(e -> {
			try {
				cleanResultText.setText("Location has been cleaned");
				cleanResultText.setFont(f1);
				cleanVBox.getChildren().add(cleanResultText);
				
				// Code for "refreshing" the tab 
				SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
				selectionModel.clearSelection();	// Clears selected tabs
				selectionModel.select(2);			// Selects cleanTab again

			} catch (Exception error) {}
		});

		/* ADD ALL TO SCENE */
		vbox.getChildren().add(tabPane);
		Scene scene = new Scene(vbox, 600, 800);
		String css = this.getClass().getResource("style.css").toExternalForm();
		scene.getStylesheets().add(css);
		
		/* FINAL GUI SETUP */
		stage.setScene(scene);			// Adds the scene to the stage
		stage.setTitle("CleanScapes");	// Title of Interface
		stage.show();					// Required for Interface to Launch (1/2)
	}

	public static void main(String[] args) {
		launch(args);	// Required for Interface to Launch (2/2)
	}
}
