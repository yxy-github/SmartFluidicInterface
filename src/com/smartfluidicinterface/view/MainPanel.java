package com.smartfluidicinterface.view;

import com.smartfluidicinterface.view.component.*;

import javax.swing.*;

public final class MainPanel extends JPanel {
  private static final int CHART_X = 5;
  private static final int CHART_Y = 5;

  private final StartButton startButton = new StartButton(650, 100);
  private final StopButton stopButton = new StopButton(750, 100);
  private final TrainButton trainButton = new TrainButton(650, 200);
  private final TestButton testButton = new TestButton(750, 200);
  private final FilenameTextField filenameTextField = new FilenameTextField(650, 50);
  private final JLabel message = new JLabel();
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
    add(trainButton);
    add(testButton);
  }

  private void addFields() {
    add(filenameTextField);
  }

  private void addChart() {
    add(chart);
  }

  private void addMessage() {
    message.setBounds(650, 150, 600, 20);
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

  public StartButton getStartButton() {
    return startButton;
  }

  public StopButton getStopButton() {
    return stopButton;
  }

  public TrainButton getTrainButton() {
    return trainButton;
  }

  public TestButton getTestButton() {
    return testButton;
  }

  public void resetChart() {
    remove(chart);
    chart = new Chart(CHART_X, CHART_Y);
    add(chart);
  }
}
