package com.example.vagsalesbackend.controllers;

import com.example.vagsalesbackend.dto.requests.QuestionDTO;
import com.example.vagsalesbackend.dto.responses.BrandResponse;
import com.example.vagsalesbackend.models.Question;
import com.example.vagsalesbackend.services.QuestionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QuestionController {
    private final QuestionService questionService;
    private final ModelMapper modelMapper;

    @Autowired
    public QuestionController(QuestionService questionService, ModelMapper modelMapper) {
        this.questionService = questionService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/question")
    public ResponseEntity<String> addNewQuestion(@RequestBody QuestionDTO questionDTO) {
        questionService.save(convertToQuestion(questionDTO));
        return ResponseEntity.ok("Вопрос успешно добавлен.");
    }

    @PostMapping("/answer")
    public ResponseEntity<String> answerToQuestion(@RequestBody QuestionDTO questionDTO) {
        questionService.sendMessageToEmail(questionDTO);
        return ResponseEntity.ok("Ответ успешно отправлен.");
    }

    @DeleteMapping("/answer/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Integer id) {
        questionService.delete(id);
        return ResponseEntity.ok("Вопрос успешно удалён.");
    }


    @GetMapping("/questions")
    public List<BrandResponse> getAllQuestions(){
        return questionService.getAll();
    }


    private Question convertToQuestion(QuestionDTO questionDTO){
        return this.modelMapper.map(questionDTO, Question.class);
    }
}
