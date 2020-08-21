package psu.ru.todo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import psu.ru.todo.exception.ValidationException;
import psu.ru.todo.model.pojos.UserPojo;
import psu.ru.todo.repository.UserRepository;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Objects;
import java.util.UUID;

@Service
public class UserService {

    private UserRepository userRepository;
    private ContextService contextService;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserPojo getUserByLogin(String login) {
        return userRepository.getUserByLogin(login);
    }

    // Добавление пользователя
    public void add(UserPojo userPojo) throws NoSuchAlgorithmException, InvalidKeySpecException {
        validate(userPojo);

        // Salt
        SecureRandom random = new SecureRandom();
        byte[]salt = new byte[16];
        random.nextBytes(salt);

        // hash
        KeySpec spec = new PBEKeySpec(userPojo.getPassword().toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[]hash = factory.generateSecret(spec).getEncoded();

        userPojo.setPassword(toHex(hash));
        userPojo.setSalt(toHex(salt));

        userRepository.insert(userPojo);
    }

    private void validate(UserPojo userPojo) {
        ValidationException validationException = new ValidationException();

        if (getUserByLogin(userPojo.getLogin()) != null) {
            validationException.put("emailError", "такой пользователь уже зарегестрирован!");
        }

        validationException.isErrors();
    }

    // Вход (проверка логина и пароля)
    public boolean login(UserPojo userDto) throws NoSuchAlgorithmException, InvalidKeySpecException {
        UserPojo userFromDb = userRepository.getUserByLogin(userDto.getLogin());
        if (userFromDb == null) return false;

        KeySpec spec = new PBEKeySpec(userDto.getPassword().toCharArray(), fromHex(userFromDb.getSalt()), 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] testHash = factory.generateSecret(spec).getEncoded();

        int diff = fromHex(userFromDb.getPassword()).length ^ testHash.length;
        for (int i = 0; i < fromHex(userFromDb.getPassword()).length && i < testHash.length; i++) {
            diff |= fromHex(userFromDb.getPassword())[i] ^ testHash[i];
        }
        return diff == 0;
    }

    // битовый массив в 16-ю
    private static String toHex(byte[] array) {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if (paddingLength > 0) {
            return String.format("%0" + paddingLength + "d", 0) + hex;
        } else {
            return hex;
        }
    }

    // Из 16-й в битовый массив
    private static byte[] fromHex(String hex) {
        byte[] bytes = new byte[hex.length() / 2];
        for(int i = 0; i<bytes.length ;i++)
        {
            bytes[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }

    public void validateLogin(UserPojo userPojo) throws InvalidKeySpecException, NoSuchAlgorithmException {
        ValidationException validationException = new ValidationException();
        if (userRepository.getUserByLogin(userPojo.getLogin()) == null) {
            validationException.put("emailError", "неверный адрес");
        }
        if (!login(userPojo)) {
            validationException.put("passwordError", "неверный пароль");
        }

        validationException.isErrors();
    }

    public void addSession(UUID userId, UUID sessionId) {
        userRepository.insertSession(userId, sessionId);
    }

    public void removeSession(UUID sessionId) {
        userRepository.deleteSession(sessionId);
    }

    public UserPojo getUserBySessionId(UUID sessionId) {
        return userRepository.getUserBySessionId(sessionId);
    }

    public UUID getSessionId(HttpServletRequest request) {
        if (request.getCookies() == null) return null;
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("session_id")) {
                return UUID.fromString(cookie.getValue());
            }
        }
        return null;
    }


    public void createSession(@RequestBody UserPojo user, HttpServletResponse response, HttpServletRequest request) {
        try {
            validateLogin(user);
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        UUID sessionId = getSessionId(request);
        if (sessionId == null) {
            sessionId = UUID.randomUUID();
            Cookie cookie = new Cookie("session_id", sessionId.toString());
            cookie.setDomain("localhost");
            cookie.setPath("/");
            cookie.setMaxAge(60 * 60 * 12);
            response.addCookie(cookie);
        }

        UserPojo userPojo = getUserByLogin(user.getLogin());
        addSession(userPojo.getId(), sessionId);
    }

    public void sessionClear(HttpServletResponse response, HttpServletRequest request) {
        Cookie cookie = new Cookie("session_id", null);
        cookie.setDomain("localhost");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        removeSession(getSessionId(request));
    }
}
