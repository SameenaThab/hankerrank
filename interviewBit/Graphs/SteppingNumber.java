
import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.io.*;

/* https://www.interviewbit.com/problems/stepping-numbers/ 
A = 10 B = 20
start node = 0
From 0, we can move to 1 2 3 4 5 6 7 8 9 [ these are not in our range so we don't add it ]
now from 1, we can move to 12 and 10 
from 2, 23 and 21
from 3, 34 and 32
.
.
.
.
.
.
and so on

10 and 12 are in our range, so the answer = 2.
*/

public class SteppingNumber {
    int A,B;
    ArrayList<ArrayList<Integer>> adjacentMap;

    public static void main(String[] args) {
        SteppingNumber sol = new SteppingNumber();        
        System.out.println("Solution: "+sol.stepnum(10,30));
    }

    public ArrayList<Integer> stepnum(int A, int B) {
        this.A = A;
        this.B = B;
        ArrayList<Integer> result = new ArrayList<Integer>();
        Set<Integer> visited = new HashSet<Integer>();
        for(int i=0;i<=9;i++) {
            if(!visited.contains(i))
                bfs(i,result,visited);
        }
        Collections.sort(result);
        return result;
    }

    public void dfs(int node, ArrayList<Integer> result,Set<Integer> visited) {
        visited.add(node);
        if(node > B)
            return;
        if(node >= A)
            result.add(node);
        int lastDigit = node%10;
        int plusOne = node*10+lastDigit+1;
        int minusOne = node*10+lastDigit-1;
        if(lastDigit == 0) {
            if(!visited.contains(plusOne))
                dfs(plusOne,result,visited);
        }
        else if(lastDigit == 9) {
            if(!visited.contains(minusOne))
                dfs(minusOne,result,visited);
        }
        else {
            if(!visited.contains(minusOne))
                dfs(minusOne,result,visited);
            if(!visited.contains(plusOne))
                dfs(plusOne,result,visited);
        }
    }

    public void bfs(int node, ArrayList<Integer> result,Set<Integer> visited) {

        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(node);
        while(!queue.isEmpty()) {
            int curr = queue.poll();
            System.out.println("Node: "+curr);
            visited.add(curr);
            if(curr >= A) 
                result.add(curr);
            int lastDigit = curr%10;
            int plusOne = curr*10+lastDigit+1;
            int minusOne = curr*10+lastDigit-1;
            if(lastDigit == 0) {
                if(!visited.contains(plusOne) && plusOne<=B)
                    queue.add(plusOne);
            }
            else if(lastDigit == 9) {
                if(!visited.contains(minusOne) && minusOne<=B)
                    queue.add(minusOne);
            }
            else {
                if(!visited.contains(minusOne) && minusOne<=B)
                    queue.add(minusOne);
                if(!visited.contains(plusOne) && plusOne<=B)
                    queue.add(plusOne);
            }
        }
    }

}
