package com.smartfluidicinterface.view;

import com.smartfluidicinterface.view.component.FilenameTextField;
import com.smartfluidicinterface.view.component.StartButton;
import com.smartfluidicinterface.view.component.StopButton;

import javax.swing.*;

public final class MainPanel extends JPanel {
  private final StartButton startButton = new StartButton();
  private final StopButton stopButton = new StopButton();
  private final FilenameTextField filenameTextField = new FilenameTextField();
  private final JLabel message = new JLabel("Test");
  private Chart chart = new Chart();

  public MainPanel() {
    addButtons();
    addFields();
    addChart();
    addMessage();
  }

  private void addButtons() {
    add(startButton);
    add(stopButton);
  }

  private void addFields() {
    add(filenameTextField);
  }

  private void addChart() {
    add(chart);
  }

  private void addMessage() {
    add(message);
  }

  public void setMessage(final String message) {
    this.message.setText(message);
  }

  public String getFilename() {
    return filenameTextField.getText();
  }

  public Chart getChart() {
    return chart;
  }

  public void resetChart() {
    remove(chart);
    chart = new Chart();
    add(chart);
  }
}
