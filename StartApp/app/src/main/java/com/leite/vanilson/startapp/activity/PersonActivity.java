package com.leite.vanilson.startapp.activity;

import android.app.DatePickerDialog;
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
import com.leite.vanilson.startapp.entity.Gender;
import com.leite.vanilson.startapp.entity.Person;
import com.leite.vanilson.startapp.entity.PersonType;
import com.leite.vanilson.startapp.entity.Profession;
import com.leite.vanilson.startapp.fragment.DatePickerFragment;
import com.leite.vanilson.startapp.util.Mask;
import com.leite.vanilson.startapp.util.Util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class PersonActivity extends AppCompatActivity {

    private Spinner spinnerProfession;
    private EditText edtName, edtAddress, edtCpfCnpj, edtBirth;
    private RadioGroup rbgCpfCnpj, rbgGender;
    private RadioButton rbtCpf, rbtCnpj, rbtM, rbtF;
    private TextWatcher textWatcherCpf, textWatcherCnpj;
    private TextView txt_cpfcnpj;
    private int cpfCnpjSelected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        spinnerProfession = (Spinner) findViewById(R.id.spn_profession);
        edtName = (EditText) findViewById(R.id.edt_name);
        edtAddress = (EditText) findViewById(R.id.edt_address);
        edtCpfCnpj = (EditText) findViewById(R.id.edt_cpfcnpj);
        edtBirth = (EditText) findViewById(R.id.edt_birth);
        rbgCpfCnpj = (RadioGroup) findViewById(R.id.rbg_cpfcnpj);
        rbgGender = (RadioGroup) findViewById(R.id.rbg_gender);
        rbtCpf = (RadioButton) findViewById(R.id.rbt_cpf);
        rbtCnpj = (RadioButton) findViewById(R.id.rbt_cnpj);
        rbtM = (RadioButton) findViewById(R.id.rbt_m);
        rbtF = (RadioButton) findViewById(R.id.rbt_f);
        txt_cpfcnpj = (TextView) findViewById(R.id.txt_cpfcnpj);
        textWatcherCpf = Mask.insert("###.###.###-##", edtCpfCnpj);
        edtCpfCnpj.addTextChangedListener(textWatcherCpf);
        textWatcherCnpj = Mask.insert("##.###.###/####-##", edtCpfCnpj);
        rbgCpfCnpj.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                edtCpfCnpj.setText("");
                edtCpfCnpj.requestFocus();
                cpfCnpjSelected = group.getCheckedRadioButtonId();
                if (cpfCnpjSelected == rbtCpf.getId()) {
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
                if (rbgCpfCnpj.getCheckedRadioButtonId() == rbtCpf.getId()) {
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
        spinnerProfession.setAdapter(arrayAdapter);
    }

    private void createPerson() {
        Person person = new Person();
        person.setName(edtName.getText().toString());
        person.setAddress(edtAddress.getText().toString());
        person.setCpfCnpj(edtCpfCnpj.getText().toString());
        switch (rbgCpfCnpj.getCheckedRadioButtonId()) {
            case R.id.rbt_cpf:
                person.setPersonType(PersonType.PHYSICAL);
                break;
            case R.id.rbt_cnpj:
                person.setPersonType(PersonType.LEGAL);
                break;
        }
        switch (rbgGender.getCheckedRadioButtonId()) {
            case R.id.rbt_m:
                person.setGender(Gender.MALE);
                break;
            case R.id.rbt_f:
                person.setGender(Gender.FEMALE);
                break;
        }
        Profession profession = Profession.getProfession(spinnerProfession.getSelectedItemPosition());
        person.setProfession(profession);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date birthday = dateFormat.parse(edtBirth.getText().toString());
            person.setDtBirthday(birthday);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Util.showMsgToast(this, person.toString());
    }

    public void sendPerson(View view) {
        createPerson();
    }
}
