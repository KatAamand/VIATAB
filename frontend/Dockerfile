# 1. Build React/Vite app
FROM node:20-alpine AS build

WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .

ENV REACT_APP_BACKEND_URL=http://viatab-backend:8080

RUN npm run build

# 2. Serve med Nginx
FROM nginx:stable-alpine
COPY --from=build /app/dist /usr/share/nginx/html

COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
