package com.atimports.validator;

import android.support.design.widget.TextInputLayout;

public class RequiredFieldValidator extends BaseFieldValidator {

    public RequiredFieldValidator(TextInputLayout errorContainer) {
        super(errorContainer);
        mEmptyMessage = "Esse campo é obrigatório";
    }

    @Override
    protected boolean isValid(CharSequence charSequence) {
        return charSequence != null && charSequence.length() > 0;
    }
}
