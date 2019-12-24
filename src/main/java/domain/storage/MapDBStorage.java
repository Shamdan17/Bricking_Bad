package domain.storage;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.Serializer;

import java.io.*;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


// MapDBStorage is a storage manager that works as a generic persistent key value store.

public class MapDBStorage implements StorageManager {
    private String storageName;
    private DB db = null;

    private Map<String, byte[]> map = null;

    /**
     * OVERVIEW: constructor for BinaryStorage MODIFIES: storageName, DataLinks
     * EFFECT: creates new instance of BinaryStorage using the given persistent file
     * name
     *
     * @param storageName the name of the file in which the data is saved
     */
    public MapDBStorage(String storageName) throws IllegalArgumentException {
        if (storageName == null || storageName.trim().equals("")) {
            throw new IllegalArgumentException("storage name may not be null or empty");
        }

        this.storageName = storageName;
        // this.db = DBMaker.fileDB(Paths.get(System.getProperty("user.dir"), storageName).toString()).make();
        // map = db.hashMap(storageName, Serializer.STRING, Serializer.BYTE_ARRAY).createOrOpen();
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

        load();

        try {
            map.put(key, serialize(value));
        } catch (IOException e) {
            System.err.println("could not serialize value with key " + key);
        }

        close();
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

        load();

        Object value = null;

        try {
            value = deserialize(map.get(key));
        } catch (IOException e) {
            System.err.println("IO error occured when getting value with key " + key);
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("Class wa not found when getting value with key " + key);
            e.printStackTrace();
        }

        close();

        return value;
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

        load();
        boolean contains = map.containsKey(key);
        close();

        return contains;
    }

    /**
     * OVERVIEW: provides the caller of a list of all saved entires inside the
     * storage
     * MODIFIES: Nothing
     */
    public Set<String> keySet() {
        load();
        Set<String> keys = map.keySet();

        Set<String> finalKeys = new HashSet<>();
        for (String key : keys) {
            finalKeys.add(key);
        }

        close();
        return finalKeys;
    }

    /**
     * OVERVIEW: saves the storage to a file MODIFIES: File on Disk EFFECT: dumps
     * the current storage to a file (overwrites)
     */
    private void save() {
        db.commit();
    }

    /**
     * OVERVIEW: saves data to file before destroying the object MODIFIES: File on
     * disk EFFECT: dumps the current storage to a file (overwrites) upon object
     * destruction
     */
    @Override
    protected void finalize() throws Throwable {
        save();
        super.finalize();
    }

    private byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(out);
        os.writeObject(obj);
        return out.toByteArray();
    }

    private Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        ObjectInputStream is = new ObjectInputStream(in);
        return is.readObject();
    }

    private void load() {
        this.db = DBMaker.fileDB(Paths.get(System.getProperty("user.dir"), storageName).toString()).make();
        this.map = db.hashMap(storageName, Serializer.STRING, Serializer.BYTE_ARRAY).createOrOpen();
    }

    public void close() {
        db.close();
    }
}
