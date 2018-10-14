package com.atimports.utils;

import com.atimports.constantes.Constantes;

import java.math.BigDecimal;

public abstract class Utils {


    public static boolean isBlank(String texto){
        return null == texto || "".equals(texto.trim());
    }


    public static String desformatarMascaraMoeda(String texto, String simboloMoeda){
        String desformatado = texto;

        if(simboloMoeda.equals(Constantes.SIMBOLO_REAL)){
            desformatado = desformatado.replace(Constantes.SIMBOLO_REAL, Constantes.VAZIO);
            desformatado = desformatado.replace(Constantes.VIRGULA , Constantes.PONTO);
        }
        else{
            desformatado = desformatado.replace(Constantes.SIMBOLO_DOLAR, Constantes.VAZIO);
            desformatado = desformatado.replace(Constantes.VIRGULA , Constantes.VAZIO);
        }

        return desformatado;
    }


    public static String formatarMascaraMoeda(String texto, String simboloMoeda){
        String formatado = simboloMoeda + texto;

        if(simboloMoeda.equals(Constantes.SIMBOLO_REAL)){
            formatado = formatado.replace(Constantes.PONTO, Constantes.VIRGULA);
        }

        return formatado;
    }

    public static boolean isNullOrZero(BigDecimal valor){
        return null == valor || valor.compareTo(BigDecimal.ZERO) == 0;
    }


}
