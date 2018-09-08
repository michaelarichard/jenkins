README
```
git clone https://github.com/michaelarichard/jenkins.git
cd jenkins
docker run -d -p 8080:8080 -p 50000:50000 -v `pwd`:/var/jenkins_home --name jenkins jenkins/jenkins:latest
echo 'Installing plugins...'
docker exec -it jenkins bash -c '/usr/local/bin/install-plugins.sh $(cat /var/jenkins_home/plugins.txt)'
echo 'Restarting jenkins to load new plugins...'
docker restart jenkins
echo 'Waiting for jenkins to boot'
docker logs -f
sleep 60
cat secrets/initialAdminPassword
# Browse to http://localhost:8080
```
