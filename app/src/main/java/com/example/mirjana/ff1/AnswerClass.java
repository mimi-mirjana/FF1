package com.example.mirjana.ff1;

/**
 * The Question class has an Answer that is found in an ArrayList of multiple answers for a Question.
 * @author Mirjana Mijalkovic
 * @version 1.0
 * @date June 7 2017
 */

public class AnswerClass {
    int num;
    String text;

    /**
     * @param n The number of responses for the answer
     * @param s The answer as a String
     **/
    public AnswerClass(int n, String s){
        num = n;
        text = s;
    }

    /**
     * Returns the number of answer responses
     * @return num
     */
    public int getNum(){
        return num;

    }

    /**
     * Converts text into a string
     * @return the numbers of responses as a string text
     */
    @Override
    public String toString(){
        return text + " survey says " + num;
    }

}
