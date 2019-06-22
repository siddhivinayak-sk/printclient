package com.dps.config;

import javax.persistence.EntityManagerFactory;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.dps.entities.PrintRequest;
import com.dps.repositories.PrintJobRepository;
import com.dps.repositories.PrintRequestRepository;
import com.dps.services.PrintJobService;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

	@Autowired
	private EntityManagerFactory emf;
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBulderFactory;
	
	@Autowired
	private PrintJobRepository printJobRepository;
	
	@Autowired
	private PrintRequestRepository printRequestRepository;
	
	@Autowired
	private PrintJobService printJobService;
	
	
	@Bean(name = "itemReader1")
	public JpaPagingItemReader<PrintRequest> itemReader1() throws Exception {
		JpaPagingItemReader<PrintRequest> r = new JpaPagingItemReader<PrintRequest>();
		r.setPageSize(5);
		r.setQueryString("from PrintRequest pr where pr.status = 120");
		r.setEntityManagerFactory(emf);
		r.afterPropertiesSet();
		return r;
	}
	
	@Bean(name = "itemWriter1")
	public JpaItemWriter<PrintRequest> itemWriter1() throws Exception {
		JpaItemWriter<PrintRequest> w = new JpaItemWriter<>();
		w.setEntityManagerFactory(emf);
		w.afterPropertiesSet();
		return w;
	}
	
	@Bean(name = "itemProcessor1")
	public ItemProcessor<PrintRequest, PrintRequest> itemProcessor1() {
		return (item) -> {
			printJobService.process120(item);
			return item;
		};
	}
	
	@Bean(name = "step1")
	public Step step1() throws Exception {
		return stepBulderFactory.get("step1")
				.<PrintRequest, PrintRequest>chunk(10)
				.reader(itemReader1())
				.processor(itemProcessor1())
				.writer(itemWriter1())
				.build();
	}
	
	@Bean(name = "job1")
	public Job importJob1() throws Exception {
		return jobBuilderFactory.get("job1")
				.incrementer(new RunIdIncrementer())
				.listener(listener1())
				.flow(step1())
				.end()
				.build();
	}
	
	
	
	
	
	
	
	@Bean
	public JobCompletionNofifictionListener listener1() {
		return new JobCompletionNofifictionListener(1);
	}

	@Bean
	public JobCompletionNofifictionListener listener2() {
		return new JobCompletionNofifictionListener(2);
	}
	

	static class JobCompletionNofifictionListener extends JobExecutionListenerSupport {
		
		@Autowired
		private JdbcTemplate jdbcTemplate;
		
		private int flag;

		public JobCompletionNofifictionListener(int flg) {
			flag = flg;
		}
		
		public void afterJob(JobExecution jobExecution) {
			if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
				if(flag == 1) {
					
				}
			}
		}
	}
	
}
