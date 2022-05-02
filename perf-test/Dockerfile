FROM loadimpact/k6

RUN apk add --no-cache bash

WORKDIR /app

COPY script.js .

RUN addgroup -S app && adduser -S -g app app
USER app

ENTRYPOINT ./docker-run.sh
