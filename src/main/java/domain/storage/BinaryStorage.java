package domain;

import java.io.File;
import java.io.FileOutputStream;

import java.io.ObjectOutputStream;
import java.io.Serializable;

import java.nio.file.Path;

import java.util.HashMap;

public class BinaryStorage implements StorageManager {
  private HashMap<Serializable, Serializable> DataLinks;
  private String storageName;


  public BinaryStorage(String storageName) throws IllegalArgumentException {
    if (storageName == null) {
      throw new IllegalArgumentException("storage name may not be null");
    }

    this.DataLinks = new HashMap<>();
    this.storageName = storageName;
  }

  public void put(Serializable key, Serializable value) {
    this.DataLinks.put(key,value);
  }

  public Object get(Serializable key) {
    return this.DataLinks.get(key);
  }

  private void save() {
    Path filepath = Paths.get(System.getProperty("user.dir"), storageName);
    try {
      FileOutputStream fileOut = new FileOutputStream(filepath);
      ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
      objectOut.writeObject(this.DataLinks);
      objectOut.close();
    }catch (Exception e) {
      e.printStackTrace();
    }
  }
}
