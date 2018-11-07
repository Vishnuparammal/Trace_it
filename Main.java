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
	int term_count = 0;
	int raised = 0;
	double prevTerm = 0;
	double power_of_x = 0,coefficient_of_x = 0;
	int sign = 1;
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
	public void start(Stage stage) {

	
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
	Button multiply= new Button("*");
	Button divide = new Button("/");
	Button power = new Button("^");
	Button bracketOpen= new Button("(");
	Button bracketClose= new Button(")"); 
	Button num_1 = new Button("1");
	Button num_2 = new Button("2");
	Button num_3 = new Button("3");
	Button num_4 = new Button("4");
	Button num_5 = new Button("5");
	Button num_6 = new Button("6");
	Button num_7 = new Button("7");
	Button num_8 = new Button("8");
	Button num_9 = new Button("9");
	Button num_0 = new Button("0");
	Button trace = new Button("Trace_it_!!");
	
	operatorGrid.add(plus,0,0);
	operatorGrid.add(minus,1,0);
	//operatorGrid.add(multiply,2,2);
	//operatorGrid.add(divide,3,2);
	operatorGrid.add(power,2,0);
	//operatorGrid.add(bracketOpen,1,3);
	//operatorGrid.add(bracketClose,2,3);

	numberGrid.add(num_1,0,0);
	numberGrid.add(num_2,1,0);
	numberGrid.add(num_3,2,0);
	numberGrid.add(num_4,0,1);
	numberGrid.add(num_5,1,1);
	numberGrid.add(num_6,2,1);
	numberGrid.add(num_7,0,2);
	numberGrid.add(num_8,1,2);	
	numberGrid.add(num_9,2,2);
	numberGrid.add(num_0,1,3);
 	
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

			sign = 1;
			string_eqn+="x";
			equation.setText(string_eqn); ordinate.setText(string_val);	
		}
	}));



	plus.setOnMouseClicked((new EventHandler<MouseEvent>() { 
		public void handle(MouseEvent event) {
			if(raised==1)
				power_of_x = sign * prevTerm;
			else if(prevTerm!=0)
				coefficient_of_x = sign * prevTerm;
			term[term_count] = calc_term(coefficient_of_x,power_of_x);
			term_count+=1;
			
			power_of_x = 0;
			coefficient_of_x = 0;
			raised = 0;
			prevTerm = 0;
			sign = 1;
			string_eqn+=" +";
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

	minus.setOnMouseClicked((new EventHandler<MouseEvent>() { 
		public void handle(MouseEvent event) {
			if(raised==1)
				power_of_x = sign * prevTerm;
			else if(prevTerm!=0)
				coefficient_of_x = sign * prevTerm;
			term[term_count] = calc_term(coefficient_of_x,power_of_x);
			term_count+=1;
			
			power_of_x = 0;
			coefficient_of_x = 0;
			raised = 0;
			prevTerm = 0;

			sign = -1;
			string_eqn+=" -";
			equation.setText(string_eqn); ordinate.setText(string_val);
		}
	}));

	trace.setOnMouseClicked((new EventHandler<MouseEvent>() { 
		public void handle(MouseEvent event) {			
			if(raised==1)
				power_of_x = sign * prevTerm;
			else if(prevTerm!=0)
				coefficient_of_x = sign * prevTerm;
			term[term_count] = calc_term(coefficient_of_x,power_of_x);
			term_count+=1;
			
			power_of_x = 0;
			coefficient_of_x = 0;
			raised = 0;
			prevTerm = 0;
			sign = 1;
			
			for(int m=0;m<21;m++)
			{
				y[m] = 0;
				for(int n=0;n<term_count;n++)
					y[m] += term[n][m];
				string_val+= y[m]+" ";
			}
			ordinate.setText(string_val);

			term_count = 0;
			string_eqn = "y = ";
			string_val = "y: "; 

		}
	}));

	num_1.setOnMouseClicked((new EventHandler<MouseEvent>() { 
		public void handle(MouseEvent event) {
					prevTerm = prevTerm*10 + 1;
					string_eqn+="1";
					equation.setText(string_eqn); ordinate.setText(string_val);
		}
	}));

	num_2.setOnMouseClicked((new EventHandler<MouseEvent>() { 
		public void handle(MouseEvent event) {
					prevTerm = prevTerm*10 + 2;
					string_eqn+="2";
					equation.setText(string_eqn); ordinate.setText(string_val);
		}
	}));

	num_3.setOnMouseClicked((new EventHandler<MouseEvent>() { 
		public void handle(MouseEvent event) {
					prevTerm = prevTerm*10 + 3;
					string_eqn+="3";
					equation.setText(string_eqn); ordinate.setText(string_val);
		}
	}));

	num_4.setOnMouseClicked((new EventHandler<MouseEvent>() { 
		public void handle(MouseEvent event) {
					prevTerm = prevTerm*10 + 4;
					string_eqn+="4";
					equation.setText(string_eqn); ordinate.setText(string_val);
		}
	}));

	num_5.setOnMouseClicked((new EventHandler<MouseEvent>() { 
		public void handle(MouseEvent event) {
					prevTerm = prevTerm*10 + 5;
					string_eqn+="5";
					equation.setText(string_eqn); ordinate.setText(string_val);
		}
	}));

	num_6.setOnMouseClicked((new EventHandler<MouseEvent>() { 
		public void handle(MouseEvent event) {
					prevTerm = prevTerm*10 + 6;
					string_eqn+="6";
					equation.setText(string_eqn); ordinate.setText(string_val);
		}
	}));

	num_7.setOnMouseClicked((new EventHandler<MouseEvent>() { 
		public void handle(MouseEvent event) {
					prevTerm = prevTerm*10 + 7;
					string_eqn+="7";
					equation.setText(string_eqn); ordinate.setText(string_val);
		}
	}));

	num_8.setOnMouseClicked((new EventHandler<MouseEvent>() { 
		public void handle(MouseEvent event) {
					prevTerm = prevTerm*10 + 8;
					string_eqn+="8";
					equation.setText(string_eqn); ordinate.setText(string_val);
		}
	}));

	num_9.setOnMouseClicked((new EventHandler<MouseEvent>() { 
		public void handle(MouseEvent event) {
					prevTerm = prevTerm*10 + 9;
					string_eqn+="9";
					equation.setText(string_eqn); ordinate.setText(string_val);
		}
	}));

	num_0.setOnMouseClicked((new EventHandler<MouseEvent>() { 
		public void handle(MouseEvent event) {
					prevTerm = prevTerm*10 + 0;
					string_eqn+="0";
					equation.setText(string_eqn); ordinate.setText(string_val);
		}
	}));

     //Setting title to the Stage 
      stage.setTitle("Trace_it_!!"); 
         
      //Adding scene to the stage 
      stage.setScene(scene);  
      
      //Displaying the contents of the stage 
      stage.show(); 
   }      

   public static void main(String args[]){ 
      launch(args); 
   } 
}
