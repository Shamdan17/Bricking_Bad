package domain.storage;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class BinaryStorage implements StorageManager {
    private HashMap<Serializable, Serializable> DataLinks;
    private String storageName;


    public BinaryStorage(String storageName) throws IllegalArgumentException {
        if (storageName == null) {
            throw new IllegalArgumentException("storage name may not be null: " + storageName);
        }

        this.DataLinks = new HashMap<>();
        this.storageName = storageName;
    }

    public void put(Serializable key, Serializable value) throws IllegalArgumentException {
        System.out.println("putting " + storageName);
        if (key == null || value == null) {
            throw new IllegalArgumentException("storage name may not be null");
        }
        this.DataLinks.put(key, value);

        save();
    }

    public Object get(Serializable key) throws IllegalArgumentException {
        System.out.println("Getting " + storageName);
        if (key == null) {
            throw new IllegalArgumentException("storage name may not be null");
        }

        return this.DataLinks.get(key);
    }

    public List<Object> getRecords() {
        return new ArrayList<>(DataLinks.keySet());
    }

    private void save() {
        System.out.println("Saving " + storageName);
        Path filepath = Paths.get(System.getProperty("user.dir"), storageName);
        try {
            FileOutputStream fileOut = new FileOutputStream(filepath.toString());
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(this.DataLinks);
            objectOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void load() {
        System.out.println("Loading " + storageName);
        Path filepath = Paths.get(System.getProperty("user.dir"), storageName);

        try {
            FileInputStream fis = new FileInputStream(filepath.toString());
            ObjectInputStream ois = new ObjectInputStream(fis);
            DataLinks = (HashMap<Serializable, Serializable>) ois.readObject();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void finalize() throws Throwable {
        save();
        super.finalize();
    }
}

