package ro.unibuc.egv.finalProject.services;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;
import ro.unibuc.egv.finalProject.models.Address;
import ro.unibuc.egv.finalProject.models.User;
import ro.unibuc.egv.finalProject.repositories.UserRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    @DisplayName("Username is wrong - User can't sign in")
    void testSignInWrongUsername(){
        // Arrange
        String username = "notJohnDoe";
        String password = "johnDoe123";
        User user = new User(1L,"John", "Doe", "johnDoe", "johnDoe123", "johndoe@gmail.com", "0740123456", new Address(1L,"England", "East", "Essex", "High Street", 1, "A", "A", 1, 1, "C001", "JD10"));
        when(userRepository.findUserByUsername(username)).thenReturn(null);

        // Act
        String result = userService.signInCheck(username, password);

        // Assert
        assertEquals("Account doesn't exist!", result);
    }

    @Test
    @DisplayName("Password is wrong - User can't sign in")
    void testSignInWrongPassword(){
        // Arrange
        String username = "johnDoe";
        String password = "johnDoe321";
        User user = new User(1L,"John", "Doe", "johnDoe", "johnDoe123", "johndoe@gmail.com", "0740123456", new Address(1L,"England", "East", "Essex", "High Street", 1, "A", "A", 1, 1, "C001", "JD10"));
        when(userRepository.findUserByUsername(username)).thenReturn(user);

        // Act
        String result = userService.signInCheck(username, password);

        // Assert
        assertEquals("Wrong password!", result);
    }

    @Test
    @DisplayName("User can sign in")
    void testSignIn(){
        // Arrange
        String username = "johnDoe";
        String password = "johnDoe123";
        User user = new User(1L,"John", "Doe", "johnDoe", "johnDoe123", "johndoe@gmail.com", "0740123456", new Address(1L,"England", "East", "Essex", "High Street", 1, "A", "A", 1, 1, "C001", "JD10"));
        when(userRepository.findUserByUsername(username)).thenReturn(user);
        when(userRepository.findUserByUsernameAndPassword(username, password)).thenReturn(user);

        // Act
        String resultCheck = userService.signInCheck(username, password);
        User resultSignIn = userService.signIn(username, password);

        // Assert
        assertEquals("OK", resultCheck);
        assertEquals(user, resultSignIn);
    }

    @Test
    @DisplayName("User is created successfully")
    void testCreateUser(){
        // Arrange
        User user = new User("John", "Doe", "johnDoe", "johnDoe123", "johndoe@gmail.com", "0740123456", new Address("England", "East", "Essex", "High Street", 1, "A", "A", 1, 1, "C001", "JD10"));
        User savedUser = new User(1L,"John", "Doe", "johnDoe", "johnDoe123", "johndoe@gmail.com", "0740123456", new Address(1L,"England", "East", "Essex", "High Street", 1, "A", "A", 1, 1, "C001", "JD10"));
        when(userRepository.save(user)).thenReturn(savedUser);
        when(userRepository.findUserByUsername(user.getUsername())).thenReturn(savedUser);
        when(userRepository.findUserByUsernameAndPassword(user.getUsername(), user.getPassword())).thenReturn(savedUser);
        when(userRepository.findUserByEmail(user.getEmail())).thenReturn(savedUser);
        when(userRepository.findUserByPhoneNr(user.getPhoneNr())).thenReturn(savedUser);

        // Act
        userService.signUp(user);
        User result1 = userRepository.findUserByUsername(user.getUsername());
        User result2 = userRepository.findUserByUsernameAndPassword(user.getUsername(), user.getPassword());
        User result3 = userRepository.findUserByEmail(user.getEmail());
        User result4 = userRepository.findUserByPhoneNr(user.getPhoneNr());

        // Assert
        assertNotNull(result1);
        assertEquals(savedUser, result1);
        assertNotNull(result2);
        assertEquals(savedUser, result2);
        assertNotNull(result3);
        assertEquals(savedUser, result3);
        assertNotNull(result4);
        assertEquals(savedUser, result4);
    }

    @Test
    @DisplayName("User email already exists - User is not created")
    void testUserExistingEmail(){
        // Arrange
        User user = new User("John", "Doe", "johnDoe", "johnDoe123", "johndoe@gmail.com", "0740123456", new Address("England", "East", "Essex", "High Street", 1, "A", "A", 1, 1, "C001", "JD10"));
        User altUser = new User("John", "Doe", "johnDoe1", "johnDoe123", "johndoe@gmail.com", "0741123456", new Address("England", "East", "Essex", "High Street", 1, "A", "A", 1, 1, "C001", "JD10"));
        when(userRepository.findUserByEmail(user.getEmail())).thenReturn(user);

        // Act
        userService.signUp(user);
        userService.signUp(altUser);
        User result = userRepository.findUserByEmail(user.getEmail());

        // Assert
        assertNotNull(result);
        assertNotEquals(altUser.getUsername(), result.getUsername());
        assertNotEquals(altUser.getPhoneNr(), result.getPhoneNr());
        assertEquals(altUser.getEmail(), result.getEmail());
    }

    @Test
    @DisplayName("User phone number already exists - User is not created")
    void testUserExistingPhoneNr(){
        // Arrange
        User user = new User("John", "Doe", "johnDoe", "johnDoe123", "johndoe@gmail.com", "0740123456", new Address("England", "East", "Essex", "High Street", 1, "A", "A", 1, 1, "C001", "JD10"));
        User altUser = new User("John", "Doe", "johnDoe1", "johnDoe123", "johndoe1@gmail.com", "0740123456", new Address("England", "East", "Essex", "High Street", 1, "A", "A", 1, 1, "C001", "JD10"));
        when(userRepository.findUserByPhoneNr(user.getPhoneNr())).thenReturn(user);

        // Act
        userService.signUp(user);
        userService.signUp(altUser);
        User result = userRepository.findUserByPhoneNr(user.getPhoneNr());

        // Assert
        assertNotNull(result);
        assertNotEquals(altUser.getUsername(), result.getUsername());
        assertEquals(altUser.getPhoneNr(), result.getPhoneNr());
        assertNotEquals(altUser.getEmail(), result.getEmail());
    }

    @Test
    @DisplayName("Username already exists - User is not created")
    void testUserExistingUsername(){
        // Arrange
        User user = new User("John", "Doe", "johnDoe", "johnDoe123", "johndoe@gmail.com", "0740123456", new Address("England", "East", "Essex", "High Street", 1, "A", "A", 1, 1, "C001", "JD10"));
        User altUser = new User("John", "Doe", "johnDoe", "johnDoe123", "johndoe1@gmail.com", "0741123456", new Address("England", "East", "Essex", "High Street", 1, "A", "A", 1, 1, "C001", "JD10"));
        when(userRepository.findUserByUsername(user.getUsername())).thenReturn(user);

        // Act
        userService.signUp(user);
        userService.signUp(altUser);
        User result = userRepository.findUserByUsername(user.getUsername());

        // Assert
        assertNotNull(result);
        assertEquals(altUser.getUsername(), result.getUsername());
        assertNotEquals(altUser.getPhoneNr(), result.getPhoneNr());
        assertNotEquals(altUser.getEmail(), result.getEmail());
    }

    @Test
    @DisplayName("User is updated successfully")
    void testUpdateUser(){
        // Arrange
        User user = new User("John", "Doe", "johnDoe", "johnDoe123", "johndoe@gmail.com", "0740123456", new Address("England", "East", "Essex", "High Street", 1, "A", "A", 1, 1, "C001", "JD10"));
        User updatedUser = new User(1L,"Joe", "Don", "johnDoe", "joeDon123", "joedon@gmail.com", "0749123456", new Address(1L,"England", "East", "Essex", "High Street", 2, "B", "B", 2, 2, "C002", "JD20"));
        when(userRepository.findUserByUsername(user.getUsername())).thenReturn(updatedUser);

        // Act
        userService.signUp(user);
        userService.editUser(updatedUser);
        User result = userRepository.findUserByUsername(user.getUsername());

        // Assert
        assertNotNull(result);
        assertEquals(updatedUser, result);
    }

    @Test
    @DisplayName("User is deleted successfully")
    void testDeleteUser(){
        // Arrange
        User user = new User(1L, "John", "Doe", "johnDoe", "johnDoe123", "johndoe@gmail.com", "0740123456", new Address("England", "East", "Essex", "High Street", 1, "A", "A", 1, 1, "C001", "JD10"));
        when(userRepository.findUserByUsername(user.getUsername())).thenReturn(null);

        // Act
        userService.deleteUser(user);
        User result = userRepository.findUserByUsername(user.getUsername());

        // Assert
        assertNull(result);
    }

}
