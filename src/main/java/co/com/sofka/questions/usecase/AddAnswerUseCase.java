package co.com.sofka.questions.usecase;

import co.com.sofka.questions.mapper.AnswerMapper;
import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.reposioties.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class AddAnswerUseCase {
    private final AnswerRepository answerRepository;
    private final AnswerMapper answerMapper;

    @Autowired
    public AddAnswerUseCase(AnswerRepository answerRepository, AnswerMapper answerMapper) {
        this.answerRepository = answerRepository;
        this.answerMapper = answerMapper;
    }

    public Mono<AnswerDTO> addAnswer(AnswerDTO answerDTO) {
        var respuesta = answerRepository.save(answerMapper.fromAnswerDtoToAnswer(null)
                .apply(answerDTO));
        return respuesta.map(answerMapper.fromAnswerToAnswerDTO());
    }
}
