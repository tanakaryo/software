package com.example.etl.util;

import java.util.UUID;

import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryOptions;
import com.google.cloud.bigquery.Job;
import com.google.cloud.bigquery.JobId;
import com.google.cloud.bigquery.JobInfo;
import com.google.cloud.bigquery.QueryJobConfiguration;
import com.google.cloud.bigquery.TableResult;

public class BigQueryDelegate {

    private BigQuery bigQuery;

    public void getInstance(String projectId) {
        this.bigQuery = BigQueryOptions.newBuilder().setProjectId(projectId).build().getService();
    }

    public TableResult query(String query) throws Exception {
        QueryJobConfiguration config = QueryJobConfiguration.newBuilder(query).setUseLegacySql(false).build();
        JobId jobId = JobId.of(UUID.randomUUID().toString());
        Job job = this.bigQuery.create(JobInfo.newBuilder(config).setJobId(jobId).build());
        job = job.waitFor();
        return job.getQueryResults();
    }
 }
