package com.atimports.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.atimports.R;
import com.atimports.constantes.Constantes;
import com.atimports.utils.Utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AddActivity extends AppCompatActivity {

    Spinner spnCondicoes;
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
    EditText etValorFreteTransportadora;
    EditText etDataOrdem;

    TextView tvMedidaFinal;
    TextView tvFreteUsaBrasil;
    TextView tvCustoTotal;
    TextView tvCustoUnidade;
    TextView tvLucroTotal;
    TextView tvLucroUnidade;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        inicializarViews();

        aplicarMascaraMoeda(etCotacaoDolar, Utils.LOCALE_BRASIL);
        aplicarMascaraMoeda(etBaseFreteDolar, Utils.LOCALE_USA);
        aplicarMascaraMoeda(etValorLanceDolar, Utils.LOCALE_USA);
        aplicarMascaraMoeda(etValorComissaoFornecedorDolar, Utils.LOCALE_USA);
        aplicarMascaraMoeda(etValorFreteFornecedorDolar, Utils.LOCALE_USA);
        aplicarMascaraMoeda(etValorTaxaCambioReal, Utils.LOCALE_BRASIL);
        aplicarMascaraMoeda(etValorVendaUnidade, Utils.LOCALE_BRASIL);
        aplicarMascaraMoeda(etComissaoRevendedor,Utils.LOCALE_BRASIL);
        aplicarMascaraMoeda(etValorFreteRevendedor, Utils.LOCALE_BRASIL);
        aplicarMascaraMoeda(etValorFreteTransportadora, Utils.LOCALE_BRASIL);

        popularComboValorFixo(spnCondicoes, R.array.condicoes);
        popularComboValorFixo(spnMedida, R.array.medidas);
        popularComboValorFixo(spnStatusOrdem, R.array.status_ordem);
        popularComboQuantidade(spnQtd);

        calcularValorBaseadoNaCotacaoDolar(etCotacaoDolar, etBaseFreteDolar, Utils.LOCALE_USA, etBaseFreteRealCalculado, Utils.LOCALE_BRASIL);
        calcularValorBaseadoNaCotacaoDolar(etCotacaoDolar, etValorLanceDolar, Utils.LOCALE_USA, etValorLanceRealCalculado, Utils.LOCALE_BRASIL);
        calcularValorBaseadoNaCotacaoDolar(etCotacaoDolar, etValorComissaoFornecedorDolar, Utils.LOCALE_USA, etValorComissaoFornecedorRealCalculado, Utils.LOCALE_BRASIL);
        calcularValorBaseadoNaCotacaoDolar(etCotacaoDolar, etValorFreteFornecedorDolar, Utils.LOCALE_USA, etValorFreteFornecedorRealCalculado, Utils.LOCALE_BRASIL);
        calcularValorBaseadoNaCotacaoDolar(etCotacaoDolar, etValorTaxaCambioReal, Utils.LOCALE_BRASIL, etValorTaxaCambioDolarCalculado, Utils.LOCALE_USA);

        calcularValorBaseadoNaCotacaoDolar(etBaseFreteDolar, etBaseFreteDolar, Utils.LOCALE_USA, etBaseFreteRealCalculado, Utils.LOCALE_BRASIL);
        calcularValorBaseadoNaCotacaoDolar(etValorLanceDolar, etValorLanceDolar, Utils.LOCALE_USA, etValorLanceRealCalculado, Utils.LOCALE_BRASIL);
        calcularValorBaseadoNaCotacaoDolar(etValorComissaoFornecedorDolar, etValorComissaoFornecedorDolar, Utils.LOCALE_USA, etValorComissaoFornecedorRealCalculado, Utils.LOCALE_BRASIL);
        calcularValorBaseadoNaCotacaoDolar(etValorFreteFornecedorDolar, etValorFreteFornecedorDolar, Utils.LOCALE_USA, etValorFreteFornecedorRealCalculado, Utils.LOCALE_BRASIL);
        calcularValorBaseadoNaCotacaoDolar(etValorTaxaCambioReal, etValorTaxaCambioReal, Utils.LOCALE_BRASIL, etValorTaxaCambioDolarCalculado, Utils.LOCALE_USA);

        calcularMedidasDePeso(etMedidaInicial);

        calcularFreteUsaBrasil(etCotacaoDolar);
        calcularFreteUsaBrasil(etBaseFreteDolar);
        calcularFreteUsaBrasil(etMedidaInicial);

        Utils.criarDatePickerDialog(etDataOrdem, etDataOrdem, AddActivity.this);

        calcularCustoTotal(etCotacaoDolar);
        calcularCustoTotal(etValorLanceRealCalculado);
        calcularCustoTotal(tvFreteUsaBrasil);
        calcularCustoTotal(etValorComissaoFornecedorRealCalculado);
        calcularCustoTotal(etValorFreteFornecedorRealCalculado);
        calcularCustoTotal(etValorTaxaCambioReal);
        calcularCustoTotal(etValorFreteTransportadora);
        calcularCustoTotal(etComissaoRevendedor);
        calcularCustoTotal(etValorFreteRevendedor);

        calcularCustoUnidade(tvCustoTotal);

        calcularValorLucroUnidade(etValorVendaUnidade);

        calcularValorLucroUnidade(tvCustoUnidade);
        calcularValorLucroTotal(tvLucroUnidade);


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

        spnQtd.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                calcularCustoUnidade();
                calcularValorLucroTotal();
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });






    }//FIM onCreate


    //**************************************************************************************************************************************************************
    //**************************************************************************************************************************************************************
    //**************************************************************************************************************************************************************
    //**************************************************************************************************************************************************************
    //**************************************************************************************************************************************************************


    //MÉTODOS


    private void inicializarViews(){

        spnCondicoes = findViewById(R.id.spn_condicoes);
        spnQtd = findViewById(R.id.spn_qtd);
        spnMedida = findViewById(R.id.spn_medida);
        spnStatusOrdem = findViewById(R.id.spn_status_ordem);

        etCotacaoDolar =  findViewById(R.id.et_cotacao_dolar);
        etBaseFreteDolar = findViewById(R.id.et_base_frete_dolar);
        etBaseFreteRealCalculado = findViewById(R.id.et_base_frete_real);
        etMedidaInicial = findViewById(R.id.et_medida_inicial);
        etMedidaFinal = findViewById(R.id.et_medida_final);
        etValorLanceDolar = findViewById(R.id.et_valor_lance_dolar);
        etValorLanceRealCalculado = findViewById(R.id.et_valor_lance_real_calculado);
        etValorComissaoFornecedorDolar = findViewById(R.id.et_valor_comissao_fornecedor_dolar);
        etValorComissaoFornecedorRealCalculado = findViewById(R.id.et_valor_comissao_fornecedor_real_calculado);
        etValorFreteFornecedorDolar = findViewById(R.id.et_valor_frete_fornecedor_dolar);
        etValorFreteFornecedorRealCalculado = findViewById(R.id.et_valor_frete_fornecedor_real_calculado);
        etValorTaxaCambioReal = findViewById(R.id.et_valor_taxa_cambio_real);
        etValorTaxaCambioDolarCalculado = findViewById(R.id.et_valor_taxa_cambio_dolar_calculado);
        etValorVendaUnidade = findViewById(R.id.et_valor_venda_unidade);
        etComissaoRevendedor = findViewById(R.id.et_comissao_revendedor);
        etValorFreteRevendedor = findViewById(R.id.et_frete_revendedor);
        etValorFreteTransportadora = findViewById(R.id.et_valor_frete_transportadora);
        etDataOrdem = findViewById(R.id.et_data_ordem);

        tvMedidaFinal = findViewById(R.id.tv_medida_final);
        tvFreteUsaBrasil = findViewById(R.id.tv_frete_usa_brasil);
        tvCustoTotal = findViewById(R.id.tv_custo_total);
        tvCustoUnidade = findViewById(R.id.tv_custo_unidade);
        tvLucroTotal = findViewById(R.id.tv_lucro_total);
        tvLucroUnidade = findViewById(R.id.tv_lucro_unidade);

        etMedidaInicial.setText(Constantes.PESO_DEFAULT_1_KG);
        etMedidaFinal.setText(Constantes.PESO_DEFAULT_1_KG_EM_LIBRA);

    }


    public static void aplicarMascaraMoeda(final EditText campo, final Locale locale){

        campo.addTextChangedListener(new TextWatcher(){

            private String current = Constantes.VAZIO;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence valorTexto, int start, int before, int count) {

                if(!valorTexto.toString().equals(current)) {

                    campo.removeTextChangedListener(this);

                    String valorMonetarioPuro = Utils.retirarMascaraMoeda(valorTexto.toString());

                    BigDecimal valorMonetario = new BigDecimal(valorMonetarioPuro);
                    valorMonetario = valorMonetario.divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_EVEN);

                    String valorMonetarioFormatado = Utils.retornaValorMontarioComMascara(valorMonetario, locale);

                    campo.setText(valorMonetarioFormatado);
                    current = valorMonetarioFormatado;
                    campo.setSelection(valorMonetarioFormatado.length());
                    campo.addTextChangedListener(this);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }

        });
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

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }

    private void popularComboValorFixo(Spinner spinner, int idLista){

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                idLista,
                android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }



    private void calcularValorBaseadoNaCotacaoDolar(final TextView campoEvento,
                                                    final TextView campoBase,
                                                    final Locale localeBase,
                                                    final TextView campoResultado,
                                                    final Locale localeResultado){

        campoEvento.addTextChangedListener(new TextWatcher(){

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence valorTexto, int start, int before, int count) {
                if(!Utils.isBlank(etCotacaoDolar.getText().toString()) && !Utils.isBlank(campoBase.getText().toString())){

                    try{
                        BigDecimal valorCotacaoDolar = Utils.retornarValorMonetario(etCotacaoDolar.getText().toString(), Utils.LOCALE_BRASIL);
                        BigDecimal valorCampoBase = Utils.retornarValorMonetario(campoBase.getText().toString(), localeBase);
                        BigDecimal valorCampoResultado;

                        if(localeResultado.equals(Utils.LOCALE_BRASIL)){
                            valorCampoResultado  = valorCotacaoDolar.multiply(valorCampoBase).setScale(2,BigDecimal.ROUND_HALF_EVEN);
                        }
                        else{
                            valorCampoResultado  = valorCampoBase.divide(valorCotacaoDolar, 2, BigDecimal.ROUND_HALF_EVEN);
                        }

                        campoResultado.setText(Utils.retornaValorMontarioComMascara(valorCampoResultado, localeResultado));

                    }
                    catch(NumberFormatException | ArithmeticException e){
                        campoResultado.setText(Constantes.VAZIO);
                    }
                }
                else{
                    campoResultado.setText(Constantes.VAZIO);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }

        });
    }



    private void calcularFreteUsaBrasil(TextView tView){
        tView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calcularFreteUsaBrasil();
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void calcularFreteUsaBrasil(){
        String tipoMedidaSelecionada = spnMedida.getSelectedItem().toString();
        String medidaKG;

        if(tipoMedidaSelecionada.equals(getString(R.string.medida_kg))){
            medidaKG = etMedidaInicial.getText().toString();
        }
        else{
            medidaKG = etMedidaFinal.getText().toString();
        }

        if(!Utils.isBlank(medidaKG) && !Utils.isBlank(etBaseFreteRealCalculado.getText().toString())){

            try{
                BigDecimal valorFreteRealCalculado = Utils.retornarValorMonetario(etBaseFreteRealCalculado.getText().toString(), Utils.LOCALE_BRASIL);
                BigDecimal valorMedidaKG = new BigDecimal(medidaKG.replace(Constantes.VIRGULA, Constantes.PONTO));
                BigDecimal valorFreteUsaBrasil  = valorFreteRealCalculado.multiply(valorMedidaKG).setScale(2,BigDecimal.ROUND_HALF_EVEN);
                tvFreteUsaBrasil.setText(Utils.retornaValorMontarioComMascara(valorFreteUsaBrasil, Utils.LOCALE_BRASIL));

            }
            catch(Exception e){
                tvFreteUsaBrasil.setText(Constantes.VAZIO);
            }
        }
        else{
            tvFreteUsaBrasil.setText(Constantes.VAZIO);
        }
    }


    private void calcularMedidasDePeso(TextView tView){
        tView.addTextChangedListener(new TextWatcher() {
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
    }





    private void calcularMedidasDePeso(){

        String valorMedidaInicial = etMedidaInicial.getText().toString();

        if(Utils.isBlank(valorMedidaInicial)){
            etMedidaFinal.setText(Constantes.VAZIO);
        }
        else{

            try{
                Double valorMedidaInicialDouble = Double.parseDouble(valorMedidaInicial.replace(Constantes.VIRGULA, Constantes.PONTO));
                Double valorMedidaFinalDouble;
                Double valor1KGEmLibra = Double.parseDouble(Constantes.PESO_DEFAULT_1_KG_EM_LIBRA.replace(Constantes.VIRGULA, Constantes.PONTO));

                String tipoMedidaSelecionada = spnMedida.getSelectedItem().toString();

                if(tipoMedidaSelecionada.equals(getString(R.string.medida_kg))){
                    valorMedidaFinalDouble = valorMedidaInicialDouble * valor1KGEmLibra;
                    tvMedidaFinal.setText(R.string.medida_libra);
                }
                else{
                    valorMedidaFinalDouble = valorMedidaInicialDouble / valor1KGEmLibra;
                    tvMedidaFinal.setText(R.string.medida_kg);

                }

                String valorFinalFormatado = String.format("%.2f", valorMedidaFinalDouble);
                valorFinalFormatado = valorFinalFormatado.replace(Constantes.PONTO, Constantes.VIRGULA);
                etMedidaFinal.setText(valorFinalFormatado);
            }
            catch(NumberFormatException e){
                etMedidaInicial.setText(Constantes.VAZIO);
                etMedidaFinal.setText(Constantes.VAZIO);
            }
        }
    }


    private void calcularCustoTotal(TextView campo){

        campo.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(Utils.isValorParaCalculoValido(etValorLanceRealCalculado.getText().toString())){

                    BigDecimal valorLance;

                    BigDecimal valorFreteUsaBrasil = BigDecimal.ZERO;
                    BigDecimal valorComissaoFornecedor = BigDecimal.ZERO;
                    BigDecimal valorFreteFornecedor = BigDecimal.ZERO;
                    BigDecimal valorTaxaCambio = BigDecimal.ZERO;
                    BigDecimal valorFreteTransportadora = BigDecimal.ZERO;
                    BigDecimal valorComissaoRevendedor = BigDecimal.ZERO;
                    BigDecimal valorFreteRevendedor = BigDecimal.ZERO;

                    BigDecimal valorCustoTotal = BigDecimal.ZERO;

                    valorLance = Utils.retornarValorMonetario(etValorLanceRealCalculado.getText().toString(), Utils.LOCALE_BRASIL);

                    if(Utils.isValorParaCalculoValido(tvFreteUsaBrasil.getText().toString())){
                        valorFreteUsaBrasil = Utils.retornarValorMonetario(tvFreteUsaBrasil.getText().toString(), Utils.LOCALE_BRASIL);
                    }

                    if(Utils.isValorParaCalculoValido(etValorComissaoFornecedorRealCalculado.getText().toString())){
                        valorComissaoFornecedor = Utils.retornarValorMonetario(etValorComissaoFornecedorRealCalculado.getText().toString(), Utils.LOCALE_BRASIL);
                    }

                    if(Utils.isValorParaCalculoValido(etValorFreteFornecedorRealCalculado.getText().toString())){
                        valorFreteFornecedor = Utils.retornarValorMonetario(etValorFreteFornecedorRealCalculado.getText().toString(), Utils.LOCALE_BRASIL);
                    }

                    if(Utils.isValorParaCalculoValido(etValorTaxaCambioReal.getText().toString())){
                        valorTaxaCambio = Utils.retornarValorMonetario(etValorTaxaCambioReal.getText().toString(), Utils.LOCALE_BRASIL);
                    }

                    if(Utils.isValorParaCalculoValido(etValorFreteTransportadora.getText().toString())){
                        valorFreteTransportadora = Utils.retornarValorMonetario(etValorFreteTransportadora.getText().toString(), Utils.LOCALE_BRASIL);
                    }


                    String qtd = spnQtd.getSelectedItem().toString();

                    if(Utils.isValorParaCalculoValido(etComissaoRevendedor.getText().toString()) && !qtd.equals(getString(R.string.selecione)) ){
                        valorComissaoRevendedor = Utils.retornarValorMonetario(etComissaoRevendedor.getText().toString(), Utils.LOCALE_BRASIL);
                        valorComissaoRevendedor = valorComissaoRevendedor.multiply(new BigDecimal(qtd)).setScale(2 ,BigDecimal.ROUND_HALF_UP);
                    }

                    if(Utils.isValorParaCalculoValido(etValorFreteRevendedor.getText().toString()) && !qtd.equals(getString(R.string.selecione)) ){
                        valorFreteRevendedor = Utils.retornarValorMonetario(etValorFreteRevendedor.getText().toString(), Utils.LOCALE_BRASIL);
                        valorFreteRevendedor = valorFreteRevendedor.multiply(new BigDecimal(qtd)).setScale(2 ,BigDecimal.ROUND_HALF_UP);
                    }

                    valorCustoTotal = valorCustoTotal.add(valorLance)
                            .add(valorFreteUsaBrasil)
                            .add(valorComissaoFornecedor)
                            .add(valorFreteFornecedor)
                            .add(valorTaxaCambio)
                            .add(valorFreteTransportadora)
                            .add(valorComissaoRevendedor)
                            .add(valorFreteRevendedor);

                    tvCustoTotal.setText(Utils.retornaValorMontarioComMascara(valorCustoTotal, Utils.LOCALE_BRASIL));

                }
                else{
                    tvCustoTotal.setText(Constantes.VAZIO);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }

        });
    }



    private void calcularCustoUnidade(TextView tView){
        tView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calcularCustoUnidade();
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }


    private void calcularCustoUnidade(){

        String qtd = spnQtd.getSelectedItem().toString();

        if(Utils.isValorParaCalculoValido(tvCustoTotal.getText().toString()) && !qtd.equals(getString(R.string.selecione)) ){

            BigDecimal valorCusto = Utils.retornarValorMonetario(tvCustoTotal.getText().toString(), Utils.LOCALE_BRASIL);
            valorCusto = valorCusto.divide(new BigDecimal(qtd),2, BigDecimal.ROUND_HALF_UP);
            tvCustoUnidade.setText(Utils.retornaValorMontarioComMascara(valorCusto, Utils.LOCALE_BRASIL));
        }
        else{
            tvCustoUnidade.setText(Constantes.VAZIO);
        }
    }


    private void calcularValorLucroUnidade(TextView tView){

        tView.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(Utils.isValorParaCalculoValido(tvCustoUnidade.getText().toString()) && Utils.isValorParaCalculoValido(etValorVendaUnidade.getText().toString() )){

                    BigDecimal valorVendaUnidade = Utils.retornarValorMonetario(etValorVendaUnidade.getText().toString(), Utils.LOCALE_BRASIL);
                    BigDecimal valorCustoUnidade = Utils.retornarValorMonetario(tvCustoUnidade.getText().toString(), Utils.LOCALE_BRASIL);

                    BigDecimal valorLucroUnidade = valorVendaUnidade.subtract(valorCustoUnidade);

                    if(valorLucroUnidade.compareTo(BigDecimal.ZERO) < 0 ){
                        tvLucroUnidade.setTextColor(getResources().getColor(R.color.dark_red));
                    }
                    else{
                        tvLucroUnidade.setTextColor(getResources().getColor(R.color.material_green));
                    }

                    tvLucroUnidade.setText(Utils.retornaValorMontarioComMascara(valorLucroUnidade, Utils.LOCALE_BRASIL));
                }
                else{
                    tvLucroUnidade.setText(Constantes.VAZIO);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }

        });
    }

    private void calcularValorLucroTotal(TextView tView){
        tView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calcularValorLucroTotal();
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }



    private void calcularValorLucroTotal(){

        String qtd = spnQtd.getSelectedItem().toString();

        if(Utils.isValorParaCalculoValido(tvLucroUnidade.getText().toString()) && !qtd.equals(getString(R.string.selecione)) ){

            BigDecimal valorLucro = Utils.retornarValorMonetario(tvLucroUnidade.getText().toString(), Utils.LOCALE_BRASIL);
            valorLucro = valorLucro.multiply(new BigDecimal(qtd)).setScale(2, BigDecimal.ROUND_HALF_UP);

            if(valorLucro.compareTo(BigDecimal.ZERO) < 0 ){
                tvLucroTotal.setTextColor(getResources().getColor(R.color.dark_red));
            }
            else{
                tvLucroTotal.setTextColor(getResources().getColor(R.color.material_green));
            }

            tvLucroTotal.setText(Utils.retornaValorMontarioComMascara(valorLucro, Utils.LOCALE_BRASIL));
        }
        else{
            tvLucroTotal.setText(Constantes.VAZIO);
        }
    }

}