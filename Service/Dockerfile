FROM php:7.1-apache

COPY ./Api /var/www/html/
COPY ./Api/apache/dev.vhost /etc/apache2/sites-enabled/000-default.conf
RUN chmod -R 777 /var/www/html/storage
RUN touch /var/www/html/database/resources.json && chmod 777 /var/www/html/database/resources.json
RUN a2enmod rewrite
RUN service apache2 restart
