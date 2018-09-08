node('master') {
    stage('Downloading Base Images') {
      checkout([
        $class: 'GitSCM', branches: [[name: '*/master']],
        userRemoteConfigs: [[url: 'https://github.com/michaelarichard/jenkins.git']]
      ])

      echo 'Building' + env.BRANCH_NAME
      if (env.BRANCH_NAME == 'master') {
          echo 'Execute on the master branch'
          File script = new File('download_iso.sh')
          script.getText().execute()
      } else {
          echo 'Execute on non-master branches'
          sh 'ls -lisah'
          sh '/usr/local/bin/git log | head -n1'
          sh 'env > env.txt'
          for (String i : readFile('env.txt').split("\r?\n")) {
            println i
          }
          sh './packer_install.sh'
          sh './terraform_install.sh'
//        sh './docker_test.sh'
          sh './download_iso.sh'
          deleteDir()
      }
      stage('Installing Base OS') {
        echo "tasks"
        sh './packer_build.sh'
        sh 'ls -la'
      }
    }
}
