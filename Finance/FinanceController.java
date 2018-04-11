package finance;

import java.io.File;
import java.net.MalformedURLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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

public class FinanceController {
    @FXML
    private Button analyticsButton;
    @FXML
    private Button accountButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Button feedbackButton;
    @FXML
    private AnchorPane logo;
    @FXML
    private TextField amountField;
    @FXML
    private CheckBox category1;
    @FXML
    private CheckBox category2;
    @FXML
    private CheckBox category4;
    @FXML
    private CheckBox category5;
    @FXML
    private CheckBox category6;
    @FXML
    private CheckBox category3;
    @FXML
    private Text errorMessage;
    @FXML
    private Button spentButton;
    @FXML
    private Button refundButton;

    public void initialize(){
        Image image;
        try {
            image = new Image((new File("/finance/resources/logo.png")).toURI().toURL().toExternalForm());
            logo.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        } 
        catch (MalformedURLException e) {
            System.out.println("Couldn't load the mooooney!");
        }
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
    private void unhighlightAccount(MouseEvent event) {
        accountButton.setStyle("-fx-background-color: #ffffff;-fx-text-fill: #000000;-fx-background-radius: 0;");
    }
    
    @FXML
    private void highlightAccount(MouseEvent event) {
        accountButton.setStyle("-fx-background-color: #5b4ed8;-fx-text-fill: #ffffff;-fx-background-radius: 0;");

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
    private void doSettings(ActionEvent event) {
        wipeScreen();
        Finance.setPane(2);
    }
    
    private void updateUser() {
        long sum = Finance.isValidSum(amountField.getText());
        if (sum != 0) {
            if (category1.isSelected()) {
                long c1 = (long) Finance.user.get(Finance.category1);
                Finance.user.put(Finance.category1, c1 + sum);
            }
            if (category2.isSelected()) {
                long c2 = (long) Finance.user.get(Finance.category2);
                Finance.user.put(Finance.category2, c2 + sum);
            }
            if (category3.isSelected()) {
                long c3 = (long) Finance.user.get(Finance.category3);
                Finance.user.put(Finance.category3, c3 + sum);
            }
            if (category4.isSelected()) {
                long c4 = (long) Finance.user.get(Finance.category4);
                Finance.user.put(Finance.category4, c4 + sum);
            }
            if (category5.isSelected()) {
                long c5 = (long) Finance.user.get(Finance.category5);
                Finance.user.put(Finance.category5, c5 + sum);
            }
            if (category6.isSelected()) {
                long c6 = (long) Finance.user.get(Finance.category6);
                Finance.user.put(Finance.category6, c6 + sum);
            }
            wipeScreen();
        }
        else {
            errorMessage.setText("ERROR: That is not a valid sum of money! Please round to the nearest pound.");
        }
    }
    
    
    

    
    private void wipeScreen() {
        category1.setSelected(false);
        category2.setSelected(false);
        category3.setSelected(false);
        category4.setSelected(false);
        category5.setSelected(false);
        category6.setSelected(false);
        amountField.setText("");
        errorMessage.setText("");
    }

    @FXML
    private void unhighlightSpent(MouseEvent event) {
        spentButton.setStyle("-fx-background-color: #ffffff;-fx-text-fill: #000000;");
    }

    @FXML
    private void highlightSpent(MouseEvent event) {
        spentButton.setStyle("-fx-background-color: #a79ff4;-fx-text-fill: #ffffff;");
    }

    @FXML
    private void doSpent(ActionEvent event) {
        updateUser();
    }

    @FXML
    private void unhighlightRefund(MouseEvent event) {
        refundButton.setStyle("-fx-background-color: #ffffff;-fx-text-fill: #000000;");
    }

    @FXML
    private void highlightRefund(MouseEvent event) {
        refundButton.setStyle("-fx-background-color: #a79ff4;-fx-text-fill: #ffffff;");
    }

    @FXML
    private void doRefund(ActionEvent event) {
    }

    @FXML
    private void doAnalytics(ActionEvent event) {
        Finance.resetPanes();
        Finance.setPane(3);
    }
    
}
