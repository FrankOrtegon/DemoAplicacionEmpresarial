package co.com.sofka.questions.usecases;

import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.reposioties.AnswerRepository;
import co.com.sofka.questions.reposioties.QuestionRepository;
import co.com.sofka.questions.usecase.DeleteUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;


@SpringBootTest
class DeleteUseCaseTest {
    @MockBean
    private AnswerRepository answerRepository;
    @MockBean
    private QuestionRepository questionRepository;

    @SpyBean
    private DeleteUseCase deleteUseCase;

    @Test
    public void deleteQuestion() {

        var questionDTO = new QuestionDTO("1","1A","Que es SpringBoot?","OPEN","Programming");
        var answerDTO = new AnswerDTO();
        answerDTO.setQuestionId("1");
        answerDTO.setUserId("1A");
        answerDTO.setAnswer("Un framework");

        Mockito.when(questionRepository.deleteById("1")).thenReturn(Mono.empty());
        Mockito.when(answerRepository.deleteByQuestionId("1")).thenReturn(Mono.empty());

        var result  = deleteUseCase.deleteByquestionId("1").block();

        Assertions.assertEquals(result,null);
    }
}