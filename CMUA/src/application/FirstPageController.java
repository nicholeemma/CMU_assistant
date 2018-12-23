package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.File;
import java.io.IOException;

import audioSystem.Play;
import audioSystem.TextToMp3;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;



public class FirstPageController {
	@FXML
	private Button button1;
	static Stage app_stage;

	// Event Listener on Button[#button1].onAction
	@FXML
	public void welcomButton(ActionEvent event) throws IOException {
		Parent report_page_parent = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
	    Scene report_page_scene = new Scene(report_page_parent);
	    app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    app_stage.setScene(report_page_scene);
	    app_stage.show();
	    
	    TextToMp3 mp3 = new TextToMp3();
		try {
			String fileName=mp3.textToMp3("Welcome to Carnegie Mellon University Australia Portal!\nI know everything you need...");

     	    File file = new File(fileName);
     	    Play p = new Play(file);
            p.playAudio();
		
		} catch (Exception e) {
		}
	}
}
