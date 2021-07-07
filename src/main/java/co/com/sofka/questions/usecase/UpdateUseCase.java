package co.com.sofka.questions.usecase;

import co.com.sofka.questions.mapper.QuestionMapper;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.reposioties.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class UpdateUseCase {
    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    @Autowired
    public UpdateUseCase(QuestionRepository questionRepository, QuestionMapper questionMapper) {
        this.questionRepository = questionRepository;
        this.questionMapper = questionMapper;
    }

    public Mono<QuestionDTO> modificarQuestion(QuestionDTO questionDTO){
        var respuesta = questionMapper.mapperToQuestion(questionDTO.getId()).apply(questionDTO);
        return questionRepository.save(respuesta).map(questionMapper.mapQuestionToDTO());
    }
}
