package com.atimports.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.atimports.R;
import com.atimports.constantes.Constantes;
import com.atimports.utils.Utils;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

public class AddActivity extends AppCompatActivity {

    Spinner spnConditions;
    Spinner spnQtd;
    Spinner spnMedida;

    EditText etCotacaoDolar;
    EditText etBaseFreteDolar;
    EditText etBaseFreteRealCalculado;

    EditText etMedidaInicial;
    EditText etMedidaFinal;
    TextView tvMedidaFinal;

    Switch   swStatusOrdem;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        spnConditions    = findViewById(R.id.spn_conditions);
        spnQtd           = findViewById(R.id.spn_qtd);
        spnMedida        = findViewById(R.id.spn_medida);

        etCotacaoDolar            =  findViewById(R.id.et_cotacao_dolar);
        etBaseFreteDolar          =  findViewById(R.id.et_base_frete_dolar);
        etBaseFreteRealCalculado  =  findViewById(R.id.et_base_frete_real);
        etMedidaInicial           =  findViewById(R.id.et_medida_inicial);
        etMedidaFinal             =  findViewById(R.id.et_medida_final);
        tvMedidaFinal             =  findViewById(R.id.tv_medida_final);


        swStatusOrdem   =  findViewById(R.id.sw_status_ordem);

        List<EditText> listaCamposCalculados = new ArrayList<>();
        listaCamposCalculados.add(etBaseFreteRealCalculado);

        popularCombosValorFixo(spnConditions, R.array.conditions);
        popularCombosValorFixo(spnMedida, R.array.medidas);
        popularComboQuantidade(spnQtd);

        aplicarMascaraMoeda(etCotacaoDolar,   Constantes.SIMBOLO_REAL,  listaCamposCalculados);
        aplicarMascaraMoeda(etBaseFreteDolar, Constantes.SIMBOLO_DOLAR, listaCamposCalculados);


        //CÁLCULO DA BASE DA FRETE
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


        //CALCULO DAS MEDIDAS DE PESO
        etMedidaInicial.setText(Constantes.PESO_DEFAULT_1_KG);
        etMedidaFinal.setText(Constantes.PESO_DEFAULT_1_KG_EM_LIBRA);


        etMedidaInicial.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calcularMedidasDePeso();
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });




        spnMedida.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                calcularMedidasDePeso();
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });





        //STATUS DE PAGAMENTO DA ORDEM DE COMPRA
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

    private void calcularMedidasDePeso(){

        String valorMedidaInicial = etMedidaInicial.getText().toString();

        if(Utils.isBlank(valorMedidaInicial)){
            etMedidaFinal.setText(Constantes.VAZIO);
        }
        else{

            try{
                Double valorMedidaInicialDouble = Double.parseDouble(valorMedidaInicial.replace(Constantes.VIRGULA, Constantes.PONTO));
                Double valorMedidaFinallDouble = 0.0;
                Double valor1KGEmLibra = Double.parseDouble(Constantes.PESO_DEFAULT_1_KG_EM_LIBRA.replace(Constantes.VIRGULA, Constantes.PONTO));

                String tipoMedidaSelecionada = spnMedida.getSelectedItem().toString();

                if(tipoMedidaSelecionada.equals(getString(R.string.medida_kg))){
                    valorMedidaFinallDouble = valorMedidaInicialDouble * valor1KGEmLibra;
                    tvMedidaFinal.setText(R.string.medida_libra);
                }
                else{
                    valorMedidaFinallDouble = valorMedidaInicialDouble / valor1KGEmLibra;
                    tvMedidaFinal.setText(R.string.medida_kg);

                }

                String valorFinalFormatado = String.format("%.2f", valorMedidaFinallDouble);
                valorFinalFormatado = valorFinalFormatado.replace(Constantes.PONTO, Constantes.VIRGULA);
                etMedidaFinal.setText(valorFinalFormatado);
            }
            catch(NumberFormatException e){
                etMedidaInicial.setText(Constantes.VAZIO);
                etMedidaFinal.setText(Constantes.VAZIO);
            }
        }
    }

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


