start:
	docker-compose up -d --build

stop:
	docker-compose down

refresh-downstream:
	docker-compose stop downstream
	docker-compose build downstream
	docker-compose rm -f downstream
	docker-compose up -d downstream

refresh-cluster:
	docker-compose stop cluster
	docker-compose build cluster
	docker-compose rm -f cluster
	docker-compose up -d cluster

hotswap-clusterenvoy:
	docker kill --signal=SIGHUP  clusterenvoy
