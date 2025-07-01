package com.hashpow.spring_backend.serviceimpl;

import com.hashpow.spring_backend.entity.CryptoTask;
import com.hashpow.spring_backend.entity.TaskStates;
import com.hashpow.spring_backend.repository.TaskRepository;
import com.hashpow.spring_backend.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private final String TOPIC = "task-topic";

    @Override
    public CryptoTask addTask(String inputText, String userId) {
        if(userId.isEmpty() || inputText.isEmpty()) return null;
        CryptoTask task = new CryptoTask(inputText, userId);
        task.setState(TaskStates.IN_QUEUE);
        task.setOutputHash(null);
        task.setCreatedAt(LocalDateTime.now());
        CryptoTask saved = taskRepository.save(task);

        kafkaTemplate.send(TOPIC, saved.getId().toString());
        return saved;
    }

    @Override
    public CryptoTask getTask(Long id) {
        Optional<CryptoTask> task =  taskRepository.findById(id);
        return task.orElse(null);
    }

    @Override
    public List<CryptoTask> getAllTasks(String userId) {
        Optional<List<CryptoTask>> tasks =  taskRepository.findByUserId(userId);
        return tasks.orElse(List.of());
    }

    @Override
    public CryptoTask deleteTask(CryptoTask task) {
        if(task == null) return null;
        taskRepository.delete(task);
        return task;
    }
}
