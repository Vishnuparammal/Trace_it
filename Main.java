import javafx.application.Application; 
import javafx.collections.FXCollections; 
import javafx.collections.ObservableList; 
import javafx.scene.Group; 
import javafx.geometry.Insets; 
import javafx.geometry.Pos; 
import javafx.scene.input.MouseEvent; 
import javafx.event.EventHandler;
import javafx.scene.Scene; 
import javafx.scene.control.Button; 
import javafx.scene.control.CheckBox; 
import javafx.scene.control.ChoiceBox; 
import javafx.scene.control.DatePicker; 
import javafx.scene.control.ListView; 
import javafx.scene.control.RadioButton; 
import javafx.scene.layout.GridPane; 
import javafx.scene.text.Text; 
import javafx.scene.control.TextField; 
import javafx.scene.control.ToggleGroup;  
import javafx.scene.control.ToggleButton; 
import javafx.stage.Stage; 
import javafx.scene.text.Font; 
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight; 
import javafx.scene.text.Text; 
import javafx.scene.control.TextField; 
import javafx.scene.shape.Rectangle; 
import javafx.scene.paint.Color;      

public class Main extends Application { 
int i=200;
   public void start(Stage stage) {

	  	 //Creating a Grid Pane 
		GridPane gridPane = new GridPane();    
      
      //Setting size for the pane  
      // gridPane.setMinSize(400, 200); 
       
      //Setting the padding  
      gridPane.setPadding(new Insets(10, 10, 10, 10)); 
      
      //Setting the vertical and horizontal gaps between the columns 
      gridPane.setVgap(5); 
      gridPane.setHgap(5);       
      
      //Setting the Grid alignment 
      gridPane.setAlignment(Pos.TOP_LEFT); 

    Text equation = new Text("Equation:"); 
	Text polynomial = new Text("Polynomial"); 
	Text text = new Text(); 
	

	text.setFont(Font.font("bahnschrift", FontWeight.BOLD, FontPosture.REGULAR, 20));
	equation.setFont(Font.font("bahnschrift", FontWeight.BOLD, FontPosture.REGULAR, 20));
	polynomial.setFont(Font.font("bahnscrift", FontWeight.BOLD, FontPosture.REGULAR, 20));

	TextField powerX = new TextField();
	TextField powerY = new TextField();

	

	Button x= new Button("x^"); 
	Button y= new Button("y^");
	Button plus = new Button("+");
	Button minus = new Button("-");
	Button sin= new Button("sin");
	Button bracketopen= new Button("(");
	Button bracketclose= new Button(")");   
	
	gridPane.add(equation,0,1);
	gridPane.add(polynomial,0,2);
	gridPane.add(x,0,3);
	gridPane.add(powerX,1,3);
	gridPane.add(y,0,4);
	gridPane.add(powerY,1,4);
	gridPane.add(plus,0,5);
	gridPane.add(minus,1,5);
 	
	// Group heading_Grp = new Group(equation,polynomial);
	// Group equation_Grp = new Group(text);
	// Group input_Grp = new Group(x,y,powerX,powerY);
	// Group operations_Grp = new Group(plus,minus);
	
	// Group root = new Group(heading_Grp,equation_Grp,input_Grp,operations_Grp,gridPane);
       
	Scene scene = new Scene(gridPane);   

//  	x.setOnMouseClicked((new EventHandler<MouseEvent>() { 
//         public void handle(MouseEvent event) { 
//         Text text = new Text("x^"+powerX.getText());
//         text.setX(i+60);
// 	text.setY(100);
//  	i=i+70;
//         text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20)); 
// 	root.getChildren().add(text);

//        } 
//       })); 
// 	y.setOnMouseClicked((new EventHandler<MouseEvent>() { 
//         public void handle(MouseEvent event) { 
//         Text text = new Text("y^"+powerY.getText());
//         text.setX(i+60);
// 	text.setY(100);
//  	i=i+70;
//         text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20)); 
// root.getChildren().add(text);

//        } 
//       })); 
//  plus.setOnMouseClicked((new EventHandler<MouseEvent>() { 
//          public void handle(MouseEvent event) { 
//              Text text = new Text("+");
//              text.setX(i+40);
// 	     text.setY(110);
//  	     i=i+10;
//              text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20)); 
// root.getChildren().add(text);

//        } 
//       })); 
// minus.setOnMouseClicked((new EventHandler<MouseEvent>() { 
//          public void handle(MouseEvent event) { 
//              Text text = new Text("-");
//              text.setX(i+40);
// 	     text.setY(100);
//  	     i=i+10;
//              text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20)); 
// root.getChildren().add(text);

//        } 
//       })); 

      
      //Setting title to the Stage 
      stage.setTitle("Registration Form"); 
         
      //Adding scene to the stage 
      stage.setScene(scene);  
      
      //Displaying the contents of the stage 
      stage.show(); 
   }      

private boolean isInt(TextField input,String message)
{
try{
int power = Integer.parseInt(input.getText());
System.out.println(power);
return true;
}
catch(NumberFormatException e){
return false;
}
}
   public static void main(String args[]){ 
      launch(args); 
   } 
}