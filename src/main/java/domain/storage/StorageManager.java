package domain.storage;

import java.io.Serializable;

/**
 * StorageManager represents a key value store that the game can use to have
 * persistent storage
 */
public interface StorageManager {

    /**
     * @param key   the identifier of saved data
     * @param value the actual data to be saved
     */
    public void put(Serializable key, Serializable value);

    /**
     * @param key the identifier of saved data
     * @return the saved data associated with the given key
     */
    public Object get(Serializable key);

    /**
     * @param key the identifier of saved data
     * @return a boolean indicating if an entry with the given key exists or not
     */
    public boolean contains(Serializable key);
}
