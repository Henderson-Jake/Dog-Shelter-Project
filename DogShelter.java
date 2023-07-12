import java.util.Scanner;

public class DogShelter {
  public static Scanner gIn = new Scanner(System.in);
  public static final int gMAX_DOGS = 100;
  public static int gShelterSize;
  public static double [] gWeight = new double[100];

  
  public static int getDogsByClassification(int classCode) {
    int i;
    int [] classificationCode = new int[5];
    for(i = 0; i < gShelterSize; ++i) {
      if(gWeight[i] >= 90.00 && gWeight[i] <= 100.00) {
        classificationCode[4] += 1;
      }
      else if(gWeight[i] >= 50.00 && gWeight[i] < 90.00) {
        classificationCode[3] += 1;
      }
      else if(gWeight[i] >= 30.00 && gWeight[i] < 50.00) {
        classificationCode[2] += 1;
      }
      else if(gWeight[i] >= 20.00 && gWeight[i] < 30.00) {
        classificationCode[1] += 1;
      }
      else if(gWeight[i] > 0.00 && gWeight[i] < 20.00) {
        classificationCode[0] += 1;
      }
    }
    return classificationCode[classCode];
  }
  
  
  public static void swapValues(int f, int t) {
    double tempVal;
    tempVal = gWeight[f];
    gWeight[f] = gWeight[t];
    gWeight[t] = tempVal;
  }
  
  
  public static int getValues(String message, int maxOption) {
    int userValue;
    System.out.print(message);
    userValue = gIn.nextInt();
     
    if(userValue > maxOption || userValue < 0) {
      do{
        System.out.println("Value out of range, please, try again.");
        System.out.print(message);
        userValue = gIn.nextInt();
      }while(userValue > maxOption || userValue < 0);
    }
    return userValue;
  }
  
  
  public static double getValues(String message, double maxOption) {
    double userValue;
    System.out.print(message);
    userValue = gIn.nextDouble();
     
    if(userValue > maxOption || userValue < 0.00) {
      do{
        System.out.println("Value out of range, please, try again.");
        System.out.print(message);
        userValue = gIn.nextDouble();
      }while(userValue > maxOption || userValue < 0.00);
    }
    return userValue;
  }
  
  
   public static void mainMenu() {
    int userInput;
    String message = "\n" + "MAIN MENU" + "\n" + "0 - Exit, 1 - Report, 2 - Assign/Modify dogs, 3 - (Ex)Change Dog, 4 - Close Shelter, 5 - List" + "\n" + "Select an option : ";
    
    userInput = getValues(message, 5);
    
    if(userInput == 0) {
      System.out.print("Thank you for looking at the Dog Shelter info! Bye!");
    }
    
    if(userInput == 1) {
      report();
        
      mainMenu();
    }
    
    if(userInput == 2) {
      assignModifyDog();
      
      mainMenu();
      }
    
    if(userInput == 3) {
      changeDog();
      
      mainMenu();
    }
    
    if(userInput == 4) {
      closeShelter();
      
      mainMenu();
    }
    
    if(userInput == 5) {
      listDogs();
      
      mainMenu();
    }
    
  }
  
  
  public static void report() {
    System.out.println("DOGS' REPORT");
    System.out.println("Xlarge : " + getDogsByClassification(4) + "\n" + "Large : " + getDogsByClassification(3) + "\n" + "Medium : " + getDogsByClassification(2) + "\n" + "Small : " + getDogsByClassification(1) + "\n" + "Xsmall : " + getDogsByClassification(0));
  }
  
  
  public static void assignModifyDog(){
    int index;
    double newWeight = 0;
    
    String message = "Enter the index (0 to " + (gShelterSize - 1) + ") : ";
    index = getValues(message, (gShelterSize - 1));
    
    System.out.print("The current weight of the dog at " + index + " is : ");
    System.out.println(gWeight[index]);
    message = "Enter the weight of the new dog (0.00 - 100.00) : ";
    newWeight = getValues(message, 100.00);
    
    gWeight[index] = newWeight;
  }
  
  public static void changeDog() {
    int idxFrom;
    int idxTo;
    
    String message = "Enter the position from (0 to " + (gShelterSize - 1) + ") : ";
    idxFrom = getValues(message, (gShelterSize - 1));
    
    message = "Enter the position to change to (0 to " + (gShelterSize - 1) + ") that is not " + idxFrom + " : ";
    idxTo = getValues(message, (gShelterSize - 1));
    if(idxTo == idxFrom) {
      do{
        System.out.println("Value out of range, please, try again.");
        System.out.print("Enter the position to change to (0 to " + (gShelterSize - 1) + ") that is not " + idxFrom + " : ");
        idxTo = gIn.nextInt();
      }while(idxTo == idxFrom || idxTo > (gShelterSize - 1) || idxTo < 0);
    }
    
    swapValues(idxFrom, idxTo);
  }
  
  public static void closeShelter() {
    int i;
    for(i = 0; i < gMAX_DOGS; ++i) {
      gWeight[i] = 0.0;
    }
  }
  
  
  public static void listDogs() {
    System.out.println("LIST OF DOGS' WEIGHT" + "\n");
    int i;
    
    for(i = 0; i < gShelterSize; ++i){
      System.out.println("Dog[" + i + "] : " + gWeight[i]);
    }
  }
  

  
  public static void main(String[] args) {
    System.out.println("UTSA - Spring 2022 - CS1083 - Section 002 - Project 2 - TexasShelter - written by Jake Henderson" + "\n");
    
    String message = "Please, enter the number of dogs in the shelter (Max 100): ";
    gShelterSize = getValues(message, gMAX_DOGS);
    
    mainMenu();
    
    }
  }