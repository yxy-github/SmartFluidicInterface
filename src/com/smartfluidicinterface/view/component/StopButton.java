package com.smartfluidicinterface.view.component;

import com.smartfluidicinterface.SmartFluidicInterface;

public final class StopButton extends AbstractButton {
  public StopButton(final int x, final int y) {
    super("Stop", x, y);
  }

  @Override
  protected void doAction() {
    SmartFluidicInterface.getInstance().getBridge().closeBridge();
    SmartFluidicInterface.getInstance().getMainPanel().resetChart();
  }
}
