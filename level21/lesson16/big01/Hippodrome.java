package com.javarush.test.level21.lesson16.big01;

import java.util.ArrayList;

/**
 * Created by Влад on 25.10.2015.
 */
public class Hippodrome
{
    public static Hippodrome game;

    public static void main(String[] args) throws InterruptedException
    {
        game = new Hippodrome();

        game.getHorses().add(new Horse("Horse1", 3, 0));
        game.getHorses().add(new Horse("Horse2", 3, 0));
        game.getHorses().add(new Horse("Horse3", 3, 0));

        game.run();
        game.printWinner();
    }

    private ArrayList<Horse> horses = new ArrayList<>();

    public ArrayList<Horse> getHorses() {
        return horses;
    }

    public void run() throws InterruptedException
    {
        for (int i = 0; i < 100; i++) {
            move();
            print();
            Thread.sleep(200);
        }
    }

    public void move() {
        for (Horse horse : getHorses()) {
            horse.move();
        }
    }

    public void print() {
        for (Horse horse : getHorses()) {
            horse.print();
        }
        System.out.println();
        System.out.println();
    }

    public Horse getWinner() {
        double max = 0.0;
        Horse winner = null;
        for (Horse horse : getHorses()) {
            if (horse.getDistance() > max) max = horse.getDistance();
        }
        for (Horse horse : getHorses()) {
            if (horse.getDistance() == max) winner = horse;
        }
        return winner;
    }

    public void printWinner() {
        System.out.println("Winner is " + getWinner().getName() + "!");
    }
}
