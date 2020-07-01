package ru.otus.myJson;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.gson.Gson;
import org.junit.jupiter.api.*;


import java.util.Collections;
import java.util.List;


class MyJsonTest {
    Gson gson = new Gson();
    MyJson serializer = new MyJson();

    @BeforeEach
    void SetUP() {
        Gson gson = new Gson();
        MyJson serializer = new MyJson();
    }

    @Test
    void bothObjectsMustReturnNull(){
        assertThat(gson.toJson(null)).isEqualTo(serializer.toJson(null));
    }

    @Test
    void shouldBuildJsonFromByteValue(){
        assertThat(gson.toJson((byte) 1)).isEqualTo(serializer.toJson((byte) 1));
    }
    @Test
    void shouldBuildJsonFromShortValue(){
        assertThat(gson.toJson((short) 1)).isEqualTo(serializer.toJson((short) 1));
    }
    @Test
    void shouldBuildJsonFromIntValue(){
        assertThat(gson.toJson(1)).isEqualTo(serializer.toJson(1));
    }
    @Test
    void shouldBuildJsonFromLongValue(){
        assertThat(gson.toJson(1L)).isEqualTo(serializer.toJson(1L));
    }
    @Test
    void shouldBuildJsonFromFloatValue(){
        assertThat(gson.toJson(1f)).isEqualTo(serializer.toJson(1f));
    }
    @Test
    void shouldBuildJsonFromDoubleValue(){
        assertThat(gson.toJson(1d)).isEqualTo(serializer.toJson(1d));
    }
    @Test
    void shouldBuildJsonFromCharValue(){
        assertThat(gson.toJson('a')).isEqualTo(serializer.toJson('a'));
    }

    @Test
    void shouldBuildJsonFromStringValue(){
        assertThat(gson.toJson("aaa")).isEqualTo(serializer.toJson("aaa"));
    }
    @Test
    void shouldBuildJsonFromArray(){
        assertThat(gson.toJson(new int[]{1, 2, 3})).isEqualTo(serializer.toJson(new int[]{1, 2, 3}));
    }
    @Test
    void shouldBuildJsonFromList(){
        assertThat(gson.toJson(List.of(1, 2, 3))).isEqualTo(serializer.toJson(List.of(1, 2, 3)));
    }
    @Test
    void shouldBuildJsonFromCollection(){
        assertThat(gson.toJson(Collections.singletonList(1))).isEqualTo(serializer.toJson(Collections.singletonList(1)));
    }



    @AfterEach
    void TearDown() {
        gson = null;
        serializer = null;
    }
}