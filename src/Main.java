

import gui.Game_Gui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage){
		// TODO Auto-generated method stub
		Game_Gui game = new Game_Gui();
		
		Scene scene1 = new Scene(game, 570, 660);
		
		primaryStage.setScene(scene1);
		primaryStage.setTitle("Tic-tac-toe");
		primaryStage.show();
	}
	

}
