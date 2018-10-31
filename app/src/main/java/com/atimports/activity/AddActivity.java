package com.atimports.activity;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.atimports.R;
import com.atimports.constantes.Constantes;
import com.atimports.utils.Utils;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AddActivity extends AppCompatActivity {

    Spinner spnConditions;
    Spinner spnQtd;
    Spinner spnMedida;
    Spinner spnStatusOrdem;

    EditText etCotacaoDolar;

    EditText etBaseFreteDolar;
    EditText etBaseFreteRealCalculado;

    EditText etMedidaInicial;
    EditText etMedidaFinal;

    EditText etValorLanceDolar;
    EditText etValorLanceRealCalculado;

    EditText etValorComissaoFornecedorDolar;
    EditText etValorComissaoFornecedorRealCalculado;

    EditText etValorFreteFornecedorDolar;
    EditText etValorFreteFornecedorRealCalculado;

    EditText etValorTaxaCambioReal;
    EditText etValorTaxaCambioDolarCalculado;

    EditText etValorVendaUnidade;
    EditText etComissaoRevendedor;
    EditText etValorFreteRevendedor;

    EditText etDataOrdem;

    TextView tvMedidaFinal;
    TextView tvFreteUsaBrasil;



    //private DatePickerDialog

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);


        //***********************
        //INICIALIZAÇÃO DAS VIEWS
        //***********************
        spnConditions    = findViewById(R.id.spn_conditions);
        spnQtd           = findViewById(R.id.spn_qtd);
        spnMedida        = findViewById(R.id.spn_medida);
        spnStatusOrdem   = findViewById(R.id.spn_status_ordem);



        etCotacaoDolar            =  findViewById(R.id.et_cotacao_dolar);

        etBaseFreteDolar          =  findViewById(R.id.et_base_frete_dolar);
        etBaseFreteRealCalculado  =  findViewById(R.id.et_base_frete_real);

        etMedidaInicial           =  findViewById(R.id.et_medida_inicial);
        etMedidaFinal             =  findViewById(R.id.et_medida_final);

        etValorLanceDolar         =  findViewById(R.id.et_valor_lance_dolar);
        etValorLanceRealCalculado =  findViewById(R.id.et_valor_lance_real_calculado);

        etValorComissaoFornecedorDolar =  findViewById(R.id.et_valor_comissao_fornecedor_dolar);
        etValorComissaoFornecedorRealCalculado =  findViewById(R.id.et_valor_comissao_fornecedor_real_calculado);

        etValorFreteFornecedorDolar =  findViewById(R.id.et_valor_frete_fornecedor_dolar);
        etValorFreteFornecedorRealCalculado =  findViewById(R.id.et_valor_frete_fornecedor_real_calculado);

        etValorTaxaCambioReal =  findViewById(R.id.et_valor_taxa_cambio_real);
        etValorTaxaCambioDolarCalculado =  findViewById(R.id.et_valor_taxa_cambio_dolar_calculado);

        etValorVendaUnidade =  findViewById(R.id.et_valor_venda_unidade);
        etComissaoRevendedor =  findViewById(R.id.et_comissao_revendedor);
        etValorFreteRevendedor =  findViewById(R.id.et_frete_revendedor);


        etDataOrdem               =  findViewById(R.id.et_data_ordem);

        tvMedidaFinal             =  findViewById(R.id.tv_medida_final);
        tvFreteUsaBrasil          =  findViewById(R.id.tv_frete_usa_brasil);



        //***************************
        //FIM INICIALIZAÇÃO DAS VIEWS
        //***************************

        //**************
        //POPULAR COMBOS
        //**************
        popularCombosValorFixo(spnConditions,  R.array.conditions);
        popularCombosValorFixo(spnMedida,      R.array.medidas);
        popularCombosValorFixo(spnStatusOrdem, R.array.status_ordem);
        popularComboQuantidade(spnQtd);
        //******************
        //FIM POPULAR COMBOS
        //******************


        //****************
        //APLICAR MÁSCARAS
        //****************
        aplicarMascaraMoeda(etCotacaoDolar,    Constantes.SIMBOLO_REAL);
        aplicarMascaraMoeda(etBaseFreteDolar,  Constantes.SIMBOLO_DOLAR);
        aplicarMascaraMoeda(etValorLanceDolar, Constantes.SIMBOLO_DOLAR);
        aplicarMascaraMoeda(etValorComissaoFornecedorDolar, Constantes.SIMBOLO_DOLAR);
        aplicarMascaraMoeda(etValorFreteFornecedorDolar, Constantes.SIMBOLO_DOLAR);
        aplicarMascaraMoeda(etValorTaxaCambioReal, Constantes.SIMBOLO_REAL);
        aplicarMascaraMoeda(etValorVendaUnidade, Constantes.SIMBOLO_REAL);
        aplicarMascaraMoeda(etComissaoRevendedor, Constantes.SIMBOLO_REAL);
        aplicarMascaraMoeda(etValorFreteRevendedor, Constantes.SIMBOLO_REAL);



        //********************
        //FIM APLICAR MÁSCARAS
        //********************


        //**********************************************
        //CÁLCULO DE CAMPOS BASEADOS NA COTAÇÃO DO DOLAR
        //**********************************************
        etCotacaoDolar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                //CHAMA TODOS OS MÉTODOS DE CÁLCULO QUE USAM COMO BASE A COTAÇÃO DO DOLAR

                calcularValorBaseadoNaCotacaoDolar(etBaseFreteDolar,  Constantes.SIMBOLO_DOLAR, etBaseFreteRealCalculado,  Constantes.SIMBOLO_REAL);
                calcularValorBaseadoNaCotacaoDolar(etValorLanceDolar, Constantes.SIMBOLO_DOLAR, etValorLanceRealCalculado, Constantes.SIMBOLO_REAL);
                calcularFreteUsaBrasil();
                calcularValorBaseadoNaCotacaoDolar(etValorComissaoFornecedorDolar, Constantes.SIMBOLO_DOLAR, etValorComissaoFornecedorRealCalculado, Constantes.SIMBOLO_REAL);
                calcularValorBaseadoNaCotacaoDolar(etValorFreteFornecedorDolar, Constantes.SIMBOLO_DOLAR, etValorFreteFornecedorRealCalculado, Constantes.SIMBOLO_REAL);
                calcularValorBaseadoNaCotacaoDolar(etValorTaxaCambioReal, Constantes.SIMBOLO_REAL, etValorTaxaCambioDolarCalculado, Constantes.SIMBOLO_DOLAR);


            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        //**************************************************
        //FIM CÁLCULO DE CAMPOS BASEADOS NA COTAÇÃO DO DOLAR
        //**************************************************



        //************************
        //CÁLCULO DA BASE DA FRETE
        //************************
        etBaseFreteDolar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calcularValorBaseadoNaCotacaoDolar(etBaseFreteDolar, Constantes.SIMBOLO_DOLAR, etBaseFreteRealCalculado, Constantes.SIMBOLO_REAL);
                calcularFreteUsaBrasil();
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        //*****************************
        //FIM CÁLCULO DA BASE DA FRETE
        //*****************************



        //***************************
        //CALCULO DAS MEDIDAS DE PESO
        //***************************
        etMedidaInicial.setText(Constantes.PESO_DEFAULT_1_KG);
        etMedidaFinal.setText(Constantes.PESO_DEFAULT_1_KG_EM_LIBRA);


        etMedidaInicial.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calcularMedidasDePeso();
                calcularFreteUsaBrasil();
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        spnMedida.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                calcularMedidasDePeso();
                calcularFreteUsaBrasil();
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        //*******************************
        //FIM CALCULO DAS MEDIDAS DE PESO
        //*******************************



        //*************************
        //CÁLCULO DO VALOR DO LANCE
        //*************************
        etValorLanceDolar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calcularValorBaseadoNaCotacaoDolar(etValorLanceDolar, Constantes.SIMBOLO_DOLAR, etValorLanceRealCalculado, Constantes.SIMBOLO_REAL);
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        //*****************************
        //FIM CÁLCULO DO VALOR DO LANCE
        //*****************************


        //********************************
        //DATE PICKER DIALOG - DATA ORDEM
        //********************************

        etDataOrdem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Calendar cal = Calendar.getInstance() ;
               int year = cal.get(Calendar.YEAR);
               int month = cal.get(Calendar.MONTH);
               int day = cal.get(Calendar.DAY_OF_MONTH);

               DatePickerDialog dpDataOrdemDialog = new DatePickerDialog(AddActivity.this,
                                                                                 android.R.style.Theme_DeviceDefault_Dialog,

                                                                                 new DatePickerDialog.OnDateSetListener() {
                                                                                    @Override
                                                                                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                                                                                        String dia = String.valueOf(dayOfMonth);
                                                                                        if(dia.length() < 2){
                                                                                            dia = "0" + dia;
                                                                                        }

                                                                                        String mes = String.valueOf(month + 1);
                                                                                        if(mes.length() < 2){
                                                                                            mes = "0" + mes;
                                                                                        }

                                                                                        etDataOrdem.setText(dia + "/" + mes + "/" + year);
                                                                                    }
                                                                                 },

                                                                                year, month, day
                                                                        );

               dpDataOrdemDialog.show();

            }
        });
        //***********************************
        //FIM DATE PICKER DIALOG - DATA ORDEM
        //***********************************

        //******************************************
        //CÁLCULO DO VALOR DO COMISSAO DO FORNECEDOR
        //******************************************
        etValorComissaoFornecedorDolar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calcularValorBaseadoNaCotacaoDolar(etValorComissaoFornecedorDolar, Constantes.SIMBOLO_DOLAR, etValorComissaoFornecedorRealCalculado, Constantes.SIMBOLO_REAL);
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        //**********************************************
        //FIM CÁLCULO DO VALOR DO COMISSAO DO FORNECEDOR
        //**********************************************

        //***************************************
        //CÁLCULO DO VALOR DO FRETE DO FORNECEDOR
        //***************************************
        etValorFreteFornecedorDolar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calcularValorBaseadoNaCotacaoDolar(etValorFreteFornecedorDolar, Constantes.SIMBOLO_DOLAR, etValorFreteFornecedorRealCalculado, Constantes.SIMBOLO_REAL);
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        //*************************************
        //FIM DO CÁLCULO DO FRETE DO FORNECEDOR
        //*************************************

        //************************************
        //CÁLCULO DO VALOR DAS TAXAS DE CÂMBIO
        //************************************
        etValorTaxaCambioReal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calcularValorBaseadoNaCotacaoDolar(etValorTaxaCambioReal, Constantes.SIMBOLO_REAL, etValorTaxaCambioDolarCalculado, Constantes.SIMBOLO_DOLAR);
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        //****************************************
        //FIM DO CÁLCULO VALOR DAS TAXAS DE CÂMBIO
        //****************************************



    }//FIM onCreate





    //**********************************************************************************************
    //MÉTODOS
    //**********************************************************************************************


    private void calcularValorBaseadoNaCotacaoDolar(EditText campoBase, String simboloMoedaBase, TextView campoResultado, String simboloMoedaResultado){

        if(!Utils.isBlank(etCotacaoDolar.getText().toString()) && !Utils.isBlank(campoBase.getText().toString())){

            try{
                BigDecimal valorCotacaoDolar   = new BigDecimal(Utils.desformatarMascaraMoeda(etCotacaoDolar.getText().toString(),   Constantes.SIMBOLO_REAL));
                BigDecimal valorCampoBase      = new BigDecimal(Utils.desformatarMascaraMoeda(campoBase.getText().toString(), simboloMoedaBase));

                BigDecimal valorCampoResultado;

                if(simboloMoedaBase.equals(Constantes.SIMBOLO_DOLAR)){
                    valorCampoResultado  = valorCotacaoDolar.multiply(valorCampoBase).setScale(2,BigDecimal.ROUND_HALF_UP);
                }
                else{
                    //valorCampoResultado  = valorCampoBase.divide(valorCotacaoDolar, 2, BigDecimal.ROUND_HALF_UP).setScale(2,BigDecimal.ROUND_HALF_UP);
                    valorCampoResultado  = valorCampoBase.divide(valorCotacaoDolar, 2, BigDecimal.ROUND_HALF_UP);
                }

                campoResultado.setText(Utils.formatarMascaraMoeda(valorCampoResultado.toString(), simboloMoedaResultado));

            }
            catch(NumberFormatException | ArithmeticException e){
                campoResultado.setText(Constantes.VAZIO);
            }
        }
        else{
            campoResultado.setText(Constantes.VAZIO);
        }

    }


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

    private void calcularFreteUsaBrasil(){

        String tipoMedidaSelecionada = spnMedida.getSelectedItem().toString();
        String medidaKG = null;

        if(tipoMedidaSelecionada.equals(getString(R.string.medida_kg))){
            medidaKG = etMedidaInicial.getText().toString();
        }
        else{
            medidaKG = etMedidaFinal.getText().toString();
        }

        if(!Utils.isBlank(medidaKG) && !Utils.isBlank(etBaseFreteRealCalculado.getText().toString())){

            try{
                BigDecimal valorFreteRealCalculado = new BigDecimal(Utils.desformatarMascaraMoeda(etBaseFreteRealCalculado.getText().toString(), Constantes.SIMBOLO_REAL));
                BigDecimal valorMedidaKG = new BigDecimal(medidaKG.replace(Constantes.VIRGULA, Constantes.PONTO));
                BigDecimal valorFreteUsaBrasil  = valorFreteRealCalculado.multiply(valorMedidaKG).setScale(2,BigDecimal.ROUND_HALF_UP);
                tvFreteUsaBrasil.setText(Utils.formatarMascaraMoeda(valorFreteUsaBrasil.toString(), Constantes.SIMBOLO_REAL));

            }
            catch(NumberFormatException | ArithmeticException e){
                tvFreteUsaBrasil.setText(Constantes.VAZIO);
            }
        }
        else{
            tvFreteUsaBrasil.setText(Constantes.VAZIO);
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



    private void aplicarMascaraMoeda(final EditText campo, final String simboloMoeda){
                                     campo.addTextChangedListener(new TextWatcher(){

            private String current = Constantes.VAZIO;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
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


