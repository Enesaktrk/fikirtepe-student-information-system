# Fikirtepe-Student-Information-System (In Development)
## About
The main idea behind this project is to maintain a online platform for high school students studying at Fikirtepe for free within the Association for the Support of Contemporary Living. Registration page is active for now

## Installation
```
git clone https://github.com/mharikmert/Fikirtepe-Student-Information-System
```
## Running Locally
First, set your environment variables to your own  **config.properties** file under the resources  as in **sample_properties** file.

### Build with
```
$cd Fikirtepe-Student-Information-System
/Fikirtepe-Student-Information-System$ gradle build
```
After your jar file is created, run your jar file.
```
/Fikirtepe-Student-Information-System$ java -jar build/libs/Fikirtepe-Student-Information-System-0.0.1-SNAPSHOT.jar
```

Also you can use Dockerfile directly

```
docker build fikirtepe-app .
docker run -p 80:8080 fikirtepe-app
```
Check http://localhost:8080 with your browser to see our main page


## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.
