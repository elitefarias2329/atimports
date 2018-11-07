package com.atimports.validator;

import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.atimports.constantes.Constantes;
import com.atimports.utils.Utils;

public abstract class FieldValidator {


    public static boolean validarCampoObrigatorio(TextView tvLabelCampo, EditText etCampo, TextView tvLabelErroCampo){

        String labelCampo = tvLabelCampo.getText().toString();
        String campo = etCampo.getText().toString();

        if(Utils.isBlank(campo)){
            tvLabelErroCampo.setText("O Campo " + labelCampo + " é obrigatório!");
            return false;
        }
        else{
            tvLabelErroCampo.setText(Constantes.VAZIO);
            return true;
        }
    }

    public static boolean validarCampoObrigatorio(TextView tvLabelCampo, Spinner spn, TextView tvLabelErroCampo){

        String labelCampo = tvLabelCampo.getText().toString();
        String campo = spn.getSelectedItem().toString();

        if(Constantes.SELECIONE.equals(campo)){
            tvLabelErroCampo.setText("O Campo " + labelCampo + " é obrigatório!");
            return false;
        }
        else{
            tvLabelErroCampo.setText(Constantes.VAZIO);
            return true;
        }
    }

    public static boolean validarCampoMonetarioObrigatorio(TextView tvLabelCampo, EditText etCampo, TextView tvLabelErroCampo){

        String labelCampo = tvLabelCampo.getText().toString();
        String campo = etCampo.getText().toString();

        if(!Utils.isValorParaCalculoValido(campo)){
            tvLabelErroCampo.setText("O Campo " + labelCampo + " não pode ser vazio ou zerado!");
            return false;
        }
        else{
            tvLabelErroCampo.setText(Constantes.VAZIO);
            return true;
        }
    }






}
