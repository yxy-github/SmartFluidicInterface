package com.smartfluidicinterface.data.listener;

import com.phidgets.event.BridgeDataEvent;
import com.phidgets.event.BridgeDataListener;
import com.smartfluidicinterface.SmartFluidicInterface;
import com.smartfluidicinterface.processing.Testing;
import info.monitorenter.gui.chart.ITrace2D;
import weka.classifiers.functions.LibSVM;
import weka.core.Instances;

public class DataListenerUI implements BridgeDataListener {
  private long startTime;
  int count = 0;
  boolean flag;
  String features = "";
  double[] featureVector = new double[4];

  LibSVM svm;
  Instances test;

  private static class Param {
    double m;
    double c;
    private Param(final double m, final double c) {
     this.m = m;
     this.c = c;
    }
  }

  public DataListenerUI(final boolean isTest) {
    flag = isTest;
    if (isTest) {
      svm = Testing.loadSVMModel();
      test = Testing.createInstance(2, 4);
    }
  }

  public void bridgeData(final BridgeDataEvent dataEvent) {
    final int channel = dataEvent.getIndex();
    final double voltage = dataEvent.getValue();
    final ITrace2D[] traces = SmartFluidicInterface.getInstance().getMainPanel().getChart().getITraces();

    count++;
    final Param paramCalibration = getParam(channel);
    final double voltageCalibrate = paramCalibration.m * voltage + paramCalibration.c;
    conditionString(channel, voltageCalibrate);

    traces[channel].addPoint(((double) System.currentTimeMillis() - startTime) / 1000, voltageCalibrate);

    if (channel == 3) {
      if (flag) {
        test = Testing.setInstanceValue(test, featureVector);
        System.out.println(Testing.classify(test, svm));
      }
      SmartFluidicInterface.getInstance().getDataSaver().saveData(features);
      features = "";
    }
  }

  private static Param getParam(final int channel) {
    final Param paramCalibration;
    switch (channel) {
      case 0:
        paramCalibration = new Param(0.0051, -0.0224);
        break;
      case 1:
        paramCalibration = new Param(0.0052, -0.0846);
        break;
      case 2:
        paramCalibration = new Param(0.0051, -0.0056);
        break;
      case 3:
        paramCalibration = new Param(0.0050, -0.0153);
        break;
      default:
        paramCalibration = new Param(0, 0);
    }
    return paramCalibration;
  }

  private void conditionString(final int channel, final double voltageCalibrate) {
    featureVector[channel] = voltageCalibrate;
    if (channel == 3) {
      features += Double.toString(voltageCalibrate) + "\n";
    }
    else {
      features += Double.toString(voltageCalibrate) + ",";
    }

    // Remove the feature if the first channel is not 0
    if (count == 1 && channel != 0) {
      featureVector = clearVector();
      features = "";
      count--;
    }
  }

  private double[] clearVector() {
    for (int i =0; i < featureVector.length; i++) {
      featureVector[i] = 0;
    }
    return featureVector;
  }

  public void setStartTime(final long startTime) {
    this.startTime = startTime;
  }
}
