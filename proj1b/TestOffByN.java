import org.junit.Test;
import static org.junit.Assert.*;



public class TestOffByN {



    @Test
    public void testEqualChars5(){
        final CharacterComparator offByN = new OffByN(5);

        assertTrue(offByN.equalChars('a','f'));
        assertTrue(offByN.equalChars('f','a'));
        assertFalse(offByN.equalChars('b','b'));
        assertFalse(offByN.equalChars('z','a'));
        assertFalse(offByN.equalChars('c','f'));
    }

    @Test
    public void testEqualChars2(){
        final CharacterComparator offByN = new OffByN(2);

        assertTrue(offByN.equalChars('a','c'));
        assertTrue(offByN.equalChars('c','a'));
        assertFalse(offByN.equalChars('b','b'));
        assertFalse(offByN.equalChars('z','a'));
        assertFalse(offByN.equalChars('c','d'));
    }

}


