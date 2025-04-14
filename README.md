# Demo for Problem Details

This repo contains a simple Spring Boot demo project
for [Problem Details](https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-ann-rest-exceptions.html).

## What

This project includes two examples of using Problem Details.

1. Using the `spring.mvc.problemdetails.enabled` to atomically convert Spring Boot errors into Problem Details.
2. Manually creating a Problem Detail but using a Controller Advice exception handler.

The test suite demonstrates the above scenarios and are a good entry place into the codebase.

## Why

Problem Details provide a standardized way to represent errors in HTTP APIs, as proposed by
the [Internet Engineering Task Force (IETF)](https://www.ietf.org/about/introduction/). By structuring error responses
in a machine-readable format, they improve debugging, interoperability, and API usability.

[RFC 9457 for reference](https://datatracker.ietf.org/doc/html/rfc9457).

### Why Should You Care?

Traditional HTTP status codes, while useful, often lack the detail needed to diagnose issues efficiently. Without
structured error responses, developers must rely on inconsistent messages, making debugging harder and increasing
development time. Worse, missing critical context in error handling can lead to security risks, such as exposing
sensitive information or misinterpreting failures.

By adopting Problem Details, you gain:

- **Consistency**: A uniform format across APIs makes error handling predictable and easier for clients to integrate.
- **Extensibility**: You can include additional, application-specific fields to convey more relevant error details.
- **Better Documentation**: The `type` field can link to API documentation, helping developers quickly understand and
  resolve issues.

### Structure

* **type** *(string, optional)*: A URI reference identifying the problem type (defaults to `"about:blank"`).
* **title** *(string, optional)*: A short, human-readable summary of the problem.
* **status** *(integer, optional)*: The associated HTTP status code.
* **detail** *(string, optional)*: A human-readable explanation specific to this error occurrence.
* **instance** *(string, optional)*: A URI reference identifying this particular instance of the problem.
* **Additional members** *(optional)*: Custom fields can be added for more context.  

