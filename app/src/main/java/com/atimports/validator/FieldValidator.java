package com.atimports.validator;

import android.widget.EditText;
import android.widget.TextView;

import com.atimports.constantes.Constantes;
import com.atimports.utils.Utils;

public abstract class FieldValidator {


    public static void validarCampoObrigatorio(TextView tvLabelCampo, EditText etCampo, TextView tvLabelErroCampo){

        String labelCampo = tvLabelCampo.getText().toString();
        String campo = etCampo.getText().toString();

        if(Utils.isBlank(campo)){
            tvLabelErroCampo.setText("O Campo " + labelCampo + " é obrigatório!");
        }
        else{
            tvLabelErroCampo.setText(Constantes.VAZIO);
        }
    }

    public static void validarCampoMonetarioObrigatorio(TextView tvLabelCampo, EditText etCampo, TextView tvLabelErroCampo){

        String labelCampo = tvLabelCampo.getText().toString();
        String campo = etCampo.getText().toString();

        if(Utils.isValorParaCalculoValido(campo)){
            tvLabelErroCampo.setText("O Campo " + labelCampo + " é obrigatório!");
        }
        else{
            tvLabelErroCampo.setText(Constantes.VAZIO);
        }
    }




}
