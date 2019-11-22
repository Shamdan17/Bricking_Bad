package domain;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;

import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

import java.nio.file.Path;
import java.nio.file.Paths;

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

  public void put(Serializable key, Serializable value) throws IllegalArgumentException {
    if (key == null || value == null) {
      throw new IllegalArgumentException("storage name may not be null");
    }

    this.DataLinks.put(key,value);
  }

  public Object get(Serializable key) throws IllegalArgumentException {
    if (key == null) {
      throw new IllegalArgumentException("storage name may not be null");
    }

    return this.DataLinks.get(key);
  }

  public void save() {
    Path filepath = Paths.get(System.getProperty("user.dir"), storageName);
    try {
      FileOutputStream fileOut = new FileOutputStream(filepath.toString());
      ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
      objectOut.writeObject(this.DataLinks);
      objectOut.close();
    }catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void load() {
    Path filepath = Paths.get(System.getProperty("user.dir"), storageName);

    try {
      FileInputStream fis = new FileInputStream(filepath.toString());
      ObjectInputStream ois = new ObjectInputStream(fis);
      DataLinks = (HashMap<Serializable, Serializable>) ois.readObject();
      ois.close();
    }catch (Exception e) {
      e.printStackTrace();
    }
  }
}
