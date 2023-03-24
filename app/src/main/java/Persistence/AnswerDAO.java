package Persistence;

import BusinessLogic.Answer;

public class AnswerDAO extends DBA<Answer> {
    public AnswerDAO(){
        init(Answer.class);
    }
}
