package domain.storage;

import org.junit.jupiter.api.Test;

import java.io.Serializable;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestBinaryStorage {

    @Test
    void testStoragePut() {
        human john = new human("john", 32);

        BinaryStorage bs = new BinaryStorage("test");
        bs.put("key", john);
        assertEquals((human) bs.get("key"), john);
    }
}

class human implements Serializable {
    protected String name;
    protected int age;

    protected human(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public boolean equals(Object o) {

        // Check if o is an instance of Complex or not
        // "null instanceof [type]" also returns false
        if (!(o instanceof human)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        human c = (human) o;

        // Compare the data members and return accordingly
        return name.equals(c.name)
                && Integer.compare(age, c.age) == 0;
    }
}

