package com.codecool.dogmate.service;

import com.codecool.dogmate.dto.lessons.LessonDto;
import com.codecool.dogmate.dto.lessons.NewLessonDto;
import com.codecool.dogmate.entity.Lesson;
import com.codecool.dogmate.excpetion.LessonNotFoudException;
import com.codecool.dogmate.mapper.LessonMapper;
import com.codecool.dogmate.repository.LessonRepository;
import com.codecool.dogmate.repository.TrainingLevelRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonsService {
    private final LessonRepository lessonRepository;
    private final TrainingLevelRepository trainingLevelRepository;
    private final LessonMapper lessonMapper;

    public LessonsService(LessonRepository lessonRepository, TrainingLevelRepository trainingLevelRepository, LessonMapper lessonMapper) {
        this.lessonRepository = lessonRepository;
        this.trainingLevelRepository = trainingLevelRepository;
        this.lessonMapper = lessonMapper;
    }

    public List<LessonDto> getLessons() {
        return lessonRepository.findAllBy().stream()
                .map(lessonMapper::mapEntityToLessonDto)
                .toList();
    }
    public List<LessonDto> getLessons(Pageable pageable) {
        return lessonRepository.findAllBy(pageable).stream()
                .map(lessonMapper::mapEntityToLessonDto)
                .toList();
    }

    public LessonDto getLessonById(Integer id) {
        return lessonRepository.findOneById(id)
                .map(lessonMapper::mapEntityToLessonDto)
                .orElseThrow(() -> new LessonNotFoudException(id));
    }

    public LessonDto createLesson(NewLessonDto lesson) {
        Lesson entity = lessonMapper.mapLessonDtoToEntity(
                lesson,
                trainingLevelRepository.findOneById(lesson.trainingLevel()).get());
        Lesson savedEntity = lessonRepository.save(entity);
        return lessonMapper.mapEntityToLessonDto(savedEntity);
    }
}
