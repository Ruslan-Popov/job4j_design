package ru.job4j;

import org.junit.Test;
import org.junit.Assert;

public class TriggerTest {

    @Test
    public void test() {
        Assert.assertEquals(1, new Trigger().someLogic());
    }

}