package com.smartfluidicinterface.data.reader;

import com.phidgets.BridgePhidget;
import com.phidgets.PhidgetException;
import com.smartfluidicinterface.SmartFluidicInterface;
import com.smartfluidicinterface.data.listener.AttachListenerUI;
import com.smartfluidicinterface.data.listener.DataListenerUI;
import com.smartfluidicinterface.data.listener.DetachListenerUI;
import com.smartfluidicinterface.data.listener.ErrorListenerUI;

public class Bridge {
  private BridgePhidget bridge;
  private ErrorListenerUI errorListener;
  private DataListenerUI dataListener;

  public void startBridge(final long startTime, final boolean isTest) {
    try {
      bridge = new BridgePhidget();

      final AttachListenerUI attachListener = new AttachListenerUI();
      final DetachListenerUI detachListener = new DetachListenerUI();
      errorListener = new ErrorListenerUI();
      dataListener = new DataListenerUI(isTest);

      bridge.addAttachListener(attachListener);
      bridge.addDetachListener(detachListener);
      bridge.addErrorListener(errorListener);

      // Open any device
      bridge.openAny();
      SmartFluidicInterface.getInstance().getMainPanel().setMessage(
          "Waiting for the Phidget Bridge to be attached...\n");
      bridge.waitForAttachment();

      //Thread.sleep(100);

      // Enable bridge, set gain/data rate
      for (int i = 0; i < bridge.getInputCount(); i++) {
        bridge.setEnabled(i, true);
        bridge.setGain(i, BridgePhidget.PHIDGET_BRIDGE_GAIN_1);
      }
      bridge.setDataRate(128);

      dataListener.setStartTime(startTime);
      bridge.addBridgeDataListener(dataListener);
    } catch (final PhidgetException e) {
      SmartFluidicInterface.getInstance().getMainPanel().setMessage(
          "Phidget Error" + e.getDescription() + e.getErrorNumber());
      e.printStackTrace();
    }
  }

  public void closeBridge() {
    try {
      bridge.removeBridgeDataListener(dataListener);
      bridge.removeErrorListener(errorListener);
      bridge.close();
      bridge = null;
      SmartFluidicInterface.getInstance().getMainPanel().setMessage("Turning off Phidget Bridge");
    } catch (final PhidgetException e) {
      SmartFluidicInterface.getInstance().getMainPanel().setMessage(
          "Phidget Error" + e.getDescription() + e.getErrorNumber());
      e.printStackTrace();
    }
  }
}
