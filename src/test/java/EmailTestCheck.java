import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class EmailTestCheck {

    @ParameterizedTest(name = "#{index} - Run test with email = {0}")
    @MethodSource("validEmailProvider")
    void test_email_valid(String email) {
        assertTrue(EmailCheck.isValid(email));
    }

    @ParameterizedTest(name = "#{index} - Run test with email = {0}")
    @MethodSource("invalidEmailProvider")
    void test_email_invalid(String email) {
        assertFalse(
                EmailCheck.isValid(email));
    }

    // Valid email addresses
    static Stream<String> validEmailProvider() {
        return Stream.of(
                "myemail@test.com",                // simple
                "myemail@test.gov.ua",              // .gov.ua, 2 tld
                "myemail-23@test.com",           // -
                "myemail.23@test.com",           // .
                "myemail_23@test.com",           // _
                "m@test.com");                   // one letter
    }

    // Invalid email addresses
    static Stream<String> invalidEmailProvider() {
        return Stream.of(
                "myemail",                            // email need at least one @
                "myemail@23@test.com",           // email doesn't allow more than one @
                ".myemail@test.com",               // local-part can't start with a dot .
                "myemail.@test.com",               // local-part can't end with a dot .
                "myemail@test.c",                  // domain tld min 2 chars
                "myemail@test..com",               // domain doesn't allow dot . appear consecutively
                "myemail@.com");                      // domain doesn't start with a dot .

    }

}