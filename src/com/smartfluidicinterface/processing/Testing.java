package com.smartfluidicinterface.processing;

import com.smartfluidicinterface.SmartFluidicInterface;
import weka.classifiers.functions.LibSVM;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;
import weka.core.SerializationHelper;

import java.util.ArrayList;

public class Testing {

  public static LibSVM loadSVMModel() {
    LibSVM svm = null;
    final String sourceFolder = SmartFluidicInterface.getInstance().getDataSaver().getFolderName();
    try {
      svm = (LibSVM) SerializationHelper.read(sourceFolder + "/svm.model");
      System.out.println(svm.getCost() + "\t" + svm.getGamma() + "\t" + svm.getKernelType());
    } catch (final Exception e) {
      SmartFluidicInterface.getInstance().getMainPanel().setMessage("Error: Testing-loadModel.");
      e.printStackTrace();
    }
    return svm;
  }

  public static String classify(final Instances test, final LibSVM svm) {
    double label = 0;
    try {
      label = svm.classifyInstance(test.firstInstance());
    } catch (final Exception e) {
      e.printStackTrace();
    }
    return test.classAttribute().value((int) label);
  }

  public static Instances setInstanceValue(final Instances test, final double[] featureVector) {
    for (int i = 0; i < featureVector.length; i++) {
      test.get(0).setValue(i, featureVector[i]);
    }
    return test;
  }

  public static Instances createInstance(final int nClass, final int nFeature) {
    final ArrayList<String> classLabel = getClassLabel(nClass);
    final ArrayList<Attribute> attribute = getAttributes(nFeature, classLabel);
    final Instances test = new Instances("TestInstances", attribute, 1);

    final double[] value = new double[test.numAttributes()];
    final DenseInstance denseInstance = new DenseInstance(1.0, value);
    test.add(new DenseInstance(1.0, value));
    test.setClassIndex(test.numAttributes() - 1);

    return test;
  }

  private static ArrayList<String> getClassLabel(final int nClass) {
    final ArrayList<String> classLabel = new ArrayList<String>();
    for (int i = 0; i < nClass; i++) {
      classLabel.add("c" + i);
    }
    return classLabel;
  }

  private static ArrayList<Attribute> getAttributes(final int nFeature, final ArrayList<String> classLabel) {
    final ArrayList<Attribute> attribute = new ArrayList<Attribute>();
    for (int i = 0; i < nFeature; i++) {
      attribute.add(new Attribute("C" + i));
    }
    attribute.add(new Attribute("@@class@@", classLabel));
    return attribute;
  }

}