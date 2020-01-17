import org.testng.annotations.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class TestUnionFind {
    @Test
    public void testFind() {
        UnionFind unionTest = new UnionFind(8 );
        unionTest.union(0,1);
        unionTest.union(1,2);
        unionTest.union(2,3);
        assertEquals(unionTest.find(1),unionTest.find(3));
        assertEquals(5,unionTest.find(5));
    }

    @Test
    public void testConnected(){
        UnionFind unionTest = new UnionFind(6);
        unionTest.union(3,4);
        unionTest.union(0,1);
        unionTest.union(1,2);
        unionTest.union(4,1);
        assertTrue(unionTest.connected(1,3));
        assertFalse(unionTest.connected(1,5));
    }

    @Test
    public void testSize(){
        UnionFind unionTest = new UnionFind(10);
        unionTest.union(3,4);
        unionTest.union(0,1);
        unionTest.union(1,2);
        unionTest.union(4,1);
        unionTest.union(7,8);
        unionTest.union(6,8);
        assertEquals(3,unionTest.sizeOf(6));
        assertEquals(5,unionTest.sizeOf(4));
    }

    @Test
    public void testParent(){
        UnionFind unionTest = new UnionFind(10);
        unionTest.union(3,4);
        unionTest.union(0,1);
        unionTest.union(1,2);
        unionTest.union(4,1);
        unionTest.union(7,8);
        unionTest.union(6,8);
        assertEquals(-1,unionTest.parent(9));
        assertEquals(4,unionTest.parent(3));
        assertEquals(1,unionTest.parent(0));
    }

    @Test
    public void testFindSteps(){
        int size=10000;
        UnionFind unionTest = new UnionFind(size);
        unionN(unionTest,size);
        int maxSteps = 0;
        for(int i=0; i<size; i++){
            int steps = unionTest.findSteps(i);
            if (steps > maxSteps){
                maxSteps = steps;
            }
            assertTrue(steps <= Math.log(size));
        }
        System.out.println(maxSteps);
    }


    public void unionN (UnionFind unionFind,int n){
        int negParent = n;
        while(negParent >1){
            Random rand = new Random();
            int x = rand.nextInt(n);
            int y = rand.nextInt(n);
            unionFind.union(x,y);
            negParent = 0;
            for(int j=0; j<n; j++){
                if (unionFind.parent(j)<0){
                    negParent++;
                }
            }
        }
    }

}
