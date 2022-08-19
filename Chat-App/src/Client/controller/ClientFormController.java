package Client.controller;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
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
    public HBox hboxmessage;
    public VBox vboxmessage;
    public ImageView imgEmoji;
    public ImageView emoji1;
    public ImageView emoji11;
    public ImageView emoji2;
    public ImageView emoji12;
    public ImageView emoji3;
    public ImageView emoji13;
    public ImageView emoji4;
    public ImageView emoji14;
    public ImageView emoji5;
    public ImageView emoji15;
    public ImageView emoji6;
    public ImageView emoji16;
    public ImageView emoji7;
    public ImageView emoji17;
    public ImageView emoji8;
    public ImageView emoji18;
    public ImageView emoji9;
    public ImageView emoji19;
    public ImageView emoji10;
    public ImageView emoji20;
    public AnchorPane rootEmoji;

    Socket socket;
    BufferedReader bufferedReader;
    PrintWriter printWriter;

    FileChooser fileChooser;
    File filePath;
    URL url;
    String []ePath=new String[20];

    public void initialize() {
        connectSocket();
        lblUsername.setText(username);

        {
            for (int i = 0; i < ePath.length; i++) {
                ePath[i] = "assets/emojis/" + (i + 1) + ".png";
                //System.out.println(ePath[i]);

            }
            System.out.println("Emojis path set to array");
        }
    }

    private void connectSocket() {
        try {
            socket = new Socket("localhost", 5000);
            System.out.println("Connect With Server");

            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
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
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        HBox hBox = new HBox();

                        if (fulmsg.toString().startsWith("assets/emojis/") ) {
                            System.out.println("Emoji path "+fulmsg);
                            hBox.setAlignment(Pos.CENTER_LEFT);
                            hBox.setPadding(new Insets(5, 10, 5, 5));
                            Text text = new Text(cmd + " ");
                            ImageView imageView = new ImageView();
                            Image image = new Image(String.valueOf(fulmsg));
                            imageView.setImage(image);
                            imageView.setFitWidth(50);
                            imageView.setFitHeight(50);
                            TextFlow textFlow = new TextFlow(text, imageView);
                            VBox vBox = new VBox(textFlow);
                            vBox.setAlignment(Pos.CENTER_LEFT);
                            vBox.setPadding(new Insets(5, 10, 5, 5));
                            vboxmessage.getChildren().add(vBox);
                        } else if (fulmsg.toString().endsWith(".png") || fulmsg.toString().endsWith(".jpg") || fulmsg.toString().endsWith(".jpeg") || fulmsg.toString().endsWith(".gif")) {
                            boolean bool = fulmsg.toString().endsWith(".png");
                            System.out.println(bool);
                            hBox.setAlignment(Pos.TOP_LEFT);
                            hBox.setPadding(new Insets(5, 10, 5, 5));
                            Text text = new Text(cmd + " ");
                            ImageView imageView = new ImageView();
                            Image image = new Image(String.valueOf(fulmsg));
                            imageView.setImage(image);
                            imageView.setFitWidth(100);
                            imageView.setFitHeight(100);
                            TextFlow textFlow = new TextFlow(text, imageView);
                            /*textFlow.setStyle("-fx-color:rgb(239,242,255);"
                                    + "-fx-background-color: rgb(182,182,182);" +
                                    "-fx-background-radius: 10px");*/
                            textFlow.setPadding(new Insets(5, 0, 5, 5));
                            VBox vBox1 = new VBox(textFlow);
//                            VBox vBox2 = new VBox(imageView);
                            vBox1.setAlignment(Pos.CENTER_LEFT);
//                            vBox1.setPadding(new Insets(5, 10, 5, 5));
//                            vBox2.setAlignment(Pos.CENTER_LEFT);
//                            vBox2.setPadding(new Insets(5, 10, 5, 5));
//                            hBox.getChildren().add(textFlow);
//                            vboxmessage.getChildren().add(vBox1);
//                            vboxmessage.getChildren().add(vBox2);
                            vboxmessage.getChildren().add(vBox1);
                        } else {
                            boolean bool = fulmsg.toString().endsWith(".png");
                            System.out.println(bool);
                            hBox.setAlignment(Pos.CENTER_LEFT);
                            hBox.setPadding(new Insets(5, 10, 5, 5));
                            Text text = new Text(msg);
                            TextFlow textFlow = new TextFlow(text);
                            textFlow.setStyle("-fx-color:rgb(239,242,255);"
                                    + "-fx-background-color: rgb(182,182,182);" +
                                    "-fx-background-radius: 10px");
                            textFlow.setPadding(new Insets(5, 0, 5, 5));
                            text.setFill(Color.color(0, 0, 0));
                            hBox.getChildren().add(textFlow);
                            vboxmessage.getChildren().add(hBox);
                        }
                    }
                });
                /*txtClientPane.appendText(msg + "\n");*/
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
//        txtClientPane.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_RIGHT);
        hBox.setPadding(new Insets(5, 5, 5, 10));
        Text text = new Text(msg);
        TextFlow textFlow = new TextFlow(text);
        textFlow.setStyle("-fx-color:rgb(239,242,255);"
                + "-fx-background-color: rgb(15,125,242);" +
                "-fx-background-radius: 20px");
        textFlow.setPadding(new Insets(5, 10, 5, 10));
        text.setFill(Color.color(0.934, 0.945, 0.996));
        hBox.getChildren().add(textFlow);
        vboxmessage.getChildren().add(hBox);
        printWriter.flush();

