package com.example.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PaperScoreCalculate {
    public static HashMap<String, Object> paper_chinese = new HashMap<String, Object>();
    public static HashMap<String, Object> paper_english = new HashMap<String, Object>();
    public static List<Float> paper_score = new ArrayList<Float>();

    public static HashMap<String, Object> getPaper_chinese() {
        return paper_chinese;
    }

    public static List<Float> getPaper_score() {
        return paper_score;
    }

    public static void setPaper_score(List<Float> paper_score) {
        PaperScoreCalculate.paper_score = paper_score;
    }

    public static void setPaper_chinese(HashMap<String, Object> paper_chinese) {
        PaperScoreCalculate.paper_chinese = paper_chinese;
    }

    public static HashMap<String, Object> getPaper_english() {
        return paper_english;
    }

    public static void setPaper_english(HashMap<String, Object> paper_english) {
        PaperScoreCalculate.paper_english = paper_english;
    }
}
