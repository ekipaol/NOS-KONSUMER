package com.application.bris.ikurma_nos_konsumer.util;

/**
 * Created by Ridho on 25/11/2019
 */

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

public class NpwpTextWatcher implements TextWatcher {

    EditText editText;

    public NpwpTextWatcher() {

    }

    public NpwpTextWatcher(EditText editText) {
        this.editText = editText;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        try
        {
            editText.removeTextChangedListener(this);
            String value = editText.getText().toString();
            int length = value.length();
            String npwpFormatted = "";

            if (value != null && !value.equalsIgnoreCase(""))
            {
                if (length == 2)
                {
                    npwpFormatted += insertString(value, ".", length - 1);
                    setString(npwpFormatted);
                }

                if (length == 6)
                {
                    npwpFormatted += insertString(value, ".", length - 1);
                    setString(npwpFormatted);
                }

                if (length == 10)
                {
                    npwpFormatted += insertString(value, ".", length - 1);
                    setString(npwpFormatted);
                }

                if (length == 12)
                {
                    npwpFormatted += insertString(value, "-", length - 1);
                    setString(npwpFormatted);
                }

                if (length == 16)
                {
                    npwpFormatted += insertString(value, ".", length - 1);
                    setString(npwpFormatted);
                }
            }
            editText.addTextChangedListener(this);
            return;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            Log.d("Error Catch", ex.getMessage());
            editText.addTextChangedListener(this);

        }

    }

    public static String insertString(String original, String inserted, int index)
    {
        String newString = new String();

        for (int i = 0; i < original.length(); i++)
        {
            newString += original.charAt(i);

            if (i == index)
            {
                newString += inserted;
            }
        }

        return newString;
    }

    public void setString(String value)
    {
        if (!value.equalsIgnoreCase(""))
        {
            editText.setText(value);
        }
        editText.setSelection(editText.getText().toString().length());
    }
}
