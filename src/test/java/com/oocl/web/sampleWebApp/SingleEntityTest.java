package com.oocl.web.sampleWebApp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;


import static com.oocl.web.sampleWebApp.AssertHelper.assertThrows;
import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SingleEntityTest {

    @Autowired
    public SingleEntityRepository singleEntityRepository;

    @Test
    public void should_not_store_in_db(){
        final SingleEntity singleEntity = new SingleEntity();
        singleEntity.id = 2L;
        singleEntity.name = "12345678901";

        assertThrows(Exception.class,() -> {
            singleEntityRepository.save(singleEntity);
            singleEntityRepository.flush();
        });
    }

    @Test
    public void should_fetch_entity(){
        final SingleEntity singleEntity = new SingleEntity();
        singleEntity.id = 1L;
        singleEntity.name = "World";
        singleEntityRepository.save(singleEntity);

        final SingleEntity fetch_entity = singleEntityRepository.getOne(1L);

        assertEquals("World",fetch_entity.name);
    }

}
