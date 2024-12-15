


```sh

curl -N localhost:8080/ai/generateStream

curl 'https://d1dwq032kyr03c.cloudfront.net/upload/images/20240806/2016129049tSDbXBcM.jpg' \
  -H 'accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7' \
  -H 'accept-language: zh-TW,zh;q=0.9' \
  -H 'priority: u=0, i' \
  -H 'sec-ch-ua: "Google Chrome";v="131", "Chromium";v="131", "Not_A Brand";v="24"' \
  -H 'sec-ch-ua-mobile: ?0' \
  -H 'sec-ch-ua-platform: "Windows"' \
  -H 'sec-fetch-dest: document' \
  -H 'sec-fetch-mode: navigate' \
  -H 'sec-fetch-site: none' \
  -H 'sec-fetch-user: ?1' \
  -H 'upgrade-insecure-requests: 1' \
  -H 'user-agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36' -o example.jpg


curl -N \
  -F "message=List words in this image" \
  -F "file=@/workspace/spring-integration/ollama/example.jpg" \
  localhost:8080/imagequery

curl -X POST -N \
  -F "prompt=What time is it?" \
  localhost:8080/func

curl -X POST -N \
  -F "prompt=Do you know products of year 2023?" \
  localhost:8080/func


```