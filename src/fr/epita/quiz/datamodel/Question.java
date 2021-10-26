package fr.epita.quiz.datamodel;

/**
*Question entity is dealing with global question information
*/
public class Question {

    private static final int DEFAULT_DIFFICULTY = 2;

    private Integer id;

    public static int getDefaultDifficulty() {
        return DEFAULT_DIFFICULTY;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // Defaults to 2
    private Integer difficulty = DEFAULT_DIFFICULTY;
    private String question;

    private Question(){

    }

    public Question(String title){
        this.question = title;
    }


    public Integer getDifficulty() {

        return difficulty;
    }

    public void setDifficulty(Integer difficulty)
    {
        this.difficulty = difficulty;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
