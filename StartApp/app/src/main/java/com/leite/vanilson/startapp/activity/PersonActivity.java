package com.leite.vanilson.startapp.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.leite.vanilson.startapp.R;
import com.leite.vanilson.startapp.entity.Profession;
import com.leite.vanilson.startapp.fragment.DatePickerFragment;
import com.leite.vanilson.startapp.util.Mask;
import com.leite.vanilson.startapp.util.Util;

import java.util.ArrayList;
import java.util.Calendar;

public class PersonActivity extends AppCompatActivity {

    private Spinner spinnerJob;
    private EditText edtCpfCnpj;
    private EditText edtBirth;
    private RadioGroup rgbCpfCnpj;
    private RadioButton rdbCpf;
    private TextWatcher textWatcherCpf;
    private TextWatcher textWatcherCnpj;
    private TextView txt_cpfcnpj;
    private int cpfCnpjSelected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        spinnerJob = (Spinner) findViewById(R.id.spn_job);
        edtCpfCnpj = (EditText) findViewById(R.id.edt_cpfcnpj);
        edtBirth = (EditText) findViewById(R.id.edt_birth);
        rgbCpfCnpj = (RadioGroup) findViewById(R.id.rgb_cpfcnpj);
        rdbCpf = (RadioButton) findViewById(R.id.rdb_cpf);
        txt_cpfcnpj = (TextView) findViewById(R.id.txt_cpfcnpj);
        textWatcherCpf = Mask.insert("###.###.###-##", edtCpfCnpj);
        edtCpfCnpj.addTextChangedListener(textWatcherCpf);
        textWatcherCnpj = Mask.insert("##.###.###/####-##", edtCpfCnpj);
        rgbCpfCnpj.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                edtCpfCnpj.setText("");
                edtCpfCnpj.requestFocus();
                cpfCnpjSelected = group.getCheckedRadioButtonId();
                if (cpfCnpjSelected == rdbCpf.getId()) {
                    edtCpfCnpj.removeTextChangedListener(textWatcherCnpj);
                    edtCpfCnpj.addTextChangedListener(textWatcherCpf);
                    txt_cpfcnpj.setText("CPF");
                } else {
                    edtCpfCnpj.removeTextChangedListener(textWatcherCpf);
                    edtCpfCnpj.addTextChangedListener(textWatcherCnpj);
                    txt_cpfcnpj.setText("CNPJ");
                }
            }
        });

        edtCpfCnpj.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (rgbCpfCnpj.getCheckedRadioButtonId() == rdbCpf.getId()) {
                    if (edtCpfCnpj.getText().length() < 14) {
                        edtCpfCnpj.setText("");
                    }
                } else {
                    if (edtCpfCnpj.getText().length() < 18) {
                        edtCpfCnpj.setText("");
                    }
                }
            }

        });

        initProfessions();
    }

    public void setData(View view) {
        DatePickerFragment datePickerFragment = new DatePickerFragment();
        Calendar calendar = Calendar.getInstance();
        Bundle bundle = new Bundle();
        bundle.putInt("day", calendar.get(Calendar.DAY_OF_MONTH));
        bundle.putInt("month", calendar.get(Calendar.MONTH));
        bundle.putInt("year", calendar.get(Calendar.YEAR));
        datePickerFragment.setArguments(bundle);
        datePickerFragment.setDateListener(dateListener);
        datePickerFragment.show(getFragmentManager(), "Data Nasc.");
    }

    private DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            edtBirth.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
        }
    };

    private void initProfessions() {
        ArrayList<String> professions = new ArrayList<String>();
        for (Profession p: Profession.values()) {
            professions.add(p.getDescription());
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(PersonActivity.this,
                android.R.layout.simple_spinner_item, professions);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerJob.setAdapter(arrayAdapter);
    }
}
