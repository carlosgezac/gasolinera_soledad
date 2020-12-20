package com.gasolinerasoledadsacv.view.validation;

import javax.swing.text.PlainDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

public class LimitadorCaracteres extends PlainDocument {

    private final int limite;

    public LimitadorCaracteres(int limite) {
        super();
        this.limite = limite;
    }

    @Override
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str == null) {
            return;
        }

        if ((getLength() + str.length()) <= limite) {
            super.insertString(offset, str, attr);
        } else {
            java.awt.Toolkit.getDefaultToolkit().beep();
        }
    }
}
