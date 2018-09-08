import jenkins.model.Jenkins
import org.jenkinsci.plugins.workflow.job.WorkflowJob
import org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition
import hudson.plugins.git.GitSCM
import hudson.plugins.git.*
  
def repo = 'https://github.com/michaelarichard/jenkins.git'
def jobname = 'test-job1'

printf("Instance = %s\n", Jenkins.instance)
println(Jenkins.instance.getItem(jobname))

if (Jenkins.instance.getItem(jobname)) {

  println('Job found. Skipping creation.')

} else {
  
  println('Job NOT found. Creating job.')

  scm = new GitSCM(repo)
  scm.branches = [new BranchSpec("*/master")];

  WorkflowJob job = Jenkins.instance.createProject(WorkflowJob, jobname)

  def definition = new CpsScmFlowDefinition(scm, 'Jenkinsfile')
  //definition.scm.userRemoteConfigs[0].credentialsId = 'jenkins_id_rsa'

  job.definition = definition

}
