package com.pennant.prodmtr.test;

import com.pennant.prodmtr.Dao.Interface.ModuleDao;
import com.pennant.prodmtr.model.Dto.ModuleDTO;
import com.pennant.prodmtr.model.Entity.Module;
import com.pennant.prodmtr.model.Entity.Project;
import com.pennant.prodmtr.model.Input.ModuleInput;
import com.pennant.prodmtr.service.Impl.ModuleServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

public class ModuleServiceImplTest {
    @Mock
    private ModuleDao moduleDao;

    @InjectMocks
    private ModuleServiceImpl moduleService;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetModuleByProjId() {
        // Test data
        int projectId = 1;

        // Create a list of module DTOs to be returned by the mock
        List<ModuleDTO> moduleDTOs = new ArrayList<>(); // Add the desired module DTOs to the list

        // Stub the moduleDao.getModuleDetailsByProjId() method
        Mockito.when(moduleDao.getModuleDetailsByProjId(projectId)).thenReturn(moduleDTOs);

        // Create the module service with the mock moduleDao
        ModuleServiceImpl moduleService = new ModuleServiceImpl(moduleDao);

        // Call the service method
        List<ModuleDTO> result = moduleService.getModuleByProjId(projectId);

        // Verify the interaction
        Mockito.verify(moduleDao).getModuleDetailsByProjId(projectId);

        // Perform additional assertions on the result
        // ...
    }


    @Test
    public void testCreateModule() {
        // Test data
        ModuleInput moduleInput = new ModuleInput();
        moduleInput.setModule_name("Test Module");
        moduleInput.setModule_desc("Test Description");

        Module module = new Module();
        module.setModuleName(moduleInput.getModule_name());
        module.setModuleDescription(moduleInput.getModule_desc());
        module.setModuleProject(new Project()); // Create a dummy project object

        // Create a partial mock of moduleDao
        ModuleDao moduleDaoMock = Mockito.spy(moduleDao);

        // Stub the moduleDao.save() method
        Mockito.doNothing().when(moduleDaoMock).save(Mockito.any(Module.class));

        // Create the module service with the mock moduleDao
        ModuleServiceImpl moduleService = new ModuleServiceImpl(moduleDaoMock);

        // Call the service method
        moduleService.createModule(moduleInput);

        // Verify the interaction
        Mockito.verify(moduleDaoMock).save(Mockito.any(Module.class));
    }

}
