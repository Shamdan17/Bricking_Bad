package domain.storage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestBinaryStorage {
    String storageName = "test";

    @Test
    void testStoragePutGet() {
        String key = "key";
        String value = "value";

        BinaryStorage bs = new BinaryStorage(storageName);
        bs.put(key, value);
        assertEquals(bs.get(key), value);
    }

    @Test
    void testDoesContainKey() {
        String key = "key exists";
        String value = "definitely";

        BinaryStorage bs = new BinaryStorage(storageName);
        assertDoesNotThrow(() -> bs.put(key, value));
        assertTrue(bs.contains(key), "prevously inserted key must be found");
    }

    @Test
    void testDoesNotContainKey() {
        String key = "key does not exist";

        BinaryStorage bs = new BinaryStorage(storageName);
        assertFalse(bs.contains(key), "un-inserted key must not be found");
    }


    // Assert that methods are guarded against null inputs
    @Test
    void testInvalidArgumentPut() {

        assertThrows(IllegalArgumentException.class, () -> {
            BinaryStorage bs = new BinaryStorage(storageName);
            bs.put(null, null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            BinaryStorage bs = new BinaryStorage(storageName);
            bs.put("key", null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            BinaryStorage bs = new BinaryStorage(storageName);
            bs.put(null, "val");
        });
    }

    @Test
    void testInvalidArgumentGet() {
        assertThrows(IllegalArgumentException.class, () -> {
            BinaryStorage bs = new BinaryStorage(storageName);
            bs.get(null);
        });
    }

    @Test
    void testInvalidArgumentContains() {
        assertThrows(IllegalArgumentException.class, () -> {
            BinaryStorage bs = new BinaryStorage(storageName);
            bs.contains(null);
        });
    }

    @Test
    void testInvalidArgumentConstructer() {
        assertThrows(IllegalArgumentException.class, () -> {
            new BinaryStorage(null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new BinaryStorage(" ");
        });

        assertDoesNotThrow(() -> {
            new BinaryStorage("valid-name");
        });
    }

}
