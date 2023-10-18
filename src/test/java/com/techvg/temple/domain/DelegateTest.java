package com.techvg.temple.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.techvg.temple.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DelegateTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Delegate.class);
        Delegate delegate1 = new Delegate();
        delegate1.setId(1L);
        Delegate delegate2 = new Delegate();
        delegate2.setId(delegate1.getId());
        assertThat(delegate1).isEqualTo(delegate2);
        delegate2.setId(2L);
        assertThat(delegate1).isNotEqualTo(delegate2);
        delegate1.setId(null);
        assertThat(delegate1).isNotEqualTo(delegate2);
    }
}
