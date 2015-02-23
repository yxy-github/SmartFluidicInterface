package com.smartfluidicinterface.view.component;

import com.smartfluidicinterface.SmartFluidicInterface;

public class TestButton extends AbstractButton{
  public TestButton(final int x, final int y) {
    super("Test", x, y);
  }

  @Override
  protected void doAction() {
    final long startTime = System.currentTimeMillis();
    SmartFluidicInterface.getInstance().getDataSaver().createFile(SmartFluidicInterface.getInstance().getMainPanel().getFilename());
    SmartFluidicInterface.getInstance().getDataSaver().createHeader("C1,C2,C3,C4\n");
    SmartFluidicInterface.getInstance().getBridge().startBridge(startTime, true);
    SmartFluidicInterface.getInstance().getMainPanel().getStopButton().setEnabled(true);
  }
}
