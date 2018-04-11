package finance;

import java.io.File;
import java.net.MalformedURLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

public class AnalyticsController {
    private Button analyticsButton;
    @FXML
    private Button accountButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Button feedbackButton;
    @FXML
    private AnchorPane logo;
    private Button spentButton;
    private Button refundButton;
    @FXML
    private Button financeButton;
    @FXML
    private PieChart pieChart;

    public void initialize(){
        Image image;
        try {
            image = new Image((new File("./finance/resources/logo.png")).toURI().toURL().toExternalForm());
            logo.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        } 
        catch (MalformedURLException e) {
            System.out.println("Couldn't load the mooooney!");
        }
        
        if (Finance.user != null && !(Finance.user.isEmpty())) {
            ObservableList<PieChart.Data> pieChartData =
            FXCollections.observableArrayList(
            new PieChart.Data(Finance.category1, (long) Finance.user.get(Finance.category1)),
            new PieChart.Data(Finance.category2, (long) Finance.user.get(Finance.category2)),
            new PieChart.Data(Finance.category3, (long) Finance.user.get(Finance.category3)),
            new PieChart.Data(Finance.category4, (long) Finance.user.get(Finance.category4)),
            new PieChart.Data(Finance.category5, (long) Finance.user.get(Finance.category5)),
            new PieChart.Data(Finance.category6, (long) Finance.user.get(Finance.category6)));
                
            pieChart.setData(pieChartData);
            pieChart.setVisible(true);
        }
    }
    
    
    private void unhighlightAnalytics(MouseEvent event) {
        analyticsButton.setStyle("-fx-background-color: #ffffff;-fx-text-fill: #000000;-fx-background-radius: 0;");
    }
    
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
        Finance.logOut();
        Finance.setPane(0);
    }

    @FXML
    private void doSettings(ActionEvent event) {
        Finance.setPane(2);
    }
 

    private void unhighlightSpent(MouseEvent event) {
        spentButton.setStyle("-fx-background-color: #ffffff;-fx-text-fill: #000000;");
    }

    private void highlightSpent(MouseEvent event) {
        spentButton.setStyle("-fx-background-color: #a79ff4;-fx-text-fill: #ffffff;");
    }


    private void unhighlightRefund(MouseEvent event) {
        refundButton.setStyle("-fx-background-color: #ffffff;-fx-text-fill: #000000;");
    }

    private void highlightRefund(MouseEvent event) {
        refundButton.setStyle("-fx-background-color: #a79ff4;-fx-text-fill: #ffffff;");
    }

    @FXML
    private void doFinance(ActionEvent event) {
        Finance.setPane(1);
    }

    @FXML
    private void unhighlightFinance(MouseEvent event) {
        financeButton.setStyle("-fx-background-color: #ffffff;-fx-text-fill: #000000;-fx-background-radius: 0;");
    }

    @FXML
    private void highlightFinance(MouseEvent event) {
        financeButton.setStyle("-fx-background-color: #5b4ed8;-fx-text-fill: #ffffff;-fx-background-radius: 0;");
    }
    
}
