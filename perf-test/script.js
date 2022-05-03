import http from 'k6/http'
import { fail, check, sleep } from 'k6';

const baseUrl = "http://localhost:8080";

export const options = {
  vus: 10,
  duration: '30s',
  thresholds: {
    // the rate of successful checks should be higher than 90%
    checks: ['rate>0.9'],
  },
}

export function setup() {
  let started = false;
  while (!started) {
    console.log("Polling service")
    let resp = http.get(`${baseUrl}/health`);
    started = JSON.parse(resp.body) != null;
    sleep(10);
  }

  let healthStatus = "";
  while (healthStatus !== "UP") {
    console.log("Checking service health")
    let resp = http.get(`${baseUrl}/health`);
    healthStatus = JSON.parse(resp.body).status;
    sleep(5);
  }
  console.log("Set up complete");
}

export default function() {

  const resp = http.get(`${baseUrl}/secret/client_secret_1`)

  if (!check(resp, {
    'get secret status is 200': (r) => r.status === 200
  })) {
    fail(`Failed to get secret: http status = ${resp.status}`);
  }
}
