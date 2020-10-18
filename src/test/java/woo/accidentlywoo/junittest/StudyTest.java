package woo.accidentlywoo.junittest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest {

    @Test
//    @DisplayName("스터디 만들기 ╯°□°）╯")
    public void create_new_study() throws Exception{
        //given
        Study actual = new Study(10);
        //when
        //then
        assertThat(actual.getLimit()).isGreaterThan(0);
    }
}