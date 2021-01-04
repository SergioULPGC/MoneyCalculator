/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moneycalculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 *
 * @author delSe
 */
public class MoneyCalculator {

    private double amount;
    private double exchangerate;
    private String currencyFrom;
    private String currencyTo;
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        /*
        System.out.println("Introduzca una cantidad en dolares: ");
        Scanner scanner = new Scanner(System.in);
        double amount = Double.parseDouble(scanner.next());
        double exchangeRate = getExchangeRate("USD", "EUR");
        System.out.println(amount + " USD equivalen a " + amount*exchangeRate + " EUR");
        */
        
        MoneyCalculator moneyCalculator = new MoneyCalculator();
        moneyCalculator.control();
    }
    
    private void control() throws IOException {
        input();
        process();
        output();
    }
    
    private void input(){
        System.out.println("Introduzca una cantidad: ");
        Scanner scanner = new Scanner(System.in);
        amount = Double.parseDouble(scanner.next());
        
        System.out.println("Introduce una divisa: ");
        currencyFrom = scanner.next();
        
        System.out.println("Introduzca divisa de destino: ");
        currencyTo = scanner.next();
    }
    
    private void process() throws IOException{
        exchangerate = getExchangeRate(currencyFrom, currencyTo);
    }
    
    private void output(){
        System.out.println(amount + " " + currencyFrom + "equivalen a " + amount*exchangerate + " " + currencyTo);
    }
    
     private static double getExchangeRate(String from, String to) throws IOException{
        URL url = new URL("http://free.currencyconverterapi.com/api/v5/convert?q=" + from + "_" + to + "&compact=y");
        URLConnection connection = url.openConnection();
        
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))){
            String line = reader.readLine();
            String line1 = line.substring(line.indexOf(to)+12, line.indexOf("}"));
            return Double.parseDouble(line1);
            
        }
    }
}
 