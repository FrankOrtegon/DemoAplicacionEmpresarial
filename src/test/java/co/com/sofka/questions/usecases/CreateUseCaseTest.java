package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.reposioties.QuestionRepository;
import co.com.sofka.questions.usecase.CreateUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;


import static org.mockito.Mockito.when;

@SpringBootTest
class CreateUseCaseTest {
    @SpyBean
    private CreateUseCase createUseCase;

    @MockBean
    private QuestionRepository questionRepository;

    @Test
    public void createQuestion(){

        var questionDTO = new QuestionDTO("12","1","que fue primero", "OPEN","xxx");
        var question= new Question();
        question.setId("12");
        question.setQuestion("que fue primero");
        question.setUserId("1");
        question.setType("OPEN");
        question.setCategory("xxx");


        when(questionRepository.save(Mockito.any(Question.class))).thenReturn(Mono.just(question));

        var respuesta = createUseCase.insertar(questionDTO);

        Assertions.assertEquals(respuesta.block().getId(),"12");

    }
}