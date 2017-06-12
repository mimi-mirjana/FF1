package com.example.mirjana.ff1;

import java.util.List;
import java.util.ArrayList;

/**
 * The Question class has a question String and and identification tag String with an ArrayList of answers for each question
 * @author Mirjana Mijalkovic
 * @version 1.0
 * @date June 7 2017
 **/
public class QuestionClass {
    String question;
    String _id;
    ArrayList<AnswerClass> answers;

    /**
     *
     * @param i The question identification tag
     * @param s The ArrayList of answers to the question
     */
    public QuestionClass(int i, String s){
        _id=Integer.toString(i);
        question = s;
        answers = new ArrayList<>();
    }

    /**
     * Adds another type Answer to the question's ArrayList
     * @param ans
     */
    public void addAnswer(AnswerClass ans){
        answers.add(ans);
    }

    /**
     * Gets all the answers of the specific question as an ArrayList
     * @return answers
     */
    public List<AnswerClass> getAnswers(){
        return answers;
    }

    /**
     * Gets the total number of answers available for a question
     * @return answers.size()
     */
    public int numAnswers(){
        return answers.size();
    }

    /**
     * Gets the question as a String
     * @return question
     */
    public String getQuestion(){
        return question;
    }
}
