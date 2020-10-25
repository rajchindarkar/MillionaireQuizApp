package com.dc.msu.wwbm;

public class Quiz {
    String question,answer,options,prize;

    public Quiz(String question, String answer, String options,String prize) {
        this.question = question;
        this.answer = answer;
        this.options = options;
        this.prize=prize;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String getOptions() {
        return options;
    }

    public String getPrize() {
        return prize;
    }
}
