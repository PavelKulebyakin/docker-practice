package org.docker.start;

import org.docker.start.service.CalculatorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class SimpleUnitTest {

    @Autowired
    private CalculatorService calculatorService;

    @Test
    public void testSum() {
        assertThat(calculatorService.sum(3, 4)).isEqualTo(7);
    }

    @Test
    public void testDoNotPass() {
        assertThat(calculatorService.sum(3, 4)).isEqualTo(5);
    }
}
