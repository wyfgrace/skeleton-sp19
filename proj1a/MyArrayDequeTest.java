import org.junit.Test;

import static org.junit.Assert.*;

public class MyArrayDequeTest {
    @Test
    public void testAddFirst(){
        ArrayDeque<String> input = new ArrayDeque<String>();
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
        ArrayDeque<String> input = new ArrayDeque<String>();
        input.addLast("first");
        assertEquals(1,input.size());
        assertEquals("first",input.get(0));

        input.addLast("second");
        assertEquals(2,input.size());
        assertEquals("first",input.get(0));
        assertEquals("second",input.get(1));
    }

    @Test
    public void testAddLastExpand(){
        ArrayDeque<Integer> input = new ArrayDeque<>();
         for (int i =0; i < 10; i++){
            input.addLast(i);
        }
        assertEquals(10, input.size());
        for (int j = 0; j < 10; j++){
            int actual = input.get(j);
            assertEquals(j,actual);
        }

    }
    @Test
    public void testAddFirstExpand(){
        ArrayDeque<Integer> input = new ArrayDeque<>();
        for (int i =0; i < 10; i++){
            input.addFirst(i);
        }
        assertEquals(10, input.size());
        for (int j = 0; j < 10; j++){
            int actual = input.get(j);
            assertEquals((10-j-1),actual);
        }

    }

    @Test
    public void testRemoveFirstShrink(){
        ArrayDeque<Integer> input = new ArrayDeque<>();
        for (int i =0; i < 60; i++){
            input.addLast(i);
        }
        int actualLength = input.getActualLength();
        for (int j = 0; j < 50; j++){
            input.removeLast();
        }
        assertEquals(10,input.size());

        for (int x=0; x < 10;x++){
            int actual = input.get(x);
            assertEquals( x, actual);
        }
        int newActualLength = input.getActualLength();
        System.out.println(actualLength + " "+ newActualLength);
        assertTrue(actualLength > newActualLength);
    }

    @Test
    public void testRemoveFirst(){
        final ArrayDeque<String> input = new ArrayDeque<String>();
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
        ArrayDeque<String> input = new ArrayDeque<String>();
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
        ArrayDeque<String> input = new ArrayDeque<String>();
        assertNull(input.get(0));
        input.addFirst("first");
        assertEquals("first",input.get(0));
        assertNull(input.get(1));
        input.addLast("second");
        assertEquals("second",input.get(1));
    }
}
