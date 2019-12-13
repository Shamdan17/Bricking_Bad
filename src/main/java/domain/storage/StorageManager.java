package domain.storage;

import java.io.Serializable;
import java.util.List;

public interface StorageManager {
    public void put(Serializable key, Serializable value);

    public Object get(Serializable key);
    
	public boolean contains(Serializable key);

    public List<Object> getRecords();

    public void load();
}
