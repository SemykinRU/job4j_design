package ru.job4j.template;

import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class GeneratorTest {

    @Ignore
    @Test
    public void whenItsOKGenProduceThen() {
        String str = "I am a ${name}, Who are ${subject}?";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Roman");
        args.put("subject", "you");
        Generator generator = new UserGen();
        String result = generator.produce(str, args);
        String expected = "I am a Roman, Who are you?";
        assertThat(result, is(expected));
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenTemplateKeyMissingInMapThen() {
        String str = "I am a ${name}, Who are ${subject}?";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Roman");
        Generator generator = new UserGen();
        String result = generator.produce(str, args);
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenNoUsedAllKeyInMapThen() {
        String str = "I am a ${name}, Who are ${subject}?";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Roman");
        args.put("subject", "you");
        args.put("text", "Any text");
        Generator generator = new UserGen();
        String result = generator.produce(str, args);
    }
}