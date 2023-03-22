package Persistence;

import android.content.Context;

public class AnswerDAO extends DBA<Answer> {
    public AnswerDAO(){
        init(Answer.class);
    }
}
