import { RESTDataSource } from '@apollo/datasource-rest';
import { GetJobsResponse, Job } from '../types/Jobs';
import { User } from '../types/User';

export class JobsAPI extends RESTDataSource {
  override baseURL = process.env.BACKEND_API_URL;

  async getJob(id: string, token = ''): Promise<Job> {
    const headers = token !== '' ? { authorization: token } : undefined;

    return this.get<Job>(`jobs/${id}`, { headers });
  }

  async getJobs(limit = '5', page = '1', token = ''): Promise<GetJobsResponse> {
    const headers = token !== '' ? { authorization: token } : undefined;

    return this.get('jobs', {
      params: {
        per_page: limit,
        page,
      },
      headers,
    });
  }

  async getActiveJobs(
    limit = '5',
    page = '1',
    positiontitle ='',
    appliedonly = false,
    token = '',
  ): Promise<GetJobsResponse> {
    const headers = token !== '' ? { authorization: token } : undefined;

    return this.get('jobs/published', {
      params: {
        per_page: limit,
        page,
        positiontitle,
        appliedonly
      },
      headers,
    });
  }

  async authuser(username: string, password: string): Promise<string> {
    return this.post('authuser', {
      body: {
        user: username,
        password,
      },
    });
  }

  async apply(jobId: string, token: string): Promise<boolean> {
    const headers = token !== '' ? { authorization: token } : undefined;

    if (!headers) return false;

    await this.put('application', {
      body: {
        jobId,
      },
      headers,
    });

    return true;
  }

    async updateuser(name: string, token: string): Promise<boolean> {
    const headers = token !== '' ? { authorization: token } : undefined;

    if (!headers) return false;

    await this.put('updateuser', {
      body: {
        name,
      },
      headers,
    });

    return true;
  }

  async userinfo(token: string): Promise<User> {
    const headers = token !== '' ? { authorization: token } : undefined;

    // if (!headers) return false;

    return this.get('userinfo', {
      headers,
    });

  }


}
