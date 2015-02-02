package com.smartfluidicinterface.view.component;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class AbstractButton extends JButton {

  public AbstractButton(final String buttonText, final int x, final int y) {
    super(buttonText);
    addActionListener(new ButtonActionListener());
    setBounds(x, y, 90, 20);
  }

  /**
   * Performs the action when the button is clicked.
   */
  protected abstract void doAction();

  private final class ButtonActionListener implements ActionListener {

    @Override
    public void actionPerformed(final ActionEvent e) {
      doAction();
    }
  }
}
