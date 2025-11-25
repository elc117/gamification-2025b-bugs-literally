package game.butterfly.quiz;

import java.util.List;

public class Question {
    private String text;
    private List<String> options;
    private int correctIndex;

    private String butterflyName;

    public Question(String text, List<String> options, int correctIndex, String butterflyName) {
        this.text = text;
        this.options = options;
        this.correctIndex = correctIndex;
        this.butterflyName = butterflyName.toLowerCase();
    }

    public String getText() {
        return text;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectIndex() {
        return correctIndex;
    }

    public String getButterflyName() {
        return butterflyName;
    }

    public boolean isCorrect(int selectedOption) {
        return selectedOption == correctIndex;
    }
}
