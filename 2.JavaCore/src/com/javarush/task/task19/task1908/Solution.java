package com.javarush.task.task19.task1908;

/* 
Выделяем числа
*/

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws IOException{
        ArrayList<String>list=new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1= reader.readLine();
        String file2= reader.readLine();
        reader.close();

       // String pattern = "(\\W|\\b)([0-9][0-9][0-9])(\\W|\\b)"; \\   --- p{Punct} \\ а дальше в скобках можно указать группу символов по назначению \\﻿﻿﻿﻿    '\uFEFF' = 65279           |\\p{Cf}
      // String pattern = "(?<=//s)([1-9])([0-9]{1,})|(\\s0\\s)";//(?<=//s)  (?=\\s)
      // String pattern = "(?<![\\w\\p{Punct}])[0-9]{1,} ";
       // String pattern = "(?<![\\w\\p{Punct}])[1-9]\\d*(?=[\\s.])|(?<![\\w\\p{Punct}])[0](?=\\s.)";
     // String pattern = "(?<![\\w\\p{Punct}])[1-9][\\d]{1,}(?=\\s)|(?<![\\w\\p{Punct}])[0](?=\\s)";
      //  String pattern = "(?<![\\w\\p{Punct}])[-+]?\\d{1,}(?![\\w\\p{Punct}])";
        String pattern = "(?<![\\w\\p{Upper}])[-+]?\\d{1,}(?![\\w\\p{Punct}])";
       // String pattern = "(?<!\\b)[-+]?\\d{1,}(?!\\b)";

        //  String pattern = "(\\b)([1-9])([0-9]{1,})(\\b)";

       // String pattern = "(?<![\\w\\p{Punct}])[1-9]\\d*(?![\\w\\p{Punct}])|(?<![\\w\\p{Punct}])[0](?![\\w\\p{Punct}])";
     //   String pattern = "(?<![\\w\\p{Punct}])[-+]{0,}[1-9]\\d*(?![\\w\\p{Punct}])|(?<![[\\w\\p{Punct}]&&[^+-]])[0](?![\\w\\p{Punct}])";
      //  String pattern = "(?<![[\\w\\p{Punct}]&&[^+]])[-]{0,}[1-9]\\d*(?![\\w\\p{Punct}])|(?<![[\\w\\p{Punct}]&&[^+-]])0{1,}(?![\\w\\p{Punct}])";
       // String pattern = "(?<![[\\w\\p{Punct}]&&[^+]])[-]*[1-9]\\d*(?![\\w\\p{Punct}])|(?<![[\\w\\p{Punct}]&&[^+-]])0{1,}(?![\\w\\p{Punct}])";
       // String pattern = "(?<![[\\w\\p{Punct}]&&[^+-]])-?[1-9]\\d*(?![\\w\\p{Punct}])|(?<![[\\w\\p{Punct}0]&&[^+-]])0(?![\\w\\p{Punct}0])";
        //String pattern = "(?<![[\\w\\p{Punct}]&&[^+]])-?[1-9]*(?![\\w\\p{Punct}])|(?<![[\\w\\p{Punct}]&&[^+-]])0+(?![\\w\\p{Punct}0-9])";

        //String pattern = "(?<![[\\w\\p{Punct}]&&[^+]])-?[1-9]\\d*(?![\\w\\p{Punct}])|(?<![[\\\\w\\\\p{Punct}]&&[^+-]])0+?(?![\\w\\p{Punct}])";  //ВОт ЭТО


        Pattern p = Pattern.compile(pattern);

        BufferedReader fileReader= new BufferedReader (new FileReader(file1));
        BufferedWriter  fileWriter = new BufferedWriter (new FileWriter(file2));
        String st=new String();
        while ((st=fileReader.readLine() ) != null){

           /*String[]array=st.split(" ");
            for (String ar:array)
            {
                //ar = ar.replaceAll(" ","").trim();
                try{
                    fileWriter.write((Integer.parseInt(ar) + " "));
                }catch(NumberFormatException e){}
            }*/

            Matcher m = p.matcher(st);
            while (m.find()) {
                 //list.add(((st.substring(m.start(), m.end()).replaceAll("0{1,}","0"))).replaceAll("[+]",""));
                list.add((((st.substring(m.start(), m.end()).replaceAll("(?<![1-9])0*(?=[0-9])",""))).replaceAll("[+]","")).replaceAll("[-](?=0)",""));
                // fileWriter.write(st.substring(m.start(), m.end())+" ");
             // System.out.print((((st.substring(m.start(), m.end()).replaceAll("(?<![1-9])0*(?=[0-9])",""))).replaceAll("[+]","")).replaceAll("[-](?=0)","") +" ");
              //  System.out.println("2345 0 5467 0 -777 767 1111 0 -999786 0 8888987 0 888888 214 1 464 88454 36 0 2 2 14 1 244355 7878 0 444 0 0 555 0 888 -222288888 99992222 777777 0 0 2222 3333 4545 353 99999 789 919191 567 191919 -838383 10101010 2020202".equals("2345 0 5467 0 -777 767 1111 0 -999786 0 8888987 0 888888 214 1 464 88454 36 0 2 2 14 1 244355 7878 0 444 0 0 555 0 888 -222288888 99992222 777777 0 0 2222 3333 4545 353 99999 789 919191 567 191919 -838383 10101010 2020202"));
            }
        }

        for (int i = 0; i <list.size() ; i++) {
           fileWriter.write(list.get(i)+" ");
            /*if (i==list.size()-1){
                fileWriter.write(list.get(i)+" ");}
            else
                fileWriter.write(list.get(i)+" ");*/

        }

        fileReader.close();
      fileWriter.close();


    }
}
