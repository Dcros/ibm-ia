package com.jarvia.jarvia;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import com.ibm.watson.developer_cloud.http.HttpMediaType;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.RecognizeOptions;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechRecognitionResults;
import com.ibm.watson.developer_cloud.speech_to_text.v1.websocket.BaseRecognizeCallback;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Calendar;
import java.util.Formatter;
import javafx.scene.control.Button;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;
import org.json.JSONArray;
import org.json.JSONObject;
public class FXMLController implements Initializable {
    @FXML
    private Label jarviA_talk;
    @FXML
    private Button micro_speech;
    @FXML
    private Label label2;
    public String TextoTemporal;
    public String LaRespuestaIA;
    public String Larespuesta;
    public String LarespuestaVoice;
    
    @FXML
    public void handleButtonAction(ActionEvent event) throws InterruptedException, LineUnavailableException, Exception {
        micro_speech.setDisable(true);
        com.jarvia.jarvia.SpeechToText service = new com.jarvia.jarvia.SpeechToText();
        service.setUsernameAndPassword("bf118679-d704-43ed-a49b-cbfa01fcdfd3", "yZUzl4WSUpLC");

        // Signed PCM AudioFormat with 16kHz, 16 bit sample size, mono
        int sampleRate = 16000;
        AudioFormat format = new AudioFormat(sampleRate, 16, 1, true, false);
        DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

        if (!AudioSystem.isLineSupported(info)) {
          System.out.println("Line not supported");
          System.exit(0);
        }

        TargetDataLine line = (TargetDataLine) AudioSystem.getLine(info);
        line.open(format);
        line.start();

        AudioInputStream audio = new AudioInputStream(line);

        RecognizeOptions options = new RecognizeOptions.Builder()
          .interimResults(true)
          //.inactivityTimeout(3) // use this to stop listening when the speaker pauses, i.e. for 5s
          .audio(audio)
          .contentType(HttpMediaType.AUDIO_RAW + "; rate=" + sampleRate)
          .build();

        service.recognizeUsingWebSocket(options, new BaseRecognizeCallback() {
          @Override
          public void onTranscription(SpeechRecognitionResults speechResults) {
            System.out.println(speechResults);
            
            if(speechResults.toString().indexOf("\"results\": []") == -1)
            {
                jarviA_talk.setText("Talk now please");
                JSONObject myjson = new JSONObject(speechResults);
                JSONArray the_json_array = myjson.getJSONArray("results");
                String alternativesVar = getKey(the_json_array, "alternatives").toString();
                String[] parts = alternativesVar.split("\"");
                addText(parts[3], 2);
                addTextToVoice(parts[3]);
            }else
            {
                
                jarviA_talk.setText("Please talk!");
                label2.setText("I cant hear you!");
            
            }
          }
        });
        System.out.println("Speak! If not press again the button!");
            Thread.sleep(3 * 1000);
        // closing the WebSockets underlying InputStream will close the WebSocket itself.
        line.stop();
        line.close();
        label2.setText(TextoTemporal);
        if (LarespuestaVoice != "")
        {
            String respuestaas = checkToExistFile("memory.txt", LarespuestaVoice);
            if(respuestaas.equalsIgnoreCase("none"))
            {
                /*Write the new word in the IA memory*/
                writeToFile("", "memory.txt", LarespuestaVoice);
                /*String that hold the answer if the IA DONT know the word*/
                String laRespuesta = "I Don`t understand you!";
                /*Function that make IA chatting IA DONT know the word*/
                addText(laRespuesta, 1);
                /*Personal Variable use mbrola to speech IA DONT know the answer*/
            }
            /*If the IA know the word then answer to the User*/
            else
            {
                /*Personal Variable that use mbrola to speech the correct answer */
                /*Function that make IA chatting the correct answer*/
                addText(respuestaas, 1);
            }
            label2.setText(TextoTemporal);
        }
        micro_speech.setDisable(false);
        jarviA_talk.setText("Ask Again!");
        LarespuestaVoice = "";
    }
    
