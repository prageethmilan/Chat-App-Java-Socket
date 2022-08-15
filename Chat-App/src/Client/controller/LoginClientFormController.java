package Client.controller;

import Client.model.User;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author : M-Prageeth
 * @created : 11/08/2022 - 8:27 AM
 **/
public class LoginClientFormController {

    public TextField txtUsername;
    public JFXButton btnLogin;
    public static String username;
    public AnchorPane root;
    public FontAwesomeIconView btnClose;
    public FontAwesomeIconView btnMinimize;
    public static ArrayList<User> users = new ArrayList<>();

    public void initialize(){
        Platform.runLater(() -> root.requestFocus());
    }


    public void loginToChatRoomOnAction(MouseEvent mouseEvent) throws IOException {

        username = txtUsername.getText();
        loadChat();

        /*boolean checkUserResult = checkUser(txtUsername.getText());
        System.out.println(txtUsername.getText()+" : "+checkUserResult);
        if (checkUserResult){
            loadChat();
        }else {
            new Alert(Alert.AlertType.ERROR, "User Name Already exsist..!").show();
            txtUsername.setText("");
        }
        for (User user : users) {
            System.out.println("on "+ user.name);
        }*/
        /*String name = txtUsername.getText();
        username = name;

        Stage exitstage = (Stage) btnLogin.getScene().getWindow();
        exitstage.close();
        URL resource = this.getClass().getResource("/views/ClientForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.getIcons().add(new Image("assets/livechatLogo.png"));
        stage.setScene(scene);
        enableMove(scene,stage);
        stage.show();*/

    }

    private void loadChat() throws IOException {
        Stage exitstage = (Stage) btnLogin.getScene().getWindow();
        exitstage.close();
        URL resource = this.getClass().getResource("/Client/views/ClientForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.getIcons().add(new Image("assets/livechatLogo.png"));
        stage.setScene(scene);
        enableMove(scene,stage);
        stage.show();
    }

    /*private boolean checkUser(String name) {
        for (User user : users) {
            System.out.println(user.name +": ");

            if (user.name.equalsIgnoreCase(name)) {
                return false;
            }
        }
        username=name;
        users.add(new User(username));
        return true;
    }*/

    private void enableMove(Scene scene, Stage stage) {
        AtomicReference<Double> xOffset = new AtomicReference<>((double) 0);
        AtomicReference<Double> yOffset = new AtomicReference<>((double) 0);
        scene.setOnMousePressed(event -> {
            xOffset.set(stage.getX() - event.getScreenX());
            yOffset.set(stage.getY() - event.getScreenY());
        });
        //Lambda mouse event handler
        scene.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() + xOffset.get());
            stage.setY(event.getScreenY() + yOffset.get());
        });
    }

    public void closeWindowOnAction(MouseEvent mouseEvent) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    public void minimizeWindowOnAction(MouseEvent mouseEvent) {
        Stage stage = (Stage) btnMinimize.getScene().getWindow();
        stage.setIconified(true);
    }

    public void loginOnAction(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode() == KeyCode.ENTER){
            MouseEvent mouseevent = null;
            loginToChatRoomOnAction(mouseevent);
        }
    }
}
