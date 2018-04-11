package finance;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.json.simple.JSONObject;

public class LoginController {
    @FXML
    private Button loginButton;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;
    @FXML
    private CheckBox newAccount;
    @FXML
    private Text errorMessage;

    @FXML
    private void doLogin(ActionEvent event) {
        String username = usernameField.getText().toLowerCase();
        String password = passwordField.getText().toLowerCase();
        if (newAccount.isSelected()) {
            if (!(Finance.isValidUsername(username))) {
                errorMessage.setText("ERROR: Usernames must be alphanumeric and contain 3-15 characters!");
            }
            else if (!(Finance.isValidPassword(password))) {
                errorMessage.setText("ERROR: Passwords must be alphanumeric and contain 5-15 characters!");
            }
            else if (Finance.users == null || !(Finance.users.containsKey(username))) {
                JSONObject user = new JSONObject();
                user.put("username", username);
                user.put("password", password);
                user.put("budget", (long) 0);
                user.put("timeframe", (long) 1);
                user.put(Finance.category1, (long) 0);
                user.put(Finance.category2, (long) 0);
                user.put(Finance.category3, (long) 0);
                user.put(Finance.category4, (long) 0);
                user.put(Finance.category5, (long) 0);
                user.put(Finance.category6, (long) 0);
                Finance.user = user;
                Finance.users.put(username, user);
                System.out.println(user.toJSONString());
                wipeMessages();
                Finance.resetPanes();
                Finance.setPane(2);
            }
            else {
                errorMessage.setText("ERROR: That account already exists!");
            }
        }
        else {
            if ((Finance.users != null) && Finance.users.containsKey(username)) {
                JSONObject testUser = (JSONObject) Finance.users.get(username);
                if (((String) testUser.get("password")).equalsIgnoreCase(password)) {
                    Finance.user = (JSONObject) Finance.users.get(username);
                    wipeMessages();
                    Finance.resetPanes();
                    Finance.setPane(1);
                }
                else {
                    errorMessage.setText("ERROR: Invalid username or password!");
                }
            }
            else {
                errorMessage.setText("ERROR: Invalid username or password!");
            }
        }
    }
    
    private void wipeMessages() {
        errorMessage.setText("");
        passwordField.setText("");
        usernameField.setText("");
        newAccount.setSelected(false);
    }

    @FXML
    private void unhighlightGo(MouseEvent event) {
        loginButton.setStyle("-fx-background-color: #ffffff;-fx-text-fill: #000000;");
    }

    @FXML
    private void highlightGo(MouseEvent event) {
        loginButton.setStyle("-fx-background-color: #a79ff4;-fx-text-fill: #ffffff;");
    }
    
}
