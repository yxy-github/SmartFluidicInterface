package com.smartfluidicinterface.view.component;

import com.smartfluidicinterface.SmartFluidicInterface;

public final class StopButton extends AbstractButton {
  public StopButton() {
    super("Stop");
  }

  @Override
  protected void doAction() {
    SmartFluidicInterface.getInstance().getBridge().closeBridge();
    SmartFluidicInterface.getInstance().getMainPanel().resetChart();
  }
}
