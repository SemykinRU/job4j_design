package ru.job4j.io;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Petr Arsentev"));
        assertThat(config.value("surname"), is(Matchers.nullValue()));
    }

    @Test
    public void whenPairWithComment() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Petr Arsentev"));
        assertThat(config.value("surname"), is("#"));
        assertThat(config.value("host"), is("127.0.0.1"));
    }

    @Test
    public void whenThrowIllegalArgumentExceptionComment() throws IllegalArgumentException {
        String path = "./data/pair_with_IllegalArgumentException.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenIsNotTrueKeyThen() {
        Config config = new Config("path");
        config.isTrueKey("=Illegal");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenKeyStartWithDigitalThen() {
        Config config = new Config("path");
        config.isTrueKey("1name=argument");
    }

    @Test
    public void whenKeyNotStartWithDigitalButHaveDigitalThenTrue() {
        Config config = new Config("path");
        Assert.assertTrue(config.isTrueKey("one1two=1argument"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenKeyHaveSymbolThen() {
        Config config = new Config("path");
        config.isTrueKey("ewr.das=argument");
    }

    @Test
    public void whenIsTrueKeyThen() {
        Config config = new Config("path");
        Assert.assertTrue(config.isTrueKey("trueKey12=======>"));
    }
}