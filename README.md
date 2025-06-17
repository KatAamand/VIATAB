# VIATAB – DevOps & Cloud (DOC1) Projekt

Dette projekt er udviklet som en del af VIA's **DevOps & Cloud** kursus (4. semester). Det demonstrerer en fuld
implementering af et system i en containeriseret og orkestreret infrastruktur.

---

## Indhold og arkitektur

Systemet består af følgende dele:

### 1. Backend

* Implementeret i **Java (Spring Boot)**
* CRUD-endpoints til at oprette og hente "stories"
* Kommunikerer med en **PostgreSQL** database
* Eksponeres via port `8080`
* Miljøvariabler bruges til databasekonfiguration

[![codecov](https://codecov.io/gh/KatAamand/VIATAB/branch/main/graph/badge.svg)](https://codecov.io/gh/KatAamand/VIATAB)

### 2. Frontend

* Implementeret i **React** med **Vite**
* Henter og opretter "stories" via backend
* Bygges som statisk site og serves med **Nginx**
* Eksponeres via port `30000` i Kubernetes (NodePort)

### 3. Database

* PostgreSQL v15
* Initielt oprettet med tom database `viatab`
* Brugernavn og adgangskode: `viatab`

---

## Deployment og infrastruktur

Projektet kører i et **lokalt Kubernetes cluster** via Minikube og anvender:

* Docker: Containerization af backend og frontend
* Kubernetes (YAML):

    * `Deployment` og `Service` objekter for hver komponent
* GitHub Actions (CI/CD) – *valgfrit hvis implementeret*
* Makefile til opsætning og kørsel

---

## Kom i gang

### Krav

- Docker
- Minikube
- Kubectl
- GNU Make (Installer evt. via Scoop: iwr -useb get.scoop.sh | iex og scoop install make)

### Trin-for-trin

1. Start Minikube:

   ```bash
   minikube start
   ```

2. Kør hele opsætningen med:

   ```bash
   make
   ```

3. Åbn frontend i browseren:

   ```bash
   minikube service viatab-frontend
   ```

---

## Struktur

```
.
├── backend/                  # Spring Boot service
├── frontend/                 # React + Vite app
├── k8s/                      # Kubernetes YAML-manifester
│   ├── backend-deployment.yaml
│   ├── backend-service.yaml
│   ├── db-deployment.yaml
│   ├── db-service.yaml
│   ├── frontend-deployment.yaml
│   └── frontend-service.yaml
├── Makefile                  # Build og deploy script
├── README.md
```

---

## Makefile-kommandoer

| Kommando       | Beskrivelse                                    |
|----------------|------------------------------------------------|
| `make`         | Kører hele kæden: build, push, deploy, restart |
| `make build`   | Bygger Docker images for backend og frontend   |
| `make push`    | Lagrer og loader images til Minikube           |
| `make deploy`  | Udruller alle Kubernetes-manifester            |
| `make restart` | Genstarter pods for backend og frontend        |
| `make logs`    | Viser logs fra backend                         |
| `make clean`   | Fjerner images og `.tar`-filer lokalt          |

---

## Fremtidige forbedringer (idéer)

* Tilføj CI/CD pipeline via GitHub Actions
* Brug PersistentVolumeClaims til database
* Tilføj test coverage (f.eks. JaCoCo)
* Deploy til cloud (f.eks. Azure, GCP)

