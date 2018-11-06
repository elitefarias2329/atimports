package com.atimports.validator;

import android.support.design.widget.TextInputLayout;

public class BaseFieldValidator {

    protected TextInputLayout mErrorContainer;
    protected String mErrorMessage = "";
    protected String mEmptyMessage = "Esse Campo é obrigatório";

    public BaseFieldValidator(TextInputLayout errorContainer) {
        mErrorContainer = errorContainer;
    }

    protected boolean isValid(CharSequence charSequence) {
        return true;
    }

    public boolean validate(CharSequence charSequence) {
        if (mEmptyMessage != null && (charSequence == null || charSequence.length() == 0)) {
            //set Empty error message for any empty field
            mErrorContainer.setError(mEmptyMessage);
            return false;
        } else if (isValid(charSequence)) {
            //custom implementation of the isValid returns true
            mErrorContainer.setError("");
            return true;
        } else {
            //set error for any other case
            mErrorContainer.setError(mErrorMessage);
            return false;
        }
    }

    public boolean confirm(CharSequence s1, CharSequence s2) {
        return s1.equals(s2);
    }
}
