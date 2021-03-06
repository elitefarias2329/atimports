package com.atimports.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.atimports.converter.DateTypeConverter;

import java.io.Serializable;
import java.util.Date;

import io.reactivex.annotations.NonNull;

@Entity(tableName = "LOTE")
public class Lote implements Serializable {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    private Long id;

    @ColumnInfo(name = "PATH_FOTO_PRODUTO")
    private String pathFotoProduto;

    @NonNull
    @ColumnInfo(name = "VALOR_COTACAO_DOLAR")
    private String valorCotacaoDolar;

    @NonNull
    @ColumnInfo(name = "VALOR_BASE_FRETE_DOLAR")
    private String baseFreteDolar;

    @NonNull
    @ColumnInfo(name = "PRODUTO")
    private String produto;

    @NonNull
    @ColumnInfo(name = "CONDICAO")
    private String condicao;

    @NonNull
    @ColumnInfo(name = "QUANTIDADE")
    private Integer qtd;

    @ColumnInfo(name = "VENDIDOS")
    private Integer vendidos;

    @NonNull
    @ColumnInfo(name = "VALOR_LANCE_DOLAR")
    private String valorLanceDolar;

    @ColumnInfo(name = "PESO_LOTE_KG")
    private String pesoLoteKg;

    @ColumnInfo(name = "ORDEM_COMPRA")
    private String ordemCompra;

    @TypeConverters(DateTypeConverter.class)
    @ColumnInfo(name = "DATA_ORDEM_COMPRA")
    private Date dataOrdemCompra;

    @ColumnInfo(name = "STATUS_ORDEM_COMPRA")
    private String statusOrdemCompra;

    @ColumnInfo(name = "VENDEDOR")
    private String vendedor;

    @ColumnInfo(name = "E-MAIL_VENDEDOR")
    private String emailVendedor;

    @ColumnInfo(name = "FORNECEDOR")
    private String fornecedor;

    @ColumnInfo(name = "VALOR_COMISSAO_FORNECEDOR_DOLAR")
    private String valorComissaoFornecedorDolar;

    @ColumnInfo(name = "VALOR_FRETE_FORNECEDOR_DOLAR")
    private String valorFreteFornecedorDolar;

    @ColumnInfo(name = "VALOR_TAXA_CAMBIO_DOLAR")
    private String valorTaxaCambioDolar;

    @ColumnInfo(name = "VALOR_FRETE_TRANSPORTADORA")
    private String valorFreteTransportadora;

    @NonNull
    @ColumnInfo(name = "VALOR_VENDA_UNIDADE")
    private String valorVendaUnidade;

    @ColumnInfo(name = "VALOR_COMISSAO_REVENDEDOR_UNIDADE")
    private String valorComissaoRevendedorUnidade;

    @ColumnInfo(name = "VALOR_FRETE_REVENDEDOR_UNIDADE")
    private String valorFreteRevendedorUnidade;

    @ColumnInfo(name = "VALOR_GASTOS_EXTRAS")
    private String valorGastosExtras;

    @ColumnInfo(name = "PAGAMENTOS_RECEBIDOS")
    private String pagamentosRecebidos;


    //GETTERS E SETTERS

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getValorCotacaoDolar() {
        return valorCotacaoDolar;
    }

    public void setValorCotacaoDolar(String valorCotacaoDolar) {
        this.valorCotacaoDolar = valorCotacaoDolar;
    }

    public String getBaseFreteDolar() {
        return baseFreteDolar;
    }

    public void setBaseFreteDolar(String baseFreteDolar) {
        this.baseFreteDolar = baseFreteDolar;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getCondicao() {
        return condicao;
    }

    public void setCondicao(String condicao) {
        this.condicao = condicao;
    }

    public Integer getQtd() {
        return qtd;
    }

    public void setQtd(Integer qtd) {
        this.qtd = qtd;
    }

    public String getValorLanceDolar() {
        return valorLanceDolar;
    }

    public void setValorLanceDolar(String valorLanceDolar) {
        this.valorLanceDolar = valorLanceDolar;
    }

    public Date getDataOrdemCompra() {
        return dataOrdemCompra;
    }

    public void setDataOrdemCompra(Date dataOrdemCompra) {
        this.dataOrdemCompra = dataOrdemCompra;
    }

    public String getPesoLoteKg() {
        return pesoLoteKg;
    }

    public void setPesoLoteKg(String pesoLoteKg) {
        this.pesoLoteKg = pesoLoteKg;
    }

    public String getOrdemCompra() {
        return ordemCompra;
    }

    public void setOrdemCompra(String ordemCompra) {
        this.ordemCompra = ordemCompra;
    }


    public String getStatusOrdemCompra() {
        return statusOrdemCompra;
    }

    public void setStatusOrdemCompra(String statusOrdemCompra) {
        this.statusOrdemCompra = statusOrdemCompra;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getValorComissaoFornecedorDolar() {
        return valorComissaoFornecedorDolar;
    }

    public void setValorComissaoFornecedorDolar(String valorComissaoFornecedorDolar) {
        this.valorComissaoFornecedorDolar = valorComissaoFornecedorDolar;
    }

    public String getValorFreteFornecedorDolar() {
        return valorFreteFornecedorDolar;
    }

    public void setValorFreteFornecedorDolar(String valorFreteFornecedorDolar) {
        this.valorFreteFornecedorDolar = valorFreteFornecedorDolar;
    }

    public String getValorTaxaCambioDolar() {
        return valorTaxaCambioDolar;
    }

    public void setValorTaxaCambioDolar(String valorTaxaCambioDolar) {
        this.valorTaxaCambioDolar = valorTaxaCambioDolar;
    }

    public String getValorFreteTransportadora() {
        return valorFreteTransportadora;
    }

    public void setValorFreteTransportadora(String valorFreteTransportadora) {
        this.valorFreteTransportadora = valorFreteTransportadora;
    }

    public String getValorVendaUnidade() {
        return valorVendaUnidade;
    }

    public void setValorVendaUnidade(String valorVendaUnidade) {
        this.valorVendaUnidade = valorVendaUnidade;
    }

    public String getValorComissaoRevendedorUnidade() {
        return valorComissaoRevendedorUnidade;
    }

    public void setValorComissaoRevendedorUnidade(String valorComissaoRevendedorUnidade) {
        this.valorComissaoRevendedorUnidade = valorComissaoRevendedorUnidade;
    }

    public String getValorFreteRevendedorUnidade() {
        return valorFreteRevendedorUnidade;
    }

    public void setValorFreteRevendedorUnidade(String valorFreteRevendedorUnidade) {
        this.valorFreteRevendedorUnidade = valorFreteRevendedorUnidade;
    }

    public String getPathFotoProduto() {
        return pathFotoProduto;
    }

    public void setPathFotoProduto(String pathFotoProduto) {
        this.pathFotoProduto = pathFotoProduto;
    }

    public String getValorGastosExtras() {
        return valorGastosExtras;
    }

    public void setValorGastosExtras(String valorGastosExtras) {
        this.valorGastosExtras = valorGastosExtras;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public String getEmailVendedor() {
        return emailVendedor;
    }

    public void setEmailVendedor(String emailVendedor) {
        this.emailVendedor = emailVendedor;
    }

    public Integer getVendidos() {
        return vendidos;
    }

    public void setVendidos(Integer vendidos) {
        this.vendidos = vendidos;
    }

    public String getPagamentosRecebidos() {
        return pagamentosRecebidos;
    }

    public void setPagamentosRecebidos(String pagamentosRecebidos) {
        this.pagamentosRecebidos = pagamentosRecebidos;
    }
}
