package com.company;

/**
 * The SickPrinter's job is to print out all the text on the screen in a sick way-
 * that is, by printing out at a controllable speed to give time to read everything.
 */
public class SickPrinter {
    static int printSpeed; // In letters per second.
    static int printLength; // The number of characters per line.

    public SickPrinter(int speed, int length){
        printSpeed = 1000/speed;
        printLength = length;
    }

    public static void print(String text){
        boolean addNewLine = false;
        int index = 0;
        while(index < text.length()){

            if(addNewLine){
                if(Character.isWhitespace(text.charAt(index))){
                    System.out.print("\n");
                    addNewLine = false;
                }
                else if ( text.charAt(index) == '.'
                        || text.charAt(index) == '?'
                        || text.charAt(index) == '!'
                        || text.charAt(index) == ','
                        || text.charAt(index) == ';'
                        || text.charAt(index) == '-')
                {
                    printChar(text, index);
                    System.out.print("\n");
                    addNewLine = false;
                }
                else {
                    printChar(text, index);
                }
            }
            else if(index > 0 && (index%printLength) == 0){
                addNewLine = true;
                printChar(text, index);
            }
            else {
                printChar(text, index);
            }

            index++;
        }
        System.out.print("\n");
    }

    private static void printChar(String text, int index) {
        System.out.print(text.charAt(index));
        try {
            Thread.sleep(printSpeed);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}
