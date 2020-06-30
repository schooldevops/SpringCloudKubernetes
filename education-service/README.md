# EducationService

## Education-Service Project 구성

## Education-Service Packaging and Dockerizing (공통)

## Kubernetes Discovery Client

## Call Service Endpoint with Ribbon

## Call Service Endpoint with FeignClient

## Kubernetes with ConfigMap
config map 에서 중요한 포인트는

```text
metadata:
  name: education-service
```  

에서 metadata.name 값이 반드시 service-name 과 동일하게 설정해 주어야. application.properties 로 인식하게 할 수 있다. 

## Kubernetes with Sleuth

## Kubernetes with Zipkin

