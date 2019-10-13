# ARCHIMED

A barebones Gradle app, which can easily be deployed to Heroku.

This application support the [Getting Started with Gradle on Heroku](https://devcenter.heroku.com/articles/getting-started-with-gradle-on-heroku) article - check it out.

## Running Locally

Make sure you have Java installed.  Also, install the [Heroku Toolbelt](https://toolbelt.heroku.com/).

#### Be careful: java 8+ in PATH

```sh
$ git clone https://github.com/keni0k/cifra2019.git
cd cifra2019
./gradlew build
heroku local web
```

Your app should now be running on [localhost:5000](http://localhost:5000/).

If you're going to use a database, find file application.properties and change parameter spring.datasource.url

```
spring.datasource.url = jdbc:postgresql://HOST_NAME:5432/DATABASE?user=USER&password=PASSWORD

```

## Deploying to Heroku

```sh
$ git commit -a
git push heroku master
heroku open
```

## Documentation

- API: [Google Tables](https://docs.google.com/spreadsheets/d/1L5EsxPQf7EZdWSToxF0EjT_7vxg8TEJylpIweO9XBbE/edit#gid=1080676204)

For more information about using Java on Heroku, see these Dev Center articles:

- [Java on Heroku](https://devcenter.heroku.com/categories/java)
