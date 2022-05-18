package by.mmf.krupenko.validator;

/**
 * The UserValidator is responsible for validation.
 */
public class UserValidator {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9]{1}[\\w.]{4,28}[A-Za-z0-9]{1}@bsu.by$";
    private static final String PASSWORD_REGEX = "^[\\w]{6,18}$";

    /**
     * Checks if email is valid.
     *
     * @param email email
     * @return email is valid or not
     */
    public static boolean isEmailCorrect(String email) {
        return isStringCorrect(email, EMAIL_REGEX);
    }

    /**
     * Checks if password is valid.
     *
     * @param password password
     * @return password is valid or not
     */
    public static boolean isPasswordCorrect(String password) {
        return isStringCorrect(password, PASSWORD_REGEX);
    }

    private static boolean isStringCorrect(String line, String regex) {
        return line != null && line.matches(regex);
    }

}
