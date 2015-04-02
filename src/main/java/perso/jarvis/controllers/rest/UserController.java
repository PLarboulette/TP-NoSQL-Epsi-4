package perso.jarvis.controllers.rest;

/**
 * Created by Pierre on 31/03/2015.
 */



import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import perso.jarvis.beans.Project;
import perso.jarvis.beans.User;
import perso.jarvis.services.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


@Controller
public class UserController {

    Logger logger = Logger.getLogger(UserController.class);


    @Resource
    UserService userService;

    /**
     *
     * @param request
     * @param response
     */
    @RequestMapping(value="/users", method= RequestMethod.POST)
    public void createUser (HttpServletRequest request, HttpServletResponse response) {
        logger.info("createUser");

        BufferedReader br = null;
        String json = "";

        try {
            br = new BufferedReader(new InputStreamReader(request.getInputStream()));
            if(br != null){
                json = br.readLine();
            }
            ObjectMapper mapper = new ObjectMapper();
            User user = mapper.readValue(json, User.class);
            userService.createUser(user);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Get all the users in the database
     * @param request
     * @param response
     */
    @RequestMapping(value="/users", method= RequestMethod.GET)
    public void getUsers (HttpServletRequest request, HttpServletResponse response) {
        List<User> listUsers = userService.getUsers();
    }





}