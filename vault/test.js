// import necessary modules
import { check } from 'k6';
import http from 'k6/http';

// define configuration
export const options = {
  // define thresholds
  thresholds: {
    http_req_failed: [{ threshold: 'rate<0.01', abortOnFail: true }], // availability threshold for error rate
    http_req_duration: ['p(99)<1000'], // Latency threshold for percentile
  },
  // define scenarios
  scenarios: {
    breaking: {
      executor: 'ramping-vus',
      stages: [
        { duration: '10s', target: 20 },
        { duration: '50s', target: 20 },
        { duration: '50s', target: 40 },
        { duration: '50s', target: 60 },
        { duration: '50s', target: 80 },
        { duration: '50s', target: 100 },
        { duration: '50s', target: 120 },
        { duration: '50s', target: 140 },
        //....
      ],
    },
  },
};

// Define the base URL of the Spring Boot app
const BASE_URL = 'http://localhost:8080';

export default function () {
    // Send a GET request to the / endpoint
    const response = http.get(`${BASE_URL}/`);

    // Check if the response status is 200 (OK)
    check(response, {
        'status is 200': (r) => r.status === 200,
    });

    // Log the response body (optional)
    console.log(response.body);
}
