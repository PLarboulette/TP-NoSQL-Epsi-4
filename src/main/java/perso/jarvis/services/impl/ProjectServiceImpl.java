package perso.jarvis.services.impl;

import perso.jarvis.beans.Project;
import perso.jarvis.beans.User;
import perso.jarvis.redis.Redis;
import perso.jarvis.services.ProjectService;

import java.util.*;

/**
 * Created by Pierre on 31/03/2015.
 */
public class ProjectServiceImpl implements ProjectService {


    /**
     * State : OK (01/04/2015)
     * @param id = the user's id
     * @return = List of the user's project
     */
    public List<Project> getProjects(String id) {
        List<Project> result = new ArrayList<Project>();

        Map<String, String> userProperties = Redis.getHash("User : "+id);
        String fieldUserProjects = userProperties.get("userProjects");
        List<String> listIDsProject = Redis.getList(fieldUserProjects);
        ArrayList<String> keysProject = new ArrayList<>();

        for (String keyProject :  listIDsProject) {
                Project projectTemp = Redis.getProjectFromID(keyProject);
                result.add(projectTemp);


        }
        return result;
    }

    /**
     * State : OK (01/04/2015)
     * @param project project to save in database
     */
    public void createProject(Project project, String idUser) {
        HashMap<String,String> projectProperties = new HashMap<String,String>();
        projectProperties.put("projectId", String.valueOf(project.hashCode()));
        projectProperties.put("projectName", project.getName());
        projectProperties.put("projectDescription",project.getDescription());
        projectProperties.put("projectBeginDate", project.getBeginDate());
        projectProperties.put("projectEndDate", project.getEndDate());
        projectProperties.put("projectTasks", "Tasks : " + String.valueOf(project.hashCode()));
        String technologies = "";
        for (String s : project.getTechnologies()) {
            technologies += s +";";
        }
        projectProperties.put("projectAchieved","false");
        projectProperties.put("projectTechnologies",technologies);
        addProjectForUser(String.valueOf(project.hashCode()), idUser);
        Redis.insertHash("Project", String.valueOf(project.hashCode()), projectProperties);
    }

    /**
     *
     * State : ??
     * @param projectId project's id to add to the user's list of projects
     * @param userId user's id
     */
    public void addProjectForUser(String projectId, String userId) {

        Redis.insertList("Projects",userId,projectId);
    }

    public Project getProject(String idProject) {
        return Redis.getProjectFromID(idProject);
    }

    @Override
    public void updateProject(Project project, String idUser) {
        String idProject = project.getId();

        Redis.setValueToHash("Project : " + idProject, "projectName", project.getName());
        Redis.setValueToHash("Project : " + idProject, "projectDescription", project.getDescription());
        Redis.setValueToHash("Project : " + idProject, "projectBeginDate", project.getBeginDate());
        Redis.setValueToHash("Project : " + idProject, "projectEndDate", project.getEndDate());
        Redis.setValueToHash("Project : " + idProject, "projectName", project.getName());

        String technologies = "";
        for (String s : project.getTechnologies()) {
            technologies += s +";";
        }
       Redis.setValueToHash("Project : " + idProject, "projectAchieved", project.getAchieved());
        Redis.setValueToHash("Project : " + idProject, "projectTechnologies", technologies);
    }
}
