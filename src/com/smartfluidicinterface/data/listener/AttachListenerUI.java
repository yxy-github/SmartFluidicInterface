package com.smartfluidicinterface.data.listener;

import com.phidgets.BridgePhidget;
import com.phidgets.PhidgetException;
import com.phidgets.event.AttachEvent;
import com.phidgets.event.AttachListener;
import com.smartfluidicinterface.SmartFluidicInterface;

public class AttachListenerUI implements AttachListener {
  public void attached(final AttachEvent attachEvent) {
    try {
      final BridgePhidget attached = (BridgePhidget) attachEvent.getSource();
      final String message = attached.getDeviceName() + " is attached: " + Boolean.toString(attached.isAttached());
      SmartFluidicInterface.getInstance().getMainPanel().setMessage(message);
    } catch (final PhidgetException ex) {
      SmartFluidicInterface.getInstance().getMainPanel().setMessage(
          ex.getDescription() + "Phidget error " + ex.getErrorNumber());
    }
  }
}
