package com.atimports.utils;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.atimports.R;
import com.atimports.constantes.Constantes;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Locale;

public abstract class Utils {


    public static boolean isBlank(String texto){
        return null == texto || "".equals(texto.trim());
    }



    public static boolean isValorParaCalculoValido(String valor){

        if(null == valor ||
          valor.equals(Constantes.VALOR_MASCARA_ZERADO_DOLAR) ||
          valor.equals(Constantes.VALOR_MASCARA_ZERADO_REAL)  ||
          valor.equals(Constantes.VAZIO) ){

            return false;
        }
        return true;
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



    public static void criarDatePickerDialog(View campoClicavel, final TextView campoResultado, final Context ctx){

        campoClicavel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar cal = Calendar.getInstance() ;
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dpDataOrdemDialog = new DatePickerDialog(ctx,
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

                                                                                    campoResultado.setText(dia + "/" + mes + "/" + year);
                                                                                }
                                                                            },

                                                                             year, month, day
                                                                        );

                dpDataOrdemDialog.show();

                dpDataOrdemDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "CANCELAR", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == DialogInterface.BUTTON_NEGATIVE) {
                            campoResultado.setText(Constantes.VAZIO);
                        }
                    }
                });


            }
        });


    }













}
