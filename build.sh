mvn package
 echo $1
 buildNumber=`echo $1 |jq '.PIPELINE_BUILD_NUMBER'`
 echo $buildNumber >> BUILD_NUMBER
mkdir output
mv BUILD_NUMBER output
mv target/*.jar output

