package perso.jarvis.controllers.rest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import perso.jarvis.beans.Task;
import perso.jarvis.services.TaskService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Pierre on 31/03/2015.
 */
@Controller
public class TaskController {

    private final Logger logger = Logger.getLogger(TaskController.class);


    @Resource
    TaskService taskService;


    /**
     *
     * @param request
     * @param httpServletResponse
     */
    @RequestMapping(value="/user/{userID}/project/{projectID}/task", method= RequestMethod.POST)
    public @ResponseBody
    void createTask (@PathVariable("userID") String userID, @PathVariable("projectID") String projectID, @RequestBody Task task, HttpServletRequest request, HttpServletResponse httpServletResponse) {
        logger.info("createTask");
        logger.info(projectID);
       taskService.createTask(projectID,task);
    }

    @RequestMapping(value="project/{projectId}/tasks", method= RequestMethod.GET)
    public @ResponseBody
    void getTasks (@PathVariable("projectId") String id, HttpServletRequest request, HttpServletResponse httpServletResponse) {
        logger.info("getTasks");
    }


}
