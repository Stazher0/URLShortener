package zhulikov.project.urlshortener.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Base62UtilsTest {

    @Test
    void encode_should_return_G8_from_1000_and_conversely(){
        assertEquals("G8", Base62Utils.encode(1000));
        assertEquals(1000, Base62Utils.decode("G8"));
    }
}
