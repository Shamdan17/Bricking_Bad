package domain.storage;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TestMapDBStorage {
    String storageName = "test-mapDB";

    @Test
    void testStoragePutGet() {
        String key = "key";
        String value = "value";

        MapDBStorage bs = new MapDBStorage(storageName);
        bs.put(key, value);
        assertEquals(bs.get(key), value);
    }

    @Test
    void testDoesContainKey() {
        String key = "key exists";
        String value = "definitely";

        MapDBStorage bs = new MapDBStorage(storageName);
        assertDoesNotThrow(() -> bs.put(key, value));
        assertTrue(bs.contains(key), "prevously inserted key must be found");
    }

    @Test
    void testDoesNotContainKey() {
        String key = "key does not exist";
        MapDBStorage bs = new MapDBStorage(storageName);
        assertFalse(bs.contains(key), "un-inserted key must not be found");
    }


    // Assert that methods are guarded against null inputs
    @Test
    void testInvalidArgumentPut() {
        MapDBStorage bs = new MapDBStorage(storageName);

        assertNotNull(bs, "storage must not be null given a valid name");

        assertThrows(IllegalArgumentException.class, () -> {
            bs.put(null, null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            bs.put("key", null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            bs.put(null, "val");
        });

    }

    @Test
    void testInvalidArgumentGet() {
        MapDBStorage bs = new MapDBStorage(storageName);
        assertThrows(IllegalArgumentException.class, () -> {
            bs.get(null);
        });
    }

    @Test
    void testInvalidArgumentContains() {
        MapDBStorage bs = new MapDBStorage(storageName);
        assertThrows(IllegalArgumentException.class, () -> {
            bs.contains(null);
        });
    }

    @Test
    void testInvalidArgumentConstructer() {
        assertThrows(IllegalArgumentException.class, () -> {
            new MapDBStorage(null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new MapDBStorage(" ");
        });

        assertDoesNotThrow(() -> {
            new MapDBStorage(storageName);
        });
    }

    @Test
    void testPersistance() {
        String key = "key";
        String value = "value";

        MapDBStorage map = new MapDBStorage(storageName);
        map.put(key, value);

        map = new MapDBStorage(storageName);
        String valueAfterLoad = (String) map.get(key);
        assertEquals(value, valueAfterLoad, "saved and loaded values must be the same");
    }

    @Test
    void testGetKeys() {
        ArrayList<String> keys = new ArrayList<>();
        keys.add("key");
        keys.add("keyss");
        keys.add("anotherkey");
        keys.add("key exists");

        String value = "value";

        MapDBStorage map = new MapDBStorage(storageName);
        for (String key : keys) {
            map.put(key, value);
        }

        Set actualKeys = new HashSet(keys);
        Set storedKeys = map.keySet();

        assertEquals(actualKeys, storedKeys, "keys do not match");
    }
}
