package com.hashpow.spring_backend.service;

import com.hashpow.spring_backend.entity.CryptoTask;

import java.util.List;

public interface TaskService {
    CryptoTask addTask(String inputText, String userId);
    CryptoTask getTask(Long id);
    List<CryptoTask> getAllTasks(String userId);
    CryptoTask deleteTask(CryptoTask task);

}
