package me.sup2is.room335.kafka;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class TestMessage {

    private String name;
    private Integer age;
    private LocalDateTime createAt;

    public static TestMessage createSampleMessage() {
        TestMessage testMessage = new TestMessage();
        testMessage.name = "test";
        testMessage.age = 20;
        testMessage.createAt = LocalDateTime.now();
        return testMessage;
    }

}
