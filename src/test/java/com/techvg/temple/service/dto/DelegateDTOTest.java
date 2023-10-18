package com.techvg.temple.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.techvg.temple.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DelegateDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DelegateDTO.class);
        DelegateDTO delegateDTO1 = new DelegateDTO();
        delegateDTO1.setId(1L);
        DelegateDTO delegateDTO2 = new DelegateDTO();
        assertThat(delegateDTO1).isNotEqualTo(delegateDTO2);
        delegateDTO2.setId(delegateDTO1.getId());
        assertThat(delegateDTO1).isEqualTo(delegateDTO2);
        delegateDTO2.setId(2L);
        assertThat(delegateDTO1).isNotEqualTo(delegateDTO2);
        delegateDTO1.setId(null);
        assertThat(delegateDTO1).isNotEqualTo(delegateDTO2);
    }
}
