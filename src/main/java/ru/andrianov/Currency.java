package ru.andrianov;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
@JsonPropertyOrder(value = {"nominal","data","curs","cdx"})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Currency implements Serializable {

    @JsonProperty("nominal")
    private  Integer nominal;

    @JsonProperty("date")
    @JsonDeserialize(using = LDDeserializer.class)
    private  LocalDate data;

    @JsonProperty("curs")
    private  BigDecimal curs;

    @JsonProperty("cdx")
    private  String cdx;

    @Override
    public String toString() {
        return   data + " - " + curs;
    }
}
