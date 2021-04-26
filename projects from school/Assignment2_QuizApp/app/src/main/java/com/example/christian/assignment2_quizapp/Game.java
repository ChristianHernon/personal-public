package com.example.christian.assignment2_quizapp;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Game {

    private String name;
    private int score = 0;
    private ArrayList<String> terms = new ArrayList<String>();
    private ArrayList<String> definitions = new ArrayList<String>();
    private Map<String, String> quizMap = new HashMap<String, String>();

    private int getRandom(int max) {
        Random rand = new Random();
        return rand.nextInt(max);
    }

    public void fillHashMap() {
        for (int i = 0; i < terms.size(); i++) {
            quizMap.put(definitions.get(i), terms.get(i));
        }
        Collections.shuffle(terms);
        Collections.shuffle(definitions);
        Log.i("Info", "load of hash map complete");
    }

    public String[] nextQuestion() {
        String[] question = new String[5];
        if (definitions.size() > 0) {
            question[0] = definitions.get(0);
            question[1] = quizMap.get(question[0]);
            int index = getRandom(terms.size());
            for (int i = 2; i < question.length; i++) {
                while (Arrays.asList(question).contains(terms.get(index))) {
                    index = getRandom(terms.size());
                }
                question[i] = terms.get(index);
            }
        } else {
            question[0] = "::GAME_COMPLETE::";
        }
        return question;
    }

    public Game(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void addTerm(String term) {
        this.terms.add(term);
    }

    public String getTerm(int i) {
        return terms.get(i);
    }

    public void addDefinition(String definition) {
        this.definitions.add(definition);
    }

    public String getDefinition(int i) {
        return definitions.get(i);
    }

    public void removeDefinition(String definition) {
        definitions.remove(definition);
    }

}
