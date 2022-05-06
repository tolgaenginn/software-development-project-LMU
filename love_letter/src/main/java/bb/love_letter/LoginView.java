package bb.love_letter;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class LoginView {
    private GridPane view;
    public LoginModel model;
    public LoginController controller;
    private TextField ipField;
    private TextField portField;
    private TextField usernameField;
    private Label errorLabel;
    private Button button;
    public LoginView(LoginModel model, LoginController controller) {
        this.model = model;
        this.controller = controller;
        buildUI();
        setupListeners();
        observeModelandUpdate();
    }

    private void buildUI() {
        view = new GridPane();
        Label title = new Label("Login");
        Separator separator = new Separator();
        ipField = new TextField();
        ipField.setPromptText("IP");
        portField = new TextField();
        portField.setPromptText("Port");
        usernameField = new TextField();
        usernameField.setPromptText("Username");
        errorLabel = new Label();
        button = new Button("Login");

        view.setVgap(16);
        view.setAlignment(Pos.CENTER);
        GridPane.setHalignment(button, HPos.CENTER);
        GridPane.setHalignment(title, HPos.CENTER);

        view.addRow(0, title);
        view.addRow(1, separator);
        view.addRow(2, ipField);
        view.addRow(3, portField);
        view.addRow(4, usernameField);
        view.addRow(5, button);
        view.addRow(6, errorLabel);
    }

    private void setupListeners() {
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("Clicked");
                String ip = ipField.getText();
                int port = Integer.parseInt(portField.getText());
                String username = usernameField.getText();
                controller.connectToServer(ip, port, username);
            }
        });
    }

    private void observeModelandUpdate() {
        model.errorMessageProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (!t1.equals(s) && !t1.equals("")) {
                    errorLabel.setText(t1);
                }
            }
        });
    }

    public Parent asParent() {
        return view ;
    }
}
