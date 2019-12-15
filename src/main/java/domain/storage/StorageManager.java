package domain.storage;

import java.io.Serializable;
import java.util.List;

/**
 * StorageManager represents a key value store that the game can use to have
 * persistent storage
 */
public interface StorageManager {
	
	/**
	 * 
	 * @param key
	 * @param value
	 */
    public void   put(Serializable key, Serializable value);
    
    /**
     * 
     * @param key
     * @return
     */
    public Object get(Serializable key);

    /**
     * 
     * @param key
     * @return
     */
	public boolean contains(Serializable key);
}
