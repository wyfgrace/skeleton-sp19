import org.junit.Test;
import static org.junit.Assert.*;


public class TestArrayDequeGold {
    @Test
    public void test1() {
        final LinkedListDeque<Integer> arrayTest = new LinkedListDeque<>();
        final ArrayDequeSolution<Integer> arraySolution = new ArrayDequeSolution<>();
        final StringBuilder message = new StringBuilder("list of operations:\n");
        for (int m = 0; m < 1000; m ++) {
            int n = StdRandom.uniform(4);
            if (n == 0) {
                arrayTest.addFirst(m);
                arraySolution.addFirst(m);
                message.append("addFirst(").append(m).append(")\n");
                assertEquals(message +"size error" ,arrayTest.size(), arraySolution.size());
                assertEquals(message +"First item error",arrayTest.get(0),arraySolution.get(0));
            }else if (n == 1) {
                arrayTest.addLast(m);
                arraySolution.addLast(m);
                message.append("addLast(").append(m).append(")\n");
                assertEquals(message +"size error",arrayTest.size(),arraySolution.size());
                assertEquals(message +"Last item error",arrayTest.get(arraySolution.size()-1),arraySolution.get(arraySolution.size()-1));
            }else if (n == 2) {
                message.append("removeFirst()\n");
                if (arraySolution.size() == 0){
                    assertEquals(message +"size error",arrayTest.size(), arraySolution.size());
                }else
                    {assertEquals(message +"removed item error",arrayTest.removeFirst(), arraySolution.removeFirst());}
            }else {
                message.append("removeLast()\n");
                if (arraySolution.size() == 0){
                    assertEquals(message +"size error",arrayTest.size(), arraySolution.size());
                }else{
                    assertEquals(message +"removed position error",arrayTest.removeLast(), arraySolution.removeLast());
                }
            }
        }

    }


}
