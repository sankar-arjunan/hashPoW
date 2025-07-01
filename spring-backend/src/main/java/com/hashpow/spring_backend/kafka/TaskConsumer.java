package com.hashpow.spring_backend.kafka;

import com.hashpow.spring_backend.entity.TaskStates;
import com.hashpow.spring_backend.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;

@Component
public class TaskConsumer {

    @Autowired
    private TaskRepository taskRepository;

    public String sha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @KafkaListener(topics = "task-topic", groupId = "task-group")
    public void consume(String taskIdStr) {
        Long taskId = Long.parseLong(taskIdStr);
        taskRepository.findById(taskId).ifPresent(task -> {
            task.setState(TaskStates.PROCESSING);
            taskRepository.save(task);

            String input = task.getInputText();
            String resultHash = "";
            int nonce = 0;

            while (true) {
                System.out.println("Trying nonce: " + nonce);
                String attempt = input + nonce;
                resultHash = sha256(attempt);
                if (resultHash.startsWith("0000")) break;
                nonce++;
            }

            task.setOutputHash(resultHash);
            task.setState(TaskStates.COMPLETED);
            taskRepository.save(task);
            System.out.println("Hash Found: " + resultHash + " Nonce: " + nonce);
        });
    }


}

