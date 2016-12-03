package com;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
public class RegexMatches
{
//    private static final String REGEX = "\\bcat\\b";
//    private static final String INPUT =
//                                    "cat cat cat cattie cat";

    private static final String REGEX = "/12.*?/23.*?\\b";
    private static final String INPUT =
            "/123/23";

    public static void main( String args[] ){
       Pattern p = Pattern.compile(REGEX);
       Matcher m = p.matcher(INPUT); // 获取 matcher 对象
       int count = 0;
 
       while(m.find()) {
         count++;
         System.out.println("Match number "+count);
         System.out.println("start(): "+m.start());
         System.out.println("end(): "+m.end());
      }



   }
}