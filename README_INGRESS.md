# Ingress Nginx Controller 이용하기. 

스프링 클라우드에서 API Gateway 를 사용하여 리퀘스트를 라우팅 하였다면, Kubernetes 에서는 Ingress 를 통해서 인입되는 트래픽을 적절한 서비스로 라우팅 합니다. 

Ingress 를 활용하기 위해서는 다음 2가지 작업이 필요합니다. 

1. Ingress Controller 설치하기
2. Ingress Policy를 Congroller 에 적용하기. 

위 두 단계를 거치며 우리는 Ingress-nginx 컨트롤러를 활용하겠습니다. 보통 가장 많이 사용하는 컨트롤러가 nginx 컨트롤러 입니다.  

## Ingress nginx 

상세한 설치 방법은 다음 경로를 확인하세요. 

https://github.com/kubernetes/ingress-nginx/blob/master/docs/deploy/index.md#docker-for-mac

우리는 여기서 Docker for Mac 을 활용 하겠습니다. 

다음 명령을 통해서 Ingress Controller 을 Docker for Mac 에 설치해 줍니다. 


### Ingress nginx Controller 설치하기 

```text
kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/master/deploy/static/provider/cloud/deploy.yaml
```

만약 Mimikube를 이용하신다면 다음 명령을 이용하세요. 
```text
$ minikube addons enable ingress
🌟  The 'ingress' addon is enabled
```

### Ingress nginx Controller 설치여부 확인하기  
```text
$kubectl get all -n ingress-nginx

NAME                                            READY   STATUS    RESTARTS   AGE
pod/ingress-nginx-controller-579fddb54f-c497l   1/1     Running   0          2d19h

NAME                                         TYPE           CLUSTER-IP      EXTERNAL-IP   PORT(S)                      AGE
service/ingress-nginx-controller             LoadBalancer   10.98.35.32     localhost     80:31610/TCP,443:31237/TCP   2d19h
service/ingress-nginx-controller-admission   ClusterIP      10.107.80.123   <none>        443/TCP                      2d19h

NAME                                       READY   UP-TO-DATE   AVAILABLE   AGE
deployment.apps/ingress-nginx-controller   1/1     1            1           2d19h

NAME                                                  DESIRED   CURRENT   READY   AGE
replicaset.apps/ingress-nginx-controller-579fddb54f   1         1         1       2d19h

NAME                                       COMPLETIONS   DURATION   AGE
job.batch/ingress-nginx-admission-create   1/1           9s         2d20h
job.batch/ingress-nginx-admission-patch    1/1           13s        2d20h

```

## Set Ingress Rule Setting

Ingress Rule 세팅하기. 

kubernetes/ingress.yml 파일을 다음과 같이 설정합니다. 

```text
apiVersion: networking.k8s.io/v1beta1
kind: Ingress
metadata:
  name: education-ingress
  annotations:
    kubernetes.io/ingress.class: nginx
    nginx.ingress.kubernetes.io/backend-protocol: "HTTP"
    ingress.kubernetes.io/rewrite-target: /
spec:
  rules:
    - host: education.kr
      http:
        paths:
          - path: /api/students
            backend:
              serviceName: student-service
              servicePort: 8081
          - path: /api/professors
            backend:
              serviceName: professor-service
              servicePort: 8181
          - path: /api/educations
            backend:
              serviceName: education-service
              servicePort: 8281
```

정상적으로 적용되었는지 확인하기. 

위 내용을 간단히 살펴보면 다음과 같습니다. 

- host: education.kr 접근할때 host 이름으로 접근하도록 설정하였습니다. education.kr 을 통해 ingress 에 접근해야 통과됩니다. 
- path: /api/students 라우팅을 수행할 경로 uri를 매치 합니다. 매치되면 백엔드로 전달됩니다. 
- backend.serviceName: student-service 백엔드로 전달될 서비스 이름을 나타냅니다. 
- backend.servicePort: 8081 서비스 포트를 나타냅니다. 

위 정책에 따라서 들어오는 path의 uri 를 보고, 어떠한 백엔드 서버로 전달할지를 결정하는 룰을 세팅했습니다. 


ingress 를 위한 ingress controller 컴포넌트들이 설치된 것을 확인할 수 있습니다.

pod, service, replicaset, batch 등 ingress 를 안정적으로 운영하기 위한 컴포넌트가 설치되어 있습니다. 


### Ingress 룰 적용여부 확인하기. 

```text
$kubectl get ingress

NAME                HOSTS          ADDRESS     PORTS   AGE
education-ingress   education.kr   localhost   80      13m

```

룰도 정상적으로 확인됩니다. 

이제 80 포트를 통해서 우리가 구성한 서비스로 접근이 가능합니다. 

/etc/hosts 파일에 다음 내용을 추가해 줍니다. 

```text
127.0.0.1 education.kr 
```

```text
curl -i education.kr/api/educations/education/Math
```

결과가 정상으로 노출됩니다. 