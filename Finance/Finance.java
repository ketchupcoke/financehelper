package finance;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Finance extends Application {
    
    public static final String category1 = "Food";
    public static final String category2 = "Clothes";
    public static final String category3 = "Memes";
    public static final String category4 = "Riot Points";
    public static final String category5 = "Bitconnect";
    public static final String category6 = "Maverick Merch";
    
    static AnchorPane mainPane;
    static List<AnchorPane> panes = new ArrayList<AnchorPane>();
    private static int index;
    public static JSONObject users;
    public static JSONObject user = new JSONObject();
    public static JSONObject categories;
    public static Application instance;

    @Override
    public void start(Stage stage) throws Exception {
        instance = this;
        JSONParser parser = new JSONParser();
        
        try {
            users = (JSONObject) parser.parse(new FileReader("users.json"));
        }
        catch (FileNotFoundException e) {
            System.out.println("Couldn't find the 'users' data file! Created a new one!");
            users = new JSONObject();
        }
        catch (Exception e) {
            System.out.println("Couldn't parse the 'users' data file! Perhaps it's empty?");
            users = new JSONObject();
        }
        try {
            categories = (JSONObject) parser.parse(new FileReader("categories.json"));
        }
        catch (FileNotFoundException e) {
            System.out.println("Couldn't find the 'categories' data file! Created a new one!");
            categories = new JSONObject();
        }
        catch (Exception e) {
            System.out.println("Couldn't parse the 'categories' data file! Perhaps it's empty?");
            categories = new JSONObject();
        }
        
        mainPane = (AnchorPane) FXMLLoader.load(getClass().getResource("Anchor.fxml"));
        panes.add((AnchorPane) FXMLLoader.load(getClass().getResource("Login.fxml")));
        panes.add((AnchorPane) FXMLLoader.load(getClass().getResource("MyFinances.fxml")));
        panes.add((AnchorPane) FXMLLoader.load(getClass().getResource("Settings.fxml")));
        panes.add((AnchorPane) FXMLLoader.load(getClass().getResource("Settings.fxml")));
        
        
        
        mainPane.getChildren().add(panes.get(0));
        index = 0;
        Scene scene = new Scene(mainPane, 960, 640);
        
        stage.setResizable(false);
        scene.getStylesheets().add(getClass().getResource("/finance/resources/fontstyle.css").toExternalForm());
        
        stage.setScene(scene);
        stage.show();
    }
    
    public void saveDataToDisk() {
        if (user != null) {
            Finance.logOut();
        }
        saveFile("users", "users.json", users);
        saveFile("categories", "categories.json", categories);
    }
    
    private void saveFile(String filename, String filedir, JSONObject data) {
        if (!(data == null)) {
            try {
                FileWriter filewriter = new FileWriter(filedir);
                BufferedWriter bufferedwriter = new BufferedWriter(filewriter);
                bufferedwriter.write(data.toJSONString());
                bufferedwriter.close();
            } 
            catch (Exception e) {
                System.out.println("Error writing to '" + filename + "' data file!");
            }
        }
        else {
            System.out.println("Nothing to save to the file: " + filedir + "!");
        }
    }
    
    public static void resetPanes() {
        panes = null;
        panes = new ArrayList<AnchorPane>();
        try {
            panes.add((AnchorPane) FXMLLoader.load(instance.getClass().getResource("Login.fxml")));
            panes.add((AnchorPane) FXMLLoader.load(instance.getClass().getResource("MyFinances.fxml")));
            panes.add((AnchorPane) FXMLLoader.load(instance.getClass().getResource("Settings.fxml")));
            panes.add((AnchorPane) FXMLLoader.load(instance.getClass().getResource("Analytics.fxml")));
        }
        catch (Exception e) {
            System.out.println("Error resetting panes!");
        }
        
    }
    
     
    public static void setPane(int setIndex) {
        mainPane.getChildren().remove(panes.get(index));
        mainPane.getChildren().add(panes.get(setIndex));
        index = setIndex;
    }

    public static void main(String[] args) {
        launch(args);
        
    }
    
    @Override
    public void stop(){
        saveDataToDisk();
    }
    
    public static long isValidSum(String sum) {
        try {
            long a = Integer.parseInt(sum);
            if (a > 0) {
                return a;
            }
            else {
                return 0;
            }
        }
        catch (Exception e) {
            return 0;
        }
    }
    
    public static boolean isValidUsername(String string) {
        if (string.matches("^[a-zA-Z0-9]*$") && string.length() > 2 && string.length() < 16) {
            return true;
        }
        return false;
    }
    public static boolean isValidPassword(String string) {
        if (string.matches("^[a-zA-Z0-9]*$") && string.length() > 4 && string.length() < 16) {
            return true;
        }
        return false;
    }
    
    public static void logOut() {
        users.put(user.get("username"), user);
        user = null;
        resetPanes();
    }
    
}
