package com.company;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    Room triggerRoom1;
    Item triggerItem1;
    Event triggerEvent1;

    Room triggerRoom2;
    Item triggerItem2;
    Event triggerEvent2;

    final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        triggerRoom1 = new Room(0,0,"TestRoom1", "Room 1 description.");
        triggerItem1 = new Item("TestItem1","Item 1 description.",triggerRoom1);
        triggerEvent1 = new Event(triggerRoom1, triggerItem1, "Event 1 triggered.");

        triggerRoom2 = new Room(0,1,"TestRoom2", "Room 2 description.");
        triggerItem2 = new Item("TestItem1","Item 2 description.",triggerRoom1);
        triggerEvent2 = new Event(triggerRoom2, triggerItem2, "Event 2 triggered.");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getRoom() {
        Room room1 = triggerEvent1.getRoom();
        assertEquals(triggerRoom1, room1);
    }

    @Test
    void getItem() {
        Item item1 = triggerEvent1.getItem();
        assertEquals(triggerItem1, item1);
    }

    @Test
    void triggerEvent() {
        System.setOut(new PrintStream(outContent));
        triggerEvent1.triggerEvent();
        assertEquals("Event 1 triggered.\n",outContent.toString());
    }
}