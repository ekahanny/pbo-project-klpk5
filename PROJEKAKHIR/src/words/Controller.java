package words;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller implements Initializable {

    @FXML
    private Label timeLabel;
    @FXML
    private Label displayUsername;

    @FXML
    private Text total;
    @FXML
    private Text wpm;
    @FXML
    private Text invalid;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Page 1

        File newFile = new File("Username.txt");
        if (newFile.length() != 0) {
            Scanner reader = null;
            try {
                reader = new Scanner(newFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            String data = reader.nextLine();
            displayUsername.setText("Selamat Datang, "+data);

        }
        // mengatur hari
        Date date = new Date();
        Locale locale = new Locale("id");
        DateFormat formatter = new SimpleDateFormat("EEEE", locale);
        String strDay = formatter.format(date);

        timeLabel.setText("Hari ini adalah " + strDay);

        // menampilkan data
        int[] data = FileHandling.sumUpNumbers("src/data");
        total.setText(String.valueOf(data[0]));
        wpm.setText(String.valueOf(Math.round(data[1]*1.0/data[3])));
        invalid.setText(String.valueOf(data[2]));
    }

    // Button PlayGame
    public void playGame(ActionEvent ddd) throws IOException {
        Main m = new Main();

        File newFile = new File("Username.txt");
        if (newFile.length() == 0) {
            try {
                m.changeScene("login.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            m.changeScene("game.fxml");
        }

    }
}
