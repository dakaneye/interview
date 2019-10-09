package com.sam.dacanay.interview.braces;

import java.util.Stack;

public class BracesSolution {

    public static boolean bracesAreValid(String expression) {
        Stack<Character> braceStack = new Stack<>();
        for (int i = 0; i < expression.length(); i++) {
            char currentChar = expression.charAt(i);
            if (isOpenBrace(currentChar)) {
                braceStack.push(currentChar);
            } else if (isCloseBrace(currentChar)) {
                if (!isSameBraceType(braceStack.pop(), currentChar)) {
                    return false;
                }
            }
        }
        return braceStack.pop() == null;
    }

    public static boolean isOpenBrace(char aChar) {
        return aChar == '{' || aChar == '[' || aChar == '(';
    }

    public static boolean isCloseBrace(char aChar) {
        return aChar == '}' || aChar == ']' || aChar == ')';
    }

    public static boolean isSameBraceType(char poppedChar, char currentChar) {
        return (poppedChar == '{' && currentChar == '}') || (poppedChar == '[' && currentChar == ']') || (poppedChar == '(' && currentChar == ')');
    }
}
