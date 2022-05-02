package br.com.luizimcpi.aws_project02.config.local;

import com.amazon.sqs.javamessaging.ProviderConfiguration;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.CreateQueueResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.support.destination.DynamicDestinationResolver;

import javax.jms.Session;

@Configuration
@EnableJms
@Profile("local")
public class JmsConfigLocal {

    private static final Logger LOG = LoggerFactory.getLogger(JmsConfigLocal.class);

    public static final String HTTP_LOCALHOST_4566 = "http://localhost:4566";
    @Value("${aws.sqs.queue.product.events.name}")
    private String localQueueName;
    private SQSConnectionFactory sqsConnectionFactory;

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        createLocalQueue();

        sqsConnectionFactory = new SQSConnectionFactory(
                new ProviderConfiguration(),
                AmazonSQSClient.builder()
                        .withEndpointConfiguration(
                                new AwsClientBuilder.EndpointConfiguration(HTTP_LOCALHOST_4566,
                                        Regions.US_EAST_1.getName()))
                        .withCredentials(new DefaultAWSCredentialsProviderChain())
                        .build());

        DefaultJmsListenerContainerFactory factory =
                new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(sqsConnectionFactory);
        factory.setDestinationResolver(new DynamicDestinationResolver());
        factory.setConcurrency("2");
        factory.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);

        return factory;
    }

    private void createLocalQueue() {
        AmazonSQS sqsClient = AmazonSQSClient.builder()
                .withEndpointConfiguration(new AwsClientBuilder
                        .EndpointConfiguration(HTTP_LOCALHOST_4566, Regions.US_EAST_1.getName()))
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .build();

        sqsClient.createQueue(new CreateQueueRequest(localQueueName));

        LOG.info("Local queue has been created!");
    }
}