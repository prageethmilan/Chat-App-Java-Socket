package Client.controller;

import Client.model.User;
import com.jfoenix.controls.JFXButton;
import com.sun.org.apache.xerces.internal.impl.io.UTF8Reader;
import com.sun.xml.internal.stream.writers.UTF8OutputStreamWriter;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXMLLoader;
import javafx.geometry.NodeOrientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicReference;

import static Client.controller.LoginClientFormController.username;

/**
 * @author : M-Prageeth
 * @created : 11/08/2022 - 8:28 AM
 **/
public class ClientFormController extends Thread {
    public FontAwesomeIconView btnClose;
    public FontAwesomeIconView btnMinimize;
    public Label lblUsername;
    public ImageView btnBackToLogin;
    public TextArea txtClientPane;
    public TextField txtClientMessage;
    public JFXButton btnSend;
    public FontAwesomeIconView btnImage;

    Socket socket;
    BufferedReader bufferedReader;
    PrintWriter printWriter;

    FileChooser fileChooser;
    File filePath;

    public void initialize() {
        connectSocket();
        lblUsername.setText(username);
    }

    private void connectSocket() {
        try {
            socket = new Socket("localhost", 5000);
            System.out.println("Connect With Server");

            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            printWriter = new PrintWriter(socket.getOutputStream(), true);

            this.start();

        } catch (IOException e) {

        }
    }

    public void run() {
        try {
            while (true) {
                String msg = bufferedReader.readLine();
                System.out.println("Message : " + msg);
                String[] tokens = msg.split(" ");
                String cmd = tokens[0];
                System.out.println("cmd : " + cmd);
                StringBuilder fulmsg = new StringBuilder();
                for (int i = 1; i < tokens.length; i++) {
                    fulmsg.append(tokens[i]);
                }
                System.out.println("fulmsg : " + fulmsg);
                System.out.println();
                if (cmd.equalsIgnoreCase(LoginClientFormController.username + ":")) {
                    continue;
                } else if (fulmsg.toString().equalsIgnoreCase("bye")) {
                    break;
                }
                txtClientPane.appendText(msg + "\n");
            }
            bufferedReader.close();
            printWriter.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        } /*finally {
            try {
                bufferedReader.close();
                printWriter.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
    }

    public void closeWindowOnAction(MouseEvent mouseEvent) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    public void minimizeWindowOnAction(MouseEvent mouseEvent) {
        Stage stage = (Stage) btnMinimize.getScene().getWindow();
        stage.setIconified(true);
    }

    public void backToLoginOnAction(MouseEvent mouseEvent) throws IOException {
        Stage stg = (Stage) btnBackToLogin.getScene().getWindow();
        stg.close();
        URL resource = this.getClass().getResource("/Client/views/LoginClientForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.getIcons().add(new Image("assets/livechatLogo.png"));
        stage.setScene(scene);
        enableMove(scene, stage);
        stage.show();
    }

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

    public void sendMessageOnAction(MouseEvent mouseEvent) throws IOException {
        send();
        /*for (User user : LoginClientFormController.users) {
            System.out.println(user.name);
        }*/
    }

    public void send() {
        String msg = txtClientMessage.getText();
        printWriter.println(LoginClientFormController.username + ": " + msg);
        txtClientPane.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        txtClientPane.appendText("Me: " + msg + "\n");
        txtClientMessage.setText("");
        if (msg.equalsIgnoreCase("BYE") || (msg.equalsIgnoreCase("logout"))) {
            System.exit(0);
        }
    }

    public void sendMessageByKeyOnAction(KeyEvent keyEvent) {
        if (keyEvent.getCode().toString().equals("ENTER")) {
            send();
        }
    }

    public void chooseImageOnAction(MouseEvent mouseEvent) {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a Image");
        this.filePath = fileChooser.showOpenDialog(stage);
        txtClientMessage.setText(filePath.getPath());
    }
}