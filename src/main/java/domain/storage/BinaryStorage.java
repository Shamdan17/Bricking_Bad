package domain;

import java.io.Serializable;
import java.util.HashMap;
import java.io.File;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class BinaryStorage implements StorageManager {
  private HashMap<Serializable, Serializable> DataLinks;


  public BinaryStorage() {
    DataLinks = new HashMap<>();
  }

  public void put(Serializable key, Serializable value) {
      this.DataLinks.put(key,value);
  }

  public Object get(Serializable key) {
     return this.DataLinks.get(key);
  }

  private void save() {
    // TODO: Serialize the Datalinks and save them into a file
  }
}
