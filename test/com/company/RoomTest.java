package com.company;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

    Room testingRoom;
    Room nRoom;
    Room eRoom;
    Room sRoom;
    Room wRoom;
    Item item1;
    Item item2;
    World world;
    private HashMap<String, Room> connectingRoomsTest;

    @BeforeEach
    void setUp() {
        world = new World("test/com/company/roomDataTest.txt", "test/com/company/itemDataTest.csv");
        testingRoom = new Room(101, "TestRoom", "This is the Test room.");
        world.addRoom(testingRoom);
        item1 = new Item("item1","Test item 1",testingRoom);
        item2 = new Item("item2","Test item 2",testingRoom);
        testingRoom.addItem(item1);
        testingRoom.addItem(item2);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getConnectingDirections() {

        nRoom = new Room(102, "North Room", "This room is to the North");
        testingRoom.addConnectingRoom("n",1);
        int[] connectingDirs = testingRoom.getConnectingRooms();

        assertTrue(connectingDirs[0]!=-1);

    }

    @Test
    void getName() {
        String name = testingRoom.getName();
        assertEquals("TestRoom",name);
    }

    @Test
    void getDescription() {
        String description = "This is the Test room. Test item 1 Test item 2";
        String actualDescription = testingRoom.getDescription();
        assertEquals(description,actualDescription);
    }

    @Test
    void addItem() {
    }

    @Test
    void removeItem() {
    }

    @Test
    void getItems() {
    }

    @Test
    void getConnectingRooms(){
        nRoom = new Room(102, "North Room", "This room is to the North");
        eRoom = new Room(103, "East Room", "This room is to the East");
        sRoom = new Room(104, "South Room", "This room is to the South");
        wRoom = new Room(105, "West Room", "This room is to the West");

        world.addRoom(nRoom);
        world.addRoom(eRoom);
        world.addRoom(sRoom);
        world.addRoom(wRoom);

        testingRoom.addConnectingRoom("n",102);
        testingRoom.addConnectingRoom("e",103);
        testingRoom.addConnectingRoom("s",104);
        testingRoom.addConnectingRoom("w",105);

        Room northRoom = testingRoom.getRoomNorth();
        Room eastRoom = testingRoom.getRoomEast();
        Room southRoom = testingRoom.getRoomSouth();
        Room westRoom = testingRoom.getRoomWest();

        assertEquals(sRoom, testingRoom.getRoomSouth());


    }

    @Test
    void worldGetRoom(){
        assertNotNull(world.getRoom("TestRoom"));
    }

    @Test
    void removeRoom(){
        world.removeRoom(testingRoom);
        assertNull(world.getRoom("TestRoom"));
        world.addRoom(testingRoom);
        world.addRoom(testingRoom);
        world.removeRoom(testingRoom);
        assertNotNull(world.getRoom("TestRoom"));
        world.removeRoom(testingRoom);
        assertNull(world.getRoom("TestRoom"));
    }

    @Test
    void removeConnectingRoom1(){
        nRoom = new Room(102, "North Room", "This room is to the North");
        testingRoom.addConnectingRoom("n",102);
        testingRoom.removeConnectingRoom("n");
        assertNull(testingRoom.getRoomNorth());
    }

    @Test
    void removeConnectingRoom2(){
        nRoom = new Room(103, "North Room", "This room is to the North");
        testingRoom.addConnectingRoom("n",103);
        testingRoom.removeConnectingRoom("n");
        testingRoom.removeConnectingRoom("n");
        testingRoom.removeConnectingRoom("n");
        assertNull(testingRoom.getRoomNorth());
    }

    @Test
    void addConnectingRoom() {
        Room southRoomTest = testingRoom.getRoomSouth();
        assertEquals(sRoom, southRoomTest);
    }
}