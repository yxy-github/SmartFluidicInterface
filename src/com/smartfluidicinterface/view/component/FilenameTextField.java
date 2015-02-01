package com.smartfluidicinterface.view.component;

import javax.swing.*;
import java.awt.*;

public final class FilenameTextField extends JPanel {
  private final JLabel fieldLabel = new JLabel("Filename: ");
  private final JTextField textField = new JTextField();

  public FilenameTextField() {
    super(new BorderLayout());

    textField.setPreferredSize(new Dimension(200, 20));

    add(fieldLabel, BorderLayout.WEST);
    add(textField, BorderLayout.EAST);
  }

  public String getText() {
    return textField.getText();
  }
}
