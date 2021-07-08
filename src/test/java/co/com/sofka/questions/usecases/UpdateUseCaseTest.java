package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.reposioties.QuestionRepository;
import co.com.sofka.questions.usecase.UpdateUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;


import static org.mockito.Mockito.when;
@SpringBootTest
class UpdateUseCaseTest {
    @SpyBean
    private UpdateUseCase updateUseCase;
    @MockBean
    private QuestionRepository questionRepository;

    @Test
    public void updateQuestion() {
        var questionDTO = new QuestionDTO("1","1A","Que es DDD?", "OPEN","Programming");
        var question = new Question();
        question.setId("1");
        question.setUserId("1A");
        question.setQuestion("Que es DDD?");
        question.setType("OPEN");
        question.setCategory("Programming");

        when(questionRepository.save(Mockito.any(Question.class))).thenReturn(Mono.just(question));

        var spy = updateUseCase.modificarQuestion(questionDTO);

        Assertions.assertEquals(spy.block().getQuestion(),"Que es DDD?");
    }
}