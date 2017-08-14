#!/bin/bash
docker-compose exec php_apache -c "chmod -R 777 /var/www"/storage/logs"
docker-compose exec php_apache -c "touch /var/www/database/resources.json" && chmod 777 /var/www/database/resources.json"
docker-compose exec php_apache -c "cd Api && php artisan create:service $1 $2 $3"
