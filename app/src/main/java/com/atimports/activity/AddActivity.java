package com.atimports.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.atimports.R;
import com.atimports.constantes.Constantes;
import com.atimports.model.Lote;
import com.atimports.repository.LoteRepository;
import com.atimports.utils.PhotoUtils;
import com.atimports.utils.Utils;
import com.atimports.validator.FieldValidator;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AddActivity extends AppCompatActivity {


    TextView tvCondicoes;
    Spinner spnCondicoes;
    TextView errorMsgCondicoes;

    TextView tvQtd;
    Spinner spnQtd;
    Spinner spnVendidos;
    TextView errorMsgQtd;

    Spinner spnMedida;
    Spinner spnStatusOrdem;

    TextView tvCotacaoDolar;
    EditText etCotacaoDolar;
    TextView errorMsgCotacaoDolar;

    TextView tvBaseFreteDolar;
    EditText etBaseFreteDolar;
    EditText etBaseFreteRealCalculado;
    TextView errorMsgFreteDolar;

    TextView tvProduto;
    EditText etProduto;
    TextView errorMsgProduto;

    EditText etOrdemCompra;

    EditText etVendedor;
    EditText etEmailVendedor;
    EditText etFornecedor;


    EditText etMedidaInicial;
    EditText etMedidaFinal;

    TextView tvValorLanceDolar;
    EditText etValorLanceDolar;
    EditText etValorLanceRealCalculado;
    TextView errorMsgValorLanceDolar;

    EditText etValorComissaoFornecedorDolar;
    EditText etValorComissaoFornecedorRealCalculado;
    EditText etValorFreteFornecedorDolar;
    EditText etValorFreteFornecedorRealCalculado;
    EditText etValorTaxaCambioReal;
    EditText etValorTaxaCambioDolarCalculado;

    TextView tvValorVendaUnidade;
    EditText etValorVendaUnidade;
    TextView errorMsgValorVendaUnidade;

    EditText etComissaoRevendedor;
    EditText etValorFreteRevendedor;
    EditText etValorFreteTransportadora;
    EditText etGastosExtras;
    EditText etPagamentosRecebidos;
    EditText etDataOrdem;

    TextView tvMedidaFinal;
    TextView tvFreteUsaBrasil;
    TextView tvCustoTotal;
    TextView tvCustoUnidade;
    TextView tvLucroTotal;
    TextView tvLucroUnidade;

    ImageView ivFotoProduto;
    ImageButton ibPickPhoto;
    ImageButton ibRemovePhoto;

    Button btSalvar;

    private LoteRepository loteRepository;

    private Lote detalheLote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        inicializarViews();

        removePhoto();
        pickPhoto();

        aplicarMascaras();

        popularComboValorFixo(spnCondicoes, R.array.condicoes);
        popularComboValorFixo(spnMedida, R.array.medidas);
        popularComboValorFixo(spnStatusOrdem, R.array.status_ordem);
        popularComboQuantidade(spnVendidos);
        popularComboQuantidade(spnQtd);

        calcularValorBaseadoNaCotacaoDolar(etCotacaoDolar, etBaseFreteDolar, Constantes.LOCALE_USA, etBaseFreteRealCalculado, Constantes.LOCALE_BRASIL);
        calcularValorBaseadoNaCotacaoDolar(etCotacaoDolar, etValorLanceDolar, Constantes.LOCALE_USA, etValorLanceRealCalculado, Constantes.LOCALE_BRASIL);
        calcularValorBaseadoNaCotacaoDolar(etCotacaoDolar, etValorComissaoFornecedorDolar, Constantes.LOCALE_USA, etValorComissaoFornecedorRealCalculado, Constantes.LOCALE_BRASIL);
        calcularValorBaseadoNaCotacaoDolar(etCotacaoDolar, etValorFreteFornecedorDolar, Constantes.LOCALE_USA, etValorFreteFornecedorRealCalculado, Constantes.LOCALE_BRASIL);
        calcularValorBaseadoNaCotacaoDolar(etCotacaoDolar, etValorTaxaCambioReal, Constantes.LOCALE_BRASIL, etValorTaxaCambioDolarCalculado, Constantes.LOCALE_USA);

        calcularValorBaseadoNaCotacaoDolar(etBaseFreteDolar, etBaseFreteDolar, Constantes.LOCALE_USA, etBaseFreteRealCalculado, Constantes.LOCALE_BRASIL);
        calcularValorBaseadoNaCotacaoDolar(etValorLanceDolar, etValorLanceDolar, Constantes.LOCALE_USA, etValorLanceRealCalculado, Constantes.LOCALE_BRASIL);
        calcularValorBaseadoNaCotacaoDolar(etValorComissaoFornecedorDolar, etValorComissaoFornecedorDolar, Constantes.LOCALE_USA, etValorComissaoFornecedorRealCalculado, Constantes.LOCALE_BRASIL);
        calcularValorBaseadoNaCotacaoDolar(etValorFreteFornecedorDolar, etValorFreteFornecedorDolar, Constantes.LOCALE_USA, etValorFreteFornecedorRealCalculado, Constantes.LOCALE_BRASIL);
        calcularValorBaseadoNaCotacaoDolar(etValorTaxaCambioReal, etValorTaxaCambioReal, Constantes.LOCALE_BRASIL, etValorTaxaCambioDolarCalculado, Constantes.LOCALE_USA);

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
        calcularCustoTotal(etGastosExtras);

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


        verificarDetalhamento();

        salvar();



    }//FIM onCreate


    //**************************************************************************************************************************************************************
    //**************************************************************************************************************************************************************
    //**************************************************************************************************************************************************************
    //**************************************************************************************************************************************************************
    //**************************************************************************************************************************************************************


    //MÉTODOS

    private void inicializarViews(){

        tvCondicoes = findViewById(R.id.tv_condicoes);
        spnCondicoes = findViewById(R.id.spn_condicoes);
        errorMsgCondicoes = findViewById(R.id.error_msg_condicoes);

        tvQtd = findViewById(R.id.tv_qtd);
        spnQtd = findViewById(R.id.spn_qtd);
        spnVendidos = findViewById(R.id.spn_vendidos);
        errorMsgQtd = findViewById(R.id.error_msg_qtd);


        spnMedida = findViewById(R.id.spn_medida);
        spnStatusOrdem = findViewById(R.id.spn_status_ordem);

        tvCotacaoDolar = findViewById(R.id.tv_cotacao_dolar);
        etCotacaoDolar = findViewById(R.id.et_cotacao_dolar);
        errorMsgCotacaoDolar = findViewById(R.id.error_msg_cotacao_dolar);

        tvProduto = findViewById(R.id.tv_produto);
        etProduto = findViewById(R.id.et_produto);
        errorMsgProduto = findViewById(R.id.error_msg_produto);

        tvBaseFreteDolar = findViewById(R.id.tv_base_frete_dolar);
        etBaseFreteDolar = findViewById(R.id.et_base_frete_dolar);
        etBaseFreteRealCalculado = findViewById(R.id.et_base_frete_real);
        errorMsgFreteDolar = findViewById(R.id.error_msg_frete_dolar);


        etMedidaInicial = findViewById(R.id.et_medida_inicial);
        etMedidaFinal = findViewById(R.id.et_medida_final);

        tvValorLanceDolar = findViewById(R.id.tv_valor_lance_dolar);
        etValorLanceDolar = findViewById(R.id.et_valor_lance_dolar);
        etValorLanceRealCalculado = findViewById(R.id.et_valor_lance_real_calculado);
        errorMsgValorLanceDolar = findViewById(R.id.error_msg_valor_lance_dolar);

        etValorComissaoFornecedorDolar = findViewById(R.id.et_valor_comissao_fornecedor_dolar);
        etValorComissaoFornecedorRealCalculado = findViewById(R.id.et_valor_comissao_fornecedor_real_calculado);

        etValorFreteFornecedorDolar = findViewById(R.id.et_valor_frete_fornecedor_dolar);
        etValorFreteFornecedorRealCalculado = findViewById(R.id.et_valor_frete_fornecedor_real_calculado);
        etValorTaxaCambioReal = findViewById(R.id.et_valor_taxa_cambio_real);
        etValorTaxaCambioDolarCalculado = findViewById(R.id.et_valor_taxa_cambio_dolar_calculado);

        etOrdemCompra = findViewById(R.id.et_ordem_compra);

        etVendedor = findViewById(R.id.et_vendedor);
        etEmailVendedor = findViewById(R.id.et_email_vendedor);
        etFornecedor = findViewById(R.id.et_fornecedor);

        tvValorVendaUnidade = findViewById(R.id.tv_valor_venda_unidade);
        etValorVendaUnidade = findViewById(R.id.et_valor_venda_unidade);
        errorMsgValorVendaUnidade = findViewById(R.id.error_msg_valor_venda_unidade);

        etComissaoRevendedor = findViewById(R.id.et_comissao_revendedor);
        etValorFreteRevendedor = findViewById(R.id.et_frete_revendedor);
        etValorFreteTransportadora = findViewById(R.id.et_valor_frete_transportadora);
        etGastosExtras = findViewById(R.id.et_gastos_extras);
        etPagamentosRecebidos = findViewById(R.id.et_pagamentos_recebidos);
        etDataOrdem = findViewById(R.id.et_data_ordem);

        tvMedidaFinal = findViewById(R.id.tv_medida_final);
        tvFreteUsaBrasil = findViewById(R.id.tv_frete_usa_brasil);
        tvCustoTotal = findViewById(R.id.tv_custo_total);
        tvCustoUnidade = findViewById(R.id.tv_custo_unidade);
        tvLucroTotal = findViewById(R.id.tv_lucro_total);
        tvLucroUnidade = findViewById(R.id.tv_lucro_unidade);

        etMedidaInicial.setText(Constantes.PESO_DEFAULT_1_KG);
        etMedidaFinal.setText(Constantes.PESO_DEFAULT_1_KG_EM_LIBRA);

        ivFotoProduto = findViewById(R.id.iv_foto_produto);
        ibPickPhoto = findViewById(R.id.ib_pick_photo);
        ibRemovePhoto = findViewById(R.id.ib_remove_photo);



        btSalvar = findViewById(R.id.bt_salvar);

        detalheLote = null;

    }

    private void aplicarMascaras(){
        aplicarMascaraMoeda(etCotacaoDolar, Constantes.LOCALE_BRASIL);
        aplicarMascaraMoeda(etBaseFreteDolar, Constantes.LOCALE_USA);
        aplicarMascaraMoeda(etValorLanceDolar, Constantes.LOCALE_USA);
        aplicarMascaraMoeda(etValorComissaoFornecedorDolar, Constantes.LOCALE_USA);
        aplicarMascaraMoeda(etValorFreteFornecedorDolar, Constantes.LOCALE_USA);
        aplicarMascaraMoeda(etValorTaxaCambioReal, Constantes.LOCALE_BRASIL);
        aplicarMascaraMoeda(etValorVendaUnidade, Constantes.LOCALE_BRASIL);
        aplicarMascaraMoeda(etComissaoRevendedor,Constantes.LOCALE_BRASIL);
        aplicarMascaraMoeda(etValorFreteRevendedor, Constantes.LOCALE_BRASIL);
        aplicarMascaraMoeda(etValorFreteTransportadora, Constantes.LOCALE_BRASIL);
        aplicarMascaraMoeda(etGastosExtras, Constantes.LOCALE_BRASIL);
        aplicarMascaraMoeda(etPagamentosRecebidos, Constantes.LOCALE_BRASIL);
    }


    public static void aplicarMascaraMoeda(final EditText campo, final Locale locale){

        campo.addTextChangedListener(new TextWatcher(){

            private String current = Constantes.VAZIO;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence valorTexto, int start, int before, int count) {

                try {

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
                catch (Exception e){
                    Log.d("TAG", e.getMessage());
                    campo.removeTextChangedListener(this);
                    campo.setText(Constantes.VAZIO);
                    aplicarMascaraMoeda(campo, locale);
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
                        BigDecimal valorCotacaoDolar = Utils.retornarValorMonetario(etCotacaoDolar.getText().toString(), Constantes.LOCALE_BRASIL);
                        BigDecimal valorCampoBase = Utils.retornarValorMonetario(campoBase.getText().toString(), localeBase);
                        BigDecimal valorCampoResultado;

                        if(localeResultado.equals(Constantes.LOCALE_BRASIL)){
                            valorCampoResultado  = valorCotacaoDolar.multiply(valorCampoBase).setScale(2,BigDecimal.ROUND_HALF_EVEN);
                        }
                        else{
                            valorCampoResultado  = valorCampoBase.divide(valorCotacaoDolar, 2, BigDecimal.ROUND_HALF_EVEN);
                        }

                        campoResultado.setText(Utils.retornaValorMontarioComMascara(valorCampoResultado, localeResultado));

                    }
                    catch(Exception e){
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

        try{
            BigDecimal valorFreteRealCalculado = Utils.retornarValorMonetario(etBaseFreteRealCalculado.getText().toString(), Constantes.LOCALE_BRASIL);
            BigDecimal valorMedidaKG = retornarMedidaPesoKG();
            BigDecimal valorFreteUsaBrasil  = valorFreteRealCalculado.multiply(valorMedidaKG).setScale(2,BigDecimal.ROUND_HALF_EVEN);
            tvFreteUsaBrasil.setText(Utils.retornaValorMontarioComMascara(valorFreteUsaBrasil, Constantes.LOCALE_BRASIL));

        }
        catch(Exception e){
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


                try {

                    if(Utils.isValorParaCalculoValido(etValorLanceRealCalculado.getText().toString())){

                        BigDecimal valorLance;

                        BigDecimal valorFreteUsaBrasil = BigDecimal.ZERO;
                        BigDecimal valorComissaoFornecedor = BigDecimal.ZERO;
                        BigDecimal valorFreteFornecedor = BigDecimal.ZERO;
                        BigDecimal valorTaxaCambio = BigDecimal.ZERO;
                        BigDecimal valorFreteTransportadora = BigDecimal.ZERO;
                        BigDecimal valorComissaoRevendedor = BigDecimal.ZERO;
                        BigDecimal valorFreteRevendedor = BigDecimal.ZERO;
                        BigDecimal valorGastosExtras = BigDecimal.ZERO;

                        BigDecimal valorCustoTotal = BigDecimal.ZERO;

                        valorLance = Utils.retornarValorMonetario(etValorLanceRealCalculado.getText().toString(), Constantes.LOCALE_BRASIL);

                        if(Utils.isValorParaCalculoValido(tvFreteUsaBrasil.getText().toString())){
                            valorFreteUsaBrasil = Utils.retornarValorMonetario(tvFreteUsaBrasil.getText().toString(), Constantes.LOCALE_BRASIL);
                        }

                        if(Utils.isValorParaCalculoValido(etValorComissaoFornecedorRealCalculado.getText().toString())){
                            valorComissaoFornecedor = Utils.retornarValorMonetario(etValorComissaoFornecedorRealCalculado.getText().toString(), Constantes.LOCALE_BRASIL);
                        }

                        if(Utils.isValorParaCalculoValido(etValorFreteFornecedorRealCalculado.getText().toString())){
                            valorFreteFornecedor = Utils.retornarValorMonetario(etValorFreteFornecedorRealCalculado.getText().toString(), Constantes.LOCALE_BRASIL);
                        }

                        if(Utils.isValorParaCalculoValido(etValorTaxaCambioReal.getText().toString())){
                            valorTaxaCambio = Utils.retornarValorMonetario(etValorTaxaCambioReal.getText().toString(), Constantes.LOCALE_BRASIL);
                        }

                        if(Utils.isValorParaCalculoValido(etValorFreteTransportadora.getText().toString())){
                            valorFreteTransportadora = Utils.retornarValorMonetario(etValorFreteTransportadora.getText().toString(), Constantes.LOCALE_BRASIL);
                        }

                        if(Utils.isValorParaCalculoValido(etGastosExtras.getText().toString())){
                            valorGastosExtras = Utils.retornarValorMonetario(etGastosExtras.getText().toString(), Constantes.LOCALE_BRASIL);
                        }

                        String qtd = spnQtd.getSelectedItem().toString();

                        if(Utils.isValorParaCalculoValido(etComissaoRevendedor.getText().toString()) && !qtd.equals(getString(R.string.selecione)) ){
                            valorComissaoRevendedor = Utils.retornarValorMonetario(etComissaoRevendedor.getText().toString(), Constantes.LOCALE_BRASIL);
                            valorComissaoRevendedor = valorComissaoRevendedor.multiply(new BigDecimal(qtd)).setScale(2 ,BigDecimal.ROUND_HALF_UP);
                        }

                        if(Utils.isValorParaCalculoValido(etValorFreteRevendedor.getText().toString()) && !qtd.equals(getString(R.string.selecione)) ){
                            valorFreteRevendedor = Utils.retornarValorMonetario(etValorFreteRevendedor.getText().toString(), Constantes.LOCALE_BRASIL);
                            valorFreteRevendedor = valorFreteRevendedor.multiply(new BigDecimal(qtd)).setScale(2 ,BigDecimal.ROUND_HALF_UP);
                        }

                        valorCustoTotal = valorCustoTotal.add(valorLance)
                                .add(valorFreteUsaBrasil)
                                .add(valorComissaoFornecedor)
                                .add(valorFreteFornecedor)
                                .add(valorTaxaCambio)
                                .add(valorFreteTransportadora)
                                .add(valorComissaoRevendedor)
                                .add(valorFreteRevendedor)
                                .add(valorGastosExtras);


                        tvCustoTotal.setText(Utils.retornaValorMontarioComMascara(valorCustoTotal, Constantes.LOCALE_BRASIL));

                    }
                    else{
                        tvCustoTotal.setText(Constantes.VAZIO);
                    }

                }
                catch(Exception e){
                    Log.d("TAG" ,e.getMessage());
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

        try{
            if(Utils.isValorParaCalculoValido(tvCustoTotal.getText().toString()) && !qtd.equals(getString(R.string.selecione)) ){

                BigDecimal valorCusto = Utils.retornarValorMonetario(tvCustoTotal.getText().toString(), Constantes.LOCALE_BRASIL);
                valorCusto = valorCusto.divide(new BigDecimal(qtd),2, BigDecimal.ROUND_HALF_UP);
                tvCustoUnidade.setText(Utils.retornaValorMontarioComMascara(valorCusto, Constantes.LOCALE_BRASIL));
            }
            else{
                tvCustoUnidade.setText(Constantes.VAZIO);
            }
        }
        catch(Exception e){
            Log.d("TAG", e.getMessage());
        }


    }


    private void calcularValorLucroUnidade(TextView tView){

        tView.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try{
                    if(Utils.isValorParaCalculoValido(tvCustoUnidade.getText().toString()) && Utils.isValorParaCalculoValido(etValorVendaUnidade.getText().toString() )){

                        BigDecimal valorVendaUnidade = Utils.retornarValorMonetario(etValorVendaUnidade.getText().toString(), Constantes.LOCALE_BRASIL);
                        BigDecimal valorCustoUnidade = Utils.retornarValorMonetario(tvCustoUnidade.getText().toString(), Constantes.LOCALE_BRASIL);

                        BigDecimal valorLucroUnidade = valorVendaUnidade.subtract(valorCustoUnidade);

                        if(valorLucroUnidade.compareTo(BigDecimal.ZERO) < 0 ){
                            tvLucroUnidade.setTextColor(getResources().getColor(R.color.dark_red));
                        }
                        else{
                            tvLucroUnidade.setTextColor(getResources().getColor(R.color.material_green));
                        }

                        tvLucroUnidade.setText(Utils.retornaValorMontarioComMascara(valorLucroUnidade, Constantes.LOCALE_BRASIL));
                    }
                    else{
                        tvLucroUnidade.setText(Constantes.VAZIO);
                    }
                }
                catch(Exception e){
                    Log.d("TAG", e.getMessage());
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

        try{
            if(Utils.isValorParaCalculoValido(tvLucroUnidade.getText().toString()) && !qtd.equals(getString(R.string.selecione)) ){

                BigDecimal valorLucro = Utils.retornarValorMonetario(tvLucroUnidade.getText().toString(), Constantes.LOCALE_BRASIL);
                valorLucro = valorLucro.multiply(new BigDecimal(qtd)).setScale(2, BigDecimal.ROUND_HALF_UP);

                if(valorLucro.compareTo(BigDecimal.ZERO) < 0 ){
                    tvLucroTotal.setTextColor(getResources().getColor(R.color.dark_red));
                }
                else{
                    tvLucroTotal.setTextColor(getResources().getColor(R.color.material_green));
                }

                tvLucroTotal.setText(Utils.retornaValorMontarioComMascara(valorLucro, Constantes.LOCALE_BRASIL));
            }
            else{
                tvLucroTotal.setText(Constantes.VAZIO);
            }
        }
        catch(Exception e){
            Log.d("TAG" ,e.getMessage());
        }
    }



    private void removePhoto(){

        ibRemovePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivFotoProduto.setImageDrawable(null);
            }
        });
    }


    private void pickPhoto(){

        ibPickPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder pictureDialog = new AlertDialog.Builder(AddActivity.this);
                pictureDialog.setTitle("Selecione a foto do produto");

                String[] pictureDialogItems = {"Selecionar da Galeria", "Capturar com a câmera" };

                pictureDialog.setItems(pictureDialogItems,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0:
                                        choosePhotoFromGallary();
                                        break;
                                    case 1:
                                        takePhotoFromCamera();
                                        break;
                                }
                            }
                        });
                pictureDialog.show();
            }
        });
    }

    private void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        if (galleryIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(galleryIntent, Constantes.GALLERY);
        }
    }

    private void takePhotoFromCamera() {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, Constantes.CAMERA);
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        try {

            Uri uri = data.getData();

            if (resultCode == this.RESULT_CANCELED) {
                return;
            }

            else if (requestCode == Constantes.GALLERY && resultCode == RESULT_OK) {

                if (data != null) {

                    Glide.with(AddActivity.this)
                            .load(uri)
                            .apply(new RequestOptions().circleCrop().override(ivFotoProduto.getWidth(), ivFotoProduto.getHeight()))
                            .into(ivFotoProduto);
                }
            }

            else if (requestCode == Constantes.CAMERA && resultCode == RESULT_OK) {

                Glide.with(AddActivity.this)
                        .load(data.getExtras().get("data"))
                        .apply(new RequestOptions().circleCrop().override(ivFotoProduto.getWidth(), ivFotoProduto.getHeight()))
                        .into(ivFotoProduto);
            }

        }
        catch (Exception e) {
            Toast.makeText(AddActivity.this, "Impossivel carregar a imagem!", Toast.LENGTH_SHORT).show();
        }

    }


    private void salvar(){

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validarCampos()){

                    try {

                        final String pathFotoProduto;


                        if(null != ivFotoProduto.getDrawable() && null != ((BitmapDrawable)ivFotoProduto.getDrawable()).getBitmap() ){
                            Bitmap bitmap = ((BitmapDrawable)ivFotoProduto.getDrawable()).getBitmap();
                            pathFotoProduto  = PhotoUtils.saveImage(bitmap, AddActivity.this);
                        }
                        else{
                            pathFotoProduto = null;
                        }

                        Observable.create(

                                new ObservableOnSubscribe<Object>() {

                                    @Override
                                    public void subscribe(ObservableEmitter<Object> emitter) throws Exception {

                                        try {
                                            loteRepository = LoteRepository.getInstance(AddActivity.this);

                                            Lote lote = prepararDadosParaPersistencia();
                                            lote.setPathFotoProduto(pathFotoProduto);

                                            Lote detalheLote = AddActivity.this.detalheLote;

                                            if(null == detalheLote){
                                                loteRepository.insertLote(lote);
                                            }
                                            else{

                                                if(!Utils.isBlank(detalheLote.getPathFotoProduto())){
                                                    PhotoUtils.deleteImage(detalheLote.getPathFotoProduto(), AddActivity.this);
                                                }

                                                lote.setId(detalheLote.getId());
                                                loteRepository.updateLote(lote);
                                            }

                                            emitter.onComplete();
                                        }
                                        catch (Exception e) {
                                            emitter.onError(e);
                                        }
                                    }
                                }).observeOn(AndroidSchedulers.mainThread())
                                .subscribeOn(Schedulers.io())
                                .subscribe();


                        Toast.makeText(AddActivity.this, "Lote Salvo com sucesso.", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AddActivity.this, MainActivity.class));
                    }
                    catch (Exception e){
                        Toast.makeText(AddActivity.this, "Problema so salvar o lote." + e.getMessage(), Toast.LENGTH_LONG).show();
                    }

                }
                else{
                    Toast.makeText(AddActivity.this, "Verifique os campos obrigatórios.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    private Lote prepararDadosParaPersistencia(){


        Lote lote = new Lote();


        lote.setValorCotacaoDolar(Utils.retornarValorMonetario(etCotacaoDolar.getText().toString().trim(),Constantes.LOCALE_BRASIL).toString().trim());


        lote.setBaseFreteDolar(!Utils.isValorParaCalculoValido(etBaseFreteDolar.getText().toString().trim())? null :
                Utils.retornarValorMonetario(etBaseFreteDolar.getText().toString().trim(),Constantes.LOCALE_USA).toString().trim());

        lote.setProduto(etProduto.getText().toString().trim());
        lote.setCondicao(spnCondicoes.getSelectedItem().toString().trim());
        lote.setQtd(Integer.valueOf(spnQtd.getSelectedItem().toString().trim()));
        lote.setVendidos(spnVendidos.getSelectedItem().toString().trim().equals(Constantes.SELECIONE) ? null : Integer.valueOf(spnVendidos.getSelectedItem().toString()));

        lote.setValorLanceDolar(Utils.retornarValorMonetario(etValorLanceDolar.getText().toString().trim(),Constantes.LOCALE_USA).toString().trim());

        BigDecimal valorMedidaKg = retornarMedidaPesoKG();
        lote.setPesoLoteKg(valorMedidaKg.compareTo(BigDecimal.ZERO) == 0 ? null : valorMedidaKg.toString().trim());

        lote.setOrdemCompra(Utils.isBlank(etOrdemCompra.getText().toString().trim()) ? null : etOrdemCompra.getText().toString().trim());


        lote.setDataOrdemCompra(Utils.isBlank(etDataOrdem.getText().toString().trim()) ? null : Utils.retornarDateFromString(etDataOrdem.getText().toString().trim()));


        lote.setStatusOrdemCompra(spnStatusOrdem.getSelectedItem().toString().trim().equals(Constantes.SELECIONE) ? null : spnStatusOrdem.getSelectedItem().toString().trim());


        lote.setVendedor(Utils.isBlank(etVendedor.getText().toString().trim()) ? null : etVendedor.getText().toString().trim() );
        lote.setEmailVendedor(Utils.isBlank(etEmailVendedor.getText().toString().trim()) ? null : etEmailVendedor.getText().toString().trim() );
        lote.setFornecedor(Utils.isBlank(etFornecedor.getText().toString().trim()) ? null : etFornecedor.getText().toString().trim() );

        lote.setValorComissaoFornecedorDolar(!Utils.isValorParaCalculoValido(etValorComissaoFornecedorDolar.getText().toString().trim())? null :
                Utils.retornarValorMonetario(etValorComissaoFornecedorDolar.getText().toString().trim(),Constantes.LOCALE_USA).toString().trim());

        lote.setValorFreteFornecedorDolar(!Utils.isValorParaCalculoValido(etValorFreteFornecedorDolar.getText().toString().trim())? null :
                Utils.retornarValorMonetario(etValorFreteFornecedorDolar.getText().toString().trim(),Constantes.LOCALE_USA).toString().trim());


        lote.setValorTaxaCambioDolar(!Utils.isValorParaCalculoValido(etValorTaxaCambioReal.getText().toString().trim())? null :
                Utils.retornarValorMonetario(etValorTaxaCambioReal.getText().toString().trim(),Constantes.LOCALE_BRASIL).toString().trim());

        lote.setValorFreteTransportadora(!Utils.isValorParaCalculoValido(etValorFreteTransportadora.getText().toString().trim())? null :
                Utils.retornarValorMonetario(etValorFreteTransportadora.getText().toString().trim(),Constantes.LOCALE_BRASIL).toString().trim());


        lote.setValorVendaUnidade(Utils.retornarValorMonetario(etValorVendaUnidade.getText().toString().trim(),Constantes.LOCALE_BRASIL).toString().trim());

        lote.setValorComissaoRevendedorUnidade(!Utils.isValorParaCalculoValido(etComissaoRevendedor.getText().toString().trim())? null :
                Utils.retornarValorMonetario(etComissaoRevendedor.getText().toString().trim(),Constantes.LOCALE_BRASIL).toString().trim());

        lote.setValorFreteRevendedorUnidade(!Utils.isValorParaCalculoValido(etValorFreteRevendedor.getText().toString().trim())? null :
                Utils.retornarValorMonetario(etValorFreteRevendedor.getText().toString().trim(),Constantes.LOCALE_BRASIL).toString().trim());

        lote.setValorGastosExtras(!Utils.isValorParaCalculoValido(etGastosExtras.getText().toString().trim())? null :
                Utils.retornarValorMonetario(etGastosExtras.getText().toString().trim(),Constantes.LOCALE_BRASIL).toString().trim());

        lote.setPagamentosRecebidos(!Utils.isValorParaCalculoValido(etPagamentosRecebidos.getText().toString().trim())? null :
                Utils.retornarValorMonetario(etPagamentosRecebidos.getText().toString().trim(),Constantes.LOCALE_BRASIL).toString().trim());

        return lote;
    }


    private BigDecimal retornarMedidaPesoKG(){

        String tipoMedidaSelecionada = spnMedida.getSelectedItem().toString();
        String medidaKG;

        if(tipoMedidaSelecionada.equals(getString(R.string.medida_kg))){
            medidaKG = etMedidaInicial.getText().toString();
        }
        else{
            medidaKG = etMedidaFinal.getText().toString();
        }

        if(Utils.isBlank(medidaKG)){
            return BigDecimal.ZERO;
        }

        medidaKG = medidaKG.replace(Constantes.VIRGULA, Constantes.PONTO);

        if(medidaKG.endsWith(Constantes.PONTO)){
            medidaKG = medidaKG.replace(Constantes.VIRGULA, Constantes.VAZIO);
        }

        return new BigDecimal(medidaKG).setScale(1, BigDecimal.ROUND_HALF_EVEN);

    }




    private boolean validarCampos(){

        int countErro = 0;

        if(!FieldValidator.validarCampoMonetarioObrigatorio( tvCotacaoDolar, etCotacaoDolar, errorMsgCotacaoDolar)){
            countErro++;
        }

        if(!FieldValidator.validarCampoObrigatorio( tvProduto, etProduto, errorMsgProduto)){
            countErro++;
        }

        if(!FieldValidator.validarCampoObrigatorio( tvCondicoes, spnCondicoes, errorMsgCondicoes)){
            countErro++;
        }

        if(!FieldValidator.validarCampoObrigatorio( tvQtd, spnQtd, errorMsgQtd)){
            countErro++;
        }

        if(!FieldValidator.validarCampoMonetarioObrigatorio( tvValorLanceDolar, etValorLanceDolar, errorMsgValorLanceDolar)){
            countErro++;
        }

        if(!FieldValidator.validarCampoMonetarioObrigatorio( tvValorVendaUnidade, etValorVendaUnidade, errorMsgValorVendaUnidade)){
            countErro++;
        }

        return countErro == 0;
    }



    public void verificarDetalhamento(){

        Bundle b = getIntent().getExtras();

        if(b != null){
            detalheLote = (Lote)b.getSerializable(Constantes.DETALHE_LOTE);
            popularDetalhamento();
        }
    }

    public void popularDetalhamento(){

        if(!Utils.isBlank(this.detalheLote.getPathFotoProduto())){
            Glide.with(AddActivity.this)
                    .load(this.detalheLote.getPathFotoProduto())
                    .into(ivFotoProduto);
        }


        tratarDetalhamentoValorMonetario(this.detalheLote.getValorCotacaoDolar(), etCotacaoDolar);
        tratarDetalhamentoValorMonetario(this.detalheLote.getBaseFreteDolar(), etBaseFreteDolar);

        etProduto.setText(this.detalheLote.getProduto());

        spnCondicoes.setSelection(((ArrayAdapter)spnCondicoes.getAdapter()).getPosition(this.detalheLote.getCondicao()));
        spnQtd.setSelection(((ArrayAdapter)spnQtd.getAdapter()).getPosition(this.detalheLote.getQtd().toString()));


        if(null != this.detalheLote.getVendidos()){
            spnVendidos.setSelection(((ArrayAdapter)spnVendidos.getAdapter()).getPosition(this.detalheLote.getVendidos().toString()));
        }


        tratarDetalhamentoValorMonetario(this.detalheLote.getValorLanceDolar(), etValorLanceDolar);

        if(!Utils.isBlank(this.detalheLote.getPesoLoteKg())){
            etMedidaInicial.setText(this.detalheLote.getPesoLoteKg().replace(Constantes.PONTO,Constantes.VIRGULA));
        }

        if(!Utils.isBlank(this.detalheLote.getOrdemCompra())){
            etOrdemCompra.setText(this.detalheLote.getOrdemCompra());
        }

        if(null != this.detalheLote.getDataOrdemCompra()){
            etDataOrdem.setText(Utils.retornarFormatedDate(this.detalheLote.getDataOrdemCompra()));
        }

        spnStatusOrdem.setSelection(((ArrayAdapter)spnStatusOrdem.getAdapter()).getPosition(this.detalheLote.getStatusOrdemCompra()));

        if(!Utils.isBlank(this.detalheLote.getVendedor())){
            etVendedor.setText(this.detalheLote.getVendedor());
        }

        if(!Utils.isBlank(this.detalheLote.getEmailVendedor())){
            etEmailVendedor.setText(this.detalheLote.getEmailVendedor());
        }

        if(!Utils.isBlank(this.detalheLote.getFornecedor())){
            etFornecedor.setText(this.detalheLote.getFornecedor());
        }

        tratarDetalhamentoValorMonetario(this.detalheLote.getValorComissaoFornecedorDolar(), etValorComissaoFornecedorDolar);
        tratarDetalhamentoValorMonetario(this.detalheLote.getValorFreteFornecedorDolar(), etValorFreteFornecedorDolar);
        tratarDetalhamentoValorMonetario(this.detalheLote.getValorTaxaCambioDolar(), etValorTaxaCambioReal);
        tratarDetalhamentoValorMonetario(this.detalheLote.getValorFreteTransportadora(), etValorFreteTransportadora);
        tratarDetalhamentoValorMonetario(this.detalheLote.getValorVendaUnidade(), etValorVendaUnidade);
        tratarDetalhamentoValorMonetario(this.detalheLote.getValorComissaoRevendedorUnidade(), etComissaoRevendedor);
        tratarDetalhamentoValorMonetario(this.detalheLote.getValorFreteRevendedorUnidade(), etValorFreteRevendedor);
        tratarDetalhamentoValorMonetario(this.detalheLote.getValorGastosExtras(), etGastosExtras);
        tratarDetalhamentoValorMonetario(this.detalheLote.getPagamentosRecebidos(), etPagamentosRecebidos);
    }

    private void tratarDetalhamentoValorMonetario(String valorTexto, EditText campo){

        Double valor = 0.0;

        try{
            if(!Utils.isBlank(valorTexto)){
                valor = Double.parseDouble(valorTexto);
                campo.setText(String.format("%.2f", valor));
            }
        }
        catch (Exception e){
            Log.d("TAG", e.getMessage());
        }
    }










}