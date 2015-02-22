package com.smartfluidicinterface.processing;

import com.smartfluidicinterface.SmartFluidicInterface;
import libsvm.svm;
import libsvm.svm_print_interface;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.LibSVM;
import weka.core.Instances;
import weka.core.SerializationHelper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Training {
  private static class SVMparameters {
    double cost;
    double gamma;

    private SVMparameters(final double cost, final double gamma) {
      this.cost = cost;
      this.gamma = gamma;
    }
  }

  public static void trainModel() {
    disableSVMOutput();

    final String sourceFolder = SmartFluidicInterface.getInstance().getDataSaver().getFolderName();
    final Instances train = getInstances(sourceFolder + "/train.arff");
    final Instances cv = getInstances(sourceFolder + "/train.arff");

    final LibSVM svm = new LibSVM();
    final SVMparameters bestSVMParam = gridSearch(svm, train, cv);

    buildBestClassifier(train, svm, bestSVMParam);

    try {
      SerializationHelper.write(sourceFolder + "/svm.model", svm);
    } catch (final Exception e) {
      SmartFluidicInterface.getInstance().getMainPanel().setMessage("Error: Training-trainModel");
      e.printStackTrace();
    }

    // Move this to test later
    /*try {
      LibSVM svm2 = (LibSVM) SerializationHelper.read(sourceFolder + "/svm.model");
      System.out.println(svm2.getCost() + "\t" + svm2.getGamma() + "\t" + svm2.getKernelType());
    } catch (Exception e) {
      e.printStackTrace();
    }*/
  }

  private static void disableSVMOutput() {
    svm.svm_set_print_string_function(new svm_print_interface() {
      @Override
      public void print(final String s) {
      }
    });
  }

  private static Instances getInstances(final String s) {
    final BufferedReader data = readData(s);

    Instances instances = null;
    try {
      instances = new Instances(data);
    } catch (final IOException e) {
      SmartFluidicInterface.getInstance().getMainPanel().setMessage("Error: Training-getInstances");
      e.printStackTrace();
    }

    assert instances != null;
    instances.setClassIndex(instances.numAttributes() - 1);

    return instances;
  }

  private static BufferedReader readData(final String filename) {
    BufferedReader inputReader = null;
    try {
      inputReader = new BufferedReader(new FileReader(filename));
    } catch (final FileNotFoundException e) {
      SmartFluidicInterface.getInstance().getMainPanel().setMessage("Error: Training-readData");
      e.printStackTrace();
    }

    return inputReader;
  }

  private static SVMparameters gridSearch(final LibSVM svm, final Instances train, final Instances cv) {
    final List<Double> accuracy = new ArrayList<Double>();
    final List<SVMparameters> svmParam = new ArrayList<SVMparameters>();

    for (int i = -5; i < 16; i++) {
      for (int j = -15; j < 4; j++) {
        final SVMparameters param = new SVMparameters(Math.pow(2, i), Math.pow(2, j));
        svmParam.add(param);
        evaluateClassifier(svm, train, cv, accuracy, param);
      }
    }

    final int bestIndex = getMaximum(accuracy);
    final SVMparameters bestParam = new SVMparameters(svmParam.get(bestIndex).cost, svmParam.get(bestIndex).gamma);

    printResults(accuracy, svmParam, bestIndex);

    return bestParam;
  }

  private static void evaluateClassifier(final LibSVM svm, final Instances train, final Instances cv,
                                         final List<Double> accuracy, final SVMparameters param) {
    svm.setCost(param.cost);
    svm.setGamma(param.gamma);

    final Evaluation eval;
    try {
      svm.buildClassifier(train);
      eval = new Evaluation(train);
      eval.evaluateModel(svm, cv);
      accuracy.add(eval.pctCorrect());
    } catch (final Exception e) {
      SmartFluidicInterface.getInstance().getMainPanel().setMessage("Error: Training-gridSearch");
      e.printStackTrace();
    }
  }

  private static int getMaximum(final List<Double> accuracy) {
    int maxIndex = 0;
    double maxValue = accuracy.get(0);
    for (int i = 1; i < accuracy.size(); i++) {
      if (accuracy.get(i) > maxValue) {
        maxValue = accuracy.get(i);
        maxIndex = i;
      }
    }

    return maxIndex;
  }

  private static void printResults(final List<Double> accuracy, final List<SVMparameters> svmParam,
                                   final int bestIndex) {
    final String message = "Best accuracy = " + accuracy.get(bestIndex) + "% when cost = "
        + svmParam.get(bestIndex).cost + " and gamma = " + svmParam.get(bestIndex).gamma + ".";
    SmartFluidicInterface.getInstance().getMainPanel().setMessage(message);
  }

  private static void buildBestClassifier(final Instances train, final LibSVM svm, final SVMparameters bestSVMParam) {
    svm.setCost(bestSVMParam.cost);
    svm.setGamma(bestSVMParam.gamma);
    try {
      svm.buildClassifier(train);
    } catch (final Exception e) {
      SmartFluidicInterface.getInstance().getMainPanel().setMessage("Error: Training-trainModel");
      e.printStackTrace();
    }
  }
}
