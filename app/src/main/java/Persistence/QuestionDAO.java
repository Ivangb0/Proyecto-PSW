package Persistence;


public class QuestionDAO extends DBA<Question> {
    public QuestionDAO(){
        init(Question.class);
    }
}
