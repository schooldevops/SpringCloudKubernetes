# RUN SpringCloud Kubernetes

## Initial Command 

## RBAC 이슈 발생시 

```text
kubectl create clusterrolebinding         admin --clusterrole=cluster-admin --serviceaccount=default:default
```

## Local Docker Image 이용시 

로컬에서 minikube 이용시 도커 환경 사용할 수 있도록 요청 

```text
eval $(minikube docker-env)
```

## Zipkin 실행

zipkin 으로 각 서비스가 연동될 수 있도록 zipkin 을 먼저 실행해준다. 

```text
kubectl delete -f education-service/kubernetes/zipkin-service-deployment.yaml 
kubectl delete -f education-service/kubernetes/zipkin-service.yml 

kubectl create -f education-service/kubernetes/zipkin-service-deployment.yaml
kubectl create -f education-service/kubernetes/zipkin-service.yml
```

zipkin 정상 확인 

```text
http://localhost:30999/zipkin/
```

## student-service 실행하기. 

```text
kubectl delete -f student-service/kubernetes/db-secret.yml
kubectl create -f student-service/kubernetes/db-secret.yml

kubectl delete -f student-service/kubernetes/mongodb-deployment.yml
kubectl create -f student-service/kubernetes/mongodb-deployment.yml

kubectl delete -f student-service/kubernetes/mongodb-service.yml
kubectl create -f student-service/kubernetes/mongodb-service.yml

kubectl delete -f student-service/kubernetes/student-service-deployment.yml
kubectl create -f student-service/kubernetes/student-service-deployment.yml

kubectl delete -f student-service/kubernetes/student-service.yml
kubectl create -f student-service/kubernetes/student-service.yml
```

```text
curl -i localhost:30082/api/students
```

## Professor Service 실행하기 

```text
kubectl delete -f professor-service/kubernetes/mysql-db-secret.yml
kubectl create -f professor-service/kubernetes/mysql-db-secret.yml

kubectl delete -f professor-service/kubernetes/mysql-deployment.yml
kubectl create -f professor-service/kubernetes/mysql-deployment.yml

kubectl delete -f professor-service/kubernetes/mysql-service.yml
kubectl create -f professor-service/kubernetes/mysql-service.yml

-- 설치이후 DB 접근하여 DB(professor) 생성 및 Grant 부여 

kubectl delete -f professor-service/kubernetes/professor-service-deployment.yml
kubectl create -f professor-service/kubernetes/professor-service-deployment.yml

kubectl delete -f professor-service/kubernetes/professor-service.yml
kubectl create -f professor-service/kubernetes/professor-service.yml
```

실행 테스트하기

```text
curl -i localhost:30182/api/professors
```

## Education Service 실행하기. 

```text
kubectl delete -f education-service/kubernetes/education-config.yaml
kubectl create -f education-service/kubernetes/education-config.yaml

kubectl delete -f education-service/kubernetes/education-service-deployment.yaml
kubectl create -f education-service/kubernetes/education-service-deployment.yaml

kubectl delete -f education-service/kubernetes/education-service.yml
kubectl create -f education-service/kubernetes/education-service.yml
```

서비스 테스트하기 

```text
curl -i localhost:30282/api/educations/education/Math
```

## Ingress 실행하기. 

### Ingress-Nginx-Controller 설치하기. 

```text
kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/master/deploy/static/provider/cloud/deploy.yaml
```

### Ingress policy deploy 하기 

```text
kubectl delete -f kubernetes/ingress.yaml
kubectl create -f kubernetes/ingress.yaml
```

ingress 접근하기. 

```text
curl -i education.kr/api/educations/education/Math
```



