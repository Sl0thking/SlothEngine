package de.sloth.hmi;

import java.util.LinkedList;
import java.util.List;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class HMICore extends StackPane {

	private LayeredCanvas canvas;
	private List<GameInterfaceLayer> gameInterfaceLayers;
	
	public HMICore(int canvasWidth, int canvasHeight, int width, int height, int layers) {
		gameInterfaceLayers = new LinkedList<GameInterfaceLayer>();
		this.setWidth(width);
		this.setHeight(height);
		this.canvas = new LayeredCanvas(layers, canvasWidth, canvasHeight);
		this.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		BorderPane bp = new BorderPane();
		bp.setMinWidth(width);
		bp.setMinHeight(height);
		bp.setCenter(canvas);
		this.getChildren().add(bp);
	}
	
	public HMICore(int width, int height) {
		gameInterfaceLayers = new LinkedList<GameInterfaceLayer>();
		this.setWidth(width);
		this.setHeight(height);
		this.canvas = new LayeredCanvas(4, width, height);
		this.getChildren().add(canvas);
	}

	public LayeredCanvas getCanvas() {
		return canvas;
	}
	
	public void registerGameInterfaceLayer(GameInterfaceLayer gil) {
		this.gameInterfaceLayers.add(gil);
		this.getChildren().add(gil);
	}
	
	public GameInterfaceLayer getGameInterfaceLayer(String uid) {
		for(GameInterfaceLayer gil : this.gameInterfaceLayers) {
			if(gil.getUid().equals(uid)) {
				return gil;
			}
		}
		return null;
	}
}