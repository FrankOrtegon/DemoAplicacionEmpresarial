package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.reposioties.QuestionRepository;
import co.com.sofka.questions.usecase.ListUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Flux;


import static org.mockito.Mockito.when;
@SpringBootTest
class ListUseCaseTest {
    @SpyBean
    private ListUseCase listUseCase;

    @MockBean
    private QuestionRepository questionRepository;

    @Test
    public void listQuestionTest(){
        var questionDTO1 = new QuestionDTO("12","2","que fue primero", "OPEN","xxxx");
        var questionDTO2 = new QuestionDTO("13","3","cual es el sentido de la vida", "OPEN","yyyy");

        var question1 = new Question();
        question1.setId("12");
        question1.setUserId("2");
        question1.setQuestion("que fue primero");
        question1.setType("OPEN");
        question1.setCategory("xxxx");


        var question2 = new Question();
        question2.setId("13");
        question2.setUserId("3");
        question2.setQuestion("cual es el sentido de la vida");
        question2.setType("OPEN");
        question2.setCategory("yyyy");


        when(questionRepository.findAll()).thenReturn(Flux.just(question1,question2));

        var respuesta = listUseCase.listQuestion();

        Assertions.assertEquals(respuesta.blockFirst().getId(),questionDTO1.getId());
        Assertions.assertEquals(respuesta.blockLast().getId(),questionDTO2.getId());

    }
}