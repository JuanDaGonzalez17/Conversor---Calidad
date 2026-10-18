package acceptance;

import com.intuit.karate.junit5.Karate;

class ConversorAcceptanceTest {

    @Karate.Test
    Karate conversiones() {
        return Karate.run("conversor").relativeTo(getClass());
    }
}
