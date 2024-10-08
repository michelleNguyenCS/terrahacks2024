/**
 * [CleanScapes] : 
 * GUI 
 * 
 * @author Michelle Nguyen
 * @version 2024-08-04
 */


/* IMPORT STATEMENTS */
import java.io.FileInputStream;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class GUIDriver extends Application {
	
	@Override
	public void start(Stage stage) throws Exception {
		
		/* FRAME */
		VBox vbox = new VBox(25);		// Page Frame
		vbox.setAlignment(Pos.CENTER);	// Center in the middle
		
		/* FONTS */
		Font titleFont = new Font("Quicksand", 56);
		Font f1 = new Font("Quicksand", 24);
		
		/* APP LOGO */
		Image image = new Image(new FileInputStream("logo.png"));
		ImageView imageView = new ImageView(image); 
		imageView.setFitHeight(75);
		imageView.setFitWidth(75);
		
		/* APP TITLE */
		Text appTitle = new Text("CleanScapes");
		appTitle.setFont(titleFont);
		
		vbox.getChildren().addAll(imageView, appTitle);
		
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
		VBox homeVBox = new VBox(10);
		VBox addVBox = new VBox(10);
		VBox cleanVBox = new VBox(10);
		homeTab.setContent(homeVBox);
		addTab.setContent(addVBox);
		cleanTab.setContent(cleanVBox);
		homeVBox.setAlignment(Pos.CENTER);
		addVBox.setAlignment(Pos.CENTER);
		cleanVBox.setAlignment(Pos.CENTER);
		
		/* F1 - HOME */
		Text homeText = new Text("Places to be Cleaned");
		homeText.setFont(f1);
		Text filler = new Text("[Nothing Yet]");	// Filler text for no entries
		filler.setFont(f1);
		homeVBox.getChildren().addAll(homeText, filler);
		
		/* F2 - ADD */
		
		Text addText = new Text("Add an Address");
		addText.setFont(f1);
		VBox.setMargin(addText, new Insets(10, 0, 0, 0));	// (Top, Right, Bottom, Left)
		
		// Input Box for Username
		
		HBox addUserHBox = new HBox(10);
		addUserHBox.setAlignment(Pos.CENTER);
		
		Label addUserLabel = new Label("Enter Username:");
		addUserLabel.setFont(f1);
		
		TextField addUserInput = new TextField();
		addUserInput.setFont(f1);
		addUserInput.setPromptText("Name..");
		
		addUserHBox.getChildren().addAll(addUserLabel, addUserInput);
		
		// Input Box for Address
		
		HBox addAddressHBox = new HBox(10);
		addAddressHBox.setAlignment(Pos.CENTER);
		
		Label addAddressLabel = new Label("Enter Address:");
		addAddressLabel.setFont(f1);
		
		TextField addAddressInput = new TextField();
		addAddressInput.setPrefWidth(320);	// 320px so the ends align with username field
		addAddressInput.setFont(f1);
		addAddressInput.setPromptText("Search..");
		
		addAddressHBox.getChildren().addAll(addAddressLabel, addAddressInput);
		
		// Submit Button
		
		Button addButton = new Button("Submit");
		
		addVBox.getChildren().addAll(addText, addUserHBox, addAddressHBox,addButton);
		
		/* F3 - CLEAN */
		
		Text cleanText = new Text("Submit a Cleaning");
		cleanText.setFont(f1);
		VBox.setMargin(cleanText, new Insets(10, 0, 0, 0));	// (Top, Right, Bottom, Left)

		// Input Box for Username
		
		HBox cleanUserHBox = new HBox(10);
		cleanUserHBox.setAlignment(Pos.CENTER);
		
		Label cleanUserLabel = new Label("Enter Username:");
		cleanUserLabel.setFont(f1);
		
		TextField cleanUserInput = new TextField();
		cleanUserInput.setFont(f1);
		cleanUserInput.setPromptText("Name..");
		
		cleanUserHBox.getChildren().addAll(cleanUserLabel, cleanUserInput);
		
		// Input Box for Address
		
		HBox cleanAddressHBox = new HBox(10);
		cleanAddressHBox.setAlignment(Pos.CENTER);
		
		Label cleanAddressLabel = new Label("Enter Address:");
		cleanAddressLabel.setFont(f1);
		
		TextField cleanAddressInput = new TextField();
		cleanAddressInput.setPrefWidth(320);	// 320px so the ends align with username field
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
				String username = addUserInput.getText();
				String address = addAddressInput.getText();
				
				addResultText.setText(Method.voteLocation(address, username));
				
				// "Refreshing" the Home Tab
				
				homeVBox.getChildren().clear();
				homeVBox.getChildren().add(homeText);
				
				ArrayList<Location> locations = Method.loadData("database.txt");
				Text locationText = new Text(Method.readData(locations));
				locationText.setFont(f1);
				
				ScrollPane scroll = new ScrollPane(locationText);	// Scroll bar
				homeVBox.getChildren().addAll(locationText, scroll);
				
				// Action Message for Adding
				
				addResultText.setFont(f1);
				addVBox.getChildren().add(addResultText);
								
				// Code for "refreshing" the Add Tab
				/*
				 * There's a problem where after clicking a button, the content on the tab
				 * doesn't refresh until you click out of the tab and back in
				 * 
				 * I had to code that the program will select out and in of the tab to
				 * "refresh"
				 */
				SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
				selectionModel.clearSelection();	// Clears selected tabs
				selectionModel.select(1);			// Selects addTab again
				
			} catch (Exception error) {}
		});
		
		// Clean Button
		
		Text cleanResultText = new Text();
		cleanButton.setOnAction(e -> {
			try {
				String username = cleanUserInput.getText();
				String address = cleanAddressInput.getText();
				
				cleanResultText.setText(Method.removeLocation(address, username));
				
				// "Refreshing" the Home Tab
				
				homeVBox.getChildren().clear();
				homeVBox.getChildren().add(homeText);
				
				ArrayList<Location> locations = Method.loadData("database.txt");
				Text locationText = new Text(Method.readData(locations));
				locationText.setFont(f1);
				
				ScrollPane scroll = new ScrollPane(locationText);	// Scroll bar
				homeVBox.getChildren().addAll(locationText, scroll);

				// Action Message for Cleaning
				
				cleanResultText.setFont(f1);
				cleanVBox.getChildren().add(cleanResultText);
				
				// Code for "refreshing" the Clean Tab
				
				SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
				selectionModel.clearSelection();	// Clears selected tabs
				selectionModel.select(2);			// Selects cleanTab again

			} catch (Exception error) {}
		});

		/* ADD ALL TO SCENE */
		vbox.getChildren().add(tabPane);		
		Scene scene = new Scene(vbox, 600, 800);
		String css = this.getClass().getResource("style.css").toExternalForm();	// CSS
		scene.getStylesheets().add(css);
		
		/* FINAL GUI SETUP */
		stage.setScene(scene);			// Adds the scene to the stage
		stage.getIcons().add(new Image("file:icon.png"));	// App Icon
		stage.setTitle("CleanScapes");	// Title of Interface
		stage.show();					// Required for Interface to Launch (1/2)
	}

	public static void main(String[] args) {
		launch(args);	// Required for Interface to Launch (2/2)
	}
}