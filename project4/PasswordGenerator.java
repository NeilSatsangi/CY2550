import java.util.*;
import java.io.*;


public class PasswordGenerator{
  public static void main(String[] args) throws NumberFormatException, IOException{
    int words = 4;
    int caps = 0;
    int nums = 0;
    int symbols = 0;
    ArrayList<String> password = new ArrayList<String>();
    
    int numLines = 0;
    try (BufferedReader reader = new BufferedReader(new FileReader("words.txt"))) {
      while (reader.readLine() != null) {
        numLines++;
      }
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    
    BufferedReader reader = new BufferedReader(new FileReader("words.txt"));
    ArrayList<String> wordList = new ArrayList<String>();
    
    for (int i = 0; i < numLines; i++) {
      wordList.add(reader.readLine());
    }
    reader.close();
    
    
    ArrayList<String> symbolsList = new ArrayList<String>(Arrays.asList("~", "!", "@", 
        "#", "$", "%", "^", "&", "*", "+"));
  
    
    ArrayList<String> numbersList = new ArrayList<String>(Arrays.asList("0", "1", "2", "3", "4", 
        "5", "6", "7", "8", "9"));

    Iterator<String> line = Arrays.asList(args).iterator();
    while (line.hasNext()) {
      switch (line.next()) {
        case "-h":
        case "--help":
          System.out.println("usage: xkcdpwgen [-h] [-w WORDS] [-c CAPS] [-n NUMBERS] [-s SYMBOLS]\r\n"
              + "\r\n Generate a secure, memorable password using the XKCD method\r\n"
              + "\r\n optional arguments:\r\n"
              + "    -h, --help            show this help message and exit\r\n"
              + "    -w WORDS, --words WORDS\r\n"
              + "                          include WORDS words in the password (default=4)\r\n"
              + "    -c CAPS, --caps CAPS  capitalize the first letter of CAPS random words\r\n"
              + "                          (default=0)\r\n"
              + "    -n NUMBERS, --numbers NUMBERS\r\n"
              + "                          insert NUMBERS random numbers in the password\r\n"
              + "                          (default=0)\r\n"
              + "    -s SYMBOLS, --symbols SYMBOLS\r\n"
              + "                          insert SYMBOLS random symbols in the password\r\n"
              + "                          (default=0)");
          break;
        case "-w":
        case "--words":
          words = Integer.parseInt(line.next());
          break;
        case "-c":
        case "--caps":
          caps += Integer.parseInt(line.next());
          break;
        case "-n":
        case "--numbers":
          nums += Integer.parseInt(line.next());
          break;
        case "-s":
        case "--symbols":
          symbols += Integer.parseInt(line.next());
      }   
    }
    
    for (int j = 0; j < words; j++) {
      int randIndex = (int) (Math.random() * numLines);
      password.add(wordList.get(randIndex));
    }
    for (int j = 0; j < caps; j++) {
      int r = (int)(Math.random()*password.size());
      String temp = password.get(r);
      temp = temp.substring(0,1).toUpperCase() + temp.substring(1);
      password.set(r, temp);
    }
    for (int j = 0; j < nums; j++) {
      int r1 = (int)(Math.random()*2);
      int r2 = (int)(Math.random()*password.size());
      int r3 = (int)(Math.random()*numbersList.size());
      String temp = password.get(r2);
      String tempNum = numbersList.get(r3);
      if (r1 == 0) { 
        temp += tempNum;
        password.set(r2, temp);
      }
      else {
        temp = tempNum + temp;
        password.set(r2, temp);
      }
    }
    for (int j = 0; j < symbols; j++) {
      int r1 = (int)(Math.random()*2);
      int r2 = (int)(Math.random()*password.size());
      int r3 = (int)(Math.random()*symbolsList.size());
      String temp = password.get(r2);
      String tempSym = symbolsList.get(r3);
      if (r1 == 0) {
        temp += tempSym;
        password.set(r2, temp);
      }
      else {
        temp = tempSym + temp;
        password.set(r2, temp);
      }
    }
    
    
    for (int i = 0; i < password.size(); i++) {
      System.out.print(password.get(i));
    }
    System.out.println("");


  }
}

