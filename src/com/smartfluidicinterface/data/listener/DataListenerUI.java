package com.smartfluidicinterface.data.listener;

import com.phidgets.event.BridgeDataEvent;
import com.phidgets.event.BridgeDataListener;
import com.smartfluidicinterface.SmartFluidicInterface;
import info.monitorenter.gui.chart.ITrace2D;

public class DataListenerUI implements BridgeDataListener {
  private long startTime;

  public void bridgeData(final BridgeDataEvent dataEvent) {
    final int channel = dataEvent.getIndex();
    final double voltage = dataEvent.getValue();
    final double[] voltageCalibrate = new double[2];
    final ITrace2D[] traces = SmartFluidicInterface.getInstance().getMainPanel().getChart().getITraces();

    double m = 0;
    double c = 0;
    voltageCalibrate[0] = channel;
    String s = String.valueOf(channel) + (", ");
    switch (channel) {
      case 0:
        m = 0.0051;
        c = -0.0224;
     //   voltageCalibrate[1] = 0.0051 * voltage - 0.0224;
      //  traces[0].addPoint(((double) System.currentTimeMillis() - startTime) / 1000, voltageCalibrate[1]);
        break;
      case 1:
        m = 0.0052;
        c = -0.0846;
      //  voltageCalibrate[1] = 0.0052 * voltage - 0.0846;
      //  traces[1].addPoint(((double) System.currentTimeMillis() - startTime) / 1000, voltageCalibrate[1]);
        break;
      case 2:
        m = 0.0051;
        c = -0.0056;
       // voltageCalibrate[1] = 0.0051 * voltage - 0.0056;
       // traces[2].addPoint(((double) System.currentTimeMillis() - startTime) / 1000, voltageCalibrate[1]);
        break;
      case 3:
        m = 0.0050;
        c = -0.0153;
        //voltageCalibrate[1] = 0.0050 * voltage - 0.0153;
        //traces[3].addPoint(((double) System.currentTimeMillis() - startTime) / 1000, voltageCalibrate[1]);
        break;
    }
    voltageCalibrate[1] = m * voltage + c;
    traces[channel].addPoint(((double) System.currentTimeMillis() - startTime) / 1000, voltageCalibrate[1]);
    s += Double.toString(voltageCalibrate[1]) + "\n";
    SmartFluidicInterface.getInstance().getDataSaver().saveData(s);
  }

  public void setStartTime(final long startTime) {
    this.startTime = startTime;
  }
}
