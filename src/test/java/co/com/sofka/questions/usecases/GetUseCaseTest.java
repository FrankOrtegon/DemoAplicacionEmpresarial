package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.reposioties.QuestionRepository;
import co.com.sofka.questions.usecase.GetUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;


import static org.mockito.Mockito.when;
@SpringBootTest
class GetUseCaseTest {

    @MockBean
    private QuestionRepository questionRepository;

    @SpyBean
    private GetUseCase getUseCase;

    @Test
    public void getQuestion() {

        var questionDTO = new QuestionDTO("1","1A","Que es SpringBoot?","OPEN","Programming");
        var question = new Question();
        question.setId("1");
        question.setUserId("1A");
        question.setQuestion("Ques es SpringBoot");
        question.setType("OPEN");
        question.setCategory("Programming");

        when(questionRepository.findById(Mockito.any(String.class))).thenReturn(Mono.just(question));

        var result = getUseCase.apply(questionDTO.getId());

        Assertions.assertEquals(result.block().getId(),"1");
    }
}