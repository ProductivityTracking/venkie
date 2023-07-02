package com.pennant.prodmtr.test;

import com.google.gson.Gson;
import com.pennant.prodmtr.controller.TaskController;
import com.pennant.prodmtr.model.Dto.TaskDto;
import com.pennant.prodmtr.model.Entity.Task;
import com.pennant.prodmtr.model.Entity.User;
import com.pennant.prodmtr.service.Interface.TaskService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.pennant.prodmtr.model.Dto.TFilterCriteria;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;

public class TaskControllerTest {

    @Mock
    private TaskService taskService;

    @Mock
    private Model model;

    @Mock
    private HttpSession session;

    @InjectMocks
    private TaskController taskController;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testViewTasksForUser() {
        // Arrange
        User user = new User();
        user.setUserId(1);
        List<TaskDto> tasks = new ArrayList<>();
        when(session.getAttribute("user")).thenReturn(user);
        when(taskService.getTasksByUserId(user.getUserId())).thenReturn(tasks);

        // Act
        String viewName = taskController.viewTasksForUser(model, session);

        // Assert
        verify(model).addAttribute("tasks", tasks);
        verify(model).addAttribute("Task", "User");
        assert viewName.equals("Taskslist");
    }

    @Test
    public void testViewTasksForUser_WhenUserNotLoggedIn() {
        // Arrange
        when(session.getAttribute("user")).thenReturn(null);

        // Act
        String viewName = taskController.viewTasksForUser(model, session);

        // Assert
        assert viewName.equals("redirect:/login");
    }

    @Test
    public void testViewAllTasks() {
        // Arrange
        List<TaskDto> tasks = new ArrayList<>();
        when(taskService.getAllTasks()).thenReturn(tasks);

        // Act
        String viewName = taskController.viewAllTasks(model);

        // Assert
        verify(model).addAttribute("tasks", tasks);
        verify(model).addAttribute("Task", "Task");
        assert viewName.equals("Taskslist");
    }

    @Test
    public void testGetAllTasks_ShouldReturnTasksdetailsbyidView() {
        // Arrange
        List<TaskDto> tasks = new ArrayList<>();
        when(taskService.getAllTasks()).thenReturn(tasks);

        // Act
        String viewName = taskController.getAllTasks(model);

        // Assert
        verify(model).addAttribute("tasks", tasks);
        assert viewName.equals("tasksdetailsbyid");
    }
//
//    @Test
//    void testUpdateTaskStatus_ShouldReturnTaskStatusUpdateView() {
//        // Arrange
//        TaskController taskController = new TaskController(taskService);
//        Task task = new Task(); // Create a mock Task object for testing
//
//        when(taskService.getTaskById(1)).thenReturn(task);
//
//        // Act
//        String viewName = taskController.updateTaskStatus(1, model);
//
//        // Assert
//        assertEquals("task-status-update", viewName);
//        verify(taskService, times(1)).getTaskById(1);
//        // Add more assertions as needed
//    }

//    @Test
//    public void testUpdateTaskStatusSuccess_ShouldRedirectToTasksPage() {
//        // Arrange
//        int taskId = 1;
//
//        // Act
//        String viewName = taskController.updateTaskStatusSuccess(taskId);
//
//        // Assert
//        verify(taskService).updateStatus(taskId);
//        assert viewName.equals("redirect:/tasks");
//    }

    @Test
    void testFilterTasks() {
        TaskController taskController = new TaskController(taskService);
        TFilterCriteria filterCriteria = new TFilterCriteria(); // Create a mock TFilterCriteria object for testing

        // Set up mock behavior
        when(taskService.TfilterTasks(filterCriteria)).thenReturn(new ArrayList<>());

        // Call the method under test
        String json = taskController.filterTasks(filterCriteria);

        // Perform assertions
        // Add assertions to verify the JSON response
    }

    @Test
    public void testViewIndvtasks() {
        // Arrange
        int projId = 1;
        List<Task> tasks = new ArrayList<>();
        when(taskService.getTasksByProjectId(projId)).thenReturn(tasks);

        // Act
        String viewName = taskController.viewIndvtasks(projId, model);

        // Assert
        verify(model).addAttribute("tasks", tasks);
        assert viewName.equals("Indvtasks");
    }
   


    // Additional test cases can be added here

}



    
   

