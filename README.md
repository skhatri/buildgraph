### Build Graph
BuildGraph is a dependency processor that will build up the dependency graph. It exposes queries to query dependencies.

### Design
```mermaid
graph LR;
  events --> redis(Redis Queue)
  redis --> builder(Processor)
  builder --> graph(Graph Database)
```

#### Run Locally
Run graph server and redis
```
docker-compose up -d
```

send application dependency to event ingestor which will write to redis queue.
```shell
gradle clean exportDependency
```

#### Gremlin
```
:remote console
systems.graph('dependencies').create()
system.graphs()
:remote config alias g dependencies.g
g.V()
```

