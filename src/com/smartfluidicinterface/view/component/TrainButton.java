package com.smartfluidicinterface.view.component;

import com.smartfluidicinterface.SmartFluidicInterface;
import com.smartfluidicinterface.processing.Preprocessing;
import com.smartfluidicinterface.processing.Training;

public final class TrainButton extends AbstractButton{
  public TrainButton(final int x, final int y) {
    super("Train", x, y);
  }

  @Override
  protected void doAction() {
    preprocessData();
    Training.trainModel();
  }

  private static void preprocessData() {
    final String sourceFolder = SmartFluidicInterface.getInstance().getDataSaver().getFolderName();
    final Preprocessing preprocess = new Preprocessing(sourceFolder + "/train", 2);
    preprocess.mergeFiles(sourceFolder);
    preprocess.convertCSVtoARFF(sourceFolder + "/train");
  }
}
