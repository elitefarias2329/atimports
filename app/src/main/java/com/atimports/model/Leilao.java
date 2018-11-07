package com.atimports.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.atimports.converter.Converters;

import java.math.BigDecimal;
import java.util.Date;

import io.reactivex.annotations.NonNull;

@Entity(tableName = "LEILAO")
public class Leilao {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    private Long id;

    @TypeConverters(Converters.class)
    @NonNull
    @ColumnInfo(name = "VALOR_COTACAO_DOLAR")
    private BigDecimal valorCotacaoDolar;

    @TypeConverters(Converters.class)
    @NonNull
    @ColumnInfo(name = "BASE_FRETE_DOLAR")
    private BigDecimal baseFreteDolar;

    @TypeConverters(Converters.class)
    @NonNull
    @ColumnInfo(name = "BASE_FRETE_REAL")
    private BigDecimal baseFreteReal;

    @NonNull
    @ColumnInfo(name = "NOME_PRODUTO")
    private String nomeProduto;

    @NonNull
    @ColumnInfo(name = "CONDICAO")
    private String condicao;

    @NonNull
    @ColumnInfo(name = "QUANTIDADE")
    private Integer qtd;

    @TypeConverters(Converters.class)
    @NonNull
    @ColumnInfo(name = "VALOR_LANCE_DOLAR")
    private BigDecimal valorLanceDolar;

    @TypeConverters(Converters.class)
    @NonNull
    @ColumnInfo(name = "VALOR_LANCE_REAL")
    private BigDecimal valorLanceReal;

    @TypeConverters(Converters.class)
    @NonNull
    @ColumnInfo(name = "VALOR_FRETE_USA_REAL")
    private BigDecimal valorFreteUsaReal;

    @ColumnInfo(name = "PESO_LOTE_KG")
    private Double pesoLoteKg;


    @ColumnInfo(name = "ORDEM_COMPRA")
    private String ordemCompra;

    @ColumnInfo(name = "DATA_ORDEM_COMPRA")
    private Date dataOrdemCompra;

    @ColumnInfo(name = "STATUS_ORDEM_COMPRA")
    private String statusOrdemCompra;

    @ColumnInfo(name = "NOME_FORNECEDOR")
    private String nomeFornecedor;

    @TypeConverters(Converters.class)
    @ColumnInfo(name = "VALOR_COMISSAO_FORNECEDOR_DOLAR")
    private BigDecimal valorComissaoFornecedorDolar;

    @TypeConverters(Converters.class)
    @ColumnInfo(name = "VALOR_COMISSAO_FORNECEDOR_REAL")
    private BigDecimal valorComissaoFornecedorReal;

    @TypeConverters(Converters.class)
    @ColumnInfo(name = "VALOR_FRETE_FORNECEDOR_DOLAR")
    private BigDecimal valorFreteFornecedorDolar;

    @TypeConverters(Converters.class)
    @ColumnInfo(name = "VALOR_FRETE_FORNECEDOR_REAL")
    private BigDecimal valorFreteFornecedorReal;

    @TypeConverters(Converters.class)
    @ColumnInfo(name = "VALOR_TAXA_CAMBIO_DOLAR")
    private BigDecimal valorTaxaCambioDolar;

    @TypeConverters(Converters.class)
    @ColumnInfo(name = "VALOR_TAXA_CAMBIO_REAL")
    private BigDecimal valorTaxaCambioReal;

    @TypeConverters(Converters.class)
    @ColumnInfo(name = "VALOR_FRETE_TRANSPORTADORA")
    private BigDecimal valorFreteTransportadora;

    @NonNull
    @TypeConverters(Converters.class)
    @ColumnInfo(name = "VALOR_VENDA_UNIDADE")
    private BigDecimal valorVendaUnidade;

    @TypeConverters(Converters.class)
    @ColumnInfo(name = "VALOR_COMISSAO_REVENDEDOR_UNIDADE")
    private BigDecimal valorComissaoRevendedorUnidade;

    @TypeConverters(Converters.class)
    @ColumnInfo(name = "VALOR_FRETE_REVENDEDOR_UNIDADE")
    private BigDecimal valorFreteRevendedorUnidade;

    @NonNull
    @TypeConverters(Converters.class)
    @ColumnInfo(name = "VALOR_CUSTO_TOTAL")
    private BigDecimal valorCustoTotal;

    @NonNull
    @TypeConverters(Converters.class)
    @ColumnInfo(name = "VALOR_CUSTO_UNIDADE")
    private BigDecimal valorCustoUnidade;


    @NonNull
    @TypeConverters(Converters.class)
    @ColumnInfo(name = "VALOR_LUCRO_TOTAL")
    private BigDecimal valorLucroTotal;

    @NonNull
    @TypeConverters(Converters.class)
    @ColumnInfo(name = "VALOR_LUCRO_UNIDADE")
    private BigDecimal valorLucroUnidade;

    @ColumnInfo(name = "PATH_FOTO_PRODUTO")
    private String pathFotoProduto;


