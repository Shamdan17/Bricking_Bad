package domain.storage;

import java.io.Serializable;
import java.util.Set;

/**
 * StorageManager represents an on disk key value store that the game can use to have
 * persistent storage
 */
public interface StorageManager {

    /**
     * @param key   the identifier of saved data
     * @param value the actual data to be saved
     */
    public void put(String key, Serializable value);

    /**
     * @param key the identifier of saved data
     * @return the saved data associated with the given key
     */
    public Object get(String key);

    /**
     * @param key the identifier of saved data
     * @return a boolean indicating if an entry with the given key exists or not
     */
    public boolean contains(String key);

    /**
     * @return a set of saved entry keys
     */
    public Set<String> keySet();

    /**
     * syncs the storage with disk and closes io
     */
    public void close();
}
