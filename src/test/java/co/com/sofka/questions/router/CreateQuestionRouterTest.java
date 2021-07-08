package co.com.sofka.questions.router;

import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.usecase.CreateUseCase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;


@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {CreateQuestionRouter.class})
class CreateQuestionRouterTest {
    @MockBean
    private CreateUseCase createUseCase;
    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void crearQuestionTest(){

        var questionDTO = new QuestionDTO("1", "12", "cual es el sentido de la vida", "OPEN","xxx");

        Mockito.when(createUseCase.insertar(questionDTO)).thenReturn(Mono.just(questionDTO));

        webTestClient.post().uri("/crearquestion")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(questionDTO), QuestionDTO.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(QuestionDTO.class)
                .value(userResponse ->{
                    Assertions.assertThat(userResponse.getId()).isEqualTo(questionDTO.getId());
                });
    }

}