import javafx.application.Application; 
import javafx.collections.*;  
import javafx.stage.*; 
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*; 
import javafx.scene.input.*; 
import javafx.scene.text.*;  
import javafx.geometry.*; 
import javafx.event.*;     

public class Main extends Application { 
	double[] y = new double [21];
	double[][] term = new double[100][21];
	double power_of_x = 0,coefficient_of_x = 0,prevTerm = 0;
	int term_count = 0,raised = 0, sign = 1,decimal_point = 0,decimal_part = 0,button_number,buttonX,buttonY;
	String string_eqn = new String("y = ");
	String string_val = new String("y: ");

	public double[] calc_term(double temp_coefficient_of_x,double temp_power_of_x)
	{
		double termPlot[] = new  double[21];
		for(int a = -10, b=0; a<=10;a++,b++)
		{
			termPlot[b] = temp_coefficient_of_x*java.lang.Math.pow(a,temp_power_of_x);
		}
		return termPlot;
	}

	public void termEnd()
	{
			if(raised==1 && prevTerm==0)
			{
				sign = -1;
				return;
			}
			else if(raised==1)
				power_of_x = sign * prevTerm;
			else if(prevTerm!=0)
				coefficient_of_x = sign * prevTerm;
			term[term_count] = calc_term(coefficient_of_x,power_of_x);
			term_count+=1;
			decimal_point = 0;
			power_of_x = 0;
			coefficient_of_x = 0;
			raised = 0;
			prevTerm = 0;
	}

	public void start(Stage stage)
	{
		GridPane operatorGrid = new GridPane();
		GridPane numberGrid = new GridPane();  

		Text variable_txt = new Text("Variable");
		Text operator_txt = new Text("Operator");
		Text number_txt = new Text("Number");
		Text equation = new Text(string_eqn);
		Text ordinate = new Text(string_val);

		Button x_btn= new Button("x"); 
		Button plus = new Button("+");
		Button minus = new Button("-");
		Button decimal= new Button(".");
		Button power = new Button("^");
		Button bracketOpen= new Button("(");
		Button bracketClose= new Button(")");
		Button[] button = new Button[10];
		Button trace = new Button("Trace_it_!!");
		for(button_number = 0; button_number < button.length; button_number++) {
	            button[button_number] = new Button(Integer.toString(button_number));
	        }	
		
		operatorGrid.add(plus,0,0);
		operatorGrid.add(minus,1,0);
		operatorGrid.add(decimal,2,0);
		operatorGrid.add(power,3,0);
		//operatorGrid.add(bracketOpen,1,3);
		//operatorGrid.add(bracketClose,2,3);

		for(buttonY=0,button_number=1;buttonY<3;buttonY++){
			for(buttonX=0;buttonX<3;buttonX++,button_number++)
			{
				numberGrid.add(button[button_number],buttonX,buttonY);
			}
		}
		numberGrid.add(button[0],1,3);
	 	
		VBox interaction = new VBox(5);
		interaction.setPadding(new Insets(10, 10, 10, 10));
		interaction.getChildren().addAll(variable_txt,x_btn,operator_txt,operatorGrid,number_txt,numberGrid,trace,equation,ordinate);
	       
		Scene scene = new Scene(interaction);    

		x_btn.setOnMouseClicked((new EventHandler<MouseEvent>() { 
			public void handle(MouseEvent event) {
				coefficient_of_x = sign * 1;
				if(prevTerm!=0)
					coefficient_of_x = sign * prevTerm;
				prevTerm = 0;
				power_of_x = 1;	//default power of x;
				decimal_point = 0;
				sign = 1;
				string_eqn+="x";
				equation.setText(string_eqn); ordinate.setText(string_val);	
			}
		}));

		plus.setOnMouseClicked((new EventHandler<MouseEvent>() { 
			public void handle(MouseEvent event) {
				termEnd();
				sign = 1;
				string_eqn+=" +";
				equation.setText(string_eqn); ordinate.setText(string_val);		
			}
		}));

		minus.setOnMouseClicked((new EventHandler<MouseEvent>() { 
			public void handle(MouseEvent event) {
				termEnd();
				sign = -1;
				string_eqn+=" -";
				equation.setText(string_eqn); ordinate.setText(string_val);
			}
		}));

		decimal.setOnMouseClicked((new EventHandler<MouseEvent>() { 
			public void handle(MouseEvent event) {
				decimal_point = 1;
				decimal_part = 0;
				string_eqn+=".";
				equation.setText(string_eqn); ordinate.setText(string_val);
			}
		}));

		power.setOnMouseClicked((new EventHandler<MouseEvent>() { 
			public void handle(MouseEvent event) {
				raised = 1;
				string_eqn+="^";
				equation.setText(string_eqn); ordinate.setText(string_val);	
			}
		}));

		trace.setOnMouseClicked((new EventHandler<MouseEvent>() { 
			public void handle(MouseEvent event) {			
				termEnd();
				for(int m=0;m<21;m++)
				{
					y[m] = 0;
					for(int n=0;n<term_count;n++)
						y[m] += term[n][m];
					string_val+= y[m]+" ";
				}
				ordinate.setText(string_val);
				sign = 1;
				term_count = 0;
				string_eqn = "y = ";
				string_val = "y: "; 
			}
		}));

		for(button_number = 0;button_number<button.length;button_number++){
			final Button buttonI = button[button_number];
			buttonI.setOnMouseClicked((new EventHandler<MouseEvent>() { 
				public void handle(MouseEvent event) {
							if(decimal_point==1)
							{
								decimal_part-=1;
								prevTerm+= Integer.parseInt(buttonI.getText())*java.lang.Math.pow(10,decimal_part);
							}
							else
								prevTerm = prevTerm*10 + Integer.parseInt(buttonI.getText());
							string_eqn+=buttonI.getText();
							equation.setText(string_eqn); ordinate.setText(string_val);
				}
			}));	
		}
		stage.setTitle("Trace_it_!!");
		stage.setScene(scene);
		stage.show();
	}      

	public static void main(String args[]){
		launch(args);
	} 
}