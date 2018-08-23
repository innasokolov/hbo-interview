package com.hbo.interview.bowling;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by innasokolov on 3/7/17.
 */
public class Test {

    public static void main(String []argh)
    {
        Scanner sc = new Scanner(System.in);
        Stack<Character> stack;
        while (sc.hasNext()) {
            stack = new Stack<Character>();
            String input=sc.next();
            for (int i = 0; i < input.length(); i++) {
                if (stack.empty()) {
                    stack.push(input.charAt(i));
                    i++;
                }
                if (i < input.length()) {
                    char next = input.charAt(i);
                    switch (stack.peek()) {
                        case '[' : {
                            if (next == ']') {
                                stack.pop();
                            }
                            else {
                                stack.push(next);
                            }
                            //               ({()})  {}()
                            break;              //               012345  0123
                        }
                        case '{' : {
                            if (next == '}') {
                                stack.pop();
                            }
                            else {
                                stack.push(next);
                            }
                            break;
                        }
                        case '(' : {
                            if (next == ')') {
                                stack.pop();
                            }
                            else {
                                stack.push(next);
                            }
                            break;
                        }
                    }
                }
            }
            System.out.println(stack.empty());
        }
    }
}
