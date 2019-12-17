package creatures;


import huglife.*;
import org.junit.Test;

import java.awt.*;
import java.util.HashMap;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class TestClorus {
    @Test
    public void TestBasics(){
        Clorus c = new Clorus(2);
        assertEquals("clorus",c.name());
        assertEquals(new Color(34,0,231),c.color());
        c.move();
        assertEquals(1.97,c.energy(),0.01);
        c.stay();
        assertEquals(1.96,c.energy(),0.01);
        Plip p = new Plip(1);
        c.attack(p);
        assertEquals(2.96,c.energy(),0.01);
    }
    @Test
    public void testReplicate() {
        Clorus c = new Clorus(2);
        assertEquals(1,c.replicate().energy(),0.01);
        assertEquals(1,c.energy(),0.01);
    }

    @Test
    public void testChooseRule1() {
        // No empty adjacent spaces; stay.
        Clorus c = new Clorus(1.2);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Plip(2));
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Plip(1));

        Action actual = c.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);
    }

    @Test
    public void testChooseRule2() {
        //attack plip
        Clorus c = new Clorus(1.2);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Empty());
        surrounded.put(Direction.BOTTOM, new Plip(1.2));
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Plip(1));

        Action actual = c.chooseAction(surrounded);
        Action expected1 = new Action(Action.ActionType.ATTACK, Direction.RIGHT);
        Action expected2 = new Action(Action.ActionType.ATTACK, Direction.BOTTOM);
        assertThat(actual, anyOf(is(expected1), is(expected2)));
    }

    @Test
    public void testChooseRule3() {
        //energy > = 1, no plip, replicate to a random empty space
        Clorus c = new Clorus(2);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Empty());
        surrounded.put(Direction.BOTTOM, new Empty());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());

        Action actual = c.chooseAction(surrounded);
        Action expected1 = new Action(Action.ActionType.REPLICATE, Direction.TOP);
        Action expected2 = new Action(Action.ActionType.REPLICATE, Direction.BOTTOM);
        assertThat(actual, anyOf(is(expected1), is(expected2)));

    }

    @Test
    public void testChooseRule4() {
        //energy < 1, no plip, move to a random empty space
        Clorus c = new Clorus(0.7);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Empty());
        surrounded.put(Direction.BOTTOM, new Empty());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());

        Action actual = c.chooseAction(surrounded);
        Action expected1 = new Action(Action.ActionType.MOVE, Direction.TOP);
        Action expected2 = new Action(Action.ActionType.MOVE, Direction.BOTTOM);
        assertThat(actual, anyOf(is(expected1), is(expected2)));


    }



}
