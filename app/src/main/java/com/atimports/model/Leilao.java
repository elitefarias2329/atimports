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
    private Double valorCotacaoDolar;

    @NonNull
    @ColumnInfo(name = "VALOR_FRETE_USA_DOLAR")
    private Double valorFreteUsaDolar;

    @NonNull
    @ColumnInfo(name = "VALOR_FRETE_USA_REAL")
    private Double valorFreteUsaReal;

    @NonNull
    @ColumnInfo(name = "NOME_PRODUTO")
    private String nomeProduto;

    @NonNull
    @ColumnInfo(name = "CONDICAO")
    private String condicao;

    @NonNull
    @ColumnInfo(name = "QUANTIDADE")
    private Integer qtd;




    public String toString() {
        return this.id + ". " + this.nomeProduto + " [$" + this.valorCotacaoDolar + "]";
    }



    //GETTERS E SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValorCotacaoDolar() {
        return valorCotacaoDolar;
    }

    public void setValorCotacaoDolar(Double valorCotacaoDolar) {
        this.valorCotacaoDolar = valorCotacaoDolar;
    }

    public Double getValorFreteUsaDolar() {
        return valorFreteUsaDolar;
    }

    public void setValorFreteUsaDolar(Double valorFreteUsaDolar) {
        this.valorFreteUsaDolar = valorFreteUsaDolar;
    }

    public Double getValorFreteUsaReal() {
        return valorFreteUsaReal;
    }

    public void setValorFreteUsaReal(Double valorFreteUsaReal) {
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
