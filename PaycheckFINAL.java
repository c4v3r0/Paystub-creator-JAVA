/*
Marcelo Cavero
MIS 350 
This program will generate employee paystub through manager entry of hours, salary along with other vital information in order to produce a weekly paystub.
*/

//import scanners
import java.util.Scanner;
import java.util.Calendar;

public class PaycheckFINAL{
   //Main method 
   public static void main(String [] args) {
      //scanner input and calendar declaration
      Scanner input = new Scanner(System.in);
      Calendar now = Calendar.getInstance();
      
      //1. instantiate the employee class - Create a employee object
      Employees [] emps = new Employees [75];
      
      emps[0] = new Employees("Marcelo", "Cavero", 3075, "H", 17.75);
      emps[1] = new Employees("Michelle", "Cross", 1234, "S", 950);
      
      //pointer
      int p = 2;
      
      //declare
      int rIndex = 0;
      double totalPay = 0;
      double fed = 0;
      double state = 0;
      double ss = 0;
      double mdc = 0;
      double totalDeducted = 0;
      double netPay = 0;
      String fd = null;
      String ld = null;
      double hours = 0;
      double overtimeHours = 0;
      double overtime = 0;
      double rate = 0;
      
      //welcome message
      System.out.println("Hello, welcome to paycheck generator. \nThis program will allow you to generate a paycheck for an employee or add a new employee to the database.");
      
      
      //user input for choice
         System.out.println("1. Add new employee\n2. Generate paycheck for existing employee");
         System.out.println("Please enter the service number (e.g., 1 or 2): ");
         int transactionNum  = input.nextInt();
         
         //switch statement
         switch (transactionNum) {
            case 1:
            System.out.print("Please enter Employee's last four social security number: ");
            int search = input.nextInt();
            rIndex = searchByNumber(search, emps, p);
      
               if(rIndex == -1){
                     //set the employee first and last name (input then send to employee class)
                     System.out.print("Please enter the employee first name: ");
                     String fn = input.next();
                     System.out.print("Please enter the last name: ");
                     String ln = input.next();
                     
                     //Set employee last four Social Security
                     int lf = search;
                     
                     //Set Hourly or salaried - ask the user H for hourly or S for Salaried employee
                     System.out.print("Enter either 'H' for hourly employee or 'S' for Salaried Employee(note: ENTER ONLY UPPERCASE):");
                     String es = input.next();
               
                     //set pay rate 
                     System.out.print("Enter Employee's Pay rate(e.g., 17.64):$");
                     double pr = input.nextDouble();
                     emps[p] = new Employees(fn, ln, lf, es, pr);
                     p++;
                     
                     }else break;
            case 2:
               //do while loop to enter valid Employee ID 
               do{
               System.out.print("Please enter Employee's last four social security number: ");
            
               rIndex = searchByNumber(input.nextInt(), emps, p);
               
               if (rIndex == -1){
               System.out.println("Invalid");}
               else{

                  System.out.println("\nEnter the starting date that this employee is getting paid for(e.g., 01/21/2020): ");
                     fd = input.next();
                     System.out.println("Ending date: ");
                     ld = input.next();
                        
                        //if statement(hourly/salaried) 
                        if(emps[rIndex].getEStatus().equals("H")){ 
                           //User input Amount of hours worked
                           System.out.printf("\nPlease enter %s's amount of hours worked in this week(e.g., 45.6): ", emps[rIndex].getName()); 
                           hours = input.nextDouble();
                           rate = emps[rIndex].getPayRate();
                           
            
                              //if employee worked > 40 hours then calculate overtime pay. else calculate regular pay
                              if(hours > 40){
                                 overtime = emps[rIndex].overtimePay(hours, rate);
                                 totalPay = overtime  + (40 * rate);
                                 overtimeHours = hours - 40;
                              }else{
                                 overtime = 0;
                                 totalPay = hours * rate;
                              }   
                           
                          }
                           
                          //else Salaried
                          else {
                             totalPay = emps[rIndex].getPayRate();
                             
                           }
                           break;
                   
                    } 
                 }while(rIndex == -1);    
                        
         }//end switch
      
     
          //Calculate Total pay and deduct Federal/state Withholdings, medicare, and social security)
               fed = totalPay * .0599;
               state = totalPay * .0291;
               ss = totalPay * .062;
               mdc = totalPay * .0145;
               //calculate total earnings, total deducted, net earnings
               totalDeducted = fed + state + ss + mdc;
               netPay = totalPay - totalDeducted;
            //display paystub in a table 
            System.out.println("");
            System.out.print("Printed On: ");
            System.out.print(now.getTime());
            System.out.println("\nPEAKE DESIGN & BUILD LLC.");
            System.out.println("123 West St. Fairfax, VA 22030");
            System.out.println("EMPLOYEE\t\t\t\tSOCIAL SECURITY NO.\t\tPAY RATE\t\tPAY PERIOD");
            System.out.printf("%-18sXXX-XX-%-17d%-12.2f%s to %s\n",emps[rIndex].getName(), emps[rIndex].getLastFour(), emps[rIndex].getPayRate(), fd, ld );
            System.out.println("");
            System.out.println("EARNINGS\t\tHOURS\t\t   AMOUNT\t\t\tDEDUCTIONS\t\t\tAMOUNT");
            System.out.printf("Regular\t\t%-12.2f%-10.2f\t\tFederal W/H\t\t\t%.2f\n", hours - overtimeHours,(hours - overtimeHours) * emps[rIndex].getPayRate(), fed );
            System.out.printf("Overtime\t\t%-12.2f%-10.2f\t\tVA State W/H\t\t%.2f\n", overtimeHours, totalPay - ((hours - overtimeHours) * emps[rIndex].getPayRate()),state );
            System.out.printf("\t\t\t\t\t\t\t\t\t\t\t\t\tFICA\t\t\t\t\t%.2f\n", ss);
            System.out.printf("GROSS EARNINGS:\t\t\t%-15.2fMedicare\t\t\t\t%.2f\n", totalPay, mdc);
            System.out.printf("TOTAL DEDUCTED:\t\t\t%.2f\n", totalDeducted);
            System.out.printf("NET EARNINGS:  \t\t\t%.2f", netPay);
            
            System.out.println("");

      
      
     

      
   }//end main method
   
  
      
   
   //search method 
   public static int searchByNumber(int n, Employees[] e, int p){
      for (int i = 0; i < p; i++) {
         if(e[i].getLastFour() == n)
            return i;
      }  
      return -1; 
   } 
   
    
}//end public class 