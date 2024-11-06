build:
	#./mvnw clean package
	mvnw.cmd clean package,

db-up:
	docker rm -f client-db || true
	docker system prune -a -f
	docker-compose -f db-docker-compose.yaml up -d
