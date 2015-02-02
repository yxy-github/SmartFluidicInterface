package com.smartfluidicinterface.view;

import com.smartfluidicinterface.view.component.FilenameTextField;
import com.smartfluidicinterface.view.component.StartButton;
import com.smartfluidicinterface.view.component.StopButton;

import javax.swing.*;

public final class MainPanel extends JPanel {
  private static final int CHART_X = 10;
  private static final int CHART_Y = 10;

  private final StartButton startButton = new StartButton(650, 100);
  private final StopButton stopButton = new StopButton(750, 100);
  private final FilenameTextField filenameTextField = new FilenameTextField(650, 50);
  private final JLabel message = new JLabel("Test");
  private Chart chart = new Chart(CHART_X, CHART_Y);

  public MainPanel() {
    setLayout(null);

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
    message.setBounds(650, 150, 300, 20);
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
    chart = new Chart(CHART_X, CHART_Y);
    add(chart);
  }
}
