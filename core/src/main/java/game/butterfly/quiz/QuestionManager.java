package game.butterfly.quiz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuestionManager {

    private List<Question> questions;

    public QuestionManager() {
        questions = new ArrayList<>();

        //janeira
        questions.add(new Question(
            "Por que a Janeira (Morpho catenarius) não se alimenta na fase adulta?",
            Arrays.asList(
                "Porque vive pouco após emergir",
                "Porque usa energia acumulada quando era lagarta",
                "Porque se alimenta apenas à noite",
                "Porque depende de minerais do solo"
            ),
            1,
            "janeira"
        ));

        //gema
        questions.add(new Question(
            "O que são os panapanás observados na Gema-de-ovo (Phoebis philea)?",
            Arrays.asList(
                "Acasalamento coletivo",
                "Agrupamentos que consomem nutrientes no lodo de rios",
                "Migração de longa distância",
                "Disputas territoriais"
            ),
            1,
            "gema"
        ));

        //caixão
        questions.add(new Question(
            "Por que a Caixão-de-defunto (Heraclides thoas brasiliensis) recebe esse nome?",
            Arrays.asList(
                "Pelo formato dos ovos",
                "Pelos tons escuros de suas asas",
                "Porque vive em cemitérios",
                "Porque voa apenas à noite"
            ),
            1,
            "caixao"
        ));

        //88
        questions.add(new Question(
            "Qual característica marca a Oitenta-e-oito (Diaethria clymena)?",
            Arrays.asList(
                "Asas totalmente pretas",
                "Padrão que lembra folhas",
                "Desenho que parece o número 88",
                "Padrão em formato de raio"
            ),
            2,
            "oito"
        ));

        //morpho
        questions.add(new Question(
            "O que destaca o Morpho archilles no artigo?",
            Arrays.asList(
                "Asas pretas opacas",
                "Faixas azuis metálicas que brilham ao sol",
                "Vive apenas em cidades",
                "Lagartas que comem flores carnívoras"
            ),
            1,
            "morpho"
        ));

        //viuvinha
        questions.add(new Question(
            "Por que a Viuvinha (Heraclides hectorides) vive apenas em florestas?",
            Arrays.asList(
                "Porque tolera apenas baixa temperatura",
                "Porque coloca ovos em periparobas, inexistentes nas cidades",
                "Porque é migratória",
                "Porque só se alimenta à noite"
            ),
            1,
            "viuvinha"
        ));

        //monarca
        questions.add(new Question(
            "O que o artigo afirma sobre a Monarca (Danaus plexippus) do Brasil?",
            Arrays.asList(
                "Migra até o Canadá",
                "A subespécie brasileira não migra",
                "As lagartas comem folhas de pinheiro",
                "É exclusiva do litoral"
            ),
            1,
            "monarca"
        ));

        //azulão
        questions.add(new Question(
            "O que é dito sobre o Azulão (Morpho aega) no artigo?",
            Arrays.asList(
                "A fêmea é azul e o macho é pardo",
                "Pode haver até cem machos para cada fêmea",
                "Vive apenas na beira de rios",
                "Aparece somente no inverno"
            ),
            1,
            "azulao"
        ));

        //maria-boba
        questions.add(new Question(
            "Por que a Maria-boba (Heliconius erato phyllis) vive mais que a maioria das borboletas?",
            Arrays.asList(
                "Porque tem baixa taxa metabólica",
                "Porque também consome pólen, aumentando a longevidade",
                "Porque só se reproduz uma vez",
                "Porque vive em ninhos coletivos"
            ),
            1,
            "maria"
        ));
    }

    public Question getQuestionForButterfly(String name) {
        String n = name.toLowerCase();

        for (Question q : questions)
            if (q.getButterflyName().equals(n))
                return q;

        return questions.get((int)(Math.random() * questions.size()));
    }
}
