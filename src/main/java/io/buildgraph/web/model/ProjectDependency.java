package io.buildgraph.web.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectDependency {
    private final String project;
    private final String version;
    private final List<Dependency> dependencies;

    @JsonCreator
    public ProjectDependency(@JsonProperty("project") String project, @JsonProperty("version") String version, @JsonProperty("dependencies") List<Dependency> dependencies) {
        this.project = project;
        this.version = version;
        this.dependencies = dependencies;
    }

    public String getProject() {
        return project;
    }

    public String getVersion() {
        return version;
    }

    public List<Dependency> getDependencies() {
        return dependencies;
    }
}
