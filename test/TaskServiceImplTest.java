package com.pennant.prodmtr.test;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.pennant.prodmtr.Dao.Interface.TaskDao;
import com.pennant.prodmtr.model.Dto.ResTaskFilter;
import com.pennant.prodmtr.model.Dto.TFilterCriteria;
import com.pennant.prodmtr.model.Dto.TaskDto;
import com.pennant.prodmtr.model.Entity.Task;
import com.pennant.prodmtr.model.view.TaskStatusRequest;
import com.pennant.prodmtr.model.view.TaskUpdateFormModel;
import com.pennant.prodmtr.service.Impl.TaskServiceImpl;
import com.pennant.prodmtr.service.Interface.TaskService;

public class TaskServiceImplTest {

    @Mock
    private TaskDao taskDao;

    @InjectMocks
    private TaskServiceImpl taskService;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        taskService = new TaskServiceImpl(taskDao);

    }

    @Test
    public void testFilterTasks() {
        // Prepare test data
        ResTaskFilter resTaskFilter = new ResTaskFilter();
        List<TaskDto> expectedTasks = new ArrayList<>();
        when(taskDao.filterTasks(resTaskFilter)).thenReturn(expectedTasks);

        // Perform the test
        List<TaskDto> actualTasks = taskService.filterTasks(resTaskFilter);

        // Verify the results
        verify(taskDao).filterTasks(resTaskFilter);
        // Add more assertions based on your specific test case
    }

    @Test
    public void testGetTasksByUserId() {
        // Prepare test data
        int userId = 1;
        List<TaskDto> expectedTasks = new ArrayList<>();
        when(taskDao.getTasksByUserId(userId)).thenReturn(expectedTasks);

        // Perform the test
        List<TaskDto> actualTasks = taskService.getTasksByUserId(userId);

        // Verify the results
        verify(taskDao).getTasksByUserId(userId);
        // Add more assertions based on your specific test case
    }

    @Test
    public void testGetAllTasks() {
        // Prepare test data
        List<TaskDto> expectedTasks = new ArrayList<>();
        when(taskDao.getAllTasks()).thenReturn(expectedTasks);

        // Perform the test
        List<TaskDto> actualTasks = taskService.getAllTasks();

        // Verify the results
        verify(taskDao).getAllTasks();
        // Add more assertions based on your specific test case
    }


    @Test
    public void testGetTaskById() {
        // Prepare test data
        int taskId = 1;
        Task expectedTask = new Task();
        when(taskDao.getTaskById(taskId)).thenReturn(expectedTask);

        // Perform the test
        Task actualTask = taskService.getTaskById(taskId);

        // Verify the results
        verify(taskDao).getTaskById(taskId);
        // Add more assertions based on your specific test case
    }

    @Test
    public void testSaveTask() {
        // Prepare test data
        Task task = new Task();

        // Perform the test
        taskService.saveTask(task);

        // Verify the results
        verify(taskDao).saveTask(task);
        // Add more assertions based on your specific test case
    }

    // Add more test methods to cover other methods in TaskServiceImpl

}
