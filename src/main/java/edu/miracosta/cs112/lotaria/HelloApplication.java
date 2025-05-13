package edu.miracosta.cs112.lotaria;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.*;

public class HelloApplication extends Application implements EventHandler<ActionEvent> {
    private static final LoteriaCard[] LOTERIA_CARDS = {
            new LoteriaCard("Las matematicas", "1.png", 1),
            new LoteriaCard("Las ciencias", "2.png", 2),
            new LoteriaCard("La Tecnología", "8.png", 8),
            new LoteriaCard("La ingeniería", "9.png", 9),
    };

    Button drawCardButton;
    ProgressBar progressBar;
    Label messageLabel;
    ImageView imageView;

    @Override
    public void start(Stage stage) {
        StackPane root = new StackPane();

        VBox vbox = new VBox();
        vbox.setPrefSize(350, 500);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);
        Label title = new Label("Loteria!");
        title.setStyle("-fx-font: 18 arial; -fx-font-weight: bold;");

        imageView = new ImageView();
        imageView.setFitWidth(220);
        imageView.setFitHeight(300);

        messageLabel = new Label();
        messageLabel.setStyle("-fx-font: 14 arial;");

        drawCardButton = new Button("Draw Card");
        drawCardButton.setPrefSize(150, 40);
        drawCardButton.setStyle(
                "-fx-background-color: #2196F3;" +     // Blue background (Material Blue 500)
                        "-fx-text-fill: white;" +             // White text
                        "-fx-background-radius: 12;" +        // Rounded edges
                        "-fx-border-width: 0;" +              // Remove border thickness
                        "-fx-font: 14 arial;"                 // Font
        );
        drawCardButton.setOnAction(this);

        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.getChildren().addAll(title, imageView, messageLabel, drawCardButton);

        progressBar = new ProgressBar(0, LOTERIA_CARDS.length, vbox);

        root.getChildren().addAll(vbox);
        Scene scene = new Scene(root, 350, 500);
        stage.setTitle("Loteria!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private class ProgressBar {
        int progress;
        int min;
        int max;

        StackPane holder;
        Region outer;
        Region inner;

        double tick;

        public ProgressBar(int min, int max, VBox vbox) {
            this.min = min;
            this.max = max;
            this.progress = min;

            holder = new StackPane();
            holder.setMaxWidth(Region.USE_PREF_SIZE); // Respect your prefWidth
            VBox.setVgrow(holder, Priority.NEVER); // Don't let VBox stretch it
            holder.setPrefSize(200, 10);
            holder.setAlignment(Pos.CENTER_LEFT);

            outer = new Region();
            outer.setPrefSize(200, 10);
            outer.setStyle("-fx-background-color: #aaaaaa;" + "-fx-background-radius: 20;");

            tick = outer.getPrefWidth() / (max - min);

            inner = new Region();
            inner.setMaxWidth(Region.USE_PREF_SIZE);
            inner.setPrefSize(0, 10);
            inner.setStyle("-fx-background-color: #2196F3;" + "-fx-background-radius: 20;");

            holder.getChildren().addAll(outer, inner);
            vbox.getChildren().addAll(holder);
        }

        public void increment() {
            if (progress >= max) {return;}
            inner.setPrefWidth((progress+1) * tick);
            progress++;
        }

        public int getProgress() {return progress;}
    }

    @Override
    public void handle(ActionEvent event) {
        // generate
        if (event.getSource() == drawCardButton) {
            imageView.setImage(LOTERIA_CARDS[progressBar.getProgress()].getImage());
            messageLabel.setText(LOTERIA_CARDS[progressBar.getProgress()].getCardName());
            progressBar.increment();
        }
    }
}