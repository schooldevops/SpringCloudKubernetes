# Ingress Nginx Controller 이용하기. 

## Ingress nginx
https://github.com/kubernetes/ingress-nginx/blob/master/docs/deploy/index.md#docker-for-mac

우리는 여기서 Docker for Mac 을 활용하겠습니다. 

다음 명령을 통해서 Ingress Controller 을 Docker for Mac 에 설치해 줍니다. 

```text
kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/master/deploy/static/provider/cloud/deploy.yaml
```

만약 Mimikube를 이용하신다면 다음 명령을 이용하세요. 
```text
$ minikube addons enable ingress
🌟  The 'ingress' addon is enabled
```

## Set Ingress Rule Setting

