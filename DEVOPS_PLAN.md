
# DEVOPS_PLAN.md

## Branch-strategi (best practice)

Vi følger en **trunk-based udviklingsstrategi** med følgende branches:

### `main`
- Indeholder altid den **seneste stabile version** af koden.
- Al deployment (f.eks. til Minikube) sker fra `main`.

### `dev`
- **Udviklingsbranch**, hvor funktionalitet samles og testes.
- Github Actions kører automatisk **build og tests** på pushes og pull requests til `dev`.

### `feature/*`
- Bruges til **nye features**.
- Navngives som `feature/add-login` eller `feature/story-crud`.
- Branches ud fra `dev`.
- Merges tilbage til `dev` via pull request.

### `bugfix/*`
- Bruges til at fikse fejl på ikke-release-kode.
- Navngives fx `bugfix/missing-title-validation`.

### `hotfix/*`
- Bruges til hurtige rettelser direkte på `main` i tilfælde af fejl i produktion eller deployment.
- Merges til både `main` og `dev`.

---

## Pull request strategi

Alle ændringer skal merges via **pull requests (PRs)**:
- En PR skal **reviewes** før merge (selv ved solo-projekt: brug review-processen til CI-test).
- CI-workflows kører automatisk på PRs.
- PR-titler skal følge konventionen: `feat: ...`, `fix: ...`, `refactor: ...` osv.

---

## CI/CD triggers

```yaml
on:
  push:
    branches: [ main, dev ]
  pull_request:
    branches: [ main, dev ]
```

Dette sikrer:
- Automatisk test og build ved ændringer i `main` og `dev`
- Feedback før merge

---

## Deployment til Minikube

- Deployment sker kun fra `main`.
- Når en PR merges til `main`, starter GitHub Actions en deploy-job.
- Hvis cloud ikke er sat op, **simuleres deploy** med shell scripts og dummy-kommandoer.

