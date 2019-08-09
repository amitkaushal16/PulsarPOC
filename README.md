
############################################
Docker command to the pull the Pulsar Image 
###########################################

$ docker run -it \
  -p 6650:6650 \
  -p 8080:8080 \
  -v $PWD/data:/pulsar/data \
  apachepulsar/pulsar:2.4.0 \
  bin/pulsar standalone

