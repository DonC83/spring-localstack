import http from 'k6/http'
import { fail, check, sleep } from 'k6';

const baseUrl = "http://127.0.0.1:8080";

export const options = {
  vus: 10,
  duration: '30s'
}

export function setup() {
  let started = false;
  while (!started) {
    let resp = http.get(`${baseUrl}/health`);
    started = JSON.parse(resp.body) != null;
    sleep(10);
  }

  let healthStatus = "";
  while (healthStatus !== "UP") {
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
