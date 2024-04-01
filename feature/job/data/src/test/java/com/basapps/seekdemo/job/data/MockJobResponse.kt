package com.basapps.seekdemo.job.data

val loginSuccess = "{\n" +
        "  \"data\": {\n" +
        "    \"job\": {\n" +
        "      \"_id\": \"job-5\",\n" +
        "      \"positionTitle\": \"Junior Mobile Developer\",\n" +
        "      \"description\": \"Job Description for a Junior Mobile Developer\",\n" +
        "      \"salaryRange\": {\n" +
        "        \"min\": 1234,\n" +
        "        \"max\": 2345\n" +
        "      },\n" +
        "      \"location\": 1,\n" +
        "      \"industry\": 0,\n" +
        "      \"haveIApplied\": false\n" +
        "    }\n" +
        "  }\n" +
        "}"

val loginFailure = "{\n" +
        "  \"data\": {\n" +
        "    \"authuser\": null\n" +
        "  },\n" +
        "  \"errors\": [\n" +
        "    {\n" +
        "      \"message\": \"401: Unauthorized\",\n" +
        "      \"locations\": [\n" +
        "        {\n" +
        "          \"line\": 2,\n" +
        "          \"column\": 3\n" +
        "        }\n" +
        "      ],\n" +
        "      \"path\": [\n" +
        "        \"authuser\"\n" +
        "      ],\n" +
        "      \"extensions\": {\n" +
        "        \"code\": \"UNAUTHENTICATED\",\n" +
        "        \"response\": {\n" +
        "          \"url\": \"http://api:3001/authuser\",\n" +
        "          \"status\": 401,\n" +
        "          \"statusText\": \"Unauthorized\",\n" +
        "          \"body\": \"Either username or password are wrong!2\"\n" +
        "        },\n" +
        "        \"stacktrace\": [\n" +
        "          \"AuthenticationError: 401: Unauthorized\",\n" +
        "          \"    at new AuthenticationError (/usr/src/app/node_modules/@apollo/datasource-rest/dist/RESTDataSource.js:182:9)\",\n" +
        "          \"    at JobsAPI.errorFromResponse (/usr/src/app/node_modules/@apollo/datasource-rest/dist/RESTDataSource.js:60:21)\",\n" +
        "          \"    at JobsAPI.didReceiveResponse (/usr/src/app/node_modules/@apollo/datasource-rest/dist/RESTDataSource.js:36:30)\",\n" +
        "          \"    at /usr/src/app/node_modules/@apollo/datasource-rest/dist/RESTDataSource.js:137:39\",\n" +
        "          \"    at process.processTicksAndRejections (node:internal/process/task_queues:95:5)\"\n" +
        "        ]\n" +
        "      }\n" +
        "    }\n" +
        "  ]\n" +
        "}"
