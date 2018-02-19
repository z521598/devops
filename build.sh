mvn package
 echo $*
 buildNumber=`echo $* |jq '.PIPELINE_BUILD_NUMBER'`
 echo $buildNumber >> BUILD_NUMBER
mkdir output
mv BUILD_NUMBER output
mv target/*.jar output

