import org.junit.Test;

import java.util.IllformedLocaleException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class MyLinkedDequeTest {
    @Test
    public void testAddFirst(){
        LinkedListDeque<String> input = new LinkedListDeque<String>();
        input.addFirst("first");
        assertEquals(1,input.size());
        assertEquals("first",input.get(0));

        input.addFirst("second");
        assertEquals(2,input.size());
        assertEquals("second",input.get(0));
        assertEquals("first",input.get(1));
    }

    @Test
    public void testAddLast(){
        LinkedListDeque<String> input = new LinkedListDeque<String>();
        input.addLast("first");
        assertEquals(1,input.size());
        assertEquals("first",input.get(0));

        input.addLast("second");
        assertEquals(2,input.size());
        assertEquals("first",input.get(0));
        assertEquals("second",input.get(1));
    }


    @Test
    public void testRemoveFirst(){
        final LinkedListDeque<String> input = new LinkedListDeque<String>();
        assertNull(input.removeFirst());
        input.addFirst("first");
        input.addLast("second");
        assertEquals("first", input.removeFirst());
        assertEquals(1,input.size());
        assertEquals("second", input.removeFirst());
        assertEquals(0,input.size());
    }


    @Test
    public void testRemoveLast(){
        LinkedListDeque<String> input = new LinkedListDeque<String>();
        assertNull(input.removeLast());
        input.addFirst("first");
        input.addLast("second");
        assertEquals("second", input.removeLast());
        assertEquals(1,input.size());
        assertEquals("first", input.removeLast());
        assertEquals(0,input.size());
    }

    @Test
    public void testGet(){
        LinkedListDeque<String> input = new LinkedListDeque<String>();
        assertNull(input.get(0));
        input.addFirst("first");
        assertEquals("first",input.get(0));
        assertNull(input.get(1));
        input.addLast("second");
        assertEquals("second",input.get(1));
        assertEquals(input.get(1),input.getRecursive(1));
    }
}
