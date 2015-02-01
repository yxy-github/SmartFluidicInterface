package com.smartfluidicinterface.view.component;

import com.smartfluidicinterface.SmartFluidicInterface;

public final class StartButton extends AbstractButton {
  public StartButton() {
    super("Start");
  }

  @Override
  protected void doAction() {
    final String filename = SmartFluidicInterface.getInstance().getMainPanel().getFilename();

    // TODO: provide code to perform the Start action
  }
}
