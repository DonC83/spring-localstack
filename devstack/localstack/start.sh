#!/bin/bash

export AWS_ACCESS_KEY_ID=foobar
export AWS_SECRET_ACCESS_KEY=foobar

aws --endpoint-url=http://localhost:4566 dynamodb create-table \
  --region=ap-southeast-2 \
  --table-name Account \
  --attribute-definitions \
      AttributeName=ID,AttributeType=S \
  --key-schema \
      AttributeName=ID,KeyType=HASH \
  --provisioned-throughput \
      ReadCapacityUnits=10,WriteCapacityUnits=5

#aws --endpoint-url=http://localhost:4566 dynamodb update-table \
#  --region=ap-southeast-2 \
#  --table-name Account \
#  --attribute-definitions AttributeName=HashedEmail,AttributeType=S \
#  --global-secondary-index-updates \
# "[{\"Create\":{\"IndexName\": \"HashedEmailIndex\",\"KeySchema\":[{\"AttributeName\":\"HashedEmail\",\"KeyType\":\"HASH\"}], \
# \"ProvisionedThroughput\": {\"ReadCapacityUnits\": 10, \"WriteCapacityUnits\": 5},
# \"Projection\":{\"ProjectionType\":\"INCLUDE\", \"NonKeyAttributes\": [\"ID\"]}}}]" \

aws --endpoint-url=http://localhost:4566 dynamodb create-table \
  --region=ap-southeast-2 \
  --table-name Purchases \
  --attribute-definitions \
      AttributeName=ID,AttributeType=S \
  --key-schema \
      AttributeName=ID,KeyType=HASH \
  --provisioned-throughput \
      ReadCapacityUnits=10,WriteCapacityUnits=5 \
  --stream-specification \
      StreamEnabled=true,StreamViewType=NEW_AND_OLD_IMAGES

aws --endpoint-url=http://localhost:4566 secretsmanager create-secret --name client_secret_1 --secret-string \
  --region=ap-southeast-2 \
  "{\"clientId\":\"foobar\", \
    \"secret\":\"boofar\"}"

aws --endpoint-url=http://localhost:4566 secretsmanager create-secret --name client_secret_2 --secret-string \
  --region=ap-southeast-2 \
  "{\"clientId\":\"foobar2\", \
    \"secret\":\"boofar2\"}"
