# Docker Registry 第三方认证服务实例
### Quick Start:
>  sudo mkdir -p /data/registry  
>  docker pull registry:2  
>  mkdir certs  
>  openssl req -newkey rsa:2048 -nodes -keyout certs/rsa_private.key -x509 -days 365 -out certs/cert.crt  
>  docker rm -f registry  
>  docker run -d -p 5000:5000 --restart=always --name registry -v /data/registry:/var/lib/registry \   
>  -v "$(pwd)"/certs:/root/certs -e REGISTRY_AUTH_TOKEN_REALM=http://${ip}:8888/v2/token \   
>  -e REGISTRY_AUTH_TOKEN_SERVICE=127.0.0.1:5000 \   
>  -e REGISTRY_AUTH_TOKEN_ISSUER=lang -e REGISTRY_AUTH_TOKEN_ROOTCERTBUNDLE=/root/certs/cert.crt registry:2  
