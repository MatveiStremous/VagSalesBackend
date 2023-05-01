package com.example.vagsalesbackend.services;

import com.example.vagsalesbackend.dto.requests.QuestionDTO;
import com.example.vagsalesbackend.dto.responses.BrandResponse;
import com.example.vagsalesbackend.dto.responses.QuestionResponse;
import com.example.vagsalesbackend.models.Question;
import com.example.vagsalesbackend.repositories.QuestionRepository;
import com.example.vagsalesbackend.util.mail.MailSender;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    private final MailSender mailSender;
    private final QuestionRepository questionRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public QuestionService(MailSender mailSender, QuestionRepository questionRepository, ModelMapper modelMapper) {
        this.mailSender = mailSender;
        this.questionRepository = questionRepository;
        this.modelMapper = modelMapper;
    }

    public void sendMessageToEmail(QuestionDTO questionDTO) {
        try {
            mailSender.send(questionDTO.getEmail(), "VAGSales. Ответ на вопрос.", questionDTO.getMessage());
        } catch (MessagingException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<BrandResponse> getAll() {
        List<Question> questions = questionRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        return modelMapper.map(questions, new TypeToken<List<QuestionResponse>>() {}.getType());
    }

    public Question getById(int id) {
        Optional<Question> foundQuestion = questionRepository.findById(id);
        return foundQuestion.orElse(null);
    }

    public void save(Question question) {
        questionRepository.save(question);
    }

    public void delete(int id){
        questionRepository.delete(getById(id));
    }
}
