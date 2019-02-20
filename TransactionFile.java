import java.io.*;
import java.util.ArrayList;

public class TransactionFile{
  // List of transaction strings that will be written to the transactions file
  private static String transactionsFile = "daily_transaction.txt";
  private static ArrayList<String> transactionLines = new ArrayList<>();

  public static void addTransaction(String line){
    transactionLines.add(line);
  }

  public static void userTransactionLine(String code, String username, String userType, double amount){
    transactionLines.add(code + String.format(" %1$-15s %2$s %3$012.2f", username, userType, amount));
  }
  
  public static User logout(User user) {
    PrintWriter out;
    try {
      out = new PrintWriter(new BufferedWriter(new FileWriter(transactionsFile, true)));
    } catch (IOException e) {
      System.out.println("Error logging out");
      return user;
    }
    transactionLines.add(user.endSession());
    for (String line : transactionLines) {
      out.println(line);  
    }
    out.close();
    transactionLines = new ArrayList<>();
    return null;
  }
}