import { ApolloServer } from '@apollo/server';
import { startStandaloneServer } from '@apollo/server/standalone';
import { JobsAPI } from './API/JobsAPI';

// A schema is a collection of type definitions (hence "typeDefs")
// that together define the "shape" of queries that are executed against
// your data.
const typeDefs = `#graphql
  # Comments in GraphQL strings (such as this one) start with the hash (#) symbol.

  type SalaryRange {
    min: Int
    max: Int
  }
  
  # This "Job" type defines the queryable fields for every job in our data source.
  type Job {
    _id: String
    positionTitle: String
    description: String
    salaryRange: SalaryRange
    location: Int
    industry: Int
    haveIApplied: Boolean
  }

  type User {
  _id: String
  username: String
  displayname: String
  }

   type AuthUser {
  _id: String
  username: String
  displayname: String
  token: String
  }

  
  type GetJobsResponse {
    page: Int
    size: Int
    hasNext: Boolean
    total: Int
    jobs: [Job]
  }

  type Query {
    active(limit: Int, page: Int, positiontitle:String, appliedonly:Boolean): GetJobsResponse
    job(id: String!): Job
    jobs(limit: Int, page: Int): GetJobsResponse
    userinfo(userId:String): User
  }
  
  type Mutation {
    authuser(username: String!, password: String!): AuthUser
    apply(jobId: String!): Boolean
    updateuser(name: String!): Boolean
  }
`;

const resolvers = {
  Query: {
    // @ts-ignore
    active: async (
      _: unknown,
      { limit = '5', page = '1' ,positiontitle ='',appliedonly= false},
      { dataSources, authorization }: ContextValue,
    ) => dataSources.jobsAPI.getActiveJobs(limit, page,positiontitle,appliedonly ,authorization.token),
    // @ts-ignore
    job: async (_: unknown, { id }, { dataSources, authorization }) =>
      dataSources.jobsAPI.getJob(id, authorization.token),

    userinfo: async (_,{userId} ,{ dataSources, authorization }: ContextValue) =>
      dataSources.jobsAPI.userinfo(authorization.token),

    // @ts-ignore
    jobs: async (
      _: unknown,
      { limit = '5', page = '1'},
      { dataSources, authorization }: ContextValue,
    ) => dataSources.jobsAPI.getJobs(limit, page, authorization.token),
  },
  Mutation: {
    // @ts-ignore
    authuser: async (_, { username, password }, { dataSources }: ContextValue) =>
      dataSources.jobsAPI.authuser(username, password),
    // @ts-ignore
    apply: async (_, { jobId }, { dataSources, authorization }: ContextValue) =>
      dataSources.jobsAPI.apply(jobId, authorization.token),
    updateuser: async (_, { name }, { dataSources, authorization }: ContextValue) =>
      dataSources.jobsAPI.updateuser(name, authorization.token),
    
  },
};

interface ContextValue {
  dataSources: {
    jobsAPI: JobsAPI;
  };
  authorization: {
    token: string;
  };
}

const server = new ApolloServer({
  typeDefs,
  resolvers,
});

startStandaloneServer(server, {
  listen: {
    port: 3002,
  },
  context: async ({ req }) => {
    const { cache } = server;
    return {
      dataSources: {
        jobsAPI: new JobsAPI({ cache }),
      },
      authorization: {
        token: req.headers.authorization || '',
      },
    };
  },
}).then((res) => {
  const { url } = res;
  console.log(`🚀  Server ready at: ${url}`);
});
