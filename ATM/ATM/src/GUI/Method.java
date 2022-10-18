
package GUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Random;

public class Method {
    
    double sum,sub,amnt;
    String history,uname,pas,typ,samnt,bid;
    String arr1[] = new String[40];
    String myline = "";
    String otherpart = "";
    
    public String PinChange(String acc,String oldP,String newp){
        return newp;
    }
    
    public double addmoney(double mf,double mu){
        sum = mf+mu;
        return sum;
    }
    
    public double submoney(double mf, double mu){
        sub = mf-mu;
        return sub;
    }
    
    public void history(String userName,String pass,String type,Double amount){
        
        uname = userName;
        pas = pass;
        amnt = amount;
        typ = type;
        samnt = String.valueOf(amnt);
        
        String aToZ = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"; // 36 letter.
        String randomStr = generateRandom(aToZ);
        
        try {
            FileReader fr = new FileReader("temp.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                arr1 = line.split("#");
                if (arr1[0].equals(userName) && arr1[1].equals(pass)) {
                    myline = line;
                } else {
                    otherpart += line + "$";
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(CashWithdraw.class.getName()).log(Level.SEVERE, null, ex);
        }

        String myarr[] = myline.split("#");
        
        myarr[0] = uname;
        myarr[1] = pas;
        myarr[2] = typ;
        myarr[3] = samnt;
        myarr[4] = randomStr;
        
        myline = myarr[0] + "#" + myarr[1] + "#" + myarr[2] + "#" + myarr[3] + "#" + myarr[4] + "$";

        otherpart += myline;

        try {

            PrintWriter pw = new PrintWriter(new FileWriter("history.txt"));

            String linearr[] = otherpart.split("[$]");
            for (int lineno = 0; lineno < linearr.length; lineno++) {
                pw.write(linearr[lineno]);
                pw.println();
            }
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static String generateRandom(String aToZ) {
        Random rand = new Random();
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            int randIndex = rand.nextInt(aToZ.length());
            res.append(aToZ.charAt(randIndex));
        } 
        return res.toString();
    }
}


