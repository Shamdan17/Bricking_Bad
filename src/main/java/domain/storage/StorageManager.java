package domain;

import java.io.Serializable;

public interface StorageManager {
   public void   put(Serializable key, Serializable value);
   public Object get(Serializable key);
}
