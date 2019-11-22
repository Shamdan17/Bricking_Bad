package domain;

import java.io.Serializable;
import java.util.List;

public interface StorageManager {
   public void   put(Serializable key, Serializable value);
   public Object get(Serializable key);

   public List<Object> getRecords();

   public void   load();
}
