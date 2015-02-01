package com.smartfluidicinterface.data.listener;

import com.phidgets.event.ErrorEvent;
import com.phidgets.event.ErrorListener;
import com.smartfluidicinterface.SmartFluidicInterface;


public class ErrorListenerUI implements ErrorListener{

  public void error(final ErrorEvent errorEvent) {
    SmartFluidicInterface.getInstance().getMainPanel().setMessage(
        errorEvent.toString() + "Bridge Error Event");
  }
}