    //GETTERS E SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValorCotacaoDolar() {
        return valorCotacaoDolar;
    }

    public void setValorCotacaoDolar(BigDecimal valorCotacaoDolar) {
        this.valorCotacaoDolar = valorCotacaoDolar;
    }

    public BigDecimal getBaseFreteDolar() {
        return baseFreteDolar;
    }

    public void setBaseFreteDolar(BigDecimal baseFreteDolar) {
        this.baseFreteDolar = baseFreteDolar;
    }

    public BigDecimal getBaseFreteReal() {
        return baseFreteReal;
    }

    public void setBaseFreteReal(BigDecimal baseFreteReal) {
        this.baseFreteReal = baseFreteReal;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
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

    public BigDecimal getValorLanceDolar() {
        return valorLanceDolar;
    }

    public void setValorLanceDolar(BigDecimal valorLanceDolar) {
        this.valorLanceDolar = valorLanceDolar;
    }

    public BigDecimal getValorLanceReal() {
        return valorLanceReal;
    }

    public void setValorLanceReal(BigDecimal valorLanceReal) {
        this.valorLanceReal = valorLanceReal;
    }

    public BigDecimal getValorFreteUsaReal() {
        return valorFreteUsaReal;
    }

    public void setValorFreteUsaReal(BigDecimal valorFreteUsaReal) {
        this.valorFreteUsaReal = valorFreteUsaReal;
    }

    public Double getPesoLoteKg() {
        return pesoLoteKg;
    }

    public void setPesoLoteKg(Double pesoLoteKg) {
        this.pesoLoteKg = pesoLoteKg;
    }


    public String getOrdemCompra() {
        return ordemCompra;
    }

    public void setOrdemCompra(String ordemCompra) {
        this.ordemCompra = ordemCompra;
    }

    public Date getDataOrdemCompra() {
        return dataOrdemCompra;
    }

    public void setDataOrdemCompra(Date dataOrdemCompra) {
        this.dataOrdemCompra = dataOrdemCompra;
    }

    public String getStatusOrdemCompra() {
        return statusOrdemCompra;
    }

    public void setStatusOrdemCompra(String statusOrdemCompra) {
        this.statusOrdemCompra = statusOrdemCompra;
    }

    public String getNomeFornecedor() {
        return nomeFornecedor;
    }

    public void setNomeFornecedor(String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
    }

    public BigDecimal getValorComissaoFornecedorDolar() {
        return valorComissaoFornecedorDolar;
    }

    public void setValorComissaoFornecedorDolar(BigDecimal valorComissaoFornecedorDolar) {
        this.valorComissaoFornecedorDolar = valorComissaoFornecedorDolar;
    }

    public BigDecimal getValorComissaoFornecedorReal() {
        return valorComissaoFornecedorReal;
    }

    public void setValorComissaoFornecedorReal(BigDecimal valorComissaoFornecedorReal) {
        this.valorComissaoFornecedorReal = valorComissaoFornecedorReal;
    }

    public BigDecimal getValorFreteFornecedorDolar() {
        return valorFreteFornecedorDolar;
    }

    public void setValorFreteFornecedorDolar(BigDecimal valorFreteFornecedorDolar) {
        this.valorFreteFornecedorDolar = valorFreteFornecedorDolar;
    }

    public BigDecimal getValorFreteFornecedorReal() {
        return valorFreteFornecedorReal;
    }

    public void setValorFreteFornecedorReal(BigDecimal valorFreteFornecedorReal) {
        this.valorFreteFornecedorReal = valorFreteFornecedorReal;
    }

    public BigDecimal getValorTaxaCambioDolar() {
        return valorTaxaCambioDolar;
    }

    public void setValorTaxaCambioDolar(BigDecimal valorTaxaCambioDolar) {
        this.valorTaxaCambioDolar = valorTaxaCambioDolar;
    }

    public BigDecimal getValorTaxaCambioReal() {
        return valorTaxaCambioReal;
    }

    public void setValorTaxaCambioReal(BigDecimal valorTaxaCambioReal) {
        this.valorTaxaCambioReal = valorTaxaCambioReal;
    }

    public BigDecimal getValorFreteTransportadora() {
        return valorFreteTransportadora;
    }

    public void setValorFreteTransportadora(BigDecimal valorFreteTransportadora) {
        this.valorFreteTransportadora = valorFreteTransportadora;
    }

    public BigDecimal getValorVendaUnidade() {
        return valorVendaUnidade;
    }

    public void setValorVendaUnidade(BigDecimal valorVendaUnidade) {
        this.valorVendaUnidade = valorVendaUnidade;
    }

    public BigDecimal getValorComissaoRevendedorUnidade() {
        return valorComissaoRevendedorUnidade;
    }

    public void setValorComissaoRevendedorUnidade(BigDecimal valorComissaoRevendedorUnidade) {
        this.valorComissaoRevendedorUnidade = valorComissaoRevendedorUnidade;
    }

    public BigDecimal getValorFreteRevendedorUnidade() {
        return valorFreteRevendedorUnidade;
    }

    public void setValorFreteRevendedorUnidade(BigDecimal valorFreteRevendedorUnidade) {
        this.valorFreteRevendedorUnidade = valorFreteRevendedorUnidade;
    }

    public BigDecimal getValorCustoTotal() {
        return valorCustoTotal;
    }

    public void setValorCustoTotal(BigDecimal valorCustoTotal) {
        this.valorCustoTotal = valorCustoTotal;
    }

    public BigDecimal getValorCustoUnidade() {
        return valorCustoUnidade;
    }

    public void setValorCustoUnidade(BigDecimal valorCustoUnidade) {
        this.valorCustoUnidade = valorCustoUnidade;
    }

    public BigDecimal getValorLucroTotal() {
        return valorLucroTotal;
    }

    public void setValorLucroTotal(BigDecimal valorLucroTotal) {
        this.valorLucroTotal = valorLucroTotal;
    }

    public BigDecimal getValorLucroUnidade() {
        return valorLucroUnidade;
    }

    public void setValorLucroUnidade(BigDecimal valorLucroUnidade) {
        this.valorLucroUnidade = valorLucroUnidade;
    }

    public String getPathFotoProduto() {
        return pathFotoProduto;
    }

    public void setPathFotoProduto(String pathFotoProduto) {
        this.pathFotoProduto = pathFotoProduto;
    }

}
