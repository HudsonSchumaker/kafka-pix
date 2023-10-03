package com.schumaker.pix.stream;

import com.schumaker.pix.serdes.PixSerdes;
import com.schumaker.pix.view.PixDTO;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PixStream {

    @Autowired // on the method to run when the app starts
    public void buildPipeline(StreamsBuilder streamsBuilder) {

        KStream<String, PixDTO> messageStream = streamsBuilder
                .stream("pix-topic", Consumed.with(Serdes.String(), PixSerdes.serdes()))
                .peek((key, value) -> System.out.println("Pix received: " + value.getOriginKey()))
                .filter((key, value) -> value.getValue() > 10000)
                .peek((key, value) -> System.out.println("Pix: " + key + " will be verified, possible fraud"));

        messageStream.print(Printed.toSysOut());
//      messageStream.to("pix-fraud-verification", Produced.with(Serdes.String(), PixSerdes.serdes()));

//        KTable<String, Double> aggregateStream = streamsBuilder
//                .stream("pix-topic-2", Consumed.with(Serdes.String(), PixSerdes.serdes()))
//                .peek((key, value) -> System.out.println("Pix received: " + value.getOriginKey()))
//                .filter((key, value) -> value.getValue() != null)
//                .groupBy((key, value) -> value.getOriginKey())
//                .aggregate(
//                        () -> 0.0,
//                        (key, value, aggregate) -> (aggregate + value.getValue()),
//                        Materialized.with(Serdes.String(), Serdes.Double())
//                );
//
//        aggregateStream.toStream().print(Printed.toSysOut());
    }
}
