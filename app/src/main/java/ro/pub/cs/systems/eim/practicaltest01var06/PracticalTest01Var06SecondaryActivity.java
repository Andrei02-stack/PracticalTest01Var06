package ro.pub.cs.systems.eim.practicaltest01var06;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PracticalTest01Var06SecondaryActivity extends AppCompatActivity {

    private TextView resultTextView;
    private Button okButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var06_secondary);

        resultTextView = findViewById(R.id.resultTextView);
        okButton = findViewById(R.id.okButton);

        // Preluarea datelor din intenție
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("numbers") && intent.hasExtra("checkboxCount")) {
            String[] numbers = intent.getStringArrayExtra("numbers");
            int checkboxCount = intent.getIntExtra("checkboxCount", 0);

            // Verificarea numerelor și afișarea rezultatului
            if (areNumbersEqual(numbers)) {
                int gain = calculateGain(checkboxCount);
                resultTextView.setText("Gained " + gain);
                Toast.makeText(this, "Gained " + gain, Toast.LENGTH_SHORT).show();
            } else {
                resultTextView.setText("No gain");
            }
        }

        // Închidere activitate la apăsarea butonului OK
        okButton.setOnClickListener(v -> finish());
    }

    // Metodă pentru verificarea dacă toate numerele sunt la fel (asteriscul este joker)
    private boolean areNumbersEqual(String[] numbers) {
        String first = numbers[0].equals("*") ? numbers[1] : numbers[0];
        for (String number : numbers) {
            if (!number.equals(first) && !number.equals("*")) {
                return false;
            }
        }
        return true;
    }

    // Calcularea câștigului pe baza numărului de checkbox-uri bifate
    private int calculateGain(int checkboxCount) {
        switch (checkboxCount) {
            case 0:
                return 100;
            case 1:
                return 50;
            case 2:
                return 10;
            default:
                return 0;
        }
    }
}

