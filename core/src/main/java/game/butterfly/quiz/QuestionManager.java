package game.butterfly.quiz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuestionManager {
    private List<Question> questions;

    public QuestionManager() {
        questions = new ArrayList<>();

        questions.add(new Question(
            "Qual é a borboleta símbolo do Rio Grande do Sul?",
            Arrays.asList("Borboleta Monarca", "Borboleta 88", "Borboleta Azul", "Zebra"),
            1
        ));

        questions.add(new Question(
            "Qual borboleta tem padrões que lembram olhos nas asas?",
            Arrays.asList("Zebra", "Olho-de-boi", "Monarca", "88"),
            1
        ));
        //dps adicionar mais, essas são testes
    }

    public Question getQuestionForButterfly(String name) {
        // dps ligar perguntas a cada uma
        for (Question q : questions)
            if (q.getText().toLowerCase().contains(name.toLowerCase()))
                return q;

        //aleatorio por enquanto
        return questions.get((int)(Math.random() * questions.size()));
    }
}
