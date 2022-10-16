package io.buildgraph.web.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Dependency {
    private final String group;
    private final String name;
    private final String version;
    private final boolean transitive;

    @JsonCreator
    public Dependency(@JsonProperty("group") String group, @JsonProperty("name") String name, @JsonProperty("version") String version, @JsonProperty("transitive") boolean transitive) {
        this.group = group;
        this.name = name;
        this.version = version;
        this.transitive = transitive;
    }

    public String getGroup() {
        return group;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public boolean isTransitive() {
        return transitive;
    }

}
