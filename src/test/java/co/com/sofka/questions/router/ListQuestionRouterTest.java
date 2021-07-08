package co.com.sofka.questions.router;

import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.usecase.ListUseCase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;


@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ListQuestionRouter.class})
class ListQuestionRouterTest {
    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private ListUseCase listUseCase;

    @Test
    public void testGetListQuestion() {
        QuestionDTO question1 = new QuestionDTO();
        question1.setId("1111");
        question1.setUserId("xxxx");
        question1.setQuestion("cual es el sentido de la vida");
        question1.setType("OPEN");
        question1.setCategory("yyyy");

        QuestionDTO question2 = new QuestionDTO();
        question1.setId("2222");
        question1.setUserId("zzzz");
        question1.setQuestion("cual es el sentido de la vida");
        question1.setType("OPEN");
        question1.setCategory("mmmm");

        Mockito.when(listUseCase.listQuestion()).thenReturn(Flux.just(question1, question2));

        webTestClient.get()
                .uri("/listquestion")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(QuestionDTO.class)
                .value(userResponse -> {
                            Assertions.assertThat(userResponse.get(0).getId()).isEqualTo(question1.getId());
                            Assertions.assertThat(userResponse.get(1).getId()).isEqualTo(question2.getId());
                        }
                );
    }

}