package com.nizam.megacabs.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

class UserTest {

    @Test
    void testUserCreationWithValidData() {
        // Arrange
        String userId = "user123";
        String userName = "John Doe";
        String userEmailId = "john.doe@example.com";
        String userPassword = "Password123!";
        List<Role> roles = new ArrayList<>(Arrays.asList(Role.CUSTOMER));

        // Act
        User user = new User(userId, userName, userEmailId, userPassword, roles);

        // Assert
        assertThat(user.getUserId()).isEqualTo(userId);
        assertThat(user.getUserName()).isEqualTo(userName);
        assertThat(user.getUserEmailId()).isEqualTo(userEmailId);
        assertThat(user.getUserPassword()).isEqualTo(userPassword);
        assertThat(user.getRoles()).isEqualTo(roles);

    }
     @Test
    void testUserCreationWithEmptyUserId() {
        // Arrange
        String userId = "";
        String userName = "John Doe";
        String userEmailId = "john.doe@example.com";
        String userPassword = "Password123!";
        List<Role> roles = new ArrayList<>(Arrays.asList(Role.CUSTOMER));

        // Act
        User user = new User(userId, userName, userEmailId, userPassword, roles);

        // Assert
        assertThat(user.getUserId()).isEqualTo(userId);
    }
     @Test
    void testUserCreationWithNullUserId() {
        // Arrange
        String userId = null;
        String userName = "John Doe";
        String userEmailId = "john.doe@example.com";
        String userPassword = "Password123!";
        List<Role> roles = new ArrayList<>(Arrays.asList(Role.CUSTOMER));

        // Act
        User user = new User(userId, userName, userEmailId, userPassword, roles);

        // Assert
        assertThat(user.getUserId()).isEqualTo(userId);
    }
   @Test
    void testUserCreationWithEmptyEmail() {
        // Arrange
        String userId = "user123";
        String userName = "John Doe";
        String userEmailId = "";
        String userPassword = "Password123!";
        List<Role> roles = new ArrayList<>(Arrays.asList(Role.CUSTOMER));

        // Act
        User user = new User(userId, userName, userEmailId, userPassword, roles);

        // Assert
        assertThat(user.getUserEmailId()).isEqualTo(userEmailId);
    }

     @Test
    void testUserCreationWithNullEmail() {
        // Arrange
        String userId = "user123";
        String userName = "John Doe";
        String userEmailId = null;
        String userPassword = "Password123!";
        List<Role> roles = new ArrayList<>(Arrays.asList(Role.CUSTOMER));

        // Act
        User user = new User(userId, userName, userEmailId, userPassword, roles);

        // Assert
        assertThat(user.getUserEmailId()).isEqualTo(userEmailId);
    }
    @Test
    void testUserCreationWithInvalidEmail() {
        // Arrange
        String userId = "user123";
        String userName = "John Doe";
        String userEmailId = "invalid-email";
        String userPassword = "Password123!";
        List<Role> roles = new ArrayList<>(Arrays.asList(Role.CUSTOMER));

        // Act
        User user = new User(userId, userName, userEmailId, userPassword, roles);

        // Assert
        assertThat(user.getUserEmailId()).isEqualTo(userEmailId);

    }
      @Test
    void testUserCreationWithInvalidEmail2() {
        // Arrange
        String userId = "user123";
        String userName = "John Doe";
        String userEmailId = "user@example";
        String userPassword = "Password123!";
        List<Role> roles = new ArrayList<>(Arrays.asList(Role.CUSTOMER));

        // Act
        User user = new User(userId, userName, userEmailId, userPassword, roles);

        // Assert
        assertThat(user.getUserEmailId()).isEqualTo(userEmailId);

    }
      @Test
    void testUserCreationWithInvalidEmail3() {
        // Arrange
        String userId = "user123";
        String userName = "John Doe";
        String userEmailId = "user@.com";
        String userPassword = "Password123!";
        List<Role> roles = new ArrayList<>(Arrays.asList(Role.CUSTOMER));

        // Act
        User user = new User(userId, userName, userEmailId, userPassword, roles);

        // Assert
        assertThat(user.getUserEmailId()).isEqualTo(userEmailId);

    }
    @Test
    void testUserCreationWithEmptyPassword() {
        // Arrange
        String userId = "user123";
        String userName = "John Doe";
        String userEmailId = "john.doe@example.com";
        String userPassword = "";
        List<Role> roles = new ArrayList<>(Arrays.asList(Role.CUSTOMER));

        // Act
        User user = new User(userId, userName, userEmailId, userPassword, roles);

        // Assert
        assertThat(user.getUserPassword()).isEqualTo(userPassword);
    }
     @Test
    void testUserCreationWithNullPassword() {
        // Arrange
        String userId = "user123";
        String userName = "John Doe";
        String userEmailId = "john.doe@example.com";
        String userPassword = null;
        List<Role> roles = new ArrayList<>(Arrays.asList(Role.CUSTOMER));

        // Act
        User user = new User(userId, userName, userEmailId, userPassword, roles);

        // Assert
        assertThat(user.getUserPassword()).isEqualTo(userPassword);
    }

    @Test
      void testUserCreationWithInvalidPasswordFormat() {
        // Arrange
        String userId = "user123";
        String userName = "John Doe";
        String userEmailId = "john.doe@example.com";
        String userPassword = "password";
        List<Role> roles = new ArrayList<>(Arrays.asList(Role.CUSTOMER));

        // Act
        User user = new User(userId, userName, userEmailId, userPassword, roles);

        // Assert
        assertThat(user.getUserPassword()).isEqualTo(userPassword);

    }
    @Test
      void testUserCreationWithInvalidPasswordFormat2() {
        // Arrange
        String userId = "user123";
        String userName = "John Doe";
        String userEmailId = "john.doe@example.com";
        String userPassword = "password123";
        List<Role> roles = new ArrayList<>(Arrays.asList(Role.CUSTOMER));

        // Act
        User user = new User(userId, userName, userEmailId, userPassword, roles);

        // Assert
        assertThat(user.getUserPassword()).isEqualTo(userPassword);

    }
    @Test
      void testUserCreationWithEmptyRoles() {
        // Arrange
        String userId = "user123";
        String userName = "John Doe";
        String userEmailId = "john.doe@example.com";
        String userPassword = "Password123!";
        List<Role> roles = new ArrayList<>();

        // Act
        User user = new User(userId, userName, userEmailId, userPassword, roles);

        // Assert
        assertThat(user.getRoles()).isEqualTo(roles);

    }
}
