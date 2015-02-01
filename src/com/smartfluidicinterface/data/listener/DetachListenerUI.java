package com.smartfluidicinterface.data.listener;

import com.phidgets.BridgePhidget;
import com.phidgets.PhidgetException;
import com.phidgets.event.DetachEvent;
import com.phidgets.event.DetachListener;
import com.smartfluidicinterface.SmartFluidicInterface;

public class DetachListenerUI implements DetachListener {
  public void detached(final DetachEvent detachEvent) {
    try {
      final BridgePhidget detached = (BridgePhidget) detachEvent.getSource();
      final String message = detached.getDeviceName() + " is detached: " + Boolean.toString(detached.isAttached());
      SmartFluidicInterface.getInstance().getMainPanel().setMessage(message);
    } catch (final PhidgetException ex) {
      SmartFluidicInterface.getInstance().getMainPanel().setMessage(
          ex.getDescription() + "Phidget error " + ex.getErrorNumber());
    }
  }
}