    private Object getKey(JSONArray array, String key)
    {
        Object value = null;
        for (int i = 0; i < array.length(); i++)
        {
            JSONObject item = array.getJSONObject(i);
            if (item.keySet().contains(key))
            {
                value = item.get(key);
                break;
            }
        }

        return value;
    }
 
    public void addText(String newtext, Integer Speak)
    {
        String inputmic = label2.getText();
        String TheLabelWriter = inputmic;
        String TheIARespond = inputmic;
        String respuesta = inputmic;
        Formatter fmt = new Formatter();
        Calendar cal = Calendar.getInstance();
        fmt = new Formatter();
        fmt.format("(%tl:%tM:%tS) ", cal, cal, cal);
        respuesta = newtext+"\n"+inputmic;
        if(Speak == 1)
        {
            TheLabelWriter = fmt+"Jarvia say: "+newtext+"\n"+TheLabelWriter;
        }else if(Speak == 2){
            TheLabelWriter = fmt+"You say: "+newtext+"\n"+TheLabelWriter;
        }
        TextoTemporal = TheLabelWriter;
        LaRespuestaIA = TheIARespond;
        Larespuesta = respuesta;
        
    }
    
    public void addTextToVoice(String newtext){
    
        LarespuestaVoice = newtext;
    }
    
   /*Public String that check into an arry the name of another string the ask that string have and Read it*/
    public String checkToExistFile(String FileName, String Ask) throws Exception, IOException
    {
        /*Defines a buffer reader of array bucle to input what was readed in file for use IA*/
        BufferedReader fr = new BufferedReader(new InputStreamReader(new FileInputStream(new File(FileName))));
        /*String that hold the composition of the string saved into the file awaiting to be readed*/
        String line;
        /*Its a void that does an action for bring the line readed the spilter sign for read the Question-Answer and finally get it in correct string for IA*/
        while((line = fr.readLine()) != null) {
            /*String that say what splits does for each line*/
            String[] expLine = line.split("-");
            /*No Entiendo*/
            
            String newask = Ask.substring(0, Ask.length()-1);
            if(expLine[0].indexOf( Ask ) != -1)
            {
                /*No entiendo*/
                return expLine[1];
            }
        }
        /*Return Nothing*/
        return "none";
    }
    /*Void that write the file by taking some string structure like path name and stat*/
    public void writeToFile(String path, String fileName, String status) throws Exception, IOException {
        /*String that hold the text to be saved*/
        String text = status+"-respuestaas"+"\n";
        /*Path of the file memory*/
        Path p = Paths.get(path, fileName);
        /*Check if its possible write the file in the line*/
        if (Files.isWritable(p)) {
            /*If its busy the line then made a line jump*/
            Files.write(p, System.getProperty("line.separator").getBytes(), StandardOpenOption.APPEND);
            /*And write the string*/
            Files.write(p, text.getBytes(), StandardOpenOption.APPEND);
        }else/*If its not posible Write then*/{
            /*Generate the file*/
            FileWriter fileWriter = new FileWriter(fileName);
            /*Defines the new string to print into a new varuable*/
            PrintWriter printWriter = new PrintWriter(fileWriter);
            /*Print the string as structure showed Question-Answer*/
            printWriter.printf("%s-%s", status, "respuestaas");
            /*Finish and Stop the Write process the string its saved with the split!*/
            printWriter.close();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*String that hold the text into the text input*/
        String texto=label2.getText();
        /*Little check to advantage and exception spaces or none into text input to avoid take it as actual writing*/
        texto=texto.replaceAll(" ", "");
        /*Check if the text into the text input its null or 0*/
        if(texto.length()==0){
            /*If into the text input its nothing writed then print into app label a mesake from Ia that challange user to write something*/
            jarviA_talk.setText("Talk To me!");
            label2.setText("Press Micro and say something!");
        }
        /*If its a text in the text input*/
        else
        {
            label2.setText("Press Micro and say something!");
        }
    }    

}
