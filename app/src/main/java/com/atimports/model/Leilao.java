package com.atimports.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.math.BigDecimal;

import io.reactivex.annotations.NonNull;

@Entity(tableName = "LEILAO")
public class Leilao {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    private Long id;

    @NonNull
    @ColumnInfo(name = "VALOR_COTACAO_DOLAR")
    private BigDecimal valorCotacaoDolar;

    @NonNull
    @ColumnInfo(name = "VALOR_FRETE_USA_DOLAR")
    private BigDecimal valorFreteUsaDolar;

    @NonNull
    @ColumnInfo(name = "VALOR_FRETE_USA_REAL")
    private BigDecimal valorFreteUsaReal;

    @NonNull
    @ColumnInfo(name = "NOME_PRODUTO")
    private String nomeProduto;

    @NonNull
    @ColumnInfo(name = "CONDICAO")
    private String condicao;

    @NonNull
    @ColumnInfo(name = "QUANTIDADE")
    private Integer qtd;






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

    public BigDecimal getValorFreteUsaDolar() {
        return valorFreteUsaDolar;
    }

    public void setValorFreteUsaDolar(BigDecimal valorFreteUsaDolar) {
        this.valorFreteUsaDolar = valorFreteUsaDolar;
    }

    public BigDecimal getValorFreteUsaReal() {
        return valorFreteUsaReal;
    }

    public void setValorFreteUsaReal(BigDecimal valorFreteUsaReal) {
        this.valorFreteUsaReal = valorFreteUsaReal;
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

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }
}
