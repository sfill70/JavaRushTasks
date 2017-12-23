package com.javarush.task.task19.task1905;

import java.util.HashMap;
import java.util.Map;

/* 
Закрепляем адаптер
*/

public class Solution {
    public static Map<String,String> countries = new HashMap<String,String>();
    static {
        countries.put( "UA","Ukraine");
        countries.put( "RU","Russia");
        countries.put( "CA","Canada");

    }
    public static void main(String[] args) {
        String contact = "Ivanov, Ivan";
        System.out.println(contact.substring(0, contact.indexOf(", ")));
        System.out.println(contact.substring( contact.lastIndexOf(", "),contact.length()));

        String contact1="+38(050)12(3-4yu5-6(7dt((=";
        System.out.println(contact1);
        System.out.println(contact1.replaceAll("-","").replace("(","").replace(")",""));
        System.out.println(contact1.replaceAll("[^+0-9]", ""));
        System.out.println(contact1.replaceAll("[//^(+0-9]", ""));

        /*String Str = new String("Welcome to Tutorialspoint.com");
        System.out.print("Return Value :" );
        System.out.println(Str.replaceAll("(.*)Tutorials(.*)", "AMROOD"));*/
        // System.out.println(contact.getName().substring(contact.getName().lastIndexOf(" ")+1,contact.getName().length()));
    }

    public static class DataAdapter implements RowItem{
        private Customer customer;
        private Contact contact;
        public DataAdapter(Customer customer, Contact contact) {
            this.customer=customer;
            this.contact=contact;
        }
        public String getCountryCode(){String countryName=customer.getCountryName();
            String countryCode=null;
            for(Map.Entry<String, String> a: countries.entrySet()){
                if (a.getValue().equals(countryName)){
                    countryCode=a.getKey();
                }
            }
            return countryCode;}        //example UA
        public String getCompany(){return this.customer.getCompanyName();}            //example JavaRush Ltd.
        public String getContactFirstName() {
            String firstName=contact.getName().split(", ")[1];
            return firstName;
        }
        public String getContactLastName() {
            String lastName=contact.getName().split(", ")[0];
            return lastName;
        }
         /*public String getContactLastName(){return contact.getName().substring(0, contact.getName().indexOf(", "));}   //example Ivan
       public String getContactFirstName (){return contact.getName().substring(contact.getName().indexOf(" "),contact.getName().length());}    //example Ivanov*/

        public String getDialString(){return "callto://+"+contact.getPhoneNumber().replaceAll("[^0-9]", "");}
       // public String getDialString(){return  "callto://+"+ contact.getPhoneNumber().replaceAll("-","").replace("(","").replace(")","").replace("+","");}         //example callto://+380501234567

    }

    public static interface RowItem {
        String getCountryCode();        //example UA
        String getCompany();            //example JavaRush Ltd.
        String getContactFirstName();   //example Ivan
        String getContactLastName();    //example Ivanov
        String getDialString();         //example callto://+380501234567
    }

    public static interface Customer {
        String getCompanyName();        //example JavaRush Ltd.
        String getCountryName();        //example Ukraine
    }

    public static interface Contact {
        String getName();               //example Ivanov, Ivan
        String getPhoneNumber();        //example +38(050)123-45-67
    }
}