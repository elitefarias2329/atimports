package com.atimports.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.atimports.constantes.Constantes;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public abstract class Utils {


    public static final Locale LOCALE_BRASIL = new Locale("pt", "BR");
    public static final Locale LOCALE_USA = Locale.US;


    public static boolean isBlank(String texto){
        return null == texto || "".equals(texto.trim());
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

                    String valorMonetarioPuro = retirarMascaraMoeda(valorTexto.toString());

                    BigDecimal valorMonetario = new BigDecimal(valorMonetarioPuro);
                    valorMonetario = valorMonetario.divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_EVEN);

                    String valorMonetarioFormatado = retornaValorMontarioComMascara(valorMonetario, locale);

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

    public static String retirarSimboloMoeda(String texto){
        String desformatado = texto;
        desformatado = desformatado.replace(Constantes.SIMBOLO_REAL, Constantes.VAZIO);
        desformatado = desformatado.replace(Constantes.SIMBOLO_DOLAR, Constantes.VAZIO);
        return desformatado;
    }

    public static String retirarMascaraMoeda(String texto){
        String desformatado = texto;
        desformatado = desformatado.replace(Constantes.SIMBOLO_REAL, Constantes.VAZIO);
        desformatado = desformatado.replace(Constantes.SIMBOLO_DOLAR, Constantes.VAZIO);
        desformatado = desformatado.replace(Constantes.PONTO, Constantes.VAZIO);
        desformatado = desformatado.replace(Constantes.VIRGULA, Constantes.VAZIO);
        return desformatado;
    }

    public static BigDecimal retornarValorMonetario(String valor, Locale locale){

        try {
            String valorFmt = retirarSimboloMoeda(valor);
            valorFmt = NumberFormat.getNumberInstance(locale).parse(valorFmt).toString();
            return new BigDecimal(valorFmt);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }



    public static String retornaValorMontarioComMascara(BigDecimal valor, Locale locale){
        NumberFormat f = NumberFormat.getCurrencyInstance(locale);
        return f.format(valor);
    }

}
