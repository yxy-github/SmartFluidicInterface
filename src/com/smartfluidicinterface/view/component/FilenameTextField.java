package com.smartfluidicinterface.view.component;

import javax.swing.*;
import java.awt.*;

public final class FilenameTextField extends JPanel {
  private final JTextField textField = new JTextField();

  public FilenameTextField(final int x, final int y) {
    super(new BorderLayout());
    setBounds(x, y, 300, 20);

    textField.setPreferredSize(new Dimension(200, 20));

    final JLabel fieldLabel = new JLabel("Filename: ");
    add(fieldLabel, BorderLayout.WEST);
    add(textField, BorderLayout.CENTER);
  }

  public String getText() {
    return textField.getText();
  }
}
