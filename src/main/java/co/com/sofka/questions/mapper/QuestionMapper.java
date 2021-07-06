package co.com.sofka.questions.mapper;


import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.model.QuestionDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class QuestionMapper {

    //Funcion para convertir de QuestionDto a question
    public Function<QuestionDTO, Question> mapperToQuestion(String id){
        return updateQuestion -> {
            var question = new Question();
            question.setId(id);
            question.setUserId(updateQuestion.getUserId());
            question.setQuestion(updateQuestion.getQuestion());
            question.setType(updateQuestion.getType());
            question.setCategory(updateQuestion.getCategory());
            return question;
        };

    }

    // funcion para convertir de Question a questionDTO

    public Function<Question, QuestionDTO> mapQuestionToDTO() {
        return entity -> new QuestionDTO(
                entity.getId(),
                entity.getUserId(),
                entity.getQuestion(),
                entity.getType(),
                entity.getCategory());
    }
}
