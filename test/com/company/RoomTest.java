package com.company;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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
    private HashMap<String, Room> connectingRoomsTest;

    @BeforeEach
    void setUp() {
        testingRoom = new Room(1,1,"TestRoom","This is the Test room.");
        item1 = new Item("item1","Test item 1",testingRoom);
        item2 = new Item("item2","Test item 2",testingRoom);
        testingRoom.addItem(item1);
        testingRoom.addItem(item2);
        connectingRoomsTest = new HashMap<String, Room>();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getConnectingDirections() {

        nRoom = new Room(0,1,"North Room","This room is to the North");
        testingRoom.addConnectingRoom("n",nRoom);
        ArrayList<String> connectingDirs = testingRoom.getConnectingDirections();

        assertTrue(connectingDirs.contains("n"));

    }

    @Test
    void getColumn() {
        int roomNum = testingRoom.getColumn();
        assertEquals(1,roomNum);
    }

    @Test
    void getRow() {
        int roomNum = testingRoom.getRow();
        assertEquals(1,roomNum);
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
        nRoom = new Room(0,1,"North Room","This room is to the North");
        eRoom = new Room(1,2,"East Room","This room is to the East");
        sRoom = new Room(2,1,"South Room","This room is to the South");
        wRoom = new Room(1,0,"West Room","This room is to the West");

        testingRoom.addConnectingRoom("n",nRoom);
        testingRoom.addConnectingRoom("e",eRoom);
        testingRoom.addConnectingRoom("s",sRoom);
        testingRoom.addConnectingRoom("w",wRoom);

        Room northRoom = testingRoom.getConnectingRoom("n");
        Room eastRoom = testingRoom.getConnectingRoom("e");
        Room southRoom = testingRoom.getConnectingRoom("s");
        Room westRoom = testingRoom.getConnectingRoom("w");

        assertEquals(nRoom,northRoom);
        assertEquals(eRoom,eastRoom);
        assertEquals(sRoom,southRoom);
        assertEquals(wRoom,westRoom);

    }

    @Test
    void removeConnectingRoom1(){
        nRoom = new Room(0,1,"North Room","This room is to the North");
        testingRoom.addConnectingRoom("n",nRoom);
        testingRoom.removeConnectingRoom("n");
        assertNull(testingRoom.getConnectingRoom("n"));
    }

    @Test
    void removeConnectingRoom2(){
        nRoom = new Room(0, 1, "North Room", "This room is to the North");
        testingRoom.addConnectingRoom("n",nRoom);
        testingRoom.removeConnectingRoom("n");
        testingRoom.removeConnectingRoom("n");
        testingRoom.removeConnectingRoom("n");
        assertNull(testingRoom.getConnectingRoom("n"));
    }

    @Test
    void addConnectingRoom() {
        Room southRoomTest = testingRoom.getRoomSouth();
        assertEquals(sRoom, southRoomTest);
    }
}