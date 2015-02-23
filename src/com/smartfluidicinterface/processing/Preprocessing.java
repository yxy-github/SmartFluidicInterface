package com.smartfluidicinterface.processing;

import com.smartfluidicinterface.SmartFluidicInterface;
import com.smartfluidicinterface.data.reader.DataSaver;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Preprocessing {
  String filename;
  int numberClass;

  public Preprocessing(final String s, final int n) {
    filename = s;
    numberClass = n;
  }

  public void mergeFiles(final String sourceFolder){
    final DataSaver mergeSaver = new DataSaver();
    mergeSaver.createFile(filename);
    mergeSaver.createHeader("C1,C2,C3,C4,class\n");

    final String sourceFilename = sourceFolder + "/data";
    merge(mergeSaver, sourceFilename);
  }

  private void merge(final DataSaver mergeSaver, final String sourceFilename) {
    final ArrayList<File> fileList = new ArrayList<File>();
    for (int i = 0; i < numberClass; i++) {
      System.out.println("Reading " + sourceFilename + String.valueOf(i) + ".csv");
      fileList.add(new File(sourceFilename + String.valueOf(i) + ".csv"));
      try {
        final BufferedReader reader = new BufferedReader(new FileReader(fileList.get(i)));
        String line = reader.readLine();
        while (line != null) {
          line = reader.readLine();
          if (line != null) {
            line = line + ",c" + i + "\n";
            mergeSaver.saveData(line);
          }
        }
      } catch (final Exception e) {
        SmartFluidicInterface.getInstance().getMainPanel().setMessage("Error: Preprocessing-mergeFiles");
        e.printStackTrace();
      }
    }
  }

  public void convertCSVtoARFF(final String s) {
    final File file = new File(s + ".csv");
    final Instances instances = loadCSV(file);

    final File fileNew = new File(s + ".arff");
    saveARFF(fileNew, instances);
    SmartFluidicInterface.getInstance().getMainPanel().setMessage("CSV file was successfully converted to ARFF file");
  }

  private static Instances loadCSV(final File file) {
    final CSVLoader loader = new CSVLoader();
    Instances instances = null;
    try {
      loader.setSource(file);
      instances = loader.getDataSet();
    } catch (final IOException e) {
      SmartFluidicInterface.getInstance().getMainPanel().setMessage("Error: Preprocessing - loadCSV.");
      e.printStackTrace();
    }
    return instances;
  }

  private static void saveARFF(final File fileNew, final Instances instances) {
    final ArffSaver saver = new ArffSaver();
    saver.setInstances(instances);
    try {
      saver.setFile(fileNew);
      saver.writeBatch();
    } catch (final Exception e) {
      SmartFluidicInterface.getInstance().getMainPanel().setMessage("Error: Preprocessing - saveARFF.");
      e.printStackTrace();
    }
  }
}
