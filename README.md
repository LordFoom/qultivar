# Qultivar Application

## Opening the UI

You will have to read the documentation first:
- Step 1: Build the code
- Step 2: Start the environment
- Step 3: Load the default data
- Step 4: Load the test data (optional)
- Step 5: Open the UI (https://qultivar.therudeway.com) 

#
## Documentation

| Document        | Description                               |
| --------------- | ----------------------------------------- |
| [Manage certficates](./docs/manage-certificates.md) | Guide to create and manage the certificates and truststores |
| [Using the API gateway](./docs/using-the-api-gateway.md) | Guide to generate tokens and execute commands through the API gateway |
| [Navigating the database](./docs/navigating-the-database.md) | Guide to navigating the PostgreSQL database |
| [Testing media functionality](./docs/test-media-functionality.md) | Guide to managing image and video files |

#
## Project Summary

| Project Name    | Port | Code | Image | Language         | Description                               |
| --------------- | ---- | ---- | ----- | ---------------- | ----------------------------------------- |
| api-gateway     |  443 |  No  |  Yes  |                  | NGINX reverse proxy                       |
| db-service      | 5432 |  No  |  Yes  |                  | PostgreSQL database                       |
| qultivar-common |      |  Yes |  No   | Quarkus / Kotlin | Common code shared between the projects    |
| auth-service    | 5001 |  Yes |  Yes  | Quarkus / Kotlin | Authentication microservice               |
| feed-service    | 5002 |  Yes |  Yes  | Quarkus / Kotlin | Feed microservice                         |
| media-service   | 5003 |  Yes |  Yes  | Quarkus / Kotlin | Media microservice                        |
| user-service    | 5004 |  Yes |  Yes  | Quarkus / Kotlin | User microservice                         |
| api-service     | 5000 |  Yes |  Yes  | Quarkus / Kotlin | API microservice                          |
| qultivar-gui    | 3000 |  Yes |  Yes  | ReactJS          | ReactJS UI application                    |

#
## Scripts summary

### *Build scripts*

| Script Name   | Summary |
| ------------- | ------- |
| [`qbuild.sh`](./qbuild.sh) | Builds the code / images for the projects |
| [`qclean.sh`](./qclean.sh) | Cleans the build information for the projects |

### *Deployment scripts*

| Script Name   | Summary |
| ------------- | ------- |
| [`qstart.sh`](./qstart.sh)     | Starts the docker-compose environment |
| [`qstop.sh`](./qstop.sh)       | Stops the docker-compose environment |
| [`qdestroy.sh`](./qdestroy.sh) | Destroys the docker-compose environment |
| [`qlogs.sh`](./qlogs.sh)       | Displays the logs for the docker-compose environment |

### *Data scripts*

| Script Name   | Summary |
| ------------- | ------- |
| [`qloaddata.sh`](./qloaddata.sh)          | Loads the default / static data into the database |
| [`qloadtestdata.sh`](./qloadtestdata.sh)  | Loads the test data into the database |


#
## Build process

There is a single script `qbuild.sh` that builds the application code and the container images.  The script is aware of the projects thare are excluded from the code build (e.g. `api-gateway`) and the projects that do not require a container image (e.g. `qultivar-common`)

### *Building ALL projects*
```bash
./qbuild.sh
```

### *Building a single project*
```bash
# ./qbuild.sh {project_folder}
./qbuild.sh api-service
```

### *Cleaning the projects*

This script is not project aware and will clean the build code for all projects
```bash
./qclean.sh
```

#
## Managing the docker-compose environment

The docker-compose [file](./docker-compose.yaml) defines the resources and containers required to deploy the Qultivar solution.  The reverse proxy, postgres database and the microservices.

***Note:*** When the environment is started, volumes are created to persist the information between the starting and stopping of the environments.

### *Starting the environment*

This script starts the containers in the background.  To view the logs in case of errors or to view the container log output, you can run the `qlogs.sh` script.  There is no initial data loaded at first start and you will need to run the `qloaddata.sh` script to load the default data.

```bash
./qstart.sh
```

### *Stopping the environment*

This script will stop the running containers.  Due to the `volumes` specification in the docker-compose file, any data changes made to the database will be peristed and will be available at the next startup.

```bash
./qstop.sh
```

### *Destroying the environment*

This script will stop the running containers in the background and remove the persisted volumes with extreme prejudice.  If you want to reset the environment, you would run the `qdestroy.sh` script followed by the `qstart.sh`

```bash
./qdestroy.sh
```

### *Monitoring the environment*

Because the containers are started in the background, this script is used to monitor the containers logs and messages.

```bash
./qlogs.sh
```

#
## Loading the data

### *Default and static data*

This script is used to load the data for the default users and any static data required for the application to run 

```bash
./qloaddata.sh
```

### *Test data*

This script is used to load additional test data 

```bash
./qloadtestdata.sh
```
