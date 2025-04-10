# Livechat

This repository refactors the archived [k8splay](https://github.com/h3nryc0ding/k8splay) to use [fluxcd](https://github.com/fluxcd/flux2) and [bitnami helm charts](https://github.com/bitnami/charts) as dependencies.

## Features

- [x] GraphQL API
- [x] SSO Authentication
- [x] WebSocket based live chat

### Backend

- [x] Reactive Spring Boot with WebFlux and Kotlin
- [x] Persistence with R2DBC and MongoDB
- [x] Authentication and Authorization using Keycloak and oAuth2
- [x] GraphiQL available at `/graphiql`

To run the backend locally, you need to have Jave, Docker, and Docker Compose installed. Start the backend with:

```bash
cd backend
make dev
```

### Frontend

- [x] SvelteKit
- [x] ShadCN and TailwindCSS for styling
- [x] Houdini GraphQL client for SSR
- [x] Authenticated routes

To run the frontend locally, you need to have NodeJS installed. It also expects the backend to be running locally. Start the frontend with:

```bash
cd frontend
make dev
```
