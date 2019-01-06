package eu.faredge.dda.processors.echo;

import eu.faredge.dda.processors.common.config.ProcessorConfig;
import eu.faredge.dda.processors.common.model.Observation;
import eu.faredge.dda.processors.common.serialization.ObservationDeserializer;
import eu.faredge.dda.processors.common.serialization.ObservationSerializer;
import eu.faredge.dda.processors.common.serialization.Serdes;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.streams.*;
import org.apache.kafka.streams.errors.LogAndContinueExceptionHandler;
import org.apache.kafka.streams.kstream.Produced;

import java.util.Date;
import java.util.Properties;
import java.util.UUID;

/**
 * This class represents echoers, i.e. streaming applications that echo data from the input topic to the output topic.
 */
public class Echoer {

    public static void main(String... args) {

        // Configure.
        final Properties configuration = new Properties();
        configuration.put(StreamsConfig.APPLICATION_ID_CONFIG, String.format("%s_%s", System.getProperty(ProcessorConfig.EDGE_GATEWAY_ID_CONFIG), System.getProperty(ProcessorConfig.PROCESSOR_ID_CONFIG)));
        configuration.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, String.format("%s:%s", System.getProperty("faredge.input.0.host", "localhost"), System.getProperty("faredge.input.0.port", "9092")));
        configuration.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, 10 * 1000);
        configuration.put(StreamsConfig.CACHE_MAX_BYTES_BUFFERING_CONFIG, 0);
        configuration.put(StreamsConfig.DEFAULT_DESERIALIZATION_EXCEPTION_HANDLER_CLASS_CONFIG, LogAndContinueExceptionHandler.class);
        configuration.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");

        // Set up serializers and deserializers.
        final Serde<Observation> serde = Serdes.serdeFrom(new ObservationSerializer(), new ObservationDeserializer());

        // Define the processing topology.
        final StreamsBuilder builder = new StreamsBuilder();
        final String sink = System.getProperty(ProcessorConfig.SINK_ID_CONFIG);
        builder.stream(System.getProperty("faredge.input.0.topic"), Consumed.with(Serdes.String(), serde)).map((key, value) -> {
            final Observation newValue = Observation.from(value);
            newValue.setId(UUID.randomUUID().toString());
            newValue.setEdgeGatewayReferenceID(System.getProperty(ProcessorConfig.EDGE_GATEWAY_ID_CONFIG));
            newValue.setDataSourceManifestReferenceID(sink);
            newValue.setCollectionTimestamp(new Date());
            newValue.setAcquisitionTimestamp(new Date());
            return KeyValue.pair(key, newValue);
        }).to(System.getProperty("faredge.output.topic"), Produced.with(Serdes.String(), serde));
        final KafkaStreams streams = new KafkaStreams(builder.build(), configuration);

        // Clean up the local state.
        streams.cleanUp();

        // Start the processing topology.
        streams.start();

        // On SIGTERM, shut everything down gracefully.
        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
    }
}
