package domain.storage;

import utils.Constants;

import java.io.*;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Set;

// BinaryStorage is a storage manager that works as a generic persistent key value store.

public class BinaryStorage implements StorageManager {

    private HashMap<String, Serializable> DataLinks;
    private String storageName;

    /**
     * OVERVIEW: constructor for BinaryStorage MODIFIES: storageName, DataLinks
     * EFFECT: creates new instance of BinaryStorage using the given persistent file
     * name
     *
     * @param storageName the name of the file in which the data is saved
     */
    public BinaryStorage(String storageName) throws IllegalArgumentException {
        if (storageName == null || storageName.trim().equals("")) {
            throw new IllegalArgumentException("storage name may not be null or empty");
        }
        createSavesFolder();

        this.DataLinks = new HashMap<>();
        this.storageName = Paths.get(getSavesFolder(), storageName).toString() + ".bin";

        // If a previous version of the same storage name exists load it
        load();
    }

    /**
     * OVERVIEW: Adds key value pair to the storage MODIFIES: DataLinks EFFECT: puts
     * new key value pair to the BinaryStorage
     *
     * @param key   the identifier of saved data
     * @param value the actual data to be saved
     */
    public void put(String key, Serializable value) throws IllegalArgumentException {
        if (key == null || value == null) {
            throw new IllegalArgumentException("storage name may not be null");
        }
        this.DataLinks.put(key, value);
        save();
    }

    /**
     * OVERVIEW: gets a value via its key form the storage MODIFIES: Nothing EFFECT:
     * uses the key as an identifier and returns the data associated with the key
     *
     * @param key the identifier of saved data
     */
    public Object get(String key) throws IllegalArgumentException {
        if (key == null) {
            throw new IllegalArgumentException("storage name may not be null");
        }

        return this.DataLinks.get(key);
    }

    /**
     * OVERVIEW: checks if the given key is used or not
     * MODIFIES: Nothing
     * EFFECT: verifies if the given key have been used before to store data
     *
     * @param key the identifier of saved data
     */
    public boolean contains(String key) {
        if (key == null) {
            throw new IllegalArgumentException("null key cannot be exist in storage");
        }

        return this.DataLinks.containsKey(key);
    }

    /**
     * OVERVIEW: provides the caller of a list of all saved entires inside the
     * storage
     * MODIFIES: Nothing
     */
    public Set<String> keySet() {
        return this.DataLinks.keySet();
    }

    /**
     * OVERVIEW: makes sure the storage cache is in sync with disk
     * EFFECT: updates the file containing the storage
     */
    public void close() {
        save();
    }

    /**
     * OVERVIEW: saves the storage to a file
     * MODIFIES: File on Disk
     * EFFECT: dumps the current storage to a file (overwrites)
     */
    private void save() {
        try {
            FileOutputStream fileOut = new FileOutputStream(storageName);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(this.DataLinks);
            objectOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * OVERVIEW: loads the storage from a file
     * MODIFIES: DataLinks
     * EFFECT: loads the content of a storage file into memory
     */
    private void load() {
        File dataFile = new File(storageName);

        if (!dataFile.exists()) {
            return;
        }
        try {
            FileInputStream fis = new FileInputStream(dataFile);
            ObjectInputStream ois = new ObjectInputStream(fis);
            DataLinks = (HashMap<String, Serializable>) ois.readObject();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // clear clears the binary storage
    public void clear(){
        DataLinks.clear();
        save();
    }

    /**
     * OVERVIEW: saves data to file before destroying the object
     * MODIFIES: File on disk
     * EFFECT: dumps the current storage to a file (overwrites) upon object destruction
     */
    @Override
    protected void finalize() throws Throwable {
        save();
        super.finalize();
    }

    private void createSavesFolder() {
        File saveDir = new File(Paths.get(System.getProperty("user.dir"), Constants.STORAGE_FOLDER).toString());
        saveDir.mkdir();
    }

    private String getSavesFolder() {
        return Paths.get(System.getProperty("user.dir"), Constants.STORAGE_FOLDER).toString();
    }
}
