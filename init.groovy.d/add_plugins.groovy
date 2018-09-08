/* Install required plugins and restart Jenkins, if necessary.  */
final List<String> REQUIRED_PLUGINS = [
  "apache-httpcomponents-client-4-api",
  "credentials",
  "display-url-api",
  "git",
  "git-client",
  "groovy",
  "jsch",
  "junit",
  "mailer",
  "matrix-project",
  "pipeline-build-step",
  "pipeline-input-step",
  "pipeline-milestone-step",
  "pipeline-model-definition",
  "pipeline-stage-step",
  "pipeline-stage-view",
  "scm-api",
  "script-security",
  "ssh-credentials",
  "structs",
  "workflow-aggregator",
  "workflow-api",
  "workflow-basic-steps",
  "workflow-cps",
  "workflow-cps-global-lib",
  "workflow-durable-task-step",
  "workflow-job",
  "workflow-multibranch",
  "workflow-scm-step",
  "workflow-step-api",
  "workflow-support",
]
if (Jenkins.instance.pluginManager.plugins.collect {
        it.shortName
    }.intersect(REQUIRED_PLUGINS).size() != REQUIRED_PLUGINS.size()) {
    REQUIRED_PLUGINS.collect {
        Jenkins.instance.updateCenter.getPlugin(it).deploy()
    }.each {
        it.get()
    }
    Jenkins.instance.restart()
    println 'Run this script again after restarting to create the jobs!'
//    throw new RestartRequiredException(null)
}
