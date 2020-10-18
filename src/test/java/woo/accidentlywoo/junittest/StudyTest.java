package woo.accidentlywoo.junittest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StudyTest {

    @Test
    @DisplayName("스터디 만들기 ╯°□°）╯")
    public void create_new_study() throws Exception{
        //given
        Study study = new Study(10);
        //when
        assertNotNull(study);
        //then
        System.out.println("create");
    }
}