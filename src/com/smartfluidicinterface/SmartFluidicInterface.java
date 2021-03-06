package com.smartfluidicinterface;

import com.smartfluidicinterface.common.Constants;
import com.smartfluidicinterface.data.reader.Bridge;
import com.smartfluidicinterface.data.reader.DataSaver;
import com.smartfluidicinterface.view.MainPanel;
import nl.jj.swingx.gui.modal.JModalFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public final class SmartFluidicInterface {
  private static SmartFluidicInterface instance;

  private final JModalFrame applicationFrame = new JModalFrame(Constants.APPLICATION_NAME);
  private final MainPanel mainPanel = new MainPanel();
  private final DataSaver dataSaver = new DataSaver();
  private final Bridge bridge = new Bridge();

  private SmartFluidicInterface() {
    initApplicationFrame();
  }

  public static SmartFluidicInterface getInstance() {
    if (instance == null) {
      instance = new SmartFluidicInterface();
    }
    return instance;
  }

  public MainPanel getMainPanel() {
    return mainPanel;
  }

  public DataSaver getDataSaver() {
    return dataSaver;
  }

  public Bridge getBridge() {
    return bridge;
  }

  private void initApplicationFrame() {
    applicationFrame.setPreferredSize(new Dimension(640, 480));
    applicationFrame.setExtendedState(Frame.MAXIMIZED_BOTH);

    final JPanel applicationContentPanel = new JPanel();
    applicationContentPanel.setLayout(new BorderLayout());
    applicationFrame.setContentPane(applicationContentPanel);

    applicationContentPanel.add(mainPanel);

    applicationFrame.addWindowListener(new WindowAdapter() {
      public void windowClosing(final WindowEvent e) {
        exit();
      }
    });
  }

  private void exit() {
    applicationFrame.dispose();
    System.exit(0);
  }

  private void show() {
    applicationFrame.setVisible(true);
  }

  public static void main(final String[] args) {
    getInstance().show();
  }
}
