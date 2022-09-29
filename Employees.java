//This is the Employee class with states and methods to define a model. 
public class Employees { 
   // Instance Variables
   String name;
   int lastFour;
   String eStatus;
   double payRate;
   double overtimePay;
   double basePay;
   
   public Employees(){
   } 
   
   public Employees(String fn, String ln, int lf, String es, double pr){
      name = fn + " " + ln;
      lastFour = lf;
      eStatus = es;
      payRate = pr;
      
   }
   //set methods
   
      //set name
      public void setName(String fn, String ln){
         name = fn + " " + ln; 
      }

      //set employee last four digits
      public void setLastFour(int four){
         lastFour = four;
      }
      
      //set employee status(H or S)
      public void setEStatus(String status){
         eStatus = status;
      }
      
      //set employee payrate 
      public void setPayRate(double pay){
         payRate = pay;
      }

      //get methods
      
      //getName
      public String getName() {
         return name;
      }
      
      //getLastFour
      public int getLastFour() {
         return lastFour;
      }
      
      //getStatus
      public String getEStatus() {
         return eStatus;
      }
      
      //getPayRate
      public double getPayRate() {
         return payRate;
      }
      
      //overtime pay 
      public double overtimePay(double time, double pay){
         overtimePay = (time - 40) * (pay * 1.5);
         return overtimePay;
      }
      


}//end class