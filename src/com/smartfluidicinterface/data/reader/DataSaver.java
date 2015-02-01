package com.smartfluidicinterface.data.reader;

import com.smartfluidicinterface.SmartFluidicInterface;

import java.io.*;

public class DataSaver {
  private File file;

  public File createFile() {
    final String filename = SmartFluidicInterface.getInstance().getMainPanel().getFilename();
    File folder = null;
    String folderString = null;

    if (filename.lastIndexOf("/") >= 0) {
      folderString = filename.substring(0, filename.lastIndexOf("/"));
    }

    if (folderString != null) {
      folder = new File(folderString);
    }

    file = new File(filename + ".csv");
    if (file.exists()) {
      file.delete();
    }

    try {
      folder.mkdirs();
      file.createNewFile();
    }
    catch (FileNotFoundException e) {
      SmartFluidicInterface.getInstance().getMainPanel().setMessage("File not found.");
      e.printStackTrace();
    }
    catch (IOException e) {
      SmartFluidicInterface.getInstance().getMainPanel().setMessage("Something went wrong.");
      e.printStackTrace();
    }
    return file;
  }

  public void saveData(String s) {
    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
      writer.write(s);
      writer.flush();
      writer.close();
    }
    catch (FileNotFoundException e) {
      SmartFluidicInterface.getInstance().getMainPanel().setMessage("File not found.");
      e.printStackTrace();
    }
    catch(IOException e) {
      SmartFluidicInterface.getInstance().getMainPanel().setMessage("Something went wrong.");
      e.printStackTrace();
    }
  }
}
