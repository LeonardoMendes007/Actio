package view;


import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

public class Util {
	
    public static void initButtons(Button[] buttons) {

		for (Button button : buttons) {
			button.setAlignment(Pos.CENTER);
			button.setPrefWidth(200);
			button.setPrefHeight(50);
		}

	}
    
    
    public static void hoverFade(Node element) {
    	ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(0.0);
         
        
        element.setEffect(colorAdjust);
        
        element.setOnMouseEntered((x) -> {
 
             Timeline fadeIN = new Timeline(
                     new KeyFrame(Duration.millis(0), 
                             new KeyValue(colorAdjust.brightnessProperty(), colorAdjust.brightnessProperty().getValue(), Interpolator.LINEAR)), 
                             new KeyFrame(Duration.millis(500), new KeyValue(colorAdjust.brightnessProperty(), -0.3, Interpolator.LINEAR)
                             ));
    
       
             fadeIN.play();
	          
		});
		
        element.setOnMouseExited((x) -> {
			 Timeline fadeOUT = new Timeline(
                     new KeyFrame(Duration.millis(0), 
                             new KeyValue(colorAdjust.brightnessProperty(), colorAdjust.brightnessProperty().getValue(), Interpolator.LINEAR)), 
                             new KeyFrame(Duration.millis(500), new KeyValue(colorAdjust.brightnessProperty(), 0, Interpolator.LINEAR)
                             ));

             fadeOUT.play();
		});
    }
    
    public static void hoverSize(Node element) {

        element.setOnMouseEntered((x) -> {
 
             Timeline fadeIN = new Timeline(
                     new KeyFrame(Duration.millis(500),
                    		 new KeyValue(element.scaleXProperty(),1.05, Interpolator.EASE_IN),
                             new KeyValue(element.scaleYProperty(),1.05, Interpolator.EASE_IN)));

             fadeIN.play();
	          
		});
		
        element.setOnMouseExited((x) -> {
        	 Timeline fadeOUT = new Timeline(
                     new KeyFrame(Duration.millis(500),
                    		 new KeyValue(element.scaleXProperty(), 1, Interpolator.EASE_IN),
                             new KeyValue(element.scaleYProperty(), 1, Interpolator.EASE_IN)));

        	 fadeOUT.play();
		});
    }
	public static void  setFontePadrao(Node[] texts, int tamanho, FontWeight peso) {

		
		for (Node text : texts) {
			text.setStyle("-fx-font-family: Poppins; "
					+ "-fx-font-size: " + tamanho + " ;"
					+"-fx-font-weight: " +peso.getWeight() +";");
	
		}
		
	}


	
}
