package edu.miracosta.cs112.lotaria;

import edu.miracosta.cs112.lotaria.LoteriaCard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;

import java.util.Random;

public class HelloController {
    private static final LoteriaCard[] LOTERIA_CARDS = {
            new LoteriaCard("Las matematicas", "1.png", 1),
            new LoteriaCard("Las ciencias", "2.png", 2),
            new LoteriaCard("La Tecnología", "8.png", 8),
            new LoteriaCard("La ingeniería", "9.png", 9),
    };

    private final Random random = new Random();
    private int cardsDrawn = 0;

    @FXML
    private Label titleLabel;

    @FXML
    private ImageView cardImageView;

    @FXML
    private Label messageLabel;

    @FXML
    private Button drawCardButton;

    @FXML
    private ProgressBar gameProgressBar;

    @FXML
    public void initialize() {
        // Connect the button to our handler using an anonymous class
        drawCardButton.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Choose a random card
                int randomIndex = random.nextInt(LOTERIA_CARDS.length);
                LoteriaCard randomCard = LOTERIA_CARDS[randomIndex];

                // Update the image view with the new card
                cardImageView.setImage(randomCard.getImage());

                // Update message with card name and number
                messageLabel.setText("Card #" + randomCard.getCardNum() + ": " + randomCard.getCardName());

                // Update progress bar
                cardsDrawn++;
                double progress = (double) cardsDrawn / 20; // Assuming 20 draws is a complete game
                gameProgressBar.setProgress(progress);
            }
        });

        // Set initial card
        LoteriaCard initialCard = new LoteriaCard();
        cardImageView.setImage(initialCard.getImage());
        messageLabel.setText("Click the button to draw a card!");
    }
}