package creatures;

import huglife.Action;
import huglife.Creature;
import huglife.Direction;
import huglife.Occupant;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.Random;

import static huglife.HugLifeUtils.random;
import static huglife.HugLifeUtils.randomEntry;

public class Clorus extends Creature {
    public Clorus(double e) {
        super("clorus");
        energy = e;
    }

    public void move() {
        energy = energy - 0.03;
    }

    public void stay() {
        energy = energy - 0.01;
    }

    public Color color() {
        int g = 0;
        int r = 34;
        int b = 231;
        return color(r, g, b);
    }

    public void attack(Creature C) {
        energy = energy + C.energy();
    }
    public Clorus replicate() {
        Clorus newClorus = new Clorus(this.energy *0.5);
        this.energy = 0.5*this.energy;
        return newClorus;
    }

    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> plipNeighbors = new ArrayDeque<>();

        for (Map.Entry<Direction, Occupant> entry : neighbors.entrySet()) {
            if (entry.getValue() instanceof huglife.Empty) {
                emptyNeighbors.add(entry.getKey());
            }
        }
        if (emptyNeighbors.isEmpty()) {
            return new Action(Action.ActionType.STAY);
        }

        for (Map.Entry<Direction, Occupant> entry : neighbors.entrySet()){
            if(entry.getValue().name().equals("plip")){
                plipNeighbors.add(entry.getKey());
            }
        }
        if (!plipNeighbors.isEmpty()) {
            return new Action(Action.ActionType.ATTACK, randomEntry(plipNeighbors));
        }
        if (energy >= 1){
            return new Action(Action.ActionType.REPLICATE, randomEntry(emptyNeighbors));
        }
        return new Action(Action.ActionType.MOVE, randomEntry(emptyNeighbors));

    }



}