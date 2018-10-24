package com.atimports.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import com.atimports.R;
import com.atimports.constantes.Constantes;
import com.atimports.utils.Utils;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AddActivity extends AppCompatActivity {

    Spinner spnConditions;
    Spinner spnQtd;

    EditText etCotacaoDolar;
    EditText etBaseFreteDolar;
    EditText etBaseFreteRealCalculado;

    Switch   swStatusOrdem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        spnConditions = findViewById(R.id.spn_conditions);
        spnQtd        = findViewById(R.id.spn_qtd);

        etCotacaoDolar            =  findViewById(R.id.et_cotacao_dolar);
        etBaseFreteDolar          =  findViewById(R.id.et_base_frete_dolar);
        etBaseFreteRealCalculado  =  findViewById(R.id.et_base_frete_real);
        swStatusOrdem             =  findViewById(R.id.sw_status_ordem);

        List<EditText> listaCamposCalculados = new ArrayList<>();
        listaCamposCalculados.add(etBaseFreteRealCalculado);

        popularCombosValorFixo(spnConditions, R.array.conditions);
        popularComboQuantidade(spnQtd);

        aplicarMascaraMoeda(etCotacaoDolar,   Constantes.SIMBOLO_REAL,  listaCamposCalculados);
        aplicarMascaraMoeda(etBaseFreteDolar, Constantes.SIMBOLO_DOLAR, listaCamposCalculados);

        etBaseFreteDolar.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {

                    if(null != etCotacaoDolar && !Utils.isBlank(etCotacaoDolar.getText().toString())){

                        BigDecimal valorCotacaoDolar   = new BigDecimal(Utils.desformatarMascaraMoeda(etCotacaoDolar.getText().toString(),   Constantes.SIMBOLO_REAL));
                        BigDecimal valorBaseFreteDolar = new BigDecimal(Utils.desformatarMascaraMoeda(etBaseFreteDolar.getText().toString(), Constantes.SIMBOLO_DOLAR));
                        BigDecimal valorBaseFreteReal  = valorCotacaoDolar.multiply(valorBaseFreteDolar).setScale(2);

                        if(!Utils.isNullOrZero(valorBaseFreteReal)){
                            etBaseFreteRealCalculado.setText(Utils.formatarMascaraMoeda(valorBaseFreteReal.toString(), Constantes.SIMBOLO_REAL));
                        }
                        else{
                            etBaseFreteRealCalculado.setText(Constantes.VAZIO);
                        }

                    }
                }
            }
        });


        swStatusOrdem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton cb, boolean on){
                if(on){
                    //Do something when Switch button is on/checked
                    swStatusOrdem.setText(R.string.paga);
                    swStatusOrdem.setTextColor(getResources().getColor(R.color.material_green));
                }
                else{
                    swStatusOrdem.setText(R.string.nao_paga);
                    swStatusOrdem.setTextColor(getResources().getColor(R.color.dark_red));
                }
            }
        });


    }//FIM onCreate


    //*******
    //MÉTODOS
    //*******

    private void popularComboQuantidade(Spinner spinner){

        List<String> itensArray = new ArrayList<>();
        itensArray.add(getString(R.string.selecione));

        for(int i = 1; i <= 999; i++){
            itensArray.add(String.valueOf(i));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                                                          android.R.layout.simple_spinner_item,
                                                          itensArray);
        popularCombo(spinner ,adapter);

    }

    private void popularCombosValorFixo(Spinner spinner, int idLista){

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                                                                              idLista,
                                                                              android.R.layout.simple_spinner_item);


        popularCombo(spinner ,adapter);
    }

    private void popularCombo(Spinner spinner,  ArrayAdapter adapter){
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }



    private void aplicarMascaraMoeda(final EditText campo, final String simboloMoeda, final List<EditText> listaCamposcalculados){

        campo.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                //ZERAR CAMPOS CALCULADOS
                for(EditText edt: listaCamposcalculados){
                    edt.setText(Constantes.VAZIO);
                }
            }

            private String current = "";

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().equals(current)) {

                    Locale myLocale = new Locale("pt", "BR");
                    String regex = "[R$ ,.]";

                    if( simboloMoeda.equals(Constantes.SIMBOLO_DOLAR)){
                        regex = "[U$ ,.]";
                        myLocale = Locale.US;
                    }

                    campo.removeTextChangedListener(this);
                    String cleanString = s.toString().replaceAll(regex, "");

                    Double parsed = Double.parseDouble(cleanString);
                    String formatted = NumberFormat.getCurrencyInstance(myLocale).format((parsed / 100));

                    current = formatted;
                    campo.setText(formatted);
                    campo.setSelection(formatted.length());
                    campo.addTextChangedListener(this);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }

}


