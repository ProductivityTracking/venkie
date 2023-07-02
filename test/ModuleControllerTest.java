package com.pennant.prodmtr.test;

import java.util.ArrayList;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.pennant.prodmtr.controller.ModuleController;
import com.pennant.prodmtr.model.Dto.ModuleDTO;
import com.pennant.prodmtr.model.Input.ModuleInput;
import com.pennant.prodmtr.service.Interface.ModuleService;

public class ModuleControllerTest {
	
	@Mock
	private ModuleService moduleService;
	
	@InjectMocks
	private ModuleController moduleController;
	
	@Mock
	private Model model;
	
	@BeforeMethod
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testCreateModule() {
		String expectedViewName = "Addmodule";
		
		String actualViewName = moduleController.createModule(model);
		
		Assert.assertEquals(actualViewName, expectedViewName);
	}
	
	@Test
	public void testCreateModuleSuccess() {
		ModuleInput moduleInput = new ModuleInput();
		moduleInput.setModule_proj_id(1);
		
		Integer projectId = moduleInput.getModule_proj_id();
		
		String expectedRedirectURL = "redirect:/moduleDetailsByProjId?projectId=" + projectId;
		
		String actualRedirectURL = moduleController.Createmodulesuccess(moduleInput, model);
		
		Assert.assertEquals(actualRedirectURL, expectedRedirectURL);
		Mockito.verify(moduleService, Mockito.times(1)).createModule(moduleInput);
	}
	
	@Test
	public void testGetModuleDetailsByProjId() {
		Integer projectId = 1;
		
		List<ModuleDTO> expectedModules = new ArrayList<>();
		expectedModules.add(new ModuleDTO());
		
		Mockito.when(moduleService.getModuleByProjId(projectId)).thenReturn(expectedModules);
		
		String expectedViewName = "moduleDetailsbyProjId";
		
		String actualViewName = moduleController.getModuleDetailsByProjId(projectId, model);
		
		Assert.assertEquals(actualViewName, expectedViewName);
		Mockito.verify(model, Mockito.times(1)).addAttribute(Mockito.eq("moduleDTO"), Mockito.eq(expectedModules));
	}
}
