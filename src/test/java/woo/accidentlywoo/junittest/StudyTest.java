package woo.accidentlywoo.junittest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.OS;

import static org.assertj.core.api.Assertions.assertThat;

class StudyTest {

    @Test
    @DisplayName("스터디 만들기 ╯°□°）╯")
    public void create_new_study() throws Exception{
        //given
        Study actual = new Study(10);
        //when
        //then
        assertThat(actual.getLimit()).isGreaterThan(0);
    }

    @Test
    @DisplayName("스터디 만들기\uD83D\uDE31")
    @EnabledIfEnvironmentVariable(named = "TES_ENV", matches = "keesun")
    @DisabledOnOs({OS.LINUX, OS.MAC})
    public void create_new_study_again(String message) throws Exception{
        //given
        //when
        //then
        System.out.println("create1");
    }
}