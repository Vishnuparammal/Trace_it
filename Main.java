import javafx.application.Application; 
import javafx.collections.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.input.*;
import javafx.scene.text.*;
import javafx.geometry.*;
import javafx.event.*;

public class Main extends Application { 
	
	// VARIABLES

	double x_max = 10, x_min = -10, x_increment = 0.1;			// change these to control points
	int no_of_points = (int)((x_max - x_min)/x_increment + 1.0);// changes as per above params
	int i,m,n;													// loop counters
	double x;													// final x values to be plotted
	double[] y = new double [no_of_points];						// final y values to be plotted
	double power_of_x = 0,coefficient_of_x = 0,prevTerm = 0;	// term control vars
	int raised = 0, sign = 1,decimal_point = 0,decimal_part = 0;	// control triggers
	int button_number,buttonX,buttonY;							// button controls
	String string_eqn = new String("y = ");
	
	// CALCULATE EACH TERM - GENERAL FORM - coeff * x ^ power

	public void calc_term(double temp_coefficient_of_x,double temp_power_of_x)
	{
		for(x = x_min, i=0; x<= x_max; x += x_increment,i++)
			y[i] += temp_coefficient_of_x*java.lang.Math.pow(x,temp_power_of_x);
	}

	// RESET VARIABLES AND SET CONSTANTS WHEN TERM ENDS

	public void termEnd()
	{
			if(raised==1 && prevTerm==0)
			{
				return;
			}
			else if(raised==1)
				power_of_x = sign * prevTerm;
			else if(prevTerm!=0)
				coefficient_of_x = sign * prevTerm;
			calc_term(coefficient_of_x,power_of_x);
			
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

		Button x_btn= new Button("x"); 
		Button plus = new Button("+");
		Button minus = new Button("-");
		Button decimal= new Button(".");
		Button power = new Button("^");
		Button[] button = new Button[10];			// button array
		Button trace = new Button("Trace_it_!!");
		
		// initialize each button in array
		for(button_number = 0; button_number < button.length; button_number++)
			button[button_number] = new Button(Integer.toString(button_number));	
		
		// position of operators
		operatorGrid.add(plus,0,0);
		operatorGrid.add(minus,1,0);
		operatorGrid.add(decimal,2,0);
		operatorGrid.add(power,3,0);

		// position of number in mobile keypad form
		for(buttonY=0,button_number=1;buttonY<3;buttonY++)
			for(buttonX=0;buttonX<3;buttonX++,button_number++)
				numberGrid.add(button[button_number],buttonX,buttonY);
		numberGrid.add(button[0],1,3);
		
		// graph set-up
		NumberAxis xAxis = new NumberAxis(x_min,x_max,1.0);
		xAxis.setLabel("x");
		NumberAxis yAxis = new NumberAxis(x_min,x_max,1.0);
		yAxis.setLabel("y");

		LineChart linechart = new LineChart(xAxis,yAxis);

		// layout of all elements on page
		VBox input = new VBox(5);
		input.setPadding(new Insets(10, 10, 10, 10));
		input.getChildren().addAll(variable_txt,x_btn,operator_txt,operatorGrid,number_txt,numberGrid,trace);   

		VBox output = new VBox(5);
		output.setPadding(new Insets(10, 10, 10, 10));
		output.getChildren().addAll(equation,linechart);

		HBox finalScreen = new HBox(5);
		finalScreen.setPadding(new Insets(10, 10, 10, 10));
		finalScreen.getChildren().addAll(input,output);

		// BUTTON CLICK EVENTS

		x_btn.setOnMouseClicked((new EventHandler<MouseEvent>() { 
			public void handle(MouseEvent event) {
				coefficient_of_x = sign * 1;
				if(prevTerm!=0)
					coefficient_of_x = sign * prevTerm;
				prevTerm = 0;
				power_of_x = 1;	//default power of x;
				decimal_point = 0;
				sign = 1;
				string_eqn+=" x";
				equation.setText(string_eqn);	
			}
		}));

		plus.setOnMouseClicked((new EventHandler<MouseEvent>() { 
			public void handle(MouseEvent event) {
				termEnd();
				sign = 1;
				string_eqn+=" +";
				equation.setText(string_eqn);		
			}
		}));

		minus.setOnMouseClicked((new EventHandler<MouseEvent>() { 
			public void handle(MouseEvent event) {
				termEnd();
				sign = -1;
				string_eqn+=" -";
				equation.setText(string_eqn);
			}
		}));

		decimal.setOnMouseClicked((new EventHandler<MouseEvent>() { 
			public void handle(MouseEvent event) {
				decimal_point = 1;
				decimal_part = 0;
				string_eqn+=".";
				equation.setText(string_eqn);
			}
		}));

		power.setOnMouseClicked((new EventHandler<MouseEvent>() { 
			public void handle(MouseEvent event) {
				raised = 1;
				string_eqn+="^";
				equation.setText(string_eqn);
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
							equation.setText(string_eqn);
				}
			}));	
		}

		trace.setOnMouseClicked((new EventHandler<MouseEvent>() { 
			public void handle(MouseEvent event) {			
				termEnd();
				XYChart.Series series = new XYChart.Series();
				for(m=0,x=x_min ;m< no_of_points; m++,x+=x_increment)
				{
					series.getData().add(new XYChart.Data(x,y[m]));
					y[m] = 0;
				}
				linechart.getData().add(series);
				linechart.setCreateSymbols(false);            //disables the points
				sign = 1;
				string_eqn = "y = ";
			}
		}));

		// window set-up
		Scene scene = new Scene(finalScreen,finalScreen.getPrefWidth(),finalScreen.getPrefHeight()); 
		stage.setTitle("Trace_it_!!");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String args[]){
		launch(args);
	}
}