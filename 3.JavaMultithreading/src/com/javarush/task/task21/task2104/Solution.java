package com.javarush.task.task21.task2104;

import java.util.HashSet;
import java.util.Set;

/* 
Equals and HashCode
*/
public class Solution {
    private final String first, last;

    public Solution(String first, String last) {
        this.first = first;
        this.last = last;
    }

    @Override

    public boolean equals(Object n) {
        if (! (n instanceof Solution)){return false;}
        if (this == n) return true;
        if (n==null)
            return false;
        /*if (n.getClass() != this.getClass() )
            return false;*/
        Solution s1 = (Solution) n;

      /*  if (first != null ? !first.equals(s1.first) : s1.first != null) return false;
        return last != null ? last.equals(s1.last) : s1.last == null;*/

       return s1.first==first && s1.last==last;
     //  return s1.first.equals(this.first) && s1.last.equals(this.last);
    }
    @Override
    public int hashCode() {
        int result = first != null ? first.hashCode() : 0;

        result = 31 * result + (last != null ? last.hashCode() : 0);
        return result;
        //return 31 * first.hashCode() + last.hashCode();
    }

    public static void main(String[] args) {
        Set<Solution> s = new HashSet<>();
        s.add(new Solution("Donald", "Duck"));
        System.out.println(s.contains(new Solution("Donald", "Duck")));
        System.out.println(new Solution("Donald", "Duck").equals(new Solution("Donald", "Duck")));
        System.out.println(new Solution("Donald", "Duck").hashCode()==new Solution("Donald", "Duck").hashCode());
    }
}
