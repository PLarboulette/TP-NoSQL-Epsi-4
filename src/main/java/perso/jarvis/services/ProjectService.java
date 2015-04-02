package perso.jarvis.services;

import perso.jarvis.beans.Project;
import perso.jarvis.beans.User;

import java.util.List;

/**
 * Created by Pierre on 31/03/2015.
 */
public interface ProjectService {


    // State OK (01/04/2015)
    void createProject(Project project);

    void addProjectForUser (String projectId, String userId);

    // State OK (01/04/2015)
    List<Project> getProjects(String id);


}