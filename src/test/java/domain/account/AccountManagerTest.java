package domain.account;

import domain.storage.BinaryStorage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountManagerTest {

    private static String storageName = "test-data-1";

    @Test
    void TestRegisterUser() {
        BinaryStorage storage = new BinaryStorage(storageName);
        AccountManager account = new AccountManager(storage);

        String username = "Alex";
        String password = "NollButEight";

        // Before Registration
        assertFalse(storage.contains(username), "storage should not contain unregistered user");

        // During Registration
        assertTrue(account.repOK(), "Account Manager structure damaged before register");
        boolean registryState = account.register(username, password);
        assertTrue(account.repOK(), "Account Manager structure damaged after register");

        // After Registration
        assertTrue(registryState, "user with valid password should get registered");
        assertTrue(storage.contains(username), "storage should contain registered user");
    }

    @Test
    void TestAuthenticateRegisteredUser() {
        BinaryStorage storage = new BinaryStorage(storageName);
        AccountManager account = new AccountManager(storage);

        String username = "Some";
        String password = "OneButEight";

        storage.put(username, password);

        // Before Registration
        assertTrue(
                storage.contains(username),
                "storage should contain registered user before authentication");

        // During Registration
        assertTrue(account.repOK(), "Account Manager structure damaged before register");
        Account success = account.Authenticate(username, password);
        assertTrue(account.repOK(), "Account Manager structure damaged after register");

        assertNotNull(
                success, "authentication attempt with correct username and password must not fail");

        // After Registration
        assertTrue(storage.contains(username), "storage should contain registered user after auth");
    }

    @Test
    void TestAuthenticateUnregisteredUser() {
        BinaryStorage storage = new BinaryStorage(storageName);
        AccountManager account = new AccountManager(storage);

        String username = "No";
        String password = "OneButEight";

        // Before Registration
        assertFalse(
                storage.contains(username),
                "storage should not contain unregistered user before authentication");

        // During Registration
        assertTrue(account.repOK(), "Account Manager structure damaged before authentication");
        Account success = account.Authenticate(username, password);
        assertTrue(account.repOK(), "Account Manager structure damaged after authentication");

        assertNull(
                success,
                "authentication attempt with unregistered username and password must fail");

        // After Registration
        assertFalse(
                storage.contains(username), "storage should contain unregistered user after auth");
    }
}
