package br.com.vanilson.coursedevmedia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerDay = null;
    private Spinner spinnerMonth = null;

    private void validateDate() {
        int date = spinnerDay.getSelectedItemPosition();
        int month = spinnerMonth.getSelectedItemPosition();
        date++;
        month++;
        if (date > 29 && month == 2) {
            spinnerDay.setSelection(28);
        } else if ((month == 4 || month == 6 || month == 9 || month == 11) && (date > 30)) {
            spinnerDay.setSelection(29);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinnerDay = (Spinner) findViewById(R.id.spinnerDay);
        spinnerMonth = (Spinner) findViewById(R.id.spinnerMonth);
        ArrayAdapter<CharSequence> adapter_day = ArrayAdapter.createFromResource(this, R.array.days,
                android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter_month = ArrayAdapter.createFromResource(this, R.array.months,
                android.R.layout.simple_spinner_item);
        adapter_day.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter_month.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDay.setAdapter(adapter_day);
        spinnerMonth.setAdapter(adapter_month);
        spinnerDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                validateDate();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinnerMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                validateDate();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Button send = (Button) findViewById(R.id.buttonHoroscope);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int positionDate = spinnerDay.getSelectedItemPosition();
                int positionMonth = spinnerMonth.getSelectedItemPosition();
                positionDate++;
                positionMonth++;
                InterpreterSign interpreterSign = new InterpreterSign();
                Sign signResult = interpreterSign.interpreter(positionDate, positionMonth);
                Bundle args = new Bundle();
                args.putSerializable("result", signResult);
                Intent intent = new Intent(MainActivity.this, Result.class);
                intent.putExtra("sign", args);
                startActivity(intent);
            }
        });
    }
}