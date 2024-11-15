package ro.pub.cs.systems.eim.practicaltest01var06;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class PracticalTest01Var06MainActivity extends AppCompatActivity {

    private int score = 0;

    private static final int REQUEST_CODE = 1;
    private EditText number1EditText;
    private EditText number2EditText;
    private EditText number3EditText;
    private CheckBox checkbox1;
    private CheckBox checkbox2;
    private CheckBox checkbox3;
    private Button playButton;
    private Button computeButton;
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
        computeButton = findViewById(R.id.compute_button);
        random = new Random();

        // Setarea listener-ului pentru butonul "Play"
        playButton.setOnClickListener(v -> {
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

        // Setarea listener-ului pentru butonul "Compute"
        computeButton.setOnClickListener(v -> {
            // Crearea intenției pentru a lansa activitatea secundară
            Intent intent = new Intent(PracticalTest01Var06MainActivity.this, PracticalTest01Var06SecondaryActivity.class);
            intent.putExtra("numbers", new String[]{
                    number1EditText.getText().toString(),
                    number2EditText.getText().toString(),
                    number3EditText.getText().toString()
            });

            // Calcularea numărului de checkbox-uri
            int checkboxCount = 0;
            if (checkbox1.isChecked()) checkboxCount++;
            if (checkbox2.isChecked()) checkboxCount++;
            if (checkbox3.isChecked()) checkboxCount++;
            intent.putExtra("checkboxCount", checkboxCount);

            // Lansarea activității secundare pentru rezultat
            startActivityForResult(intent, REQUEST_CODE);
        });
    }

    // Suprascrierea metodei pentru a prelua rezultatul din activitatea secundară
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null && data.hasExtra("gain")) {
                int gain = data.getIntExtra("gain", 0);
                score += gain;
                Toast.makeText(this, "Total score: " + score, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private String getRandomValue() {
        return POSSIBLE_VALUES[random.nextInt(POSSIBLE_VALUES.length)];
    }
}
