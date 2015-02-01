package com.smartfluidicinterface.view;

import com.smartfluidicinterface.view.component.FilenameTextField;
import com.smartfluidicinterface.view.component.StartButton;
import com.smartfluidicinterface.view.component.StopButton;

import javax.swing.*;

public final class MainPanel extends JPanel {
  private final StartButton startButton = new StartButton();
  private final StopButton stopButton = new StopButton();
  private final FilenameTextField filenameTextField = new FilenameTextField();

  public MainPanel() {
    addButtons();
    addFields();
    addChart();
  }

  private void addButtons() {
    add(startButton);
    add(stopButton);
  }

  private void addFields() {
    add(filenameTextField);
  }

  private void addChart() {
    add(new Chart());
  }

  public String getFilename() {
    return filenameTextField.getText();
  }
}
