package daisy.teaming.controller;

import daisy.teaming.bean.Project;
import daisy.teaming.service.ProjectService;
import daisy.teaming.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @RequestMapping("/addProject")
    public Result addProject(@RequestBody Project project , HttpServletRequest request)
    {
        return projectService.addProject(project,request);
    }

    @RequestMapping("/getProject")
    public Result getProject(@RequestBody Map<String, Integer> map)
    {
        return projectService.getProject(map.get("projectId"));
    }

    @RequestMapping("/deleteProject")
    public Result deleteProject(@RequestBody Map<String, Integer> map,HttpServletRequest request)
    {
        return projectService.deleteProject(map.get("projectId"),request);
    }

    @RequestMapping("/getProjectList")
    public Result getProjectList()
    {
        return projectService.getProjectList();
    }

    @RequestMapping("updateProject")
    public Result updateProject(@RequestBody Project project,HttpServletRequest request)
    {
        return projectService.updateProject(project,request);
    }
}
