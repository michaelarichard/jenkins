import jenkins.model.*
import com.cloudbees.plugins.credentials.*
import com.cloudbees.plugins.credentials.common.*
import com.cloudbees.plugins.credentials.domains.*
import com.cloudbees.jenkins.plugins.sshcredentials.impl.*
import hudson.plugins.sshslaves.*

global_domain = Domain.global()
credentials_store = Jenkins.instance.getExtensionList(
          'com.cloudbees.plugins.credentials.SystemCredentialsProvider'
          )[0].getStore()
credentials = new BasicSSHUserPrivateKey(
          CredentialsScope.GLOBAL,
          'jenkins_id_rsa',
          'jenkins_id_rsa',
          new BasicSSHUserPrivateKey.DirectEntryPrivateKeySource("""-----BEGIN RSA PRIVATE KEY-----
<paste yours>
-----END RSA PRIVATE KEY-----"""),
          "",
          ""
        )
credentials_store.addCredentials(global_domain, credentials)
