REPOSITORY=/home/ec2-user/app/step1

echo "> 새 애플리케이션 배포"

JAR_NAME=$(ls -tr $REPOSITORY/ | grep jar | tail -n 1)

echo "> JAR Name: $JAR_NAME"

nohup java -jar \
  -Dspring.config.location=classpath:application.yml,/home/ec2-user/app/application-oauth.yml,/home/ec2-user/app/application-real-db.yml,classpath:/application-real.yml \
  -Dspring.profiles.active=real \
  $REPOSITORY/$JAR_NAME 2>&1 &

TIME_NOW=$(date +%c)
CURRENT_PID=$(pgrep -f $JAR_NAME)
echo "$TIME_NOW > 실행된 프로세스 아이디 $CURRENT_PID 입니다."
