package com.basapps.seekdemo.job.data

val loginSuccess = "{\n" +
        "  \"data\": {\n" +
        "    \"authuser\": {\n" +
        "      \"_id\": \"user-1\",\n" +
        "      \"username\": \"user1\",\n" +
        "      \"displayname\": \"BassT\",\n" +
        "      \"token\": \"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiJ1c2VyLTEiLCJkaXNwbGF5IjoiQmFzc1QiLCJpYXQiOjE3MTE5ODkyMTEsImV4cCI6MTcxMjAxODAxMX0.Sg9JfV1hCw_8WK2Z5JszimQvv3cicnMpkgwVAw4FTlQ\"\n" +
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
