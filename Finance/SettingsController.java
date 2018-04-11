package finance;

import java.io.File;
import java.net.MalformedURLException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.text.Text;

public class SettingsController {
    @FXML
    private Button analyticsButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Button feedbackButton;
    @FXML
    private AnchorPane logo;
    @FXML
    private Button financeButton;
    @FXML
    private Button setButton;
    @FXML
    private TextField budgetField;
    @FXML
    private Text timeText;
    @FXML
    private Text currentBudgetText;
    @FXML
    private Slider timeSlider;
    
    public static long timeFrame = 1;
    @FXML
    private Text errorMessage;

    public void initialize(){
        Image image;
        try {
            image = new Image((new File("/finance/resources/logo.png")).toURI().toURL().toExternalForm());
            logo.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        } 
        catch (MalformedURLException e) {
            System.out.println("Couldn't load the mooooney!");
        }
        
        if (Finance.user != null && !Finance.user.isEmpty()) {
            timeFrame = (long) Finance.user.get("timeframe");
            timeText.setText(parseTime(timeFrame));
            timeSlider.setValue(timeFrame);
            currentBudgetText.setText("(Current: £" + (long) Finance.user.get("budget") + ")");
        }
        
        timeSlider.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue arg0, Object arg1, Object arg2) {
                String time = parseTime(timeSlider.getValue());
                timeText.setText("(" + time + ")");
                timeFrame = (long) Math.round(timeSlider.getValue());
            }
        });
        
    }
    
    private String parseTime (double time) {
        if (time == 1) {
            return "Daily";
        }
        if (time == 365) {
            return "Annually";
        }
        return "Every " + Math.round(time) + " days";
    }
    
    @FXML
    private void unhighlightAnalytics(MouseEvent event) {
        analyticsButton.setStyle("-fx-background-color: #ffffff;-fx-text-fill: #000000;-fx-background-radius: 0;");
    }
    
    @FXML
    private void highlightAnalytics(MouseEvent event) {
        analyticsButton.setStyle("-fx-background-color: #5b4ed8;-fx-text-fill: #ffffff;-fx-background-radius: 0;");
    }
    
    @FXML
    private void unhighlightManager(MouseEvent event) {
        financeButton.setStyle("-fx-background-color: #ffffff;-fx-text-fill: #000000;-fx-background-radius: 0;");
    }

    @FXML
    private void highlightManager(MouseEvent event) {
        financeButton.setStyle("-fx-background-color: #5b4ed8;-fx-text-fill: #ffffff;-fx-background-radius: 0;");
    }
        
    @FXML
    private void unhighlightLogout(MouseEvent event) {
        logoutButton.setStyle("-fx-background-color: #ffffff;-fx-text-fill: #000000;-fx-background-radius: 0;");
    }
    
    @FXML
    private void highlightLogout(MouseEvent event) {
        logoutButton.setStyle("-fx-background-color: #5b4ed8;-fx-text-fill: #ffffff;-fx-background-radius: 0;");
    }


    @FXML
    private void unhighlightFeedback(MouseEvent event) {
        feedbackButton.setStyle("-fx-background-color: #ffffff;-fx-text-fill: #000000;-fx-background-radius: 0;");
    }

    @FXML
    private void highlightFeedback(MouseEvent event) {
        feedbackButton.setStyle("-fx-background-color: #5b4ed8;-fx-text-fill: #ffffff;-fx-background-radius: 0;");
    }

    @FXML
    private void doLogout(ActionEvent event) {
        wipeScreen();

        Finance.logOut();
        Finance.setPane(0);
    }

    @FXML
    private void doFinance(ActionEvent event) {
        wipeScreen();
        Finance.setPane(1);
    }
    
    private void wipeScreen() {
        errorMessage.setText("");
        budgetField.setText("");
        timeSlider.setValue((long) Finance.user.get("timeframe"));
    }

    @FXML
    private void unhighlightSet(MouseEvent event) {
        setButton.setStyle("-fx-background-color: #ffffff;-fx-text-fill: #000000;");
    }

    @FXML
    private void highlightSet(MouseEvent event) {
        setButton.setStyle("-fx-background-color: #a79ff4;-fx-text-fill: #ffffff;");
    }

    @FXML
    private void doSet(ActionEvent event) {
        long sum = Finance.isValidSum(budgetField.getText());
        if (!(sum == 0)) {
            Finance.user.put("budget", sum);
            Finance.user.put("timeframe", timeFrame);
            setBudget(sum);
        }
        else {
            errorMessage.setText("ERROR: That is not a valid sum of money! Please round to the nearest pound.");
        }
    }
    
    private void setBudget(long budget) {
        currentBudgetText.setText("(Current: £" + budget + ")");
        budgetField.setText("");
    }

    @FXML
    private void doAnalytics(ActionEvent event) {
        Finance.resetPanes();
        Finance.setPane(3);
    }
    
}
