# Compose

Compose is a module to load & setup services defined in `docker-compose.yaml` into 
container.

Examples:

```graphql
{
  compose {
    withFile(path: "docker-compose.yaml") { 
      from(address: "alpine") {
        withExec(args: ["apk", "add", "curl"]) {
          withExec(args: ["curl", "http://nginx"]) {
            stdout
          }
        }
      }
    }
  } 
}
```
