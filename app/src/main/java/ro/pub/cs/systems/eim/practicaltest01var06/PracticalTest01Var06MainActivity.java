package ro.pub.cs.systems.eim.practicaltest01var06;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class PracticalTest01Var06MainActivity extends AppCompatActivity {

    private EditText number1EditText;
    private EditText number2EditText;
    private EditText number3EditText;
    private CheckBox checkbox1;
    private CheckBox checkbox2;
    private CheckBox checkbox3;
    private Button playButton;
    private Random random;

    private static final String TAG = "PracticalTest01Var06";
    private static final String[] POSSIBLE_VALUES = {"1", "2", "3", "*"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var06_main);

        // Inițializarea elementelor UI
        number1EditText = findViewById(R.id.number1);
        number2EditText = findViewById(R.id.number2);
        number3EditText = findViewById(R.id.number3);
        checkbox1 = findViewById(R.id.checkbox1);
        checkbox2 = findViewById(R.id.checkbox2);
        checkbox3 = findViewById(R.id.checkbox3);
        playButton = findViewById(R.id.play_button);
        random = new Random();

        // Setarea listener-ului pentru butonul "Play"
        playButton.setOnClickListener(v -> {
            // Generare numere aleatoare din {1, 2, 3, *} și suprascriere doar dacă checkbox-ul NU este bifat
            if (!checkbox1.isChecked()) {
                number1EditText.setText(getRandomValue());
            }
            if (!checkbox2.isChecked()) {
                number2EditText.setText(getRandomValue());
            }
            if (!checkbox3.isChecked()) {
                number3EditText.setText(getRandomValue());
            }

            // Afișarea valorilor în Toast și Logcat
            String result = "Numere generate: " +
                    number1EditText.getText().toString() + ", " +
                    number2EditText.getText().toString() + ", " +
                    number3EditText.getText().toString();
            Toast.makeText(PracticalTest01Var06MainActivity.this, result, Toast.LENGTH_SHORT).show();
            Log.d(TAG, result);
        });
    }

    // Metodă pentru a returna un număr aleatoriu din setul {1, 2, 3, *}
    private String getRandomValue() {
        return POSSIBLE_VALUES[random.nextInt(POSSIBLE_VALUES.length)];
    }
}
