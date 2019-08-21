package application;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ScrabbleController {
	
	@FXML
	Label lblPoints, lblPreviousWords, lblError, lblGameOver;
	
	@FXML
	Label A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z;
	ArrayList<Label> labels;
	
	@FXML
	TextField txtWord;
	
	private ScrabbleModel scrabble;
	
	public void initialize() {
		scrabble = new ScrabbleModel();
		addLabels();
	}
	
	public void generatePoints(ActionEvent event) {
		lblError.setText("");
		scrabble.clearErrorMessage();
		
		
		System.out.println("started");
		String word = txtWord.getText();
		
		if(scrabble.validateWord(word) && scrabble.processWord(word)) {
			int points = scrabble.getPoints();
			lblPoints.setText(Integer.toString(points));
			lblPreviousWords.setText(scrabble.getPreviousWords());
			disableUnavailableLabels();
		}
		else {
			lblError.setText(scrabble.getErrorMessage());
		}
		
		if(scrabble.gameOver()) {
			lblGameOver.setVisible(true);
			txtWord.setDisable(true);
		}
	}
	
	private void disableUnavailableLabels() {
		this.labels.stream()
			.filter(label -> scrabble.getUnavailableLetters().contains(label.getText()))
			.forEach(label -> label.setStyle("-fx-background-color:#a1a1a1"));
	}
	
	private void addLabels() {
		this.labels = new ArrayList<Label>();
		this.labels.add(this.A);
		this.labels.add(this.B);
		this.labels.add(this.C);
		this.labels.add(this.D);
		this.labels.add(this.E);
		this.labels.add(this.F);
		this.labels.add(this.G);
		this.labels.add(this.H);
		this.labels.add(this.I);
		this.labels.add(this.J);
		this.labels.add(this.K);
		this.labels.add(this.L);
		this.labels.add(this.M);
		this.labels.add(this.N);
		this.labels.add(this.O);
		this.labels.add(this.P);
		this.labels.add(this.Q);
		this.labels.add(this.R);
		this.labels.add(this.S);
		this.labels.add(this.T);
		this.labels.add(this.U);
		this.labels.add(this.V);
		this.labels.add(this.W);
		this.labels.add(this.X);
		this.labels.add(this.Y);
		this.labels.add(this.Z);
	}
}
