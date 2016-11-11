package de.sloth.hmi;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class MainPane extends BorderPane {
	private Canvas canvas;
	
	public MainPane(double width, double heigth, double canWidth, double canHeigth) {
		this.setWidth(width);
		this.setHeight(heigth);
		canvas = new Canvas(canWidth, canHeigth);
		BorderPane pane = new BorderPane();
		pane.setMinWidth(800);
		pane.setMinHeight(100);
		Label label = new Label("Test");
		pane.setCenter(label);
		this.setCenter(canvas);
		this.setBottom(pane);
	}
	
	public GraphicsContext getCanvasContext() {
		return canvas.getGraphicsContext2D();
	}
}