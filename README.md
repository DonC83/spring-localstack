# Spring Localstack



Install jabba jvm version manager:

```
export JABBA_VERSION=0.11.2
curl -sL https://github.com/shyiko/jabba/raw/master/install.sh | bash && . ~/.jabba/jabba.sh
```

Run the following command to the install the JVM version defined in `.jabbarc` 

```
jabba install
```

Then run the below command to set the JVM version:
```
jabba use
```

Build the image using `lima` & `nerdctl`

```
lima nerdctl build -t spring-localstack .
```

### Local devstack 

To start the devstack run:
```
make devstack.start 
```
The devstack creates a user defined network named `backend` and once
started created the network $foldername-backend i.e. `devstack-backend`

When running the service using docker or containerd you will need to specify the network for the container to join.

