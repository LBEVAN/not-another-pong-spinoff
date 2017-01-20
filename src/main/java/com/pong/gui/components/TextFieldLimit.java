package com.pong.gui.components;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * Character limiter for a JTextField component.
 *
 * @author LBEVAN
 */
public class TextFieldLimit extends PlainDocument {

    // region data
    private int limit;
    // endregion

    // region init
    /**
     * Constructor.
     *
     * @param limit
     */
    public TextFieldLimit(int limit) {
        super();
        this.limit = limit;
    }
    // endregion

    // region private API
    /**
     * Insert the string, given the offset.
     *
     * @param offset
     * @param str
     * @param attr
     * @throws BadLocationException
     */
    public void insertString( int offset, String  str, AttributeSet attr ) throws BadLocationException {
        if (str == null) return;

        if ((getLength() + str.length()) <= limit) {
            super.insertString(offset, str, attr);
        }
    }
    // endregion
}
