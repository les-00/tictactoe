package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import model.Game;

public class Game_Gui extends Pane {

	private VBox endRoot = new VBox();
	private VBox menuRoot = new VBox(30);
	private VBox gameRoot = new VBox(20);
	private Text txt_current = new Text("X");
	private Text txt_state = new Text("Current Player: ");
	private Button [][] grid = new Button[3][3];
	private Button btnStart = new Button("Start Game");
	private int btnCounter = 0;
	private Game game = new Game();
	
	
	public Game_Gui() {
		setupMenuRoot();
	}
	
	
	private void initGrid()
	{
		//Initialise grid
		for(int r = 0; r < 3; r++)
		{
			for(int c = 0; c <3; c++)
			{
				grid[r][c] = new Button();
				grid[r][c].setPrefSize(150, 140);
				grid[r][c].setStyle("-fx-background-color: #1ecbe1");
			}
		}
	}
	
	
	private void clearGameStage()
	{
		//Clear buttons
		if(btnCounter > 0)
		{
			gameRoot.getChildren().remove(gameRoot.getChildren().size() - 1);
			btnCounter = 0;
		}
		
		//Change blocks to white
		for(int r = 0; r < 3; r++)
		{
			for(int c = 0; c <3; c++)
			{
				grid[r][c].setStyle("-fx-background-color: #ffffff");
				grid[r][c].setText("");
			}
		}
	}
	
	private void clearpane()
	{
		//Clear pane
		setStyle(null);
		getChildren().clear();
	}
	private void setupMenuRoot()
	{
		
		clearpane();
		
		Text t = new Text("Let's Play Tic-Tac-Toe!");
		t.setFont(Font.font("Arial", FontWeight.BOLD, 36));
		t.setTextAlignment(TextAlignment.CENTER);
		
		
		btnStart.setPrefSize(310, 80);
		btnStart.setStyle("-fx-border-color: #0000ff; -fx-border-width: 8px;-fx-font-size: 1.5em;");
		
		
		//update start button actions
		btnStart.setOnMouseClicked(e ->
		{
			setupGameRoot();
		});
		
		menuRoot.setAlignment(Pos.CENTER);
		menuRoot.setPadding(new Insets(50));

		menuRoot.getChildren().addAll(t, btnStart);
		setStyle("-fx-background-color: #1ecbe1;");
		getChildren().add(menuRoot);
	}
	
	private void setupGameRoot()
	{
		clearpane();
		
		initGrid();
		setupButtonListeners();
		
		HBox hb1 = new HBox(15);
		hb1.getChildren().addAll(txt_state, txt_current);
		
		GridPane gp = new GridPane(); 
		for(int r = 0; r < 3; r++)
		{
			gp.addRow(r, grid[r]);
		}
		
		gp.setVgap(10);
	  	gp.setHgap(10);
	  	gameRoot.setPadding(new Insets(50));
		gameRoot.getChildren().addAll(hb1, gp);
		gameRoot.setAlignment(Pos.CENTER);
		getChildren().add(gameRoot);
		setStyle("-fx-background-color: #4CB397;");
	}
	
	private void setupButtonListeners()
	{
		for(int r = 0; r < 3; r++)
		{
			for(int c = 0; c < 3; c++)
			{
				int row = r;
				int col = c;
				
				grid[r][c].setOnMouseClicked(e ->
				{
					
					
					//Check if space
					if(game.getSpace()[row][col] == '#' && !game.isThereWinner())
					{
						char chplacement = txt_current.getText().charAt(0);
						game.placeCharacter(chplacement, row, col);
						grid[row][col].setText(String.valueOf(chplacement));
						
						
						//Change player
						if(chplacement == 'X')
						{
							grid[row][col].setStyle("-fx-background-color: #00ff00");
							txt_current.setText("O");
						}
						else
						{
							grid[row][col].setStyle("-fx-background-color: #001cff");
							txt_current.setText("X");
						}
					}
					
					//check if winner exists
					if(game.isThereWinner())
					{
						System.out.println("Winner:\t" + game.getWinner());
						txt_current.setText("");
						txt_state.setText("Winner:\t" + game.getWinner());
						
						++btnCounter;
						
						if(btnCounter == 1)
						{
							Button b1 = new Button("Play Again");
							Button b2 = new Button("Quit game");
							
							HBox hb = new HBox(50,b1, b2);

							b1.setOnMouseClicked(f ->
							{
								clearGameStage();
								game.clearGame();
								txt_state.setText("Current Player: ");
								txt_current.setText("X");
							});
							b2.setOnMouseClicked(ff ->
							{
								System.exit(5);
							});
							
							hb.setPadding(new Insets(25));
							
							gameRoot.getChildren().add(hb);
						}
						
						
						return;
					}
					
					if(game.isGameDraw()) {
						System.out.println("draw!");
						
						txt_current.setText("");
						txt_state.setText("We have a Draw!");
						
						Button b1 = new Button("Play Again");
						Button b2 = new Button("Quit game");
						
						HBox hb = new HBox(50,b1, b2);

						++btnCounter;
						
						if(btnCounter == 1)
						{
							b1.setOnMouseClicked(f ->
							{
								clearGameStage();
								game.clearGame();
								txt_state.setText("Current Player: ");
								txt_current.setText("X");
							});
							b2.setOnMouseClicked(ff ->
							{
								System.exit(5);
							});
							
							hb.setPadding(new Insets(25));
							
							gameRoot.getChildren().add(hb);
						}
						
					}
					
				});
			}
		}
	}
}
