package com.techvg.temple.service.mapper;

import org.junit.jupiter.api.BeforeEach;

class DelegateMapperTest {

    private DelegateMapper delegateMapper;

    @BeforeEach
    public void setUp() {
        delegateMapper = new DelegateMapperImpl();
    }
}
