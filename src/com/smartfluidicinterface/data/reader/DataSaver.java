package com.smartfluidicinterface.data.reader;

import com.smartfluidicinterface.SmartFluidicInterface;

import java.io.*;

public class DataSaver {
  private File file;
  String folderString;

  public File createFile(final String filename) {
    File folder = null;
    folderString = null;

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
      if (folder != null) {
        folder.mkdirs();
      }
      file.createNewFile();
    }
    catch (final FileNotFoundException e) {
      SmartFluidicInterface.getInstance().getMainPanel().setMessage("File not found.");
      e.printStackTrace();
    }
    catch (final IOException e) {
      SmartFluidicInterface.getInstance().getMainPanel().setMessage("Error: DataSaver-createFile.");
      e.printStackTrace();
    }

    return file;
  }

  public String getFolderName() {
    if (folderString == null) {
      folderString = "data";
    }
    return folderString;
  }

  public void createHeader(final String s) {
    saveData(s);
  }

  public void saveData(final String s) {
    try {
      final BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
      writer.write(s);
      writer.flush();
      writer.close();
    }
    catch (final FileNotFoundException e) {
      SmartFluidicInterface.getInstance().getMainPanel().setMessage("File not found.");
      e.printStackTrace();
    }
    catch(final IOException e) {
      SmartFluidicInterface.getInstance().getMainPanel().setMessage("Something went wrong.");
      e.printStackTrace();
    }
  }
}
