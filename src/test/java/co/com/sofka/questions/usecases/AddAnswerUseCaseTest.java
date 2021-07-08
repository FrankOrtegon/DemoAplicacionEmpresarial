package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Answer;
import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.reposioties.AnswerRepository;
import co.com.sofka.questions.usecase.AddAnswerUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class AddAnswerUseCaseTest {


    @SpyBean
    private AddAnswerUseCase addAnswerUseCase;

    @MockBean
    private AnswerRepository answerRepository;

    @Test
    public void addAnswer(){

        var answerDTO = new AnswerDTO("1","22bcD","Es un framework");
        var answer = new Answer();
        answer.setQuestionId("1");
        answer.setUserId("22bcD");
        answer.setAnswer("Es un framework");

        when(answerRepository.save(Mockito.any(Answer.class))).thenReturn(Mono.just(answer));

        var result = addAnswerUseCase.addAnswer(answerDTO);

        Assertions.assertEquals(result.block().getAnswer(),"Es un framework");
    }

}