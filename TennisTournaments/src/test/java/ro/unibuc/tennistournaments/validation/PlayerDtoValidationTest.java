package ro.unibuc.tennistournaments.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ro.unibuc.tennistournaments.dto.PlayerDto;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static ro.unibuc.tennistournaments.util.PlayerDtoUtil.aPlayerDto;

public class PlayerDtoValidationTest {

    private Validator validator;
    private PlayerDto request;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        request = aPlayerDto("Andrei", "Alexandrescu");
    }

    @Test
    @DisplayName("Test request when all fields are valid")
    void test_request_whenIsValid() {

        Set<ConstraintViolation<PlayerDto>> violations = validator.validate(request);
        assertThat(violations).isEmpty();
    }

    @Test
    void test_request_whenEmail_isInvalid() {
        request.setEmail("andrei@gmail");

        Set<ConstraintViolation<PlayerDto>> violations = validator.validate(request);
        assertThat(violations.size()).isEqualTo(1);
    }
}
