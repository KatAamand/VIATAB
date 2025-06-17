# Opsætning af VIATAB i Minikube

# Navne på images
BACKEND_IMAGE=viatab-backend
FRONTEND_IMAGE=viatab-frontend

# Outputfilnavne
BACKEND_TAR=$(BACKEND_IMAGE).tar
FRONTEND_TAR=$(FRONTEND_IMAGE).tar

.PHONY: help all build images push deploy restart logs clean

all: build push deploy restart

help:
	@echo "Tilgængelige kommandoer:"
	@echo "  make build      - Byg Docker images til backend og frontend"
	@echo "  make push       - Gem og indlæs Docker images i Minikube"
	@echo "  make deploy     - Udrul Kubernetes-manifester fra ./k8s"
	@echo "  make restart    - Genstart alle deployments"
	@echo "  make logs       - Se logs fra backend"
	@echo "  make clean      - Slet images og .tar-filer (lokalt)"

build:
	docker build -t $(BACKEND_IMAGE):latest ./backend
	docker build -t $(FRONTEND_IMAGE):latest ./frontend

push:
	docker save $(BACKEND_IMAGE):latest -o $(BACKEND_TAR)
	docker save $(FRONTEND_IMAGE):latest -o $(FRONTEND_TAR)
	minikube image load $(BACKEND_TAR)
	minikube image load $(FRONTEND_TAR)

deploy:
	kubectl apply -f k8s/

restart:
	kubectl rollout restart deployment/viatab-backend
	kubectl rollout restart deployment/viatab-frontend

logs:
	kubectl logs deployment/viatab-backend

clean:
	docker rmi $(BACKEND_IMAGE):latest || true
	docker rmi $(FRONTEND_IMAGE):latest || true
	rm -f $(BACKEND_TAR) $(FRONTEND_TAR)
