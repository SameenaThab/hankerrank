import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.io.*;
import java.lang.reflect.Array;

public class  StringPermuatationsNoDup_Chap8Prob7 {

    public static void main(String[] args) {
        StringPermuatationsNoDup_Chap8Prob7 sol = new StringPermuatationsNoDup_Chap8Prob7();
        // ArrayList<String> result = sol.permutations("abcd");
        // System.out.println(result);
        ArrayList<String> result2 = sol.permutations2("abcd");
        System.out.println(result2);
        ArrayList<String> result3 = new ArrayList<String>();
        sol.permutations3("","abcd",result3);
        System.out.println(result3);
    }

    // a -> {a}
    // ab -> {ab,ba} -> insert  'a' in every permutation of b -> {a+b,b+a}
    // abc -> a*perm{bc} -> a*{bc,cb} -> {a+bc,b+a+c,bc+a,a+cb,c+a+b,a+cb}

    ArrayList<String> permutations(String st) {
        ArrayList<String> result = new ArrayList<String>();
        if(st.length()==0) {
            result.add("");
        }
        else {
            String letter = st.substring(0,1);
            ArrayList<String> subSet = permutations(st.substring(1));
            for(String perm:subSet) {
                for(int i=0;i<=perm.length();i++) {
                    result.add(perm.substring(0,i)+letter+perm.substring(i));
                }
            }
        }
        return result;  
    }

    // a -> {a}
    // ab -> {ab,ba} -> a*perm(b)
    // abc -> {abc,acb,bac,bca,cab,cba} -> {a*perm(bc), b*perm(ab), c*perm(ca)} 
    ArrayList<String> permutations2(String st) {
        ArrayList<String> result = new ArrayList<String>();
        if(st.length()==0) {
            result.add("");
        }
        else {
            for(int i=0;i<st.length();i++) {
                String before = st.substring(0,i);
                String after = st.substring(i+1);
                ArrayList<String> subSet = permutations2(before+after);
                for(String word:subSet) {
                    result.add(st.charAt(i)+word);
                }
            }
        }        
        return result;
    }

    //same as permutations2 but instead passing permutations down the stack, we push prefix to the stack
    void permutations3(String prefix,String remainder,ArrayList<String> result) {
        // System.out.println(prefix);
        if(remainder.length()==0) {
            result.add(prefix);
        }
        else {
            for(int i=0;i<remainder.length();i++) {
                String before = remainder.substring(0,i);
                String after = remainder.substring(i+1);
                permutations3(prefix+remainder.charAt(i),before+after,result);
            }
        }        
    }    
}
