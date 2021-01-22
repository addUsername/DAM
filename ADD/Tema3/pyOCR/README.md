
docker build -t pyocr .
docker run pyocr

docker ps -a
docker cp [NAMES]:/app/preguntas.xml .

docker rm -v
