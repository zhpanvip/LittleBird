package project.graduate.zhpan.littlebird.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by zhpan on 2016/12/25.
 * 输入100以内的浮点数 保留两位小数
 */

public class GradeTextWatcher implements TextWatcher {
    EditText mEditText;

    public GradeTextWatcher(EditText mEditText) {
        this.mEditText = mEditText;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {
    }

    @Override
    public void onTextChanged(CharSequence s, int i, int i1, int i2) {
        if ("".equals(s.toString())) {
            return;
        }
        if (s.toString().contains(".")) {
            if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                s = s.toString().subSequence(0,
                        s.toString().indexOf(".") + 3);
                mEditText.setText(s);
                mEditText.setSelection(s.length());
            }
        }
        if (s.toString().trim().substring(0).equals(".")) {
            s = "0" + s;
            mEditText.setText(s);
            mEditText.setSelection(2);
        }
        if (Float.parseFloat(s.toString()) > 100) {
            mEditText.setText(s.subSequence(0, s.length() - 1));
            mEditText.setSelection(s.length() - 1);
        }

        if (s.toString().startsWith("0")
                && s.toString().trim().length() > 1) {
            if (!s.toString().substring(1, 2).equals(".")) {
                mEditText.setText(s.subSequence(0, 1));
                mEditText.setSelection(1);
                return;
            }
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
