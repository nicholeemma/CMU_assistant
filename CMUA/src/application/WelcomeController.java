package application;

import java.net.URL;
import java.util.ResourceBundle;

import audioSystem.Play;
import audioSystem.TextToMp3;
import audioSystem.Voice;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.io.File;
import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.alicebot.ab.History;
import org.alicebot.ab.MagicBooleans;
import org.alicebot.ab.MagicStrings;
import org.alicebot.ab.utils.IOUtils;

public class WelcomeController implements Initializable {

	@FXML
	private Button clickToTalk;
	@FXML
	private TextField textBox;
	@FXML
	private TextArea messages;

	private static final boolean TRACE_MODE = false;
	static String botName = "super";
	
	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		String text="*** Talk to me or type your question :) ***";
		messages.appendText(text+"\n");
	
	}

	@FXML
	private void clickToTalk(ActionEvent event) throws Exception {
	/*
		TextToMp3 mp3 = new TextToMp3();
     	String fileName=mp3.textToMp3("What can i do for you?");
		File file = new File(fileName);
		Play p = new Play(file);
     	p.playAudio();
     */	
		Voice v = new Voice();
		v.voice();
		String textLine = Voice.results;
		messages.appendText("\nYou : "+textLine +"\n");
		//System.out.print(result);
		
		try {
	           String resourcesPath = getResourcesPath();
	         //System.out.println(resourcesPath);
	           MagicBooleans.trace_mode = TRACE_MODE;
	           Bot bot = new Bot("super", resourcesPath);
	           Chat chatSession = new Chat(bot);
	           bot.brain.nodeStats();
	          // while(true) {
	           //    System.out.print("You : ");
	           //    textLine = IOUtils.readInputTextLine();
	               if ((textLine == null) || (textLine.length() < 1))
	                   textLine = MagicStrings.null_input;
	               if (textLine.equals("q")) {
	                   System.exit(0);
	               } else if (textLine.equals("wq")) {
	                   bot.writeQuit();
	                   System.exit(0);
	               } else {
	                   String request = textLine;
	                   if (MagicBooleans.trace_mode)
	                       System.out.println("STATE=" + request + ":THAT=" + ((History) chatSession.thatHistory.get(0)).get(0) + ":TOPIC=" + chatSession.predicates.get("topic"));
	                   String response = chatSession.multisentenceRespond(request);
	                   while (response.contains("&lt;"))
	                       response = response.replace("&lt;", "<");
	                   while (response.contains("&gt;"))
	                       response = response.replace("&gt;", ">");
	                   messages.appendText("Andrew :" + response +"\n");
	                   TextToMp3 mp3 = new TextToMp3();
	                   String fileName=mp3.textToMp3(response);
	            	   File file = new File(fileName);
	            	   Play p = new Play(file);
	                 p.playAudio();
	        		Voice.results = "";	        			        		
	               }
	          // }
	       } catch (Exception e) {
	           e.printStackTrace();
	       }

	}
	@FXML
	private void type(ActionEvent event) throws Exception {
	/*	
		TextToMp3 mp3 = new TextToMp3();
     	String fileName=mp3.textToMp3("What can i do for you?");
		File file = new File(fileName);
		Play p = new Play(file);
     	p.playAudio();
     */	
		String textLine = textBox.getText();
		messages.appendText("\nYou : "+textLine +"\n");	
		try {
	           String resourcesPath = getResourcesPath();
	           MagicBooleans.trace_mode = TRACE_MODE;
	           Bot bot = new Bot("super", resourcesPath);
	           Chat chatSession = new Chat(bot);
	           bot.brain.nodeStats();
	               if ((textLine == null) || (textLine.length() < 1))
	                   textLine = MagicStrings.null_input;
	               if (textLine.equals("q")) {
	                   System.exit(0);
	               } else if (textLine.equals("wq")) {
	                   bot.writeQuit();
	                   System.exit(0);
	               } else {
	                   String request = textLine;
	                   if (MagicBooleans.trace_mode)
	                       System.out.println("STATE=" + request + ":THAT=" + ((History) chatSession.thatHistory.get(0)).get(0) + ":TOPIC=" + chatSession.predicates.get("topic"));
	                   String response = chatSession.multisentenceRespond(request);
	                   while (response.contains("&lt;"))
	                       response = response.replace("&lt;", "<");
	                   while (response.contains("&gt;"))
	                       response = response.replace("&gt;", ">");
	                   messages.appendText("Andrew: " + response +"\n");
	                   TextToMp3 mp3 = new TextToMp3();
	                   String fileName=mp3.textToMp3(response);
	            	   File file = new File(fileName);
	            	   Play p = new Play(file);
	                   p.playAudio();    			        		
	               }
	          		textBox.clear();
	       } catch (Exception e) {
	           e.printStackTrace();
	       }

	}

	   private static String getResourcesPath() {
	       File currDir = new File(".");
	       String path = currDir.getAbsolutePath();
	       path = path.substring(0, path.length() - 2);
	       System.out.println(path);
	       String resourcesPath = path + File.separator + "src" + File.separator + "main" + File.separator + "resources";
	       return resourcesPath;
	   }
}
