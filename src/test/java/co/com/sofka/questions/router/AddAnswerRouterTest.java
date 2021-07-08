package co.com.sofka.questions.router;

import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.usecase.AddAnswerUseCase;
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

import static org.junit.jupiter.api.Assertions.*;
@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AddAnswerRouter.class})
class AddAnswerRouterTest {
    @MockBean
    private AddAnswerUseCase addAnswerUseCase;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void updateUseCaseTest(){

        var answer = new AnswerDTO("111","2","la gallina");

        Mockito.when(addAnswerUseCase.addAnswer(answer)).thenReturn(Mono.just(answer));

        webTestClient.post().uri("/añadirrespuesta")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(answer), AnswerDTO.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(AnswerDTO.class)
                .value(userResponse ->{
                    Assertions.assertThat(userResponse.getAnswer()).isEqualTo(answer.getAnswer());
                    Assertions.assertThat(userResponse.getUserId()).isEqualTo(answer.getUserId());
                });
    }

}