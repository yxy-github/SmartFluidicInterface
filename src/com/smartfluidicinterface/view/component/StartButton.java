package com.smartfluidicinterface.view.component;

import com.smartfluidicinterface.SmartFluidicInterface;

public final class StartButton extends AbstractButton {
  public StartButton(final int x, final int y) {
    super("Start", x, y);
  }

  @Override
  protected void doAction() {
    this.setEnabled(false);
    final long startTime = System.currentTimeMillis();
    SmartFluidicInterface.getInstance().getDataSaver().createFile(SmartFluidicInterface.getInstance().getMainPanel().getFilename());
    SmartFluidicInterface.getInstance().getDataSaver().createHeader("C1,C2,C3,C4\n");
    SmartFluidicInterface.getInstance().getBridge().startBridge(startTime, false);
    SmartFluidicInterface.getInstance().getMainPanel().getStopButton().setEnabled(true);
  }
}
