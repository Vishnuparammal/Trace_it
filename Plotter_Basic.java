import javafx.application.Application;
import javafx.stage.*; 
import javafx.scene.*;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class Plotter_Basic extends Application 
{ 

    public void start(Stage stage) 
    {

        double[] x = new double [401];
        double[] y = new double [401];

        NumberAxis xAxis = new NumberAxis(-10.0,10.0,0.5);
        xAxis.setLabel("x");

        NumberAxis yAxis = new NumberAxis(-10.0,10.0,0.5);
        xAxis.setLabel("y");

        LineChart linechart = new LineChart(xAxis,yAxis);

        XYChart.Series series = new XYChart.Series();
        for(double i=-10;i<=10;i+=0.1) 
        {
            System.out.println(i);
            series.getData().add(new XYChart.Data(i,java.lang.Math.sqrt(i)));  //Take points one by one as in the array to be plotted
        }

        linechart.getData().add(series);

        Group root = new Group(linechart);
        
        Scene scene = new Scene(root,640,480);
        
        stage.setTitle("Trace it!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}