//        txtClientPane.appendText("Me: " + msg + "\n");
        txtClientMessage.setText("");
        if (msg.equalsIgnoreCase("BYE") || (msg.equalsIgnoreCase("logout"))) {
            System.exit(0);
        }

        rootEmoji.setVisible(false);
        imgEmoji.setVisible(true);
    }

    public void sendMessageByKeyOnAction(KeyEvent keyEvent) {
        if (keyEvent.getCode().toString().equals("ENTER")) {
            send();
        }
    }

    public void chooseImageOnAction(MouseEvent mouseEvent) throws MalformedURLException {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a Image");
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            printWriter.println(username + ": " + file.toURI().toURL());
        }
        /*printWriter.println(username + ": " + file.getPath());*/
        /*txtClientMessage.setText(filePath.getPath());*/
        if (file != null) {
            System.out.println("File Was Selected");
            url = file.toURI().toURL();
            System.out.println(url);
            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER_RIGHT);
            hBox.setPadding(new Insets(5, 10, 5, 5));
            ImageView imageView = new ImageView();
            Image image = new Image(String.valueOf(url));
            imageView.setImage(image);
            imageView.setFitWidth(100);
            imageView.setFitHeight(100);
            VBox vBox = new VBox(imageView);
            vBox.setAlignment(Pos.CENTER_RIGHT);
            vBox.setPadding(new Insets(5, 10, 5, 5));
            vboxmessage.getChildren().add(vBox);
        }
    }

    public void chooseEmojiOnAction(MouseEvent mouseEvent) {
        if (rootEmoji.isVisible()){
            rootEmoji.setVisible(false);
        } else {
            rootEmoji.setVisible(true);
        }
    }

    public void sendEmojiOnMouseClick(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();
            switch (icon.getId()) {
                case "emoji1":
                    byte[] emojiBytes1 = new byte[]{(byte) 0xF0, (byte) 0x9F, (byte) 0x98, (byte) 0x81};
                    String emojiAsString1 = new String(emojiBytes1, StandardCharsets.UTF_8);
                    if (txtClientMessage.getText().equalsIgnoreCase("") || txtClientMessage.getText().equalsIgnoreCase(null)) {
                        ImageView imageView = new ImageView();
                        Image image = new Image(ePath[0]);
                        imageView.setImage(image);
                        imageView.setFitWidth(50);
                        imageView.setFitHeight(50);
                        VBox vBox = new VBox(imageView);
                        vBox.setAlignment(Pos.CENTER_RIGHT);
                        vBox.setPadding(new Insets(5, 10, 5, 5));
                        vboxmessage.getChildren().add(vBox);
                        printWriter.println(username + ": " + ePath[0]);
                        rootEmoji.setVisible(false);
                        imgEmoji.setVisible(true);
                    } else {
                        txtClientMessage.appendText(emojiAsString1);

                    }
                   /* byte[] emojiBytes1 = new byte[]{(byte) 0xF0, (byte) 0x9F, (byte) 0x91, (byte) 0xba};
                    System.out.println(emojiBytes1);
                    String emojiAsString1= new String(emojiBytes1, StandardCharsets.US_ASCII);
                    txtClientMessage.appendText(emojiAsString1);
                    System.out.println(emojiAsString1);
                    String cp = "\uD83D\uDC7A";
                    byte b[] =cp.getBytes(StandardCharsets.UTF_8);
                    for (int k=0; k<b.length; k++)
                        System.out.printf(" %x ", b[k]);
                    System.out.println();
*/
                    break;
                case "emoji2":
                    byte[] emojiBytes2 = new byte[]{(byte) 0xF0, (byte) 0x9F, (byte) 0x98, (byte) 0x82};
                    String emojiAsString2 = new String(emojiBytes2, StandardCharsets.UTF_8);

                    if (txtClientMessage.getText().equalsIgnoreCase("") || txtClientMessage.getText().equalsIgnoreCase(null)) {
                        ImageView imageView = new ImageView();
                        Image image = new Image(ePath[1]);
                        imageView.setImage(image);
                        imageView.setFitWidth(50);
                        imageView.setFitHeight(50);
                        VBox vBox = new VBox(imageView);
                        vBox.setAlignment(Pos.CENTER_RIGHT);
                        vBox.setPadding(new Insets(5, 10, 5, 5));
                        vboxmessage.getChildren().add(vBox);
                        printWriter.println(username + ": " + ePath[1]);
                        rootEmoji.setVisible(false);
                        imgEmoji.setVisible(true);
                    } else {
                        txtClientMessage.appendText(emojiAsString2);
                    }
                    break;
                case "emoji3":
                    byte[] emojiBytes3 = new byte[]{(byte) 0xF0, (byte) 0x9F, (byte) 0x98, (byte) 0x83};
                    String emojiAsString3 = new String(emojiBytes3, StandardCharsets.UTF_8);

                    String ste3="assets/emojis/3.png";
                    if (txtClientMessage.getText().equalsIgnoreCase("") || txtClientMessage.getText().equalsIgnoreCase(null)) {
                        ImageView imageView = new ImageView();
                        Image image = new Image(ePath[2]);
                        imageView.setImage(image);
                        imageView.setFitWidth(50);
                        imageView.setFitHeight(50);
                        VBox vBox = new VBox(imageView);
                        vBox.setAlignment(Pos.CENTER_RIGHT);
                        vBox.setPadding(new Insets(5, 10, 5, 5));
                        vboxmessage.getChildren().add(vBox);
                        printWriter.println(username + ": " + ePath[2]);
                        rootEmoji.setVisible(false);
                        imgEmoji.setVisible(true);
                    } else {
                        txtClientMessage.appendText(emojiAsString3);
                    }
                    break;
                case "emoji4":
                    byte[] emojiBytes4 = new byte[]{(byte) 0xF0, (byte) 0x9F, (byte) 0x98, (byte) 0x84};
                    String emojiAsString4 = new String(emojiBytes4, StandardCharsets.UTF_8);

                    String ste4="assets/emojis/2.png";
                    if (txtClientMessage.getText().equalsIgnoreCase("") || txtClientMessage.getText().equalsIgnoreCase(null)) {
                        ImageView imageView = new ImageView();
                        Image image = new Image(ePath[3]);
                        imageView.setImage(image);
                        imageView.setFitWidth(50);
                        imageView.setFitHeight(50);
                        VBox vBox = new VBox(imageView);
                        vBox.setAlignment(Pos.CENTER_RIGHT);
                        vBox.setPadding(new Insets(5, 10, 5, 5));
                        vboxmessage.getChildren().add(vBox);
                        printWriter.println(username + ": " + ePath[3]);
                        rootEmoji.setVisible(false);
                        imgEmoji.setVisible(true);
                    } else {
                        txtClientMessage.appendText(emojiAsString4);
                    }
                    break;
                case "emoji5":
                    byte[] emojiBytes5 = new byte[]{(byte) 0xF0, (byte) 0x9F, (byte) 0x98, (byte) 0x85};
                    String emojiAsString5 = new String(emojiBytes5, StandardCharsets.UTF_8);
                    if (txtClientMessage.getText().equalsIgnoreCase("") || txtClientMessage.getText().equalsIgnoreCase(null)) {
                        ImageView imageView = new ImageView();
                        Image image = new Image(ePath[4]);
                        imageView.setImage(image);
                        imageView.setFitWidth(50);
                        imageView.setFitHeight(50);
                        VBox vBox = new VBox(imageView);
                        vBox.setAlignment(Pos.CENTER_RIGHT);
                        vBox.setPadding(new Insets(5, 10, 5, 5));
                        vboxmessage.getChildren().add(vBox);
                        printWriter.println(username + ": " + ePath[4]);
                        rootEmoji.setVisible(false);
                        imgEmoji.setVisible(true);
                    } else {
                        txtClientMessage.appendText(emojiAsString5);
                    }

                    break;
                case "emoji6":
                    byte[] emojiBytes6 = new byte[]{(byte) 0xF0, (byte) 0x9F, (byte) 0x98, (byte) 0x86};
                    String emojiAsString6 = new String(emojiBytes6, StandardCharsets.UTF_8);
                    if (txtClientMessage.getText().equalsIgnoreCase("") || txtClientMessage.getText().equalsIgnoreCase(null)) {
                        ImageView imageView = new ImageView();
                        Image image = new Image(ePath[5]);
                        imageView.setImage(image);
                        imageView.setFitWidth(50);
                        imageView.setFitHeight(50);
                        VBox vBox = new VBox(imageView);
                        vBox.setAlignment(Pos.CENTER_RIGHT);
                        vBox.setPadding(new Insets(5, 10, 5, 5));
                        vboxmessage.getChildren().add(vBox);
                        printWriter.println(username + ": " + ePath[5]);
                        rootEmoji.setVisible(false);
                        imgEmoji.setVisible(true);
                    } else {
                        txtClientMessage.appendText(emojiAsString6);
                    }
                    break;
                case "emoji7":
                    byte[] emojiBytes7 = new byte[]{(byte) 0xF0, (byte) 0x9F, (byte) 0x98, (byte) 0x89};
                    String emojiAsString7 = new String(emojiBytes7, StandardCharsets.UTF_8);
                    if (txtClientMessage.getText().equalsIgnoreCase("") || txtClientMessage.getText().equalsIgnoreCase(null)) {
                        ImageView imageView = new ImageView();
                        Image image = new Image(ePath[6]);
                        imageView.setImage(image);
                        imageView.setFitWidth(50);
                        imageView.setFitHeight(50);
                        VBox vBox = new VBox(imageView);
                        vBox.setAlignment(Pos.CENTER_RIGHT);
                        vBox.setPadding(new Insets(5, 10, 5, 5));
                        vboxmessage.getChildren().add(vBox);
                        printWriter.println(username + ": " + ePath[6]);
                        rootEmoji.setVisible(false);
                        imgEmoji.setVisible(true);

                    } else {
                        txtClientMessage.appendText(emojiAsString7);
                    }

                    break;
                case "emoji8":
                    byte[] emojiBytes8 = new byte[]{(byte) 0xF0, (byte) 0x9F, (byte) 0x98, (byte) 0x8A};
                    String emojiAsString8 = new String(emojiBytes8, StandardCharsets.UTF_8);
                    if (txtClientMessage.getText().equalsIgnoreCase("") || txtClientMessage.getText().equalsIgnoreCase(null)) {
                        ImageView imageView = new ImageView();
                        Image image = new Image(ePath[7]);
                        imageView.setImage(image);
                        imageView.setFitWidth(50);
                        imageView.setFitHeight(50);
                        VBox vBox = new VBox(imageView);
                        vBox.setAlignment(Pos.CENTER_RIGHT);
                        vBox.setPadding(new Insets(5, 10, 5, 5));
                        vboxmessage.getChildren().add(vBox);
                        printWriter.println(username + ": " + ePath[7]);
                        rootEmoji.setVisible(false);
                        imgEmoji.setVisible(true);
                    } else {
                        txtClientMessage.appendText(emojiAsString8);
                    }
                    break;
                case "emoji9":
                    byte[] emojiBytes9 = new byte[]{(byte) 0xF0, (byte) 0x9F, (byte) 0x98, (byte) 0x8B};
                    String emojiAsString9 = new String(emojiBytes9, StandardCharsets.UTF_8);
                    if (txtClientMessage.getText().equalsIgnoreCase("") || txtClientMessage.getText().equalsIgnoreCase(null)) {
                        ImageView imageView = new ImageView();
                        Image image = new Image(ePath[8]);
                        imageView.setImage(image);
                        imageView.setFitWidth(50);
                        imageView.setFitHeight(50);
                        VBox vBox = new VBox(imageView);
                        vBox.setAlignment(Pos.CENTER_RIGHT);
                        vBox.setPadding(new Insets(5, 10, 5, 5));
                        vboxmessage.getChildren().add(vBox);
                        printWriter.println(username + ": " + ePath[8]);
                        rootEmoji.setVisible(false);
                        imgEmoji.setVisible(true);
                    } else {
                        txtClientMessage.appendText(emojiAsString9);
                    }
                    break;
                case "emoji10":
                    byte[] emojiBytes10 = new byte[]{(byte) 0xF0, (byte) 0x9F, (byte) 0x98, (byte) 0x8C};
                    String emojiAsString10 = new String(emojiBytes10, StandardCharsets.UTF_8);
                    if (txtClientMessage.getText().equalsIgnoreCase("") || txtClientMessage.getText().equalsIgnoreCase(null)) {
                        ImageView imageView = new ImageView();
                        Image image = new Image(ePath[9]);
                        imageView.setImage(image);
                        imageView.setFitWidth(50);
                        imageView.setFitHeight(50);
                        VBox vBox = new VBox(imageView);
                        vBox.setAlignment(Pos.CENTER_RIGHT);
                        vBox.setPadding(new Insets(5, 10, 5, 5));
                        vboxmessage.getChildren().add(vBox);
                        printWriter.println(username + ": " + ePath[9]);
                        rootEmoji.setVisible(false);
                        imgEmoji.setVisible(true);
                    } else {txtClientMessage.appendText(emojiAsString10);}
                    break;
                case "emoji11":
                    byte[] emojiBytes11 = new byte[]{(byte) 0xF0, (byte) 0x9F, (byte) 0x98, (byte) 0xAD};
                    String emojiAsString11 = new String(emojiBytes11, StandardCharsets.UTF_8);
                    if (txtClientMessage.getText().equalsIgnoreCase("") || txtClientMessage.getText().equalsIgnoreCase(null)) {
                        ImageView imageView = new ImageView();
                        Image image = new Image(ePath[10]);
                        imageView.setImage(image);
                        imageView.setFitWidth(50);
                        imageView.setFitHeight(50);
                        VBox vBox = new VBox(imageView);
                        vBox.setAlignment(Pos.CENTER_RIGHT);
                        vBox.setPadding(new Insets(5, 10, 5, 5));
                        vboxmessage.getChildren().add(vBox);
                        printWriter.println(username + ": " + ePath[10]);
                        rootEmoji.setVisible(false);
                        imgEmoji.setVisible(true);
                    } else {txtClientMessage.appendText(emojiAsString11);}
                    break;
                case "emoji12":
                    byte[] emojiBytes12 = new byte[]{(byte) 0xF0, (byte) 0x9F, (byte) 0x98, (byte) 0x92};
                    String emojiAsString12 = new String(emojiBytes12, StandardCharsets.UTF_8);
                    if (txtClientMessage.getText().equalsIgnoreCase("") || txtClientMessage.getText().equalsIgnoreCase(null)) {
                        ImageView imageView = new ImageView();
                        Image image = new Image(ePath[11]);
                        imageView.setImage(image);
                        imageView.setFitWidth(50);
                        imageView.setFitHeight(50);
                        VBox vBox = new VBox(imageView);
                        vBox.setAlignment(Pos.CENTER_RIGHT);
                        vBox.setPadding(new Insets(5, 10, 5, 5));
                        vboxmessage.getChildren().add(vBox);
                        printWriter.println(username + ": " + ePath[11]);
                        rootEmoji.setVisible(false);
                        imgEmoji.setVisible(true);
                    } else {txtClientMessage.appendText(emojiAsString12);}
                    break;
                case "emoji13":
                    byte[] emojiBytes13 = new byte[]{(byte) 0xF0, (byte) 0x9F, (byte) 0x98, (byte) 0x93};
                    String emojiAsString13 = new String(emojiBytes13, StandardCharsets.UTF_8);
                    if (txtClientMessage.getText().equalsIgnoreCase("") || txtClientMessage.getText().equalsIgnoreCase(null)) {
                        ImageView imageView = new ImageView();
                        Image image = new Image(ePath[12]);
                        imageView.setImage(image);
                        imageView.setFitWidth(50);
                        imageView.setFitHeight(50);
                        VBox vBox = new VBox(imageView);
                        vBox.setAlignment(Pos.CENTER_RIGHT);
                        vBox.setPadding(new Insets(5, 10, 5, 5));
                        vboxmessage.getChildren().add(vBox);
                        printWriter.println(username + ": " + ePath[12]);
                        rootEmoji.setVisible(false);
                        imgEmoji.setVisible(true);
                    } else {txtClientMessage.appendText(emojiAsString13);}
                    break;
                case "emoji14":
                    byte[] emojiBytes14 = new byte[]{(byte) 0xF0, (byte) 0x9F, (byte) 0x98, (byte) 0x94};
                    String emojiAsString14 = new String(emojiBytes14, StandardCharsets.UTF_8);
                    if (txtClientMessage.getText().equalsIgnoreCase("") || txtClientMessage.getText().equalsIgnoreCase(null)) {
                        ImageView imageView = new ImageView();
                        Image image = new Image(ePath[13]);
                        imageView.setImage(image);
                        imageView.setFitWidth(50);
                        imageView.setFitHeight(50);
                        VBox vBox = new VBox(imageView);
                        vBox.setAlignment(Pos.CENTER_RIGHT);
                        vBox.setPadding(new Insets(5, 10, 5, 5));
                        vboxmessage.getChildren().add(vBox);
                        printWriter.println(username + ": " + ePath[13]);
                        rootEmoji.setVisible(false);
                        imgEmoji.setVisible(true);
                    } else {txtClientMessage.appendText(emojiAsString14);}
                    break;
                case "emoji15":
                    byte[] emojiBytes15 = new byte[]{(byte) 0xF0, (byte) 0x9F, (byte) 0x98, (byte) 0x96};
                    String emojiAsString15 = new String(emojiBytes15, StandardCharsets.UTF_8);
                    if (txtClientMessage.getText().equalsIgnoreCase("") || txtClientMessage.getText().equalsIgnoreCase(null)) {
                        ImageView imageView = new ImageView();
                        Image image = new Image(ePath[14]);
                        imageView.setImage(image);
                        imageView.setFitWidth(50);
                        imageView.setFitHeight(50);
                        VBox vBox = new VBox(imageView);
                        vBox.setAlignment(Pos.CENTER_RIGHT);
                        vBox.setPadding(new Insets(5, 10, 5, 5));
                        vboxmessage.getChildren().add(vBox);
                        printWriter.println(username + ": " + ePath[14]);
                        rootEmoji.setVisible(false);
                        imgEmoji.setVisible(true);
                    } else {
                        txtClientMessage.appendText(emojiAsString15);
                    }
                    break;
                case "emoji16":
                    byte[] emojiBytes16 = new byte[]{(byte) 0xF0, (byte) 0x9F, (byte) 0x98, (byte) 0x98};
                    String emojiAsString16 = new String(emojiBytes16, StandardCharsets.UTF_8);
                    if (txtClientMessage.getText().equalsIgnoreCase("") || txtClientMessage.getText().equalsIgnoreCase(null)) {
                        ImageView imageView = new ImageView();
                        Image image = new Image(ePath[15]);
                        imageView.setImage(image);
                        imageView.setFitWidth(50);
                        imageView.setFitHeight(50);
                        VBox vBox = new VBox(imageView);
                        vBox.setAlignment(Pos.CENTER_RIGHT);
                        vBox.setPadding(new Insets(5, 10, 5, 5));
                        vboxmessage.getChildren().add(vBox);
                        printWriter.println(username + ": " + ePath[15]);
                        rootEmoji.setVisible(false);
                        imgEmoji.setVisible(true);
                    } else {txtClientMessage.appendText(emojiAsString16);}
                    break;
                case "emoji17":
                    byte[] emojiBytes17 = new byte[]{(byte) 0xF0, (byte) 0x9F, (byte) 0x98, (byte) 0x94};
                    String emojiAsString17 = new String(emojiBytes17, StandardCharsets.UTF_8);
                    if (txtClientMessage.getText().equalsIgnoreCase("") || txtClientMessage.getText().equalsIgnoreCase(null)) {
                        ImageView imageView = new ImageView();
                        Image image = new Image(ePath[16]);
                        imageView.setImage(image);
                        imageView.setFitWidth(50);
                        imageView.setFitHeight(50);
                        VBox vBox = new VBox(imageView);
                        vBox.setAlignment(Pos.CENTER_RIGHT);
                        vBox.setPadding(new Insets(5, 10, 5, 5));
                        vboxmessage.getChildren().add(vBox);
                        printWriter.println(username + ": " + ePath[16]);
                        rootEmoji.setVisible(false);
                        imgEmoji.setVisible(true);
                    } else {txtClientMessage.appendText(emojiAsString17);}
                    break;
                case "emoji18":
                    byte[] emojiBytes18 = new byte[]{(byte) 0xF0, (byte) 0x9F, (byte) 0x98, (byte) 0x9A};
                    String emojiAsString18 = new String(emojiBytes18, StandardCharsets.UTF_8);
                    if (txtClientMessage.getText().equalsIgnoreCase("") || txtClientMessage.getText().equalsIgnoreCase(null)) {
                        ImageView imageView = new ImageView();
                        Image image = new Image(ePath[17]);
                        imageView.setImage(image);
                        imageView.setFitWidth(50);
                        imageView.setFitHeight(50);
                        VBox vBox = new VBox(imageView);
                        vBox.setAlignment(Pos.CENTER_RIGHT);
                        vBox.setPadding(new Insets(5, 10, 5, 5));
                        vboxmessage.getChildren().add(vBox);
                        printWriter.println(username + ": " + ePath[17]);
                        rootEmoji.setVisible(false);
                        imgEmoji.setVisible(true);
                    } else {txtClientMessage.appendText(emojiAsString18);}
                    break;
                case "emoji19":
                    byte[] emojiBytes19 = new byte[]{(byte) 0xF0, (byte) 0x9F, (byte) 0x98, (byte) 0x9C};
                    String emojiAsString19 = new String(emojiBytes19, StandardCharsets.UTF_8);
                    if (txtClientMessage.getText().equalsIgnoreCase("") || txtClientMessage.getText().equalsIgnoreCase(null)) {
                        ImageView imageView = new ImageView();
                        Image image = new Image(ePath[18]);
                        imageView.setImage(image);
                        imageView.setFitWidth(50);
                        imageView.setFitHeight(50);
                        VBox vBox = new VBox(imageView);
                        vBox.setAlignment(Pos.CENTER_RIGHT);
                        vBox.setPadding(new Insets(5, 10, 5, 5));
                        vboxmessage.getChildren().add(vBox);
                        printWriter.println(username + ": " + ePath[18]);
                        rootEmoji.setVisible(false);
                        imgEmoji.setVisible(true);
                    } else {txtClientMessage.appendText(emojiAsString19);}
                    break;
                case "emoji20":
                    byte[] emojiBytes20 = new byte[]{(byte) 0xF0, (byte) 0x9F, (byte) 0x98, (byte) 0x9D};
                    String emojiAsString20 = new String(emojiBytes20, StandardCharsets.UTF_8);
                    if (txtClientMessage.getText().equalsIgnoreCase("") || txtClientMessage.getText().equalsIgnoreCase(null)) {
                        ImageView imageView = new ImageView();
                        Image image = new Image(ePath[19]);
                        imageView.setImage(image);
                        imageView.setFitWidth(50);
                        imageView.setFitHeight(50);
                        VBox vBox = new VBox(imageView);
                        vBox.setAlignment(Pos.CENTER_RIGHT);
                        vBox.setPadding(new Insets(5, 10, 5, 5));
                        vboxmessage.getChildren().add(vBox);
                        printWriter.println(username + ": " + ePath[19]);
                        rootEmoji.setVisible(false);
                        imgEmoji.setVisible(true);
                    } else {txtClientMessage.appendText(emojiAsString20);}
                    break;
            }
        }
    }
}