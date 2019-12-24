package domain.storage;

import utils.Constants;

public class StorageManagerFactory {

    public static StorageManager get(String name){
        switch(Constants.STORAGE_PROVIDER) {
            case "MAPDB":
                return new MapDBStorage(name);
            case "BIN":
                return new BinaryStorage(name);
            default:
                throw new IllegalArgumentException("not an existing storage provider: " + Constants.STORAGE_PROVIDER);
        }
    }
}
