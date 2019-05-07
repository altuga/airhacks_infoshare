# Build
mvn clean package && docker build -t com.airhacks/police .

# RUN

docker rm -f police || true && docker run -d -p 8080:8080 -p 4848:4848 --name police com.airhacks/police